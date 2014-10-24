package com.circuit.CircuitMod.TileEntity;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.Utils.EventPacket;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.IEventRec;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import javax.vecmath.Vector3d;

public abstract class TileEntityEventSender extends ModTileEntity implements IEventRec, ICircuitConnector{



    public void SendPacketTo(EventPacket packet,ForgeDirection dir){

            if(dir != packet.LastSentFrom && dir != ForgeDirection.UNKNOWN && packet != null && !packet.TimedOut) {
                if (worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof IEventRec) {
                    TileEntity tile = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);

                    if (tile instanceof ICircuitConnector) {


                        if (((ICircuitConnector) tile).CanConnectToTile(this, dir.getOpposite()) && CanConnectToTile(tile, dir)) {


                            if (tile instanceof IEventRec) {
                                IEventRec tileE = (IEventRec) tile;
                                if (tileE.CanRecive(packet) && tileE != null) {
                                    Vector3d vec = new Vector3d(tile.xCoord, tile.yCoord, tile.zCoord);

                                    if(!EventPacket.ContainesVactor(packet, vec)) {

                                        EventPacket sendPacket = new EventPacket(packet.TimeOut, packet.ByteValue);

                                        NBTTagCompound nbt = new NBTTagCompound();
                                        packet.SaveToNBT(nbt);

                                        sendPacket.LoadFromNBT(nbt);
                                        sendPacket.LastSentFrom = dir.getOpposite();

                                        sendPacket.Resend();

                                        sendPacket.Postitions = packet.Postitions;
                                        sendPacket.Postitions.add(vec);


                                        tileE.OnRecived(sendPacket);

                                    }


                                }

                            }


                        }

                    }
                }

            }else{
                return;
            }

    }

    public void SendPacketToAround(EventPacket packet){
        for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS){
            if(dir != packet.LastSentFrom && packet != null)
           SendPacketTo(packet, dir);
        }
    }





    @Override
    public abstract void OnRecived(EventPacket packet);

    @Override
    public boolean CanRecive(EventPacket packet) {
        return true;
    }

    @Override
    public abstract boolean CanConnectToTile(TileEntity tile, ForgeDirection dir);
}
