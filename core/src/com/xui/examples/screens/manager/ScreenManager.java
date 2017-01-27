/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.xui.examples.screens.manager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Class that managers multiple {@link AbstractScreen}
 * 
 * @author 1nt3g3r
 */
public class ScreenManager {
    private Batch batch;
    private Game game;

    /**
     * @param game instance of your game
     * @param batch batch that will be passed to {@link AbstractScreen}. All screens share one batch (for performance purposes).
     */
    public ScreenManager(Game game, Batch batch) {
        this.game = game;
        this.batch = batch;
    }

    /**
     * Dispose current screen by calling game.getScreen().dispose(). Creates new instance of screenType type and 
     * set it by calling game.setScreen().
     */
    public <T extends AbstractScreen> void showScreen(Class<T> screenType) {
        try {
            if (game.getScreen() != null) {
                game.getScreen().dispose();
            }

            AbstractScreen screen = screenType.newInstance();
            game.setScreen(screen.setBatch(batch));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Dispose current screen by calling game.getScreen().dispose(). After it set passed screen by calling 
     * game.setScreen(screen);
     */
    public void showScreen(Screen screen) {
    	if (game.getScreen() != null) {
    		game.getScreen().dispose();
    	}
    	
    	game.setScreen(screen);
    }

    /**
     * Dispose current screen and load it again. It can be useful if you made some changes and need to reload current screen
     */
    public void reloadCurrentScreen() {
        AbstractScreen screen = (AbstractScreen) game.getScreen();
        showScreen(screen.getClass());
    }

    public <T extends Screen> T getCurrentScreen() {
        return (T) game.getScreen();
    }
}
