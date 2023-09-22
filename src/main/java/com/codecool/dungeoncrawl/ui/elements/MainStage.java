package com.codecool.dungeoncrawl.ui.elements;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

public class MainStage {
    private final Canvas canvas;
    private final Scene scene;
    private final StatusPane statusPane;

    public MainStage(Canvas canvas) {
        this.canvas = canvas;
        statusPane = new StatusPane();
        scene = setUpScene();
    }

    private Scene setUpScene() {
        BorderPane borderPane = statusPane.build();
        borderPane.setCenter(canvas);
        return new Scene(borderPane);
    }

    public Scene getScene() {
        return scene;
    }

    public void setHealthLabelText(String text) {
        this.statusPane.setHealthValue(text);
    }

    public void setDamageLabelText(String text) {
        this.statusPane.setDamageValue(text);
    }

    public void setDefenseLabelText(String text) {
        this.statusPane.setDefenseValue(text);
    }

    public void setInventoryLabelText(String text) {
        this.statusPane.setInventoryValue(text);
    }
}
