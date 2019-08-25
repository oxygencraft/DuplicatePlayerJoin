package me.oxygencraft.dupejoin.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.RunArgs;
import net.minecraft.client.network.packet.LoginCompressionS2CPacket;
import net.minecraft.client.network.packet.LoginSuccessS2CPacket;
import net.minecraft.network.ClientConnection;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerLoginNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Mixin(ServerLoginNetworkHandler.class)
public abstract class ServerLoginNetworkHandlerMixin
{
    @Shadow private GameProfile profile;
    @Shadow @Final private MinecraftServer server;
    @Shadow private ServerLoginNetworkHandler.State state;
    @Shadow private ServerPlayerEntity clientEntity;
    @Shadow abstract GameProfile toOfflineProfile(GameProfile gameProfile_1);
    @Shadow abstract void disconnect(Text text_1);
    @Shadow @Final public ClientConnection client;

    @Overwrite
    public void acceptPlayer() {
        if (!this.profile.isComplete()) {
            this.profile = this.toOfflineProfile(this.profile);
        }

        Text text_1 = this.server.getPlayerManager().checkCanJoin(this.client.getAddress(), this.profile);
        if (text_1 != null) {
            this.disconnect(text_1);
        } else {
            this.state = ServerLoginNetworkHandler.State.ACCEPTED;
            if (this.server.getNetworkCompressionThreshold() >= 0 && !this.client.isLocal()) {
                this.client.send(new LoginCompressionS2CPacket(this.server.getNetworkCompressionThreshold()), (channelFuture_1) -> {
                    this.client.setMinCompressedSize(this.server.getNetworkCompressionThreshold());
                });
            }

            this.client.send(new LoginSuccessS2CPacket(this.profile));
            ServerPlayerEntity serverPlayerEntity_1 = this.server.getPlayerManager().getPlayer(this.profile.getId());
            if (serverPlayerEntity_1 != null) {
                //this.state = ServerLoginNetworkHandler.State.DELAY_ACCEPT;
                //this.clientEntity = this.server.getPlayerManager().createPlayer(this.profile);
                int playerNo = 2;
                while (true)
                {
                    if (this.server.getPlayerManager().getPlayer(UUID.nameUUIDFromBytes(("DupePlayerLogin:" + profile.getName() + playerNo).getBytes(StandardCharsets.UTF_8))) == null)
                        break;
                    playerNo++;
                }
                UUID newUUID =  UUID.nameUUIDFromBytes(("DupePlayerLogin:" + profile.getName() + playerNo).getBytes(StandardCharsets.UTF_8));
                String baseUsername = this.profile.getName();
                this.profile = new GameProfile(newUUID, baseUsername + playerNo);
                this.server.getPlayerManager().onPlayerConnect(this.client, this.server.getPlayerManager().createPlayer(this.profile));
            } else {
                this.server.getPlayerManager().onPlayerConnect(this.client, this.server.getPlayerManager().createPlayer(this.profile));
            }
        }

    }

}
