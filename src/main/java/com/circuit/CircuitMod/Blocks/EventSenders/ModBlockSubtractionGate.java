package com.circuit.CircuitMod.Blocks.EventSenders;

import com.circuit.CircuitMod.Blocks.Utils.ModBlockTwoSidedEventChecker;
import com.circuit.CircuitMod.TileEntity.EventSenders.TileEntitySubtractionGate;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockSubtractionGate  extends ModBlockTwoSidedEventChecker {
    public ModBlockSubtractionGate() {
        super(Material.iron);
    }

    @Override
    public String FrontIcon() {
        return "SubtractionFront";
    }

    @Override
    public String BackIcon() {
        return "DigitEqualsBack";
    }

    @Override
    public String SideAIcon() {
        return "MathGateA";
    }

    @Override
    public String SideBIcon() {
        return "MathGateB";
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntitySubtractionGate();
    }
}