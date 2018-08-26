package com.github.jamies1211.kittools.Config;

		import com.github.jamies1211.kittools.KitTools;
		import ninja.leaping.configurate.commented.CommentedConfigurationNode;
		import ninja.leaping.configurate.hocon.HoconConfigurationLoader;
		import ninja.leaping.configurate.loader.ConfigurationLoader;

		import java.io.IOException;
		import java.nio.file.Files;
		import java.nio.file.Path;
		import java.nio.file.Paths;

		import static com.github.jamies1211.kittools.Messages.*;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class GeneralDataConfig {

	private static GeneralDataConfig config = new GeneralDataConfig();

	public static GeneralDataConfig getConfig() {
		return config;
	}

	private Path configFile = Paths.get(KitTools.getKitTools().getConfigDir() + "/general.conf");
	private ConfigurationLoader<CommentedConfigurationNode> configLoader = HoconConfigurationLoader.builder().setPath(configFile).build();
	private CommentedConfigurationNode configNode;

	public void setup() {
		if (!Files.exists(configFile)) {
			try {
				Files.createFile(configFile);
				load();
				enterData();
				save();
				KitTools.plugin.getLogger().info(newConfigFile);
			} catch (IOException e) {
				e.printStackTrace();
				KitTools.plugin.getLogger().info(configLoadError);

			}
		} else {
			load();
			KitTools.plugin.getLogger().info(loadedConfigFile);
		}
	}

	public void load() {
		try {
			configNode = configLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
			KitTools.plugin.getLogger().info(configLoadError);
		}
	}

	public void save() {
		try {
			configLoader.save(configNode);
		} catch (IOException e) {
			e.printStackTrace();
			KitTools.plugin.getLogger().info(configSaveError);
		}
	}

	public CommentedConfigurationNode get() {
		return configNode;
	}

	public void enterData() {

		get().getNode("01 - configVersion").setValue(1);

		get().getNode("02 - kits").getChildrenMap().keySet();
		get().getNode("02 - kits", "1", "object1", "Type").setValue("Command");
		get().getNode("02 - kits", "1", "object1", "Chance").setValue(1.0);
		get().getNode("02 - kits", "1", "object1", "Cumulative").setValue("false");
		get().getNode("02 - kits", "1", "object1", "Command").setValue("say Example command type");
	}
}
