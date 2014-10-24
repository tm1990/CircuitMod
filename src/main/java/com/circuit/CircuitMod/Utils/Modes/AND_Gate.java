package com.circuit.CircuitMod.Utils.Modes;

import com.circuit.CircuitMod.TileEntity.CircuitUtils.ByteValues;
import com.circuit.CircuitMod.Utils.EventPacket;
import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;

public class AND_Gate extends CircuitBoxMode {
    @Override
    public String ModeName() {
        return "AND";
    }

    @Override
    public String GetID() {
        return "AND_1";
    }

    @Override
    public int MinInputs() {
        return 2;
    }

    @Override
    public int MaxInputs() {
        return 2;
    }



    @Override
    public void OnUpdate(TileEntityCircuitBox tile, EventPacket packet) {
            tile.SendPacketTo(packet, tile.GetOutputSide());
    }

    @Override
    public boolean OutputtingSignal(TileEntityCircuitBox tile) {
        return tile.GetActiveInputs() == 2;
    }

    @Override
    public byte OutputByte(TileEntityCircuitBox tile) {
        return ByteValues.OnSignal.Value();
    }

    @Override
    public byte RequiredByteInput() {
        return ByteValues.OnSignal.Value();
    }
}
