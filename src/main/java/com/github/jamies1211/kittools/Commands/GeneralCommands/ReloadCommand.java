package com.github.jamies1211.kittools.Commands.GeneralCommands;

import com.github.jamies1211.kittools.Config.GeneralDataConfig;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.kittools.Messages.configReloaded;
import static com.github.jamies1211.kittools.Messages.kitPrefix;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class ReloadCommand implements CommandExecutor{

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		GeneralDataConfig.getConfig().load();
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + configReloaded));
		return CommandResult.success();
	}
}
