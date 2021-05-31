package consoleApp;

import lombok.Data;

import java.util.Set;

@Data
public class data{

    private Set<Character> dictionaryChars;
    private int wordLength;
    private double frequency;
    private int numberOfDictCharsInWord;

    public data(Set<Character> dictionaryChars, int wordLength, int numberOfDictCharsInWord, int numberOfDictChars) {
        this.dictionaryChars = dictionaryChars;
        this.wordLength = wordLength;
        this.frequency = Math.round((numberOfDictCharsInWord/(double)numberOfDictChars) * 100.0)/100.0;
        this.numberOfDictCharsInWord = numberOfDictCharsInWord;
    }


}
