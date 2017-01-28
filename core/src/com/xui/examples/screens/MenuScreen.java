package com.xui.examples.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.xui.examples.Settings;
import com.xui.examples.screens.manager.AbstractScreen;
import com.xui.examples.screens.manager.Screens;

import ua.com.integer.gdx.xml.ui.XUI;

public class MenuScreen extends AbstractScreen {
    @Override
    public void init() {
        XUI.inflate("menu-screen", getStage());

        linkButtonWithExample("actorWidgetExample", "widgets/actor-example");
        linkButtonWithExample("labelWidgetExample", "widgets/label-example");
    }

    private void linkButtonWithExample(String buttonName, String exampleName) {
        findActor(buttonName).addListener(new ExampleListener(exampleName));
    }

    class ExampleListener extends ClickListener {
        private String exampleName;

        public ExampleListener(String exampleName) {
            this.exampleName = exampleName;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            Settings.getInstance().setCurrentExampleName(exampleName);
            Screens.show(ExampleScreen.class);
        }
    }
}
