package net.mofusya.glasscannontools.mixin;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Item.class})
public class ItemMixin {

    @Inject(method = "getBarColor", at = @At("HEAD"), cancellable = true)
    public void getBarColor(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        if (((Item) (Object) this) instanceof DiggerItem) cir.setReturnValue(0xFF0000);
    }

    @Inject(method = "getBarWidth", at = @At("HEAD"), cancellable = true)
    public void getBarWidth(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        if (((Item) (Object) this) instanceof DiggerItem) cir.setReturnValue(1);
    }

    @Inject(method = "isBarVisible", at = @At("HEAD"), cancellable = true)
    public void isBarVisible(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        if (((Item) (Object) this) instanceof DiggerItem) cir.setReturnValue(true);
    }
}
