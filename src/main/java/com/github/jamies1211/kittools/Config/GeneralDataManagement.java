package com.github.jamies1211.kittools.Config;

import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import org.spongepowered.api.data.persistence.DataTranslators;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.ArrayList;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class GeneralDataManagement {

	/**
	 * Returns the current config version.
	 *
	 * @return
	 *          The current config version.
	 */
	public static int getConfigVersion () {
		return GeneralDataConfig.getConfig().get().getNode("01 - ConfigVersion").getInt();
	}

	/**
	 * Returns the list of all kits.
	 *
	 * @return
	 *          The list of all kits.
	 */
	public static ArrayList<String> getKitList() {

		ArrayList<String> kitList = new ArrayList<>();

		for (Object kitNameObject : GeneralDataConfig.getConfig().get().getNode("02 - kits").getChildrenMap().keySet()) {
			kitList.add(kitNameObject.toString());
		}

		return kitList;
	}

	/**
	 * Returns the list of all kits stating if each one is enabled or disabled.
	 *
	 * @return
	 *          The list of all kits with details on what are enabled and disabled.
	 */
	public static ArrayList<String> getKitListWithStatus() {

		ArrayList<String> kitListWithStatus = new ArrayList<>();

		for (String kitName : getKitList()) {
			if (checkKitEnabled(kitName)) {
				kitListWithStatus.add(kitName + " &a(enabled)");
			} else {
				kitListWithStatus.add(kitName + " &c(disabled)");
			}
		}

		return kitListWithStatus;
	}

	/**
	 * Returns boolean value if this kit is enabled.
	 *
	 * @param kitName
	 *          Name of the kit.
	 * @return
	 *          The boolean value for if this kit is enabled.
	 */
	public static boolean checkKitEnabled(String kitName) {
		return GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName, "Enabled").getBoolean();
	}

	/**
	 * Returns the full list of rewards of the kit.
	 *
	 * @param kitName
	 *          Name of the kit.
	 * @return
	 *          The reward list for this kit.
	 */
	public static ArrayList<Object> getKitRewardList(String kitName) {

		ArrayList<Object> kitRewards = new ArrayList<>();

		for (Object key : GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName).getChildrenMap().keySet()) {
			kitRewards.add(key);
		}

		return kitRewards;
	}

	/**
	 * Returns double value for the chance of this reward.
	 *
	 * @param kitName
	 *          Name of the kit.
	 * @param rewardIndex
	 *          Index of the reward to check.
	 * @return
	 *          The chance this reward will be given when the kit is redeemed.
	 */
	public static Double getRewardPercentage(String kitName, Object rewardIndex) {
		return GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName, rewardIndex, "Chance").getDouble();
	}

	/**
	 * Returns boolean value if this reward of the kit is a cumulative percentage.
	 *
	 * @param kitName
	 *          Name of the kit.
	 * @param rewardIndex
	 *          Index of the reward to check.
	 * @return
	 *          The boolean value for if this reward uses cumulative percentages.
	 */
	public static boolean getCumulativePercentage(String kitName, Object rewardIndex) {
		return GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName, rewardIndex, "Cumulative").getBoolean();
	}

	/**
	 * Returns the string name of the reward type.
	 *
	 * @param kitName
	 *          Name of the kit.
	 * @param rewardIndex
	 *          Index of the reward to check.
	 * @return
	 *          The type of reward this reward is.
	 */
	public static String getRewardType(String kitName, Object rewardIndex) {
		return GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName, rewardIndex, "Type").getString();
	}

	/**
	 * Returns the item stack for the reward.
	 *
	 * @param kitName
	 *          Name of the kit.
	 * @param rewardIndex
	 *          Index of the reward to check.
	 * @return
	 *          The item stack this reward gives.
	 */
	public static ItemStack getRewardItem(String kitName, Object rewardIndex) {
		return ItemStack.builder().fromContainer(DataTranslators.CONFIGURATION_NODE.translate(GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName, rewardIndex, "Item"))).build();
	}

	/**
	 * Returns the string of the command for the reward.
	 *
	 * @param kitName
	 *          Name of the kit.
	 * @param rewardIndex
	 *          Index of the reward to check.
	 * @return
	 *          The command that is run when this reward is given.
	 */
	public static String getRewardCommand(String kitName, Object rewardIndex) {
		return GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName, rewardIndex, "Command").getString();
	}

	/**
	 * Creates a new kit that is enabled.
	 *
	 * @param kitName
	 *          Name of the kit.
	 */
	public static void createKit(String kitName) {
		GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName, "Enabled").setValue("true");

		GeneralDataConfig.getConfig().save();
	}

	/**
	 * Removes the listed kit.
	 *
	 * @param kitName
	 *          Name of the kit.
	 */
	public static void removeKit(String kitName) {
		GeneralDataConfig.getConfig().get().getNode("02 - kits").removeChild(kitName);

		GeneralDataConfig.getConfig().save();
	}

	/**
	 * Enable a kit.
	 *
	 * @param kitName
	 *          Name of the kit.
	 */
	public static void enableKit(String kitName) {
		GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName, "Enabled").setValue("true");

		GeneralDataConfig.getConfig().save();
	}

	/**
	 * Disable a kit.
	 *
	 * @param kitName
	 *          Name of the kit.
	 */
	public static void disableKit(String kitName) {
		GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName, "Enabled").setValue("false");

		GeneralDataConfig.getConfig().save();
	}

	/**
	 * Adds an item to a kit.
	 *
	 * @param kitName
	 *          Name of the kit.
	 * @param chance
	 *          Chance this item will be given out when kit rewarded.
	 * @param cumulative
	 *          If the chance calculation is cumulative on other cumulative chances for this reward.
	 * @param stack
	 *          Item stack of the item to be given out.
	 */
	public static void addItemToKit(String kitName, double chance, boolean cumulative, ItemStack stack, int quantity) {
		CommentedConfigurationNode config = GeneralDataConfig.getConfig().get();

		int index = 1;
		while (config.getNode("02 - kits", kitName, "object" + Integer.toString(index), "Type").getValue() != null) {
			index ++;
		}
		String rewardIndex = "object" + Integer.toString(index);

		config.getNode("02 - kits", kitName, rewardIndex, "Type").setValue("Item");
		config.getNode("02 - kits", kitName, rewardIndex, "Chance").setValue(chance);
		config.getNode("02 - kits", kitName, rewardIndex, "Cumulative").setValue(cumulative);

		config.getNode("02 - kits", kitName, rewardIndex, "Item").setValue(DataTranslators.CONFIGURATION_NODE.translate(stack.toContainer()));

		config.getNode("02 - kits", kitName, rewardIndex, "Item", "Count").setValue(quantity);

		GeneralDataConfig.getConfig().save();
	}

	/**
	 * Adds a command to a kit.
	 * @param kitName
	 *          Name of the kit.
	 * @param chance
	 *          Chance this command will be run out when kit rewarded.
	 * @param cumulative
	 *          If the chance calculation is cumulative on other cumulative chances for this reward.
	 * @param command
	 *          The command to be run.
	 */
	public static void addCommandToKit(String kitName, double chance, boolean cumulative, String command) {
		CommentedConfigurationNode config = GeneralDataConfig.getConfig().get();

		int index = 1;
		while (config.getNode("02 - kits", kitName, "object" + Integer.toString(index), "Type").getValue() != null) {
			index ++;
		}
		String rewardIndex = "object" + Integer.toString(index);

		config.getNode("02 - kits", kitName, rewardIndex, "Type").setValue("Command");
		config.getNode("02 - kits", kitName, rewardIndex, "Chance").setValue(chance);
		config.getNode("02 - kits", kitName, rewardIndex, "Cumulative").setValue(cumulative);
		config.getNode("02 - kits", kitName, rewardIndex, "Command").setValue(command);

		GeneralDataConfig.getConfig().save();
	}

	/**
	 * Removes the listed kit.
	 *
	 * @param kitName
	 *          Name of the kit.
	 * @param rewardIndex
	 *          Index of the reward.
	 */
	public static void removeReward(String kitName, String rewardIndex) {
		GeneralDataConfig.getConfig().get().getNode("02 - kits", kitName).removeChild(rewardIndex);

		GeneralDataConfig.getConfig().save();
	}
}
