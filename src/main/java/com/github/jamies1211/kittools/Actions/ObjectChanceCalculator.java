package com.github.jamies1211.kittools.Actions;

import com.github.jamies1211.kittools.Reward;
import org.apache.commons.lang3.RandomUtils;

import java.util.TreeMap;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class ObjectChanceCalculator {

	public static boolean nonCumulativeSuccess(Double chance) {
		return chance >= RandomUtils.nextDouble(0, 1);
	}

	public static Reward cumulativeSuccess(TreeMap<Integer, Reward> rewardMap) {
		double randomNumber = 0.0;
		Reward finalReward = null;
		Reward currentReward;

		while (randomNumber == 0.0) {
			randomNumber = RandomUtils.nextDouble(0, 1);
		}

		for (int index = 1; index <= rewardMap.size(); index++) {
			currentReward = rewardMap.get(index);

			if (currentReward.decimalPercentage >= randomNumber) {
				finalReward = currentReward;
				break;
			}
		}

		return finalReward;
	}
}
