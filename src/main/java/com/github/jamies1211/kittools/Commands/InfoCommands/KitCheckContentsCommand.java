package com.github.jamies1211.kittools.Commands.InfoCommands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class KitCheckContentsCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String kitName = args.<String>getOne("kitName").get();

		//TODO add contents info

		return CommandResult.success();
	}
}
