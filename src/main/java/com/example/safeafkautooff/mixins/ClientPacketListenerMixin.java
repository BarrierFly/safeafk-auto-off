/*
 * This file is part of the SafeAFK Auto Off project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2026  SafeAFK Auto Off contributors
 *
 * SafeAFK Auto Off is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SafeAFK Auto Off is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with SafeAFK Auto Off.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.example.safeafkautooff.mixins;

import com.example.safeafkautooff.SafeAfkAutoOffState;
import me.fallenbreath.tweakermore.util.Messenger;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Injects into ClientPacketListener.handleLogin (called when the player
 * successfully joins a world/server). If safe AFK was auto-disabled in a
 * previous session, shows a reminder in chat and clears the flag.
 */
@Mixin(ClientPacketListener.class)
public abstract class ClientPacketListenerMixin
{
	@Inject(method = "handleLogin", at = @At("TAIL"))
	private void onJoinWorld(CallbackInfo ci)
	{
		if (SafeAfkAutoOffState.isAutoDisabled())
		{
			Minecraft mc = Minecraft.getInstance();

			Component msg = Messenger.formatting(
					Messenger.s("Safe AFK was automatically disabled because you took damage in your previous session."),
					ChatFormatting.YELLOW
			);

			mc.gui.getChat().addMessage(msg);

			// Clear the flag so we don't remind again
			SafeAfkAutoOffState.clearAutoDisabled();
		}
	}
}
