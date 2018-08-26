package com.github.jamies1211.kittools.Commands.SetupCommands;

import com.github.jamies1211.kittools.Config.GeneralDataManagement;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.data.type.HandTypes;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.ArrayList;
import java.util.Collection;

import static com.github.jamies1211.kittools.Messages.*;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class KitAddRewardCommand implements CommandExecutor {

	@Override
	public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {

		Collection<String> arguments = args.getAll("arguments");
		boolean inputError = false;

		if (arguments.size() >= 4) {

			// Initialise variables.
			int assignedValues = 0;
			String kitName = "";
			String type = "";
			double chance = 0.0;
			boolean cumulative = true;
			ArrayList<String> otherArgs = new ArrayList<>();

			// Parse arguments to variables.
			for (Object key : arguments.toArray()) {
				String currentValue = key.toString();

				switch (assignedValues) {
					case 0:
						kitName = currentValue;
						break;
					case 1:
						type = currentValue;
						break;
					case 2:

						try {
							if (Character.toString(currentValue.charAt(currentValue.length() - 1)).equals("%")) {
								currentValue = currentValue.substring(0, currentValue.length() - 1);
								chance = Double.parseDouble(currentValue) / 100;
							} else {
								chance = Double.parseDouble(currentValue);
							}
						} catch (Exception e) {
							inputError = true;
						}

						break;
					case 3:

						try {
							cumulative = Boolean.parseBoolean(currentValue);
						} catch (Exception e) {
							inputError = true;
						}

						break;
					default:
						otherArgs.add(currentValue);
						break;
				}

				assignedValues++;
			}

			if (GeneralDataManagement.getKitList().contains(kitName)) {
				if (type.equalsIgnoreCase("Item") && otherArgs.size() == 0) {
					// Cast the quantity from the other arguments.
					try {
						if (src instanceof Player) {
							Player player = (Player) src;

							ItemStack stack = player.getItemInHand(HandTypes.MAIN_HAND).get();
							int quantity = stack.getQuantity();

							GeneralDataManagement.addItemToKit(kitName, chance, cumulative, stack, quantity);
							src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitItemAdded
									.replace("%item%", stack.getItem().getName())
									.replace("%quantity%", Integer.toString(quantity))
									.replace("%kit%", kitName)
									.replace("%chance%", Double.toString(chance*100))
									.replace("%cumulative%", Boolean.toString(cumulative))));
						} else {
							src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + playerCommandError));
						}

					} catch (Exception e) {
						inputError = true;
					}

				} else if (type.equalsIgnoreCase("Command") && otherArgs.size() > 0) {
					// Create the command from the other arguments.
					String command = "";
					for (String text : otherArgs) {
						if (command.equals("")) {
							command = text;
						} else {
							command = command + " " + text;
						}
					}

					GeneralDataManagement.addCommandToKit(kitName, chance, cumulative, command);
					src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitCommandAdded
							.replace("%command%", command)
							.replace("%kit%", kitName)
							.replace("%chance%", Double.toString(chance*100))
							.replace("%cumulative%", Boolean.toString(cumulative))));
				} else {
					inputError = true;
				}

			} else {
				src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitNotExist));
			}
		} else {
			inputError = true;
		}


		if (inputError) {
			src.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + argumentError));
		}


		return CommandResult.success();
	}
}