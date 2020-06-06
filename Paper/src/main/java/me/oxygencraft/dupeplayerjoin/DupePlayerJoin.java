package me.oxygencraft.dupeplayerjoin;

import com.destroystokyo.paper.event.profile.PreLookupProfileEvent;
import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class DupePlayerJoin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void setDupePlayerProfile(AsyncPlayerPreLoginEvent event) {
        if (getServer().getPlayer(event.getUniqueId()) != null) {
            Player player = getServer().getPlayer(event.getName());
            int playerNo = 2;
            for (;;) {
                if (getServer().getPlayer(event.getName() + playerNo) == null) {
                    break;
                }
                ++playerNo;
            }
            PlayerProfile profile = event.getPlayerProfile();
            UUID uuid = UUID.nameUUIDFromBytes(("DupePlayerLogin:" + event.getName() + playerNo).getBytes(StandardCharsets.UTF_8));
            profile.setId(uuid);
            profile.setName(event.getName() + playerNo);
            event.setPlayerProfile(profile);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void lookupDupePlayerJoinProfile(PreLookupProfileEvent event) {
        if (getServer().getPlayer(event.getName()) != null) {
            Player player = getServer().getPlayer(event.getName());
            event.addProfileProperties(player.getPlayerProfile().getProperties());
            int playerNo = 2;
            for (;;) {
                if (getServer().getPlayer(event.getName() + playerNo) == null) {
                    break;
                }
                UUID uuid = UUID.nameUUIDFromBytes(("DupePlayerLogin:" + event.getName() + playerNo).getBytes(StandardCharsets.UTF_8));
                event.setUUID(uuid);
                ++playerNo;
            }
        }
    }
}
