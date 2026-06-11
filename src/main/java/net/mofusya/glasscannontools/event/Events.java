package net.mofusya.glasscannontools.event;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mofusya.glasscannontools.config.ServerConfig;
import org.joml.Vector3f;

import java.util.List;

@Mod.EventBusSubscriber
public class Events {

    @SubscribeEvent
    public static void onBreakEvent(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = player.getMainHandItem();
        Level level = player.level();
        BlockState state = event.getState();
        BlockPos pos = event.getPos();

        if (itemStack.getItem() instanceof DiggerItem item && item.isCorrectToolForDrops(itemStack, state)) {
            if (level instanceof ServerLevel server) {
                for (int i = 0; i < ServerConfig.DROP_MULTIPLIER.get() - 1; i++) {
                    List<ItemStack> drops = state.getDrops(new LootParams.Builder(server)
                            .withParameter(LootContextParams.BLOCK_STATE, state)
                            .withParameter(LootContextParams.TOOL, itemStack)
                            .withParameter(LootContextParams.ORIGIN, new Vec3(pos.getX(), pos.getY(), pos.getZ())));

                    for (ItemStack drop : drops) {
                        level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(), drop.copy()));
                    }
                }
            }

            itemStack.hurtAndBreak(item.getMaxDamage(itemStack) + 1, player, entity -> {
                entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }
    }
}