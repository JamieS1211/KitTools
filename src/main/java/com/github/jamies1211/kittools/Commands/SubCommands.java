package com.github.jamies1211.kittools.Commands;

import com.github.jamies1211.kittools.Commands.GeneralCommands.*;
import com.github.jamies1211.kittools.Commands.InfoCommands.KitCheckContentsCommand;
import com.github.jamies1211.kittools.Commands.InfoCommands.KitListAll;
import com.github.jamies1211.kittools.Commands.SetupCommands.*;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.text.Text;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Jamie on 27-Oct-16.
 */
@SuppressWarnings("ArraysAsListWithZeroOrOneArgument")
public class SubCommands {

	public static HashMap<List<String>, CommandSpec> getGeneralSubCommands() {
		final HashMap<List<String>, CommandSpec> generalSubCommands = new HashMap<>();

		generalSubCommands.put(Arrays.asList("help"), CommandSpec.builder()
				.description(Text.of("Shows kit help"))
				.extendedDescription(Text.of("Use this command to list all the kit commands"))
				.executor(new HelpCommand())
				.build());

		generalSubCommands.put(Arrays.asList("reload"), CommandSpec.builder()
				.permission("kit.reload")
				.description(Text.of("Reloads config"))
				.extendedDescription(Text.of("/kit reload"))
				.executor(new ReloadCommand())
				.build());

		generalSubCommands.put(Arrays.asList("save"), CommandSpec.builder()
				.permission("kit.save")
				.description(Text.of("Saves config"))
				.extendedDescription(Text.of("/kit save"))
				.executor(new SaveCommand())
				.build());

		generalSubCommands.put(Arrays.asList("listall"), CommandSpec.builder()
				.permission("kit.listall")
				.description(Text.of("Lists all kits with details on if they are enabled or not"))
				.extendedDescription(Text.of("/kit listall"))
				.executor(new KitListAll())
				.build());

		generalSubCommands.put(Arrays.asList("give"), CommandSpec.builder()
				.permission("kit.give")
				.description(Text.of("Gives the specified kit to the specified player"))
				.extendedDescription(Text.of("Gives the specified kit to the specified player"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("kitName"))),
						GenericArguments.onlyOne(GenericArguments.player(Text.of("targetPlayer"))))
				.executor(new KitGiveCommand())
				.build());

		generalSubCommands.put(Arrays.asList("get"), CommandSpec.builder()
				.permission("kit.get")
				.description(Text.of("Gives the kit to the player who runs the command"))
				.extendedDescription(Text.of("Gives the kit to the player who runs the command"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("kitName"))))
				.executor(new KitGetCommand())
				.build());

		generalSubCommands.put(Arrays.asList("create"), CommandSpec.builder()
				.permission("kit.create")
				.description(Text.of("Create a new empty kit with the name"))
				.extendedDescription(Text.of("/kit create [kit]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("kitName"))))
				.executor(new KitCreateCommand())
				.build());

		generalSubCommands.put(Arrays.asList("remove"), CommandSpec.builder()
				.permission("kit.remove")
				.description(Text.of("Remove the specified kit"))
				.extendedDescription(Text.of("/kit remove [kit]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("kitName"))))
				.executor(new KitRemoveCommand())
				.build());

		generalSubCommands.put(Arrays.asList("enable"), CommandSpec.builder()
				.permission("kit.enable")
				.description(Text.of("Enable the listed kit"))
				.extendedDescription(Text.of("/kit enable [kit]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("kitName"))))
				.executor(new KitEnabledCommand())
				.build());

		generalSubCommands.put(Arrays.asList("disable"), CommandSpec.builder()
				.permission("kit.disable")
				.description(Text.of("Disable the listed kit"))
				.extendedDescription(Text.of("/kit disable [kit]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("kitName"))))
				.executor(new KitDisableCommand())
				.build());

		generalSubCommands.put(Arrays.asList("addreward"), CommandSpec.builder()
				.permission("kit.addreward")
				.description(Text.of("Add a new reward to the kit"))
				.extendedDescription(Text.of("/kit addreward [kit] [type] [chance] [cumulative] [quantity/command]"))
				.arguments(
						GenericArguments.allOf(GenericArguments.string(Text.of("arguments"))))
				.executor(new KitAddRewardCommand())
				.build());

		generalSubCommands.put(Arrays.asList("removereward"), CommandSpec.builder()
				.permission("kit.removereward")
				.description(Text.of("Removes the specified reward from the kit"))
				.extendedDescription(Text.of("/kit removereward [kit] [rewardIndex]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("kitName"))),
						GenericArguments.onlyOne(GenericArguments.string(Text.of("rewardIndex"))))
				.executor(new KitRemoveRewardCommand())
				.build());

		generalSubCommands.put(Arrays.asList("checkcontents"), CommandSpec.builder()
				.permission("kit.checkcontents")
				.description(Text.of("Displays all the rewards for a kit"))
				.extendedDescription(Text.of("/kit checkcontents [kit]"))
				.arguments(
						GenericArguments.onlyOne(GenericArguments.string(Text.of("kitName"))))
				.executor(new KitCheckContentsCommand())
				.build());

		return generalSubCommands;
	}

}
