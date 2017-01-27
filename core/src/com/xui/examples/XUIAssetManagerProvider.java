package com.xui.examples;

import com.badlogic.gdx.assets.AssetManager;

import ua.com.integer.gdx.xml.ui.res.AssetProvider;

public class XUIAssetManagerProvider implements AssetProvider {
    private String folder, extension;
    protected AssetManager assetManager;
    protected Class<? extends Object> assetClass;

    public XUIAssetManagerProvider(AssetManager assetManager, Class<? extends Object> assetClass, String folder, String extension) {
        this.assetManager = assetManager;
        this.assetClass = assetClass;
        this.folder = folder;
        this.extension = extension;
    }


    @Override
    public Object getAsset(String name) {
        String fullName = folder + "/" + name + "." + extension;
        if (!assetManager.isLoaded(fullName, assetClass)) {
            assetManager.load(fullName, assetClass);
            assetManager.finishLoading();
        }

        return assetManager.get(fullName, assetClass);
    }
}
