package hex.vncvltvred.bat.mixin.invoker;

import me.shedaniel.rei.api.client.gui.widgets.Tooltip;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.impl.client.gui.widget.QueuedTooltip;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
@Mixin(QueuedTooltip.class)
public interface QueuedTooltipInvoker
{
    @Invoker(value = "entries", remap = false)
    List<Tooltip.Entry> invokeEntries();

    @Invoker(value = "getContextStack", remap = false)
    EntryStack<?> invokeGetContextStack();
}
