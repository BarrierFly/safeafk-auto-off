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

package com.example.safeafkautooff;

import net.fabricmc.api.ClientModInitializer;

public class SafeAfkAutoOffMod implements ClientModInitializer
{
	public static final String MOD_ID = "safeafk-auto-off";

	@Override
	public void onInitializeClient()
	{
		// Mixins are auto-registered via safeafk-auto-off.mixins.json
	}
}
