package Translator.GUI;

import Translator.Translator;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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

    public MainStage(Translator mainRef) {
        //stage init
        this.setWidth(1250);
        this.setHeight(800);
        this.setResizable(false);
        //#stage init

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
        AnchorPane ap = new AnchorPane(englishTextBox, farsiTranslationBox);
        AnchorPane.setRightAnchor(farsiTranslationBox, 15d);
        AnchorPane.setTopAnchor(farsiTranslationBox, 10d);
        AnchorPane.setLeftAnchor(englishTextBox, 15d);
        AnchorPane.setTopAnchor(englishTextBox, 10d);

        Scene mainScene = new Scene(ap);
        this.setScene(mainScene);
    }

    private MainStage getThis(){
        return this;
    }

}
