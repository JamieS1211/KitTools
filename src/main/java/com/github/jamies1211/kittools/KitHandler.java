package com.github.jamies1211.kittools;

import com.github.jamies1211.kittools.Actions.ObjectChanceCalculator;
import com.github.jamies1211.kittools.Config.GeneralDataManagement;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.serializer.TextSerializers;

import java.util.TreeMap;

import static com.github.jamies1211.kittools.Messages.kitPrefix;
import static com.github.jamies1211.kittools.Messages.kitReceived;
import static com.github.jamies1211.kittools.Messages.kitReceivedEmpty;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class KitHandler {

	public static void processKit (Player player, String kitName) {

		TreeMap<Integer, Reward> rewardMap = new TreeMap<>();
		Double workingValue = 0.0;
		boolean receivedReward = false;

		for (Object reward : GeneralDataManagement.getKitRewardList(kitName)) {

			if (!GeneralDataManagement.getCumulativePercentage(kitName, reward)) {
				// If the chance is non cumulative randomly assign according to percentage if this reward will be given out.
				if (ObjectChanceCalculator.nonCumulativeSuccess(GeneralDataManagement.getRewardPercentage(kitName, reward))) {
					// If the reward is to be given out immediately give reward to player.
					processRewards(kitName, reward, player);
					receivedReward = true;
				}
			} else {
				// If the chance is cumulative add to a hash map and increase the working value to later calculate if reward will be given out.
				workingValue = workingValue + GeneralDataManagement.getRewardPercentage(kitName, reward);
				rewardMap.put((rewardMap.size() + 1), new Reward(reward, workingValue));
			}
		}

		// If cumulative rewards are to be calculated.
		if (!rewardMap.isEmpty()) {
			// Randomly calculate what (if any) reward is to be given out this time.
			Reward returnedReward = ObjectChanceCalculator.cumulativeSuccess(rewardMap);
			if (returnedReward != null) {
				// If a reward is going to be given out immediately give reward to player.
				processRewards(kitName, returnedReward.reward, player);
				receivedReward = true;
			}
		}

		if (receivedReward) {
			player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitReceived
					.replace("%kit%", kitName)));
		} else {
			player.sendMessage(TextSerializers.FORMATTING_CODE.deserialize(kitPrefix + kitReceivedEmpty
					.replace("%kit%", kitName)));
		}
	}

	public static void processRewards (String kitName, Object reward, Player player) {

		String type = GeneralDataManagement.getRewardType(kitName, reward);

		if (type.equalsIgnoreCase("Item")) { // Give player item if reward type is item.

			player.getInventory().offer(GeneralDataManagement.getRewardItem(kitName, reward));

		} else if (type.equalsIgnoreCase("Command")) { // Run command if reward type is command.

			String command = GeneralDataManagement.getRewardCommand(kitName, reward)
					.replace("%uuid%", player.getUniqueId().toString())
					.replace("%player%", player.getName())
					.replace("%kit%", kitName);

			Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command);
		}
	}
}
