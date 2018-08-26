package com.github.jamies1211.kittools.Commands.SetupCommands;

import com.github.jamies1211.kittools.Config.GeneralDataManagement;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.kittools.Messages.kitAlreadyExists;
import static com.github.jamies1211.kittools.Messages.kitCreated;
import static com.github.jamies1211.kittools.Messages.kitPrefix;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class KitCreateCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String kitName = args.<String>getOne("kitName").get();

		if (GeneralDataManagement.getKitList().contains(kitName)) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitAlreadyExists));
		} else {
			GeneralDataManagement.createKit(kitName);
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitCreated
					.replace("%kit%", kitName)));
		}

		return CommandResult.success();
	}
}