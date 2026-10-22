package dev.starnova.boilerplate.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import xyz.xenondevs.invui.gui.Gui;
import xyz.xenondevs.invui.item.builder.ItemBuilder;
import xyz.xenondevs.invui.item.impl.SimpleItem;
import xyz.xenondevs.invui.window.Window;

public final class ExampleGui {

    private ExampleGui() {
    }

    public static void open(Player player) {
        Gui gui = Gui.normal()
                .setStructure(
                        "# # # # # # # # #",
                        "# . . . . . . . #",
                        "# # # # # # # # #")
                .addIngredient('#', new SimpleItem(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("")))
                .build();

        Window.single()
                .setViewer(player)
                .setTitle("Example")
                .setGui(gui)
                .build()
                .open();
    }
}
