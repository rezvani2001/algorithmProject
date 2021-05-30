package Translator.File;



import Translator.Translator;

import java.io.*;

public class FileTranslator{

    Translator mainRef;

    public FileTranslator(Translator mainRef) {
        this.mainRef = mainRef;
    }

    public void translate(String sourceFilePath, String destinationFilePath){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BufferedReader reader = new BufferedReader(new FileReader(sourceFilePath));
                    BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFilePath));
                    while(!reader.ready()){
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            mainRef.PrintError("Reading Translator.File Attempt Failed, pleaseTryAgain");
                            mainRef.log(new Event(e, "sleeping Thread interrupted"));
                        }
                    }
                    while(reader.readLine() != null){
                        String currentLine = reader.readLine();
                        for (String value : currentLine.split(" ")) {
                            writer.write(mainRef.translate(value));
                        }
                        writer.write('\n');
                    }
                } catch (IOException e) {
                    mainRef.cantOpenFile();
                }
            }
        }).start();
    }

}
