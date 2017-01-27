package com.xui.examples;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.xui.examples.screens.MenuScreen;
import com.xui.examples.screens.manager.Screens;

import ua.com.integer.gdx.xml.ui.XUI;

public class XUICore extends Game {
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Screens.init(this, batch);
        XUI.init();

        Screens.show(MenuScreen.class);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
