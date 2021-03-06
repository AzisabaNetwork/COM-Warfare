package com.rhetorical.cod.lang;

import com.rhetorical.cod.ComWarfare;
import com.rhetorical.cod.progression.ProgressionManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages the level names for each level.
 * */
public class LevelNames {

	private static LevelNames singleton;

	private Map<Integer, String> levelNames = new HashMap<>();

	private LevelNames() {
		if (singleton != null)
			return;

		singleton = this;

		setup();
	}

	private void setup() {
		FileConfiguration config = ComWarfare.getPlugin().getConfig();
		for (int i = 1; i <= ProgressionManager.getInstance().maxLevel; i++) {
			if (config.contains("LevelNames." + i)) {
				levelNames.put(i, config.getString("LevelNames." + i));
			}
		}
	}

	public static LevelNames getInstance() {
		return singleton != null ? singleton : new LevelNames();
	}

	public String getLevelName(int level) {
		return levelNames.getOrDefault(level, "");
	}
}