package com.github.jamies1211.kittools.Commands.SetupCommands;

import com.github.jamies1211.kittools.Config.GeneralDataManagement;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.kittools.Messages.*;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class KitRemoveCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String kitName = args.<String>getOne("kitName").get();

		if (GeneralDataManagement.getKitList().contains(kitName)) {
			GeneralDataManagement.removeKit(kitName);
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitRemoved
					.replace("%kit%", kitName)));
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitNotExist));
		}

		return CommandResult.success();
	}
}