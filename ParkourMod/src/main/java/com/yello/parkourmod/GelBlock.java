package com.yello.parkourmod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class GelBlock extends Block {
    public GelBlock(Properties properties){
        super(properties);
    }
    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        // Do nothing: cancels fall damage
    }
}
