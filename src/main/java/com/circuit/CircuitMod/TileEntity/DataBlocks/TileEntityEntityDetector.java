package com.circuit.CircuitMod.TileEntity.DataBlocks;

import com.circuit.CircuitMod.TileEntity.TileEntityEventSender;
import com.circuit.CircuitMod.Utils.DataPacket;
import com.circuit.CircuitMod.Utils.DataStorage.DataDoubleValue;
import com.circuit.CircuitMod.Utils.DataStorage.DataIntegerValue;
import com.circuit.CircuitMod.Utils.DataStorage.DataStringValue;
import com.circuit.CircuitMod.Utils.EventPacket;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;

import java.util.ArrayList;
import java.util.List;

public class TileEntityEntityDetector extends TileEntityEventSender {

    public static int MaxRange = 10;

    //Send When Difference is found
    List<EntityLivingBase> CurrentList = new ArrayList<EntityLivingBase>();
    List<EntityLivingBase> LastCheckedList = new ArrayList<EntityLivingBase>();

    int UpdateTick = 0;
    static int TimeToUpdate = 20;


    public void updateEntity(){

        if(UpdateTick >= TimeToUpdate) {
            UpdateTick = 0;
            LastCheckedList = CurrentList;

            CurrentList = worldObj.getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(xCoord - MaxRange, yCoord - MaxRange, zCoord - MaxRange, xCoord + MaxRange, yCoord + MaxRange, zCoord + MaxRange));

            for (Entity e : CurrentList) {
                boolean Found = false;

                for (Entity ee : LastCheckedList) {
                    if (e.isEntityEqual(ee)) {
                        Found = true;
                        break;
                    }
                }

                if (!Found) {
                    DataPacket packet = new DataPacket(-1);

                    String name = null;

                    String d = (e.getDistance(xCoord, yCoord, zCoord) + "");
                    if(d.length() >= 4)
                        d = d.substring(0, 4);

                    //Find another way around this?
                    int i = 0, max = 10; // Max 10?
                    while(name == null && i < max || name.equalsIgnoreCase("unkown") && i < max){
                        i += 1;
                        name =  e.getCommandSenderName();
                    }

                    packet.SaveData("EntityName", new DataStringValue(name));
                    packet.SaveData("EntityId", new DataIntegerValue(e.getEntityId()));
                    packet.SaveData("EntityDistance", new DataDoubleValue(Double.parseDouble(d)));

                    SendPacketToAround(packet);

                }

            }

        }else{
            UpdateTick += 1;

        }

    }


    @Override
    public void OnRecived(EventPacket packet) {}

    @Override
    public boolean CanConnectToTile(TileEntity tile, ForgeDirection dir) {
        return true;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return false;
    }
}
