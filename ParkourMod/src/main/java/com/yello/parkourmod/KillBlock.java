package com.yello.parkourmod;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class KillBlock extends Block {
    public KillBlock(Properties properties) {
        super(properties.strength(1.0f).sound(SoundType.HONEY_BLOCK).noOcclusion());
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        super.stepOn(level, pos, state, entity);
        if (!level.isClientSide) {
            if (entity instanceof LivingEntity living) {
                living.kill();
            } else {
                entity.remove(RemovalReason.KILLED);
            }
        }
    }
    @SuppressWarnings("deprecation")
    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);
        if (!level.isClientSide) {
            if (entity instanceof LivingEntity living) {
                living.kill();
            } else {
                entity.remove(RemovalReason.KILLED);
            }
        }
    }
    public boolean isToolRequired(BlockState state) {
        return false; // No tool required, can break by hand
    }

}
