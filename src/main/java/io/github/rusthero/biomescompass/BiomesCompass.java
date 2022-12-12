package io.github.rusthero.biomescompass;

import io.github.rusthero.biomescompass.item.BiomesCompassItem;
import io.github.rusthero.biomescompass.menu.BiomeSelectMenu;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class BiomesCompass extends JavaPlugin {
    @Override
    public void onEnable() {
        // TODO Configuration

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BiomesCompassItem.Listener(), this);
        pluginManager.registerEvents(new BiomeSelectMenu.Listener(), this);

        getServer().addRecipe(BiomesCompassItem.getRecipe(this));

        getLogger().info("BiomesCompass is enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("BiomesCompass is disabled");
    }
}