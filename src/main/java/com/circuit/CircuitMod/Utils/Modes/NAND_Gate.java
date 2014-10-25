package com.circuit.CircuitMod.Utils.Modes;

import com.circuit.CircuitMod.TileEntity.TileEntityCircuitBox;
import com.circuit.CircuitMod.Utils.EventPacket;

public class NAND_Gate extends CircuitBoxMode {
    @Override
    public String ModeName() {
        return "NAND";
    }

    @Override
    public String GetID() {
        return "NAND";
    }

    @Override
    public int MinInputs() {
        return 0;
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
        return tile.GetActiveInputs() < 2;
    }
}
