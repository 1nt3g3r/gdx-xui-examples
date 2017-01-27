package com.xui.examples.screens.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;

public class Screens {
    private static ScreenManager screenManager;

    public static void init(Game game, Batch batch) {
        screenManager = new ScreenManager(game, batch);
    }

    public static void show(Class<? extends AbstractScreen> screenClass) {
        checkIsInitialized();
        screenManager.showScreen(screenClass);
    }

    public static void show(Screen screen) {
        checkIsInitialized();
        screenManager.showScreen(screen);
    }

    public static void reloadCurrent() {
        checkIsInitialized();
        screenManager.reloadCurrentScreen();
    }

    public static <T extends Screen> T getCurrent() {
        checkIsInitialized();
        return screenManager.getCurrentScreen();
    }

    private static void checkIsInitialized() {
        if (screenManager == null) {
            throw new RuntimeException("Call init() before use!");
        }
    }
}
