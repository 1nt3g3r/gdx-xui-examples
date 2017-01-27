package com.xui.examples;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.xui.examples.screens.MenuScreen;
import com.xui.examples.screens.manager.Screens;

import ua.com.integer.gdx.xml.ui.XUI;

public class XUICore extends Game {
	private SpriteBatch batch;
    private AssetManager assetManager;

	@Override
	public void create () {
		batch = new SpriteBatch();
        assetManager = new AssetManager();

        XUI.init();
        registerXUIAssetProviders();

        Screens.init(this, batch);
        Screens.show(MenuScreen.class);
	}

    private void registerXUIAssetProviders() {
        XUI.registerXUIAssetProvider(Skin.class, new XUIAssetManagerProvider(assetManager, Skin.class, "skins", "json"));
    }
	
	@Override
	public void dispose () {
		batch.dispose();
        assetManager.dispose();
	}
}
