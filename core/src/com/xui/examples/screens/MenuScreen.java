package com.xui.examples.screens;

import com.xui.examples.screens.manager.AbstractScreen;

import ua.com.integer.gdx.xml.ui.XUI;

public class MenuScreen extends AbstractScreen {
    @Override
    public void init() {
        XUI.inflate("menu-screen", getStage());
    }
}
