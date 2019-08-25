package me.oxygencraft.dupejoin.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.integrated.IntegratedPlayerManager;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.net.SocketAddress;

@Mixin(IntegratedPlayerManager.class)
public abstract class IntegratedPlayerManagerMixin extends PlayerManager
{
    public IntegratedPlayerManagerMixin(MinecraftServer minecraftServer_1, int int_1)
    {
        super(minecraftServer_1, int_1);
    }

    @Overwrite
	public Text checkCanJoin(SocketAddress socketAddress_1, GameProfile gameProfile_1) {
        return super.checkCanJoin(socketAddress_1, gameProfile_1);
	}
}
