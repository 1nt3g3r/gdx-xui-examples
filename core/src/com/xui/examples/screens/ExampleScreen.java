package com.xui.examples.screens;

import com.xui.examples.Settings;
import com.xui.examples.screens.manager.AbstractScreen;
import com.xui.examples.screens.manager.Screens;

import ua.com.integer.gdx.xml.ui.XUI;

public class ExampleScreen extends AbstractScreen {
    @Override
    public void init() {
        XUI.inflate(Settings.getInstance().getCurrentExampleName(), getStage());
    }

    @Override
    public void onBackPressed() {
        Screens.show(MenuScreen.class);
    }
}
