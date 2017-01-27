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

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 * Abstract 2d screen with built-in {@link Stage}
 * 
 * It's oriented primary to work with {@link Actor} and subclasses
 * 
 * IMPORTANT! If you're using this class with ScreenManager, you should to have empty constructor. It's because 
 * {@link ScreenManager} uses reflection to create instances, and {@link ScreenManager} expects empty constructor. Use {@link #init()} 
 * to initialize your screen (add actors, listeners, etc).
 * 
 * @author 1nt3g3r
 */
public class AbstractScreen extends ScreenAdapter {
	protected Stage stage;
	private boolean initialized;

	class BackPressListener extends InputListener {
		@Override
		public boolean keyDown(InputEvent event, int keycode) {
			if (keycode == Keys.BACK || keycode == Keys.ESCAPE) {
				onBackPressed();
				return true;
			}
			return false;
		}
	}
	
	/**
	 * Set batch for this screen. When you call this method, new {@link Stage} will be created
	 */
	public AbstractScreen setBatch(Batch batch) {
		stage = new Stage(new ScreenViewport(), batch);
		stage.addListener(new BackPressListener());
		return this;
	};
	
	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

        if (!initialized) {
            initialized = true;
            init();
        }
	}

	/**
	 * Call this method to setup screen. It's called only once, after show() method.
	 */
    public void init() {

    }

	@Override
	public void render(float delta) {
		Gdx.gl20.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		stage.act(delta);
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().setScreenSize(width, height);
	}
	
	/**
	 * Override this method to handle Back (Android) or Escape (Desktop) key press.
	 * 
	 * NOTE: You should call somewhere (maybe in your ApplicationListener.create() method Gdx.input.setCatchBackKey(true); to handle it on Android devices
	 */
	public void onBackPressed() {
	}

	/**
	 * Executes task in main thread after delay seconds
	 * 
	 * NOTE: Task will be executed as an action in {@link Stage}. So if you will call stage.clearActions() before executing task, 
	 * this task will never be executed
	 */
	public void postTask(float delay, Runnable task) {
		stage.addAction(Actions.sequence(Actions.delay(delay), Actions.run(task)));
	}
	
	/**
	 * Constantly repeat task with interval seconds.
	 * 
	 * NOTE: Task will be executed as an action in {@link Stage}. So if you will call stage.clearActions() before executing task, 
	 * this task will never be executed
	 */
	public void repeatTask(float interval, Runnable task) {
		stage.addAction(Actions.forever(Actions.sequence(Actions.delay(interval), Actions.run(task))));
	}
	
	/**
	 * Allows execute some action when some key pressed. It works only for desktop. Useful for debugging
	 */
	public void debugKey(final int key, final Runnable task) {
		stage.addListener(new InputListener() {
			@Override
			public boolean keyDown(InputEvent event, int keycode) {
				if (key == keycode && Gdx.app.getType() == ApplicationType.Desktop) {
					task.run();
					return true;
				}
				return false;
			}
		});
	}
	
	/**
	 * Returns this screen {@link Stage} instance
	 * @return
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Search actor by his name. Call stage.getRoot().findActor(), so it can be slow
	 */
	public <T extends Actor> T findActor (String name) {
		return stage.getRoot().findActor(name);
	}

	/**
	 * Adds an actor to {@link Stage}
	 */
	public void addActor(Actor actor) {
		stage.addActor(actor);
	}
}
