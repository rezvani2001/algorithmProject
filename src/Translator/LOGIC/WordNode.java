package Translator.LOGIC;

/**
 * a class to hold all of the words in dictionary to have easier access to the content.
 */
public class WordNode {
    public double possibility;
    public String key;
    public String value;

    /**
     * gets english word, persian word and the possibility of the word and make it stored in the object.
     *
     * @param key
     * @param value
     * @param possibility
     */
    public WordNode(String key , String value , double possibility){
        this.key = key;
        this.value = value;
        this.possibility = possibility;
    }
}
