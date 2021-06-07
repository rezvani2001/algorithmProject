package Translator.GUI;

import Translator.Translator;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainStage extends Stage {

    private static final double BUTTONS_WIDTH = 170d;
    private static final double BUTTONS_HEIGHT = 50d;
    private static final double TEXT_AREA_WIDTH = 485;
    private static final double TEXT_AREA_HEIGHT = 300;


    public MainStage(Translator mainRef) {
        //stage init
        this.setWidth(1250);
        this.setHeight(800);
        this.setResizable(false);
        this.setTitle("English To Farsi Translator");
        //#stage init
//======================================================================================================================
        //EnglishTextSection
        Text englishTextObj = new Text("English Text : ");
        englishTextObj.setFont(Font.font(null, FontWeight.BOLD, 24));
        TextArea englishTextArea = new TextArea();
        englishTextArea.setId("englishInputText");
        englishTextArea.setPrefSize(485, 300);
        englishTextArea.setPrefSize(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        englishTextArea.setPromptText("English Text To Be Translated To Farsi.");

        VBox englishTextBox = new VBox(englishTextObj, englishTextArea);
        englishTextBox.setId("EnglishBoxContainer");
        englishTextBox.setSpacing(10);
        englishTextBox.setAlignment(Pos.TOP_LEFT);
        englishTextBox.setId("englishPartContainer");
        englishTextBox.setPadding(new Insets(10, 15, 10, 15));
        //#EnglishTextSection
//======================================================================================================================
        //PersianTextSection
        Text farsiTextObj = new Text("Farsi Translation : ");
        farsiTextObj.setFont(Font.font(null, FontWeight.BOLD, 24));
        TextArea farsiTextArea = new TextArea();
        farsiTextArea.setPrefSize(485, 300);
        farsiTextArea.setId("farsiTranslationText");
        farsiTextArea.setPrefSize(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        farsiTextArea.setPromptText("Farsi Translation of The English Text.");
        farsiTextArea.setEditable(false);

        VBox farsiTranslationBox = new VBox(farsiTextObj, farsiTextArea);
        farsiTranslationBox.setSpacing(10);
        farsiTranslationBox.setAlignment(Pos.TOP_LEFT);
        farsiTranslationBox.setId("farsiTranslationContainer");
        farsiTranslationBox.setPadding(new Insets(10, 15, 10, 15));
        //#PersianTextSection
//======================================================================================================================
        //ButtonsSection
        Button translateTextButton = new Button("Translate Text");
        translateTextButton.setPrefSize(BUTTONS_WIDTH, BUTTONS_HEIGHT);
        translateTextButton.setOnAction(event -> Platform.runLater(() -> {
            int startIndex = 0;
            int endIndex = 0;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < englishTextArea.getText().length(); i++) {
                if (englishTextArea.getText().charAt(i) == '\n' || i >= englishTextArea.getText().length() - 1) {
                    endIndex = i + (i >= englishTextArea.getText().length() - 1 ? 1 : 0);
                    sb.append(mainRef.translate(englishTextArea.getText().substring(startIndex, endIndex)).trim());
                    if (englishTextArea.getText().charAt(i) == '\n') sb.append('\n');
                    farsiTextArea.setText(sb.toString());
                    startIndex = endIndex;
                }
            }
        }));

        Button translateFileButton = new Button("Translate File");
        translateFileButton.setPrefSize(BUTTONS_WIDTH, BUTTONS_HEIGHT);
        translateFileButton.setOnAction(event -> {
            FileTranslationStage fTS = new FileTranslationStage();
            fTS.initOwner(getThis());
            fTS.initModality(Modality.APPLICATION_MODAL);
            fTS.showAndWait();
        });

        //TODO : use fileChooser and get a file and translate it and write it to file

        Button seeTreeButton = new Button("See Tree");
        seeTreeButton.setPrefSize(BUTTONS_WIDTH, BUTTONS_HEIGHT);
        seeTreeButton.setOnAction(event -> {
            TreeStage tS = new TreeStage();
            tS.initOwner(getThis());
            getThis().hide();
            tS.showAndWait();
            getThis().show();

        });
        //TODO : open treeStage and make this its owner

        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(BUTTONS_WIDTH, BUTTONS_HEIGHT);
        exitButton.setOnAction(event -> {
            Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
            //TODO : check if a file is being translated
            exitAlert.setTitle("Exit Confirmation");
            exitAlert.setHeaderText("Do You Want To Exit?");
            exitAlert.setContentText("You Are About To Exit The Translator!");
            exitAlert.initOwner(getThis());
            exitAlert.showAndWait();
            ButtonType res = exitAlert.getResult();
            if (res.equals(ButtonType.OK)) {
                getThis().close();
            }
        });

        VBox buttonsBox = new VBox(translateTextButton, translateFileButton, seeTreeButton, exitButton);
        buttonsBox.setId("buttonsContainer");
        buttonsBox.setAlignment(Pos.BOTTOM_CENTER);
        buttonsBox.setSpacing(25);

        //#ButtonsSection
//======================================================================================================================
        AnchorPane ap = new AnchorPane(englishTextBox, farsiTranslationBox, buttonsBox);
        AnchorPane.setRightAnchor(farsiTranslationBox, 15d);
        AnchorPane.setTopAnchor(farsiTranslationBox, 10d);
        AnchorPane.setLeftAnchor(englishTextBox, 15d);
        AnchorPane.setTopAnchor(englishTextBox, 10d);
        AnchorPane.setBottomAnchor(buttonsBox, 50d);
        AnchorPane.setLeftAnchor(buttonsBox, 10d);
        AnchorPane.setRightAnchor(buttonsBox, 10d);
        ap.setId("mainContainer");

        Scene mainScene = new Scene(ap);
        mainScene.getStylesheets().add("Translator/GUI/CssFiles/MainStageCssFile.css");
        this.setScene(mainScene);
    }

    private MainStage getThis() {
        return this;
    }

}
