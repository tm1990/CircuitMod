package com.circuit.CircuitMod.TileEntity.EventSenders;

import com.circuit.CircuitMod.Main.CircuitMod;
import com.circuit.CircuitMod.Utils.ByteValues;
import MiscUtils.TileEntity.IBlockInfo;
import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.DataStorage.DataIntegerValue;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;

public class TileEntityOneDigitConstant extends TileEntityEventSender implements IBlockInfo {



    public int Constant = 0;
    public void updateEntity(){

        EventPacket packet = new EventPacket(-1, ByteValues.OneDigitNumber.Value());
        packet.Data = new DataIntegerValue(Constant);

        SendPacketToAround(packet);
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public void OnRecived(EventPacket packet) {

    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return false;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);

        Constant = nbtTagCompound.getInteger("Const");


    }

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);

        nbtTagCompound.setInteger("Const", Constant);

    }


    @Override
    public void Info(ArrayList<String> Strings) {

        if(!CircuitMod.ShowHoverText)
            return;

        Strings.add(EnumChatFormatting.WHITE + worldObj.getBlock(xCoord, yCoord, zCoord).getLocalizedName() + EnumChatFormatting.RESET);
            Strings.add(StatCollector.translateToLocal("blockinfo.constant.constant").replace("$Number", (EnumChatFormatting.GRAY + "" + Constant + EnumChatFormatting.RESET)));

    }
}
