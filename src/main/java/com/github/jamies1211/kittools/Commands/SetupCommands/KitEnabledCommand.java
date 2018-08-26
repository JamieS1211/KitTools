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
 * Created by Jamie on 27/10/2016.
 */
public class KitEnabledCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String kitName = args.<String>getOne("kitName").get();

		if (GeneralDataManagement.getKitList().contains(kitName)) {
			if (GeneralDataManagement.checkKitEnabled(kitName)) { // Enabled.
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitEnabledAlready
						.replace("%kit%", kitName)));
			} else { // Disabled
				GeneralDataManagement.enableKit(kitName);
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitEnabled
						.replace("%kit%", kitName)));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitNotExist));
		}

		return CommandResult.success();
	}
}