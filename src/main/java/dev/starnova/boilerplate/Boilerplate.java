package dev.starnova.boilerplate;

import dev.starnova.boilerplate.command.BoilerplateCommand;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.incendo.cloud.annotations.AnnotationParser;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.LegacyPaperCommandManager;
import xyz.xenondevs.invui.InvUI;

public final class Boilerplate extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        InvUI.getInstance().setPlugin(this); // required because InvUI is shaded

        LegacyPaperCommandManager<CommandSender> commandManager =
                LegacyPaperCommandManager.createNative(this, ExecutionCoordinator.simpleCoordinator());
        commandManager.registerBrigadier();

        AnnotationParser<CommandSender> annotationParser = new AnnotationParser<>(commandManager, CommandSender.class);
        annotationParser.parse(new BoilerplateCommand(this));
    }

    @Override
    public void onDisable() {
    }
}
