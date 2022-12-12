package io.github.rusthero.biomescompass.listener;

import io.github.rusthero.biomescompass.BiomesCompass;
import io.github.rusthero.biomescompass.item.BiomesCompassItem;
import io.github.rusthero.biomescompass.locate.BiomeLocator;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ItemUseListener implements Listener {
    private final BiomesCompass biomesCompass;

    public ItemUseListener(final BiomesCompass biomesCompass) {
        this.biomesCompass = biomesCompass;
    }

    @EventHandler
    private void onItemUse(final PlayerInteractEvent event) {
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) return;
        if (event.getItem() == null || !new BiomesCompassItem().equals(event.getItem())) return;

        Player player = event.getPlayer();
        if (!player.hasPermission("biomescompass.use")) return;

        BiomeLocator locator = biomesCompass.getBiomeLocators().get(player);
        if (locator.isRunning()) {
            player.sendMessage("Please wait, searching.");
            return;
        }
        if (locator.isOnCooldown()) {
            player.sendMessage("Please wait, you are on cooldown.");
            return;
        }
        biomesCompass.getLocateBiomeMenu().open(player);
    }
}
