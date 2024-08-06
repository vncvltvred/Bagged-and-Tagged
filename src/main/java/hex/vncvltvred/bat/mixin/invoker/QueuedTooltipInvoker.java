package hex.vncvltvred.bat.mixin.invoker;

import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.impl.client.gui.widget.QueuedTooltip;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@SuppressWarnings("UnstableApiUsage")
@Mixin(QueuedTooltip.class)
public interface QueuedTooltipInvoker { @Invoker(value = "getContextStack", remap = false) EntryStack<?> invokeGetContextStack(); }
