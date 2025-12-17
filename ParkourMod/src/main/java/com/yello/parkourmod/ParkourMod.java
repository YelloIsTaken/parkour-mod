package com.yello.parkourmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(ParkourMod.MODID)
public class ParkourMod
{
    public static final String MODID = "parkourmod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final RegistryObject<Item> RUBBER = ITEMS.register("rubber", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Block> RUBBER_BLOCK = BLOCKS.register("rubber_block", () -> new RubberBlock(Block.Properties.of()));
    public static final RegistryObject<Item> RUBBER_BLOCK_ITEM = ITEMS.register("rubber_block", () -> new BlockItem(RUBBER_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Block> GEL_BLOCK = BLOCKS.register("gel_block", () -> new GelBlock(Block.Properties.of()));
    public static final RegistryObject<Item> GEL_BLOCK_ITEM = ITEMS.register("gel_block", () -> new BlockItem(GEL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Block> KILL = BLOCKS.register("kill_block", () -> new KillBlock(Block.Properties.of()));
    public static final RegistryObject<Item> KILL_ITEM = ITEMS.register("kill_block", () -> new BlockItem(KILL.get(), new Item.Properties()));
    public static final RegistryObject<Block> HEAL_BLOCK = BLOCKS.register("heal_block", () -> new HealBlock(Block.Properties.of()));
    public static final RegistryObject<Item> HEAL_BLOCK_ITEM = ITEMS.register("heal_block", () -> new BlockItem(HEAL_BLOCK.get(), new Item.Properties()));
    public static final RegistryObject<Item> PARKOUR_FOOD = ITEMS.register("up_food", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 6000, 1, false, false), 1.0f)
            .nutrition(2)
            .saturationMod(1f)
            .build())));

    public static final RegistryObject<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("parkour_mod_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("parkour_mod_tab"))
            .icon(() -> PARKOUR_FOOD.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(KILL_ITEM.get().getDefaultInstance());
                output.accept(PARKOUR_FOOD.get().getDefaultInstance());
                output.accept(RUBBER_BLOCK_ITEM.get().getDefaultInstance());
                output.accept(GEL_BLOCK_ITEM.get().getDefaultInstance());
                output.accept(RUBBER.get().getDefaultInstance());
            }).build());

    public ParkourMod(FMLJavaModLoadingContext context)
    {
        IEventBus modEventBus = context.getModEventBus();
        modEventBus.addListener(this::commonSetup);
        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
        context.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HELLO FROM COMMON SETUP");
        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));
        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);
        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("HELLO from server starting");
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}