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
public class KitGetCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		if (src instanceof Player) {
			Player player = (Player) src;

			final String kitName = args.<String>getOne("kitName").get();

			if (GeneralDataManagement.getKitList().contains(kitName)) {
				if (player.hasPermission("kit.get." + kitName)) {
					if (GeneralDataManagement.checkKitEnabled(kitName)) {
						KitHandler.processKit(player, kitName);
					} else {
						src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitIsDisabled));
					}
				} else {
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitNoAccess));
				}
			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitNotExist));
			}
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + playerCommandError));
		}
		return CommandResult.success();
	}
}