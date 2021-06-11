package Translator.GUI;

import Translator.Translator;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

/**
 * the stage in which the source file path for file translation is taken
 */

public class FileTranslationStage extends Stage {
    private final static double BUTTON_WIDTH = 150;
    private final static double BUTTON_HEIGHT = 40;


    public FileTranslationStage(Translator mainRef) {
        //engFileSection
        Label englishFileText = new Label("English File : ");
        TextField engFilePathTextField = new TextField();
        AtomicReference<File> selectedFile = new AtomicReference<>();
        Button englishFileSelectionButton = new Button("Select Source");
        englishFileSelectionButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        englishFileSelectionButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                    "TEXT files(*.txt)", "*.txt"));

            selectedFile.set(fileChooser.showOpenDialog(this));
            if (selectedFile.get() != null) {
                engFilePathTextField.setText(selectedFile.get().getAbsolutePath());
            }
        });

        HBox englishFileBox = new HBox(englishFileText, engFilePathTextField, englishFileSelectionButton);
        englishFileBox.setAlignment(Pos.CENTER_LEFT);
        englishFileBox.setSpacing(30d);
        //#engFileSelection
//======================================================================================================================
        //TranslateButton
        Button translateButton = new Button("Translate");
        translateButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        translateButton.setOnAction(event -> new Thread(() -> {
            if (!engFilePathTextField.getText().equals("")) {
                mainRef.translateFile(selectedFile.get().getPath());
                Platform.runLater(() -> getThis().close());
            } else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Please Choose A File First!", ButtonType.OK);
                    alert.setHeaderText("Source Error");
                    alert.show();
                });
            }
        }).start());

        //#TranslateButton
//======================================================================================================================
        VBox mainBox = new VBox(englishFileBox, translateButton);
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setPadding(new Insets(15));
        mainBox.setAlignment(Pos.CENTER);
        mainBox.setSpacing(15d);

        this.setWidth(550);
        this.setHeight(150);
        this.setResizable(false);
        this.setTitle("Choose File To Translate & To Save Translation To");
        Scene fileTranslationScene = new Scene(mainBox);
        fileTranslationScene.getStylesheets().add("Translator/GUI/CssFiles/FileTranslationCssFile.css");
        this.setScene(fileTranslationScene);

    }

    private FileTranslationStage getThis() {
        return this;
    }

}
