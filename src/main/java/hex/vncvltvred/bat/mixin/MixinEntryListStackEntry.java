package hex.vncvltvred.bat.mixin;

import hex.vncvltvred.bat.mixin.invoker.EntryListStackEntryInvoker;
import me.shedaniel.rei.impl.client.gui.widget.entrylist.EntryListStackEntry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(EntryListStackEntry.class)
public abstract class MixinEntryListStackEntry
{
    @Redirect(method = "getCurrentTooltip", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/MathHelper;clamp(III)I"))
    private int bat$adjustClamp(int value, int min, int max)
    {
        List<TagKey<?>> tags = ((EntryListStackEntryInvoker)this).invokeGetCurrentEntry().getTagsFor().toList();

        if(tags.isEmpty()) return MathHelper.clamp(value, min, max);

        MinecraftClient mc = MinecraftClient.getInstance();

        if(!InputUtil.isKeyPressed(mc.getWindow().getHandle(), InputUtil.GLFW_KEY_LEFT_CONTROL)) return MathHelper.clamp(value, min, max);

        value -= (tags.size() + 1);
        max -= (tags.size() + 1);

        return MathHelper.clamp(value, min, max);
    }
}
