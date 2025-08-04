package com.yello.parkourmod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class GelBlock extends Block {
    public GelBlock(Properties properties){
        super(properties.strength(0.1f).sound(SoundType.HONEY_BLOCK).noOcclusion());
    }
    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        // Do nothing: cancels fall damage
    }
    public boolean isToolRequired(BlockState state) {
        return false; // No tool required, can break by hand
    }
}
