package Translator.GUI;

import Translator.Translator;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

public class MainStage extends Stage {

    private static final double BUTTONS_WIDTH = 170d;
    private static final double BUTTONS_HEIGHT = 50d;

    public MainStage(Translator mainRef) {
        //stage init
        this.setWidth(1250);
        this.setHeight(800);
        this.setResizable(false);
        //#stage init
//======================================================================================================================
        //EnglishTextSection
        Text englishTextObj = new Text("English Text : ");
        englishTextObj.setFont(Font.font(null, FontWeight.BOLD, 24));

        TextArea englishTextArea = new TextArea();
        englishTextArea.setPrefSize(485, 300);
        englishTextArea.setPromptText("English Text To Be Translated To Farsi.");

        VBox englishTextBox = new VBox(englishTextObj, englishTextArea);
        englishTextBox.setSpacing(10);
        englishTextBox.setAlignment(Pos.TOP_LEFT);
        englishTextBox.setPadding(new Insets(10, 15, 10, 15));
        //#EnglishTextSection
//======================================================================================================================
        //PersianTextSection
        Text farsiTextObj = new Text("Farsi Translation : ");
        farsiTextObj.setFont(Font.font(null, FontWeight.BOLD, 24));

        TextArea farsiTextArea = new TextArea();
        farsiTextArea.setPrefSize(485, 300);
        farsiTextArea.setPromptText("Farsi Translation of The English Text.");
        farsiTextArea.setEditable(false);

        VBox farsiTranslationBox = new VBox(farsiTextObj, farsiTextArea);
        farsiTranslationBox.setSpacing(10);
        farsiTranslationBox.setAlignment(Pos.TOP_LEFT);
        farsiTranslationBox.setPadding(new Insets(10, 15, 10, 15));
        //#PersianTextSection
//======================================================================================================================
        //ButtonsSection
        Button translateTextButton = new Button("Translate Text");
        translateTextButton.setPrefSize(BUTTONS_WIDTH,BUTTONS_HEIGHT);
        translateTextButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        int startIndex = 0;
                        int endIndex = 0;
                        StringBuilder sb = new StringBuilder();
                        for (int i = 0; i < englishTextArea.getText().length(); i++) {
                            if(englishTextArea.getText().charAt(i) == '\n' || i >= englishTextArea.getText().length() - 1){
                                endIndex = i + (i >= englishTextArea.getText().length() - 1 ? 1 : 0);
                                sb.append(mainRef.translate(englishTextArea.getText().substring(startIndex, endIndex)).trim());
                                if(englishTextArea.getText().charAt(i) == '\n') sb.append('\n');
                                farsiTextArea.setText(sb.toString());
                                startIndex = endIndex;
                            }
                        }
                    }
                });

            }
        });

        Button translateFileButton = new Button("Translate File");
        translateFileButton.setPrefSize(BUTTONS_WIDTH,BUTTONS_HEIGHT);

        //TODO : use fileChooser and get a file and translate it and write it to file

        Button seeTreeButton = new Button("See Tree");
        seeTreeButton.setPrefSize(BUTTONS_WIDTH,BUTTONS_HEIGHT);
        //TODO : open treeStage and make this its owner

        Button exitButton = new Button("Exit");
        exitButton.setPrefSize(BUTTONS_WIDTH,BUTTONS_HEIGHT);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION);
                //TODO : check if a file is being translated
                exitAlert.setTitle("Exit Confirmation");
                exitAlert.setHeaderText("Do You Want To Exit?");
                exitAlert.setContentText("You Are About To Exit The Translator!");
                exitAlert.initOwner(getThis());
                exitAlert.showAndWait();
                ButtonType res = exitAlert.getResult();
                if(res.equals(ButtonType.OK)){
                    getThis().close();
                }
            }
        });

        VBox buttonsBox = new VBox(translateTextButton, translateFileButton, seeTreeButton, exitButton);
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

        Scene mainScene = new Scene(ap);
        this.setScene(mainScene);
    }

    private MainStage getThis(){
        return this;
    }

}
