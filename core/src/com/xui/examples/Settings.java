package com.xui.examples;

/**
 * Just a singleton class to hold settings
 */
public class Settings {
    private static Settings instance = new Settings();

    private String currentExampleName;

    private Settings() {}

    public static Settings getInstance() {
        return instance;
    }

    public void setCurrentExampleName(String currentExampleName) {
        this.currentExampleName = currentExampleName;
    }

    public String getCurrentExampleName() {
        return currentExampleName;
    }
}
