package Translator.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class FileTranslationStage extends Stage {
    private final static double BUTTON_WIDTH = 75;
    private final static double BUTTON_HEIGHT = 40;


    public FileTranslationStage() {
        //engFileSection
        Text englishFileText = new Text("English File : ");
        TextField engFilePathTextField = new TextField();
        Button englishFileSelectionButton = new Button("Select Source");
        englishFileSelectionButton.setPrefSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        //TODO : SADRA : set all width and heights

        HBox englishFileBox = new HBox(englishFileText, engFilePathTextField,englishFileSelectionButton);
        englishFileBox.setAlignment(Pos.CENTER_LEFT);
        englishFileBox.setSpacing(15d);
        //#engFileSelection
//======================================================================================================================
        //TranslatedFileSection
        Text translatedFileText = new Text("Translated File : ");
        TextField translatedFilePathTextField = new TextField();
        Button translatedFileSelectionButton = new Button("Select Destination");


        HBox translatedFileBox = new HBox(translatedFileText,translatedFilePathTextField, translatedFileSelectionButton);
        translatedFileBox.setAlignment(Pos.CENTER_LEFT);
        translatedFileBox.setSpacing(15d);
        //#TranslatedFileSection
//======================================================================================================================
        VBox mainBox = new VBox(englishFileBox, translatedFileBox);
        mainBox.setAlignment(Pos.CENTER);
        this.setScene(new Scene(mainBox));

    }
}
