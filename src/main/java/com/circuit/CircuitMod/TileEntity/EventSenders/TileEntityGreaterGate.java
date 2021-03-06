package com.circuit.CircuitMod.TileEntity.EventSenders;

import com.circuit.CircuitMod.Utils.ByteValues;
import com.circuit.CircuitMod.TileEntity.Utils.TileEntityTwoSidedEventChecker;
import com.circuit.CircuitMod.Utils.DataStorage.DataIntegerValue;
import com.circuit.CircuitMod.Utils.EventPacket;

public class TileEntityGreaterGate  extends TileEntityTwoSidedEventChecker {
    @Override
    public byte InputPacketA() {
        return -1;
    }

    @Override
    public byte InputPacketB() {
        return -1;
    }

    @Override
    public EventPacket OutPutPacket() {

        if(packetA != null && packetB != null){
            if(packetA.ByteValue == ByteValues.OneDigitNumber.Value() || packetA.ByteValue == ByteValues.MultiDigitNumber.Value()){
                if(packetB.ByteValue == ByteValues.OneDigitNumber.Value() || packetB.ByteValue == ByteValues.MultiDigitNumber.Value()){

                    int t1 = packetA.Data instanceof DataIntegerValue ? ((DataIntegerValue)packetA.Data).GetStoredObject() : 0;
                    int t2 = packetB.Data instanceof DataIntegerValue ? ((DataIntegerValue)packetB.Data).GetStoredObject() : 0;

                    if(t1 > t2){
                        return new EventPacket(-1, ByteValues.OnSignal.Value());
                    }

                }
            }
        }


        return null;
    }

    @Override
    public boolean CanRecive(EventPacket packet) {
        return packet.ByteValue == ByteValues.OneDigitNumber.Value() || packet.ByteValue == ByteValues.MultiDigitNumber.Value();
    }
}
