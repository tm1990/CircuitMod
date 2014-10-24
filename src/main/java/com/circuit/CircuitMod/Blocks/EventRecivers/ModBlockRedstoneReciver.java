package com.circuit.CircuitMod.Blocks.EventRecivers;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.TileEntityRedstoneReciver;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockRedstoneReciver extends ModBlockContainer {
    public ModBlockRedstoneReciver() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityRedstoneReciver();
    }
}