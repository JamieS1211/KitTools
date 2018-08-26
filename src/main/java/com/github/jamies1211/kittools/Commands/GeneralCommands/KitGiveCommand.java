package com.github.jamies1211.kittools.Commands.GeneralCommands;

import com.github.jamies1211.kittools.Config.GeneralDataManagement;
import com.github.jamies1211.kittools.KitHandler;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import static com.github.jamies1211.kittools.Messages.*;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class KitGiveCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String kitName = args.<String>getOne("kitName").get();
		final Player player = args.<Player>getOne("targetPlayer").get();

		if (GeneralDataManagement.getKitList().contains(kitName)) {
			if (GeneralDataManagement.checkKitEnabled(kitName)) {
				KitHandler.processKit(player, kitName);

				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitGiven
						.replace("%kit%", kitName)
						.replace("%player%", player.getName())));
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitIsDisabled));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitNotExist));
		}

		return CommandResult.success();
	}
}