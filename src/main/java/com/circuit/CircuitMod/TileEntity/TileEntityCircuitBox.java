package com.circuit.CircuitMod.TileEntity;

import MiscUtils.TileEntity.IBlockInfo;
import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.Utils.CircuitBoxModeUtils;
import com.circuit.CircuitMod.Utils.EventPacket;
import com.circuit.CircuitMod.Utils.Modes.CircuitBoxMode;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;

import javax.vecmath.Vector3d;
import java.util.ArrayList;

public class TileEntityCircuitBox extends TileEntityEventSender implements IBlockInfo{

    public String Mode = "EMPTY";
    public int ModeNum = 0;
    public boolean[] Sides;
    public EnumFacing[] SidesDir = new EnumFacing[]{EnumFacing.NORTH, EnumFacing.SOUTH, EnumFacing.EAST, EnumFacing.WEST};
    public int[] ResetCount = new int[4];
    public CircuitBoxMode CurrentMode;
    public EnumFacing[] Inputs = new EnumFacing[3];
    public int Color = 0;



    public int Rotation = 0;
    int ResetAt = 1;

    public void SetMode(CircuitBoxMode mode){
        this.Mode = mode.GetID();
        CurrentMode = mode;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        Color = nbtTagCompound.getInteger("Color");

        Rotation = nbtTagCompound.getInteger("ROT");
        ModeNum = nbtTagCompound.getInteger("ModeNum");

        Mode = nbtTagCompound.getString("ModeID");


        if(Mode != "EMPTY") {
            for (int i = 0; i < 4; i++)
                Sides[i] = nbtTagCompound.getBoolean("Sides_" + i);

            for (int i = 0; i < 4; i++)
                ResetCount[i] = nbtTagCompound.getInteger("ResetCountSide_" + i);


            CurrentMode = CircuitBoxModeUtils.GetMode(Mode);
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Color", Color);

        nbtTagCompound.setInteger("ROT", Rotation);

        nbtTagCompound.setInteger("ModeNum", ModeNum);

        if(Mode != "EMPTY") {
            nbtTagCompound.setString("ModeID", Mode);

            for (int i = 0; i < 4; i++)
                nbtTagCompound.setBoolean("Sides_" + i, Sides[i]);

            for (int i = 0; i < 4; i++)
                nbtTagCompound.setInteger("ResetCountSide_" + i, ResetCount[i]);



        }

    }

    public TileEntityCircuitBox(){
        Sides = new boolean[4];
    }

    public void SetSideState(EnumFacing dir, boolean t){

            if(dir == EnumFacing.NORTH) {
                Sides[0] = t;

                if(ResetCount != null && ResetCount[0] > 0)
                ResetCount[0] = 0;
            }else if(dir == EnumFacing.SOUTH) {
                Sides[1] = t;

                if(ResetCount != null && ResetCount[1] > 0)
                ResetCount[1] = 0;
            }else if(dir == EnumFacing.EAST) {
                Sides[2] = t;

                if(ResetCount != null && ResetCount[2] > 0)
                ResetCount[2] = 0;
            }else  if(dir == EnumFacing.WEST) {
                Sides[3] = t;

                if(ResetCount != null && ResetCount[3] > 0)
                ResetCount[3] = 0;
            }


    }

    public int GetActiveInputs(){
        int g = 0;

        for(int i = 0; i < Inputs.length; i++){
            if(GetInputState(i))
                g += 1;
        }

        return g;
    }

    public boolean GetInputState(int input){
        if(Inputs.length >= input && Inputs[input] != null)
        return GetSideState(Inputs[input]);

        return false;
    }

    public boolean GetSideState(EnumFacing dir){

        if(dir == EnumFacing.NORTH) {
            return Sides[0];
        }else if(dir == EnumFacing.SOUTH) {
            return Sides[1];
        }else if(dir == EnumFacing.EAST) {
            return Sides[2];
        }else  if(dir == EnumFacing.WEST) {
            return Sides[3];
        }

        return false;

    }

    public EnumFacing GetOutputSide(){
        if(Rotation == 0)
            return null;

        return EnumFacing.getFront(Rotation);
    }

    public void updateEntity(){

        if (CurrentMode != null) {
            if(GetActiveInputs() >= CurrentMode.MinInputs() && GetActiveInputs() <= CurrentMode.MaxInputs() && CurrentMode.OutputtingSignal(this)) {

                EventPacket packet = new EventPacket(CurrentMode.SignalTimeout(), ByteValues.OnSignal.Value());
                packet.LastSentFrom = GetOutputSide().getOpposite();
                packet.Postitions.add(new Vector3d(getPos().getX(), getPos().getY(), getPos().getZ()));

                CurrentMode.OnUpdate(this, packet);
            }
        }

        if(CurrentMode == null){
            ModeNum = 0;
            SetMode(CircuitBoxModeUtils.Modes.get(ModeNum));

        }

        ArrayList<EnumFacing> dd = new ArrayList<EnumFacing>();

        for(int i = 0; i < SidesDir.length; i++){
            EnumFacing side = SidesDir[i];

            if(side != GetOutputSide()){
                dd.add(side);
            }
        }

        for(int i = 0; i < dd.size(); i++){
            if(i < Inputs.length)
            Inputs[i] = dd.get(i);
        }

        for(int i = 0; i < ResetCount.length; i++){
            if(ResetCount[i] >= ResetAt && Sides[i]){
                Sides[i] = false;
            }else{

                if(Sides[i])
                ResetCount[i] += 1;
            }
        }



    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        int metaZ = this.Color;
        int metaX = 0;

        if(tile instanceof TileEntityCableConnectionPoint)
            metaX = ((TileEntityCableConnectionPoint) tile).Color;

        else if(tile instanceof TileEntityCircuitCable)
            metaX = ((TileEntityCircuitCable) tile).Color;

        else if(tile instanceof TileEntityCircuitBox)
            metaX = ((TileEntityCircuitBox) tile).Color;

        boolean t = tile instanceof TileEntityCircuitCable ? ((TileEntityCircuitCable)tile).Direction == EnumFacing.UP : true;
        boolean g = metaZ == 0 && metaX == 0 || metaZ == metaX;
        boolean j = tile instanceof TileEntityCircuitCable || tile instanceof TileEntityCircuitBox;

        if(j){
            return t && g;
        }

        return j && g && t || !j;
    }


    @Override
    public void OnRecived(EventPacket packet) {
        if(GetOutputSide() != null && GetOutputSide() != null
                && packet.LastSentFrom != GetOutputSide()) {
            SetSideState(packet.LastSentFrom, true);
        }
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OnSignal.Value();
    }

    @Override
    public void Info(ArrayList<String> Strings) {
        Strings.add(EnumChatFormatting.WHITE + worldObj.getBlockState(getPos()).getBlock().getLocalizedName() + EnumChatFormatting.RESET);

        if (CurrentMode != null) {
            Strings.add(StatCollector.translateToLocal("blockinfo.circuitbox.mode").replace("$Mode", (EnumChatFormatting.GRAY + CurrentMode.ModeName() + EnumChatFormatting.RESET)));
            Strings.add(StatCollector.translateToLocal("blockinfo.all.shiftchange"));
            Strings.add(StatCollector.translateToLocal("blockinfo.circuitbox.mininputs").replace("$Number", (EnumChatFormatting.GRAY + "" + CurrentMode.MinInputs() + EnumChatFormatting.RESET)));
            Strings.add(StatCollector.translateToLocal("blockinfo.circuitbox.maxinputs").replace("$Number", (EnumChatFormatting.GRAY + "" + CurrentMode.MaxInputs() + EnumChatFormatting.RESET)));
            Strings.add(StatCollector.translateToLocal("blockinfo.circuitbox.activeinputs").replace("$Number", (EnumChatFormatting.GRAY + "" + GetActiveInputs() + EnumChatFormatting.RESET)));
            Strings.add(StatCollector.translateToLocal("blockinfo.circuitbox.outputting").replace("$Value", (CurrentMode.OutputtingSignal(this) ? EnumChatFormatting.GREEN : EnumChatFormatting.RED) + "" + CurrentMode.OutputtingSignal(this) + EnumChatFormatting.RESET));
        }
    }
}
