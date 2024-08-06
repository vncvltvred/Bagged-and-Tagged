package hex.vncvltvred.bat.mixin;

import hex.vncvltvred.bat.mixin.invoker.QueuedTooltipInvoker;
import me.shedaniel.rei.api.client.gui.widgets.Tooltip;
import me.shedaniel.rei.impl.client.gui.widget.QueuedTooltip;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.stream.Collectors;


@SuppressWarnings("UnstableApiUsage")
@Mixin(QueuedTooltip.class)
public abstract class MixinQueuedTooltip
{
    @Inject(method = "add(Lnet/minecraft/text/Text;)Lme/shedaniel/rei/api/client/gui/widgets/Tooltip;", at = @At("RETURN"), cancellable = true)
    private void bat$appendTags(Text text, CallbackInfoReturnable<Tooltip> cir)
    {
        Tooltip tooltip = cir.getReturnValue();

        List<TagKey<?>> tags = ((QueuedTooltipInvoker)this).invokeGetContextStack().getTagsFor().toList();

        if(tags.isEmpty()) return;

        MinecraftClient mc = MinecraftClient.getInstance();

        List<Tooltip.Entry> entryList = tags
                .stream()
                .map(TagKey::toString)
                .map(entry -> entry.substring(entry.contains("minecraft:item") ? 24 : 25, entry.length() - 1))
                .sorted()
                .distinct()
                .map(entry -> Text.literal(entry).formatted(Formatting.DARK_GRAY))
                .map(Tooltip::entry)
                .collect(Collectors.toList());

        entryList.add(0, Tooltip.entry(Text.literal("\nTags:").formatted(Formatting.DARK_GRAY)));

        if(InputUtil.isKeyPressed(mc.getWindow().getHandle(), InputUtil.GLFW_KEY_LEFT_CONTROL)) entryList.forEach(entry -> tooltip.entries().add(entry));
        else tooltip.entries().removeAll(entryList);

        cir.setReturnValue(tooltip);
    }
}
