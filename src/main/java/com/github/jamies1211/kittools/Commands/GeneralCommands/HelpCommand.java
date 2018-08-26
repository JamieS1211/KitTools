package com.github.jamies1211.kittools.Commands.GeneralCommands;

import com.github.jamies1211.kittools.Commands.SubCommands;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.HashMap;
import java.util.List;

import static com.github.jamies1211.kittools.Messages.*;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class HelpCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		HashMap<List<String>, CommandSpec> subCommands = SubCommands.getGeneralSubCommands();

		int accessibleCommands = 0;
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitHelpHeader));

		for (Object key : subCommands.keySet()) {
			CommandSpec command = subCommands.get(key);

			if (command.testPermission(src)) { // Source has permission to command.
				accessibleCommands++;

				String commandIdentifier = key.toString()
						.replace("[", "")
						.replace("]", "");

				String arguments = command.getUsage(src).toPlain()
						.replace("Text{", "")
						.replace("}", "")
						.replace("*", "")
						.replace(" ", "")
						.replace("><", "> <");

				String description = command.getShortDescription(src).get().toString()
						.replace("Text{", "")
						.replace("}", "");

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitCommandHelp
						.replace("%command%", commandIdentifier)
						.replace("%arguments%", arguments)
						.replace("%description%", description)));
			}
		}
		src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitHelpFooter
				.replace("%count%", Integer.toString(accessibleCommands))));

		return CommandResult.success();
	}
}