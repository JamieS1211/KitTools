package com.github.jamies1211.kittools;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class Reward {

	public final String reward;
	public final double decimalPercentage;

	public Reward (Object reward, double decimalPercentage) {
		this.reward = reward.toString();
		this.decimalPercentage = decimalPercentage;
	}
}
