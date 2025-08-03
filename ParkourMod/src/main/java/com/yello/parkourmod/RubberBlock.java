// src/main/java/com/yello/parkourmod/RubberBlock.java
package com.yello.parkourmod;

import net.minecraft.world.level.block.Block;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class RubberBlock extends Block {
    public RubberBlock(Properties properties){
        super(properties);
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
}