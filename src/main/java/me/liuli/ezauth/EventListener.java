package me.liuli.ezauth;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerLoginEvent;

public class EventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        if(!event.getPlayer().getLoginChainData().isXboxAuthed() && !EZAuth.canBypass(event.getPlayer().getAddress().toLowerCase())) {
            event.setKickMessage("disconnectionScreen.notAuthenticated");
            event.setCancelled();
        }
    }
}
