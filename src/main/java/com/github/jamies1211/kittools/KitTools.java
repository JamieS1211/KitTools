package com.github.jamies1211.kittools;

/**
 * Created by Jamie on 27-Oct-16.
 **/

import com.github.jamies1211.kittools.Commands.GeneralCommands.HelpCommand;
import com.github.jamies1211.kittools.Commands.SubCommands;
import com.github.jamies1211.kittools.Commands.GeneralCommands.KitGetCommand;
import com.github.jamies1211.kittools.Config.GeneralDataConfig;
import com.google.inject.Inject;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.*;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Logger;

import static com.github.jamies1211.kittools.Messages.startup;

@Plugin(id = "kittools", name = "Kit Tools", version = "2.0.0-1.12.2",
		description = "Adds kits",
		authors = {"JamieS1211"})
public class KitTools {

	@Inject
	private Logger logger;
	public static KitTools plugin;

	public Logger getLogger() {
		return this.logger;
	}

	@Inject
	@ConfigDir(sharedRoot = false)
	private Path configDir;

	public static KitTools getKitTools() {
		return plugin;
	}

	public Path getConfigDir() {
		return configDir;
	}

	@Listener
	public void onPreInitialization(GamePreInitializationEvent event) {
		plugin = this;

		// Create config Directory for VoteTools.
		if (!Files.exists(configDir)) {
			try {
				Files.createDirectories(configDir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// Set up data and config files.
		GeneralDataConfig.getConfig().setup();
	}

	@Listener
	public void onServerStart(GameInitializationEvent event) {
		getLogger().info(startup);

		final CommandSpec kitCommand = CommandSpec.builder()
				.description(Text.of("Use this command to get info on the kit sub commands"))
				.extendedDescription(Text.of("Use this command to get info on the kit sub commands"))
				.executor(new HelpCommand())
				.children(SubCommands.getGeneralSubCommands())
				.build();

		Sponge.getCommandManager().register(this, kitCommand, "kit");


	}
}