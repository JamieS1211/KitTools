package com.github.jamies1211.kittools;

/**
 * Created by Jamie on 27-Oct-16.
 */
public class Messages {

	/** General messages */
	public static final String kitPrefix = "&e&l[Kit] &9";
	public static final String startup = "Kit tools started";
	public static final String newConfigFile = "Created new configuration file as none detected!";
	public static final String loadedConfigFile = "Detected configuration file and loaded";
	public static final String configLoadError = "The default configuration could not be loaded or created!";
	public static final String configSaveError = "Failed to save config file!";
	public static final String configReloaded = "Config file reloaded";

	/** Command messages */
	public static final String argumentError = "&cError, incorrect arguments!";
	public static final String kitHelpHeader = "Start of kit tools help text";
	public static final String kitCommandHelp = "/kit %command% %arguments% : %description%";
	public static final String kitHelpFooter = "End of kit tools help text. Available commands: %count%";
	public static final String playerCommandError = "This command must be run by a player";
	public static final String kitAlreadyExists = "This kit already exists so you can't create it";
	public static final String kitCreated = "You have created a new kit with the name: %kit%";
	public static final String kitRemoved = "You have just deleted kit: %kit%";
	public static final String kitItemAdded = "You have added %item%x%quantity% to %kit% at %chance%% with cumulative:%cumulative%";
	public static final String kitCommandAdded = "You have added command: %command% to %kit% at %chance%% with cumulative:%cumulative%";
	public static final String kitRewardRemoved = "The reward listed has been removed from the listed kit";
	public static final String kitRewardNotExist = "The reward index you have listed does not exist for the listed kit";
	public static final String kitNotExist = "This kit does not exist";
	public static final String kitReceived = "You have just received kit: %kit%";
	public static final String kitReceivedEmpty = "You have just been awarded kit: %kit%, however you have recieved no items. Please contact a member of staff";
	public static final String kitGiven = "You have just given kit: %kit% to %player%";
	public static final String kitNoAccess = "Unfortunately you do not have access to this kit";
	public static final String kitIsDisabled = "Unfortunately this kit is currently disabled";
	public static final String kitEnabled = "You have just enabled kit: %kit%";
	public static final String kitDisabled = "You have just disabled kit: %kit%";
	public static final String kitEnabledAlready = "Kit: %kit% is already enabled";
	public static final String kitDisabledAlready = "Kit: %kit% is already disabled";
}
