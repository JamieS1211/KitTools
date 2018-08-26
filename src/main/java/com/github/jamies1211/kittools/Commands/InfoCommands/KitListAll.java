package com.github.jamies1211.kittools.Commands.InfoCommands;

import com.github.jamies1211.kittools.Config.GeneralDataManagement;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.kittools.Messages.kitPrefix;

/**
 * Created by Jamie on 27/10/2016.
 */
public class KitListAll implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		for (String kit : GeneralDataManagement.getKitListWithStatus()) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kit));
		}

		return CommandResult.success();
	}
}
