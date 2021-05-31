package consoleApp;

import java.util.*;

public class run{
    public static void main(String[] args){

        //INITIALIZATION OF VARIABLES
        Set<data> dataSet= new LinkedHashSet<>();

        Scanner sc = new Scanner(System.in);

        String dictionary = "LOGIC";
        String input = "I love to work in global logic!";
        String exampleChoice;

        System.out.println("Do you want default Java Academy example?(Y/N)");
        do{
            exampleChoice = sc.nextLine();
        }while(!(exampleChoice.equalsIgnoreCase("Y") || exampleChoice.equalsIgnoreCase("N")));

        if(!exampleChoice.equalsIgnoreCase("Y")){
            System.out.print("Input your new dictionary:");
            dictionary = sc.nextLine().replaceAll("[^a-zA-Z0-9]","");
            System.out.print("Input your new input string:");
            input = sc.nextLine();
        }


        Set<String> dictionarySet = new LinkedHashSet<>();
        List<String> wordsList = List.of(input.split(" "));
        List<String> words = new ArrayList<>();

        int lenWithoutSpecial = input.replaceAll("[^a-zA-Z0-9]","").length();
        int numberOfDictChars = 0;

        for(char c:dictionary.toCharArray()) dictionarySet.add(String.valueOf(c).toUpperCase());
        //***************************

        //REMOVING SPECIAL CHARS
        numberOfDictChars = countDictChars(numberOfDictChars,dictionarySet,input);
        removeSpecialChars(words, wordsList);
        //**********************

        //ADDING TO DATASET(all logic of the program)
        addData(dataSet, words, dictionarySet, numberOfDictChars);
        //*****************

        //SORTING
        List<data> orderedDataList = new ArrayList<>(dataSet);
        List<String> sortedDictionary = new ArrayList<>(dictionarySet);
        orderedDataList.sort(Comparator.comparingDouble(data::getFrequency).thenComparing(data::getWordLength));
        //*******

        //OUTPUT
        output(orderedDataList,  sortedDictionary,numberOfDictChars,lenWithoutSpecial);
        //******


    }


    public static void removeSpecialChars(List<String> words, List<String> wordsList ){
        for(String s: wordsList){
            String temp = s.replaceAll("[^a-zA-Z0-9]","");
            words.add(temp);
        }

    }


    public static int countDictChars(int numberOfDictChars, Set<String> dictionarySet, String input){
        for(char c:input.toUpperCase().toCharArray()){
            if(dictionarySet.contains(String.valueOf(c))){
                numberOfDictChars++;
            }
        }

        return numberOfDictChars;
    }

    public static void addData(Set<data> dataSet, List<String> words, Set<String> dictionarySet, int numberOfDictChars){

        for(String s:words){
            if(!s.isEmpty()){
                int lengthTemp = s.length();
                LinkedHashSet<Character> setTemp = new LinkedHashSet<>();
                int numOfDictCharsInWord = 0;

                for (char c : s.toUpperCase().toCharArray()) {
                    if(dictionarySet.contains(String.valueOf(c))){
                        setTemp.add(c);
                        numOfDictCharsInWord++;
                    }
                }

                if(numOfDictCharsInWord != 0)
                    dataSet.add(new data(setTemp, lengthTemp, numOfDictCharsInWord, numberOfDictChars));
            }
        }

    }

    public static void output(List<data> orderedDataList, List<String> sortedDictionary, int numberOfDictChars, int lenWithoutSpecial){
        StringBuilder out = new StringBuilder();

        for(data d:orderedDataList){
            System.out.print("\n{ (");

            List<String> sortedList = new ArrayList<>();
            for(char c:d.getDictionaryChars()){
                sortedList.add(String.valueOf(c));
            }

            sortedList.sort(Comparator.comparing(sortedDictionary::indexOf));


            for(String s:sortedList){
                out.append(s).append(", ");
            }
            if(out.length()>1)
            out = new StringBuilder((String) out.subSequence(0, out.length() - 2));
            System.out.print(out);
            System.out.print("), " + d.getWordLength() + "} = " + d.getFrequency() + " (" + d.getNumberOfDictCharsInWord() + "/"
                    + numberOfDictChars + ")");

            out = new StringBuilder();
        }

        System.out.println("\nTOTAL Frequency: " + Math.round((numberOfDictChars/(double)lenWithoutSpecial) * 100.0)/100.0 +
                " (" + numberOfDictChars + "/" + lenWithoutSpecial + ")");
    }

}
