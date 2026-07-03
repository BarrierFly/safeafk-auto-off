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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;
import java.nio.file.Path;

/**
 * Persists the "was automatically disabled" flag across game sessions.
 * Writes a simple JSON file to the Minecraft game directory.
 */
public class SafeAfkAutoOffState
{
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
	private static final Path STATE_FILE = FabricLoader.getInstance().getGameDir().resolve("safeafk-auto-off.json");

	private static final String KEY_AUTO_DISABLED = "autoDisabledSafeAfk";

	public static boolean isAutoDisabled()
	{
		File file = STATE_FILE.toFile();
		if (!file.exists())
		{
			return false;
		}
		try (Reader reader = new FileReader(file))
		{
			JsonObject obj = GSON.fromJson(reader, JsonObject.class);
			return obj != null && obj.has(KEY_AUTO_DISABLED) && obj.get(KEY_AUTO_DISABLED).getAsBoolean();
		}
		catch (IOException e)
		{
			return false;
		}
	}

	public static void setAutoDisabled()
	{
		JsonObject obj = new JsonObject();
		obj.addProperty(KEY_AUTO_DISABLED, true);
		try (Writer writer = new FileWriter(STATE_FILE.toFile()))
		{
			GSON.toJson(obj, writer);
		}
		catch (IOException ignored)
		{
		}
	}

	public static void clearAutoDisabled()
	{
		//noinspection ResultOfMethodCallIgnored
		STATE_FILE.toFile().delete();
	}
}
