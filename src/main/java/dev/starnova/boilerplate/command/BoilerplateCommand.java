package dev.starnova.boilerplate.command;

import dev.starnova.boilerplate.Boilerplate;
import dev.starnova.boilerplate.gui.ExampleGui;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.incendo.cloud.annotations.Command;
import org.incendo.cloud.annotations.Permission;

public final class BoilerplateCommand {

    private final Boilerplate plugin;

    public BoilerplateCommand(Boilerplate plugin) {
        this.plugin = plugin;
    }

    @Command("boilerplate reload")
    @Permission("boilerplate.reload")
    public void reload(CommandSender sender) {
        plugin.reloadConfig();
        sender.sendMessage(Component.text("Config reloaded.", NamedTextColor.GREEN));
    }

    @Command("boilerplate gui")
    @Permission("boilerplate.gui")
    public void gui(Player player) {
        ExampleGui.open(player);
    }
}
