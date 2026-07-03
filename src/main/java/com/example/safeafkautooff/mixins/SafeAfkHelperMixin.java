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
import me.fallenbreath.tweakermore.config.TweakerMoreConfigStorage;
import me.fallenbreath.tweakermore.config.TweakerMoreConfigs;
import me.fallenbreath.tweakermore.impl.features.safeAfk.SafeAfkHelper;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Injects into SafeAfkHelper.onHealthUpdate() right after all safety conditions
 * are verified and the disconnect is about to happen (at the resetHurtTime call).
 * <p>
 * At this point we know the safe AFK feature has triggered, so we:
 * 1. Disable the safe AFK config toggle (so it won't trigger again)
 * 2. Force-save the config immediately (so the change persists even if the game
 *    is closed from the disconnect screen before the next tick save)
 * 3. Write a reminder flag (so we can tell the user next time they join)
 */
@Mixin(value = SafeAfkHelper.class, remap = false)
public abstract class SafeAfkHelperMixin
{
	@Inject(
			method = "onHealthUpdate",
			at = @At(
					value = "INVOKE",
					target = "Lme/fallenbreath/tweakermore/impl/features/safeAfk/SafeAfkHelper;resetHurtTime()V",
					remap = false
			),
			remap = false
	)
	private static void onSafeAfkTrigger(Minecraft mc, CallbackInfo ci)
	{
		// 1. Disable the safe AFK toggle in memory
		TweakerMoreConfigs.SAFE_AFK.setBooleanValue(false);

		// 2. Force-save to disk immediately (don't rely on tick-based periodic save)
		TweakerMoreConfigStorage.getInstance().save();

		// 3. Persist the "was auto-disabled" flag for next-join reminder
		SafeAfkAutoOffState.setAutoDisabled();
	}
}
