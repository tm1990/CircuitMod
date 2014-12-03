package com.circuit.CircuitMod.TileEntity.EventReceivers;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;


public class TileEntitySignalShortender extends TileEntityEventSender{

    public EnumFacing dir = EnumFacing.UP;
    int Reset = 0;
    static int ResetAt = 1;
    boolean Resetting;

    public void updateEntity(){
        if(Resetting){
            if(Reset < ResetAt){
                Reset += 1;
            }else{
                Reset = 0;
                Resetting = false;
            }
        }


    }

    @Override
    public void OnRecived(EventPacket packet) {
        if(!Resetting){
            SendPacketTo(packet, dir);
            Resetting = true;
        }else{
            Reset = 0;
        }



    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.LastSentFrom != dir;
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, EnumFacing dir) {
        return true;
    }


    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {

        super.readFromNBT(nbtTagCompound);

        dir = EnumFacing.getFront(nbtTagCompound.getInteger("Dir"));
        Resetting = nbtTagCompound.getBoolean("Resetting");
        Reset = nbtTagCompound.getInteger("Reset");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Dir", dir.getIndex());
        nbtTagCompound.setInteger("Reset", Reset);
        nbtTagCompound.setBoolean("Resetting", Resetting);

    }
}
