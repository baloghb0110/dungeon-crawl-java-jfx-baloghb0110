package com.codecool.dungeoncrawl.logic;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Pos;

public class GameOverScreen {
    public static void display(String message) {
        Platform.runLater(() -> {
            Stage gameoverStage = new Stage();
            gameoverStage.initModality(Modality.APPLICATION_MODAL);
            gameoverStage.setTitle("Game Over");

            VBox layout = new VBox(10);
            layout.setAlignment(Pos.CENTER);
            layout.getChildren().addAll(
                    new Label(message),
                    new Button("Quit")
            );

            Button quitButton = (Button) layout.getChildren().get(1);
            quitButton.setOnAction(e -> {
                gameoverStage.close();
                Platform.exit();
            });
            Scene scene = new Scene(layout, 100, 50);
            gameoverStage.setScene(scene);
            gameoverStage.showAndWait();
        });
    }
}
