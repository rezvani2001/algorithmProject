package Translator.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class FileTranslationStage extends Stage {
    private final static double BUTTON_WIDTH = 150;
    private final static double BUTTON_HEIGHT = 40;


    public FileTranslationStage() {
        //engFileSection
        Label englishFileText = new Label("English File : ");
        TextField engFilePathTextField = new TextField();
        Button englishFileSelectionButton = new Button("Select Source");
        englishFileSelectionButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        //TODO : SADRA : make buttons work

        englishFileSelectionButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                    "TEXT files(*.txt)" , "*.txt"));

            File selectedFile = fileChooser.showOpenDialog(this);
            if (selectedFile != null){
                engFilePathTextField.setText(selectedFile.getAbsolutePath());
            }
        });

        HBox englishFileBox = new HBox(englishFileText, engFilePathTextField, englishFileSelectionButton);
        englishFileBox.setAlignment(Pos.CENTER_LEFT);
        englishFileBox.setSpacing(30d);
        //#engFileSelection
//======================================================================================================================
        //TranslatedFileSection
        Label translatedFileText = new Label("Translation File : ");
        TextField translatedFilePathTextField = new TextField();
        Button translatedFileSelectionButton = new Button("Select Destination");
        translatedFileSelectionButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);

        translatedFileSelectionButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(
                    "TEXT files(*.txt)" , "*.txt"));

            File selectedFile = fileChooser.showOpenDialog(this);
            if (selectedFile != null){
                translatedFilePathTextField.setText(selectedFile.getAbsolutePath());
            }
        });

        HBox translatedFileBox = new HBox(translatedFileText, translatedFilePathTextField, translatedFileSelectionButton);
        translatedFileBox.setAlignment(Pos.CENTER_LEFT);
        translatedFileBox.setSpacing(15d);
        //#TranslatedFileSection
//======================================================================================================================
        VBox mainBox = new VBox(englishFileBox, translatedFileBox);
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
}
