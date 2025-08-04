// src/main/java/com/yello/parkourmod/RubberBlock.java
package com.yello.parkourmod;

import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;

public class RubberBlock extends Block {
    public RubberBlock(Properties properties){
        super(properties.strength(0.5f).sound(SoundType.HONEY_BLOCK).noOcclusion());
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);
        entity.setDeltaMovement(0, 3, 0);
    }
    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
        // Do nothing: cancels fall damage
    }
    public boolean isToolRequired(BlockState state) {
        return false; // No tool required, can break by hand
    }
}