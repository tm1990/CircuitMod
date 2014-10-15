package com.circuit.CircuitMod.TileEntity;

import MiscUtils.TileEntity.ModTileEntity;
import com.circuit.CircuitMod.CircuitEvents.EventPacket;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.ICircuitConnector;
import com.circuit.CircuitMod.TileEntity.CircuitUtils.IEventRec;
import net.minecraft.tileentity.TileEntity;

public class TileEntityRedstoneEmitter extends ModTileEntity implements IEventRec, ICircuitConnector {

    public static int Finish = 5;
    public int Do = Finish;

    public void updateEntity(){

        if(Do < Finish){
            Do += 1;
        }else{
            if(worldObj.getBlockMetadata(xCoord, yCoord, zCoord) == 1) {
                worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 0, 3);
                worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
            }
        }
    }

    @Override
    public boolean CanConnectToTile(TileEntity tile) {
        return true;
    }

    @Override
    public void OnRecived(EventPacket packet) {
        Do = 0;
        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, 1, 3);
        worldObj.notifyBlocksOfNeighborChange(xCoord, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return true;
    }
}
