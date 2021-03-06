package com.circuit.CircuitMod.Blocks;

import com.circuit.CircuitMod.Blocks.Utils.ModBlockTwoSidedEventChecker;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntitySignalGate;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockSignalGate extends ModBlockTwoSidedEventChecker {



    public ModBlockSignalGate() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySignalGate();
    }


    @Override
    public String FrontIcon() {
        return "PacketGateFront";
    }

    @Override
    public String BackIcon() {
        return "PacketGateBlank";
    }

    @Override
    public String SideAIcon() {
        return "PacketGateGreen";
    }

    @Override
    public String SideBIcon() {
        return "PacketGateRed";
    }
}
