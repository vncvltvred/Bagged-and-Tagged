package hex.vncvltvred.bat.mixin.invoker;

import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.impl.client.gui.widget.entrylist.EntryListStackEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(EntryListStackEntry.class)
public interface EntryListStackEntryInvoker { @Invoker(value = "getCurrentEntry", remap = false) EntryStack<?> invokeGetCurrentEntry(); }
