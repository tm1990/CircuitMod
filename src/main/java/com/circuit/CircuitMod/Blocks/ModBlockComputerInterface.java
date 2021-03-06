package com.circuit.CircuitMod.Blocks;

import MiscUtils.Block.ModBlockContainer;
import com.circuit.CircuitMod.TileEntity.TileEntityComputerInterface;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ModBlockComputerInterface extends ModBlockContainer {

    public ModBlockComputerInterface() {
        super(Material.iron);
    }

    @Override
    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileEntityComputerInterface();
    }
}
