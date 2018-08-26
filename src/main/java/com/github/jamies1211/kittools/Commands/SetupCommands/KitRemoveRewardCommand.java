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
public class KitRemoveRewardCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		final String kitName = args.<String>getOne("kitName").get();
		final String rewardIndex = args.<String>getOne("rewardIndex").get();

		if (GeneralDataManagement.getKitRewardList(kitName).contains(rewardIndex)) {
			GeneralDataManagement.removeReward(kitName, rewardIndex);
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitRewardRemoved
					.replace("%kit%", kitName)));
		} else {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitRewardNotExist));
		}

		return CommandResult.success();
	}
}
