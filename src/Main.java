import java.util.*;

public class Main {

    public static void main(String[] args) {
        // Defining variables which will be used
        int i, checkIndex, index = -1;
        int[] indexArray; /* Array which will store frequency of a certain character, eg. inputWord = "logic",
                             certain word = "global", indexArray = {2,1,1,0,0},
                             index in inputWord is the same as in indexArray, eg. 'l'(index) = 1, 'o'(index) = 2, etc.*/
        double frequency = 0.,amountSentenceCharacters = 0., amountWordCharacters = 0., amount, totalAmount;
        boolean flag = false;

        Scanner sc = new Scanner(System.in);
        String inputText, inputWord , outputString;
        String[] wordsArray;
        List<String> outputList = new LinkedList<String>();

        // Taking input
        System.out.print("Input a single word -> ");
        inputWord = sc.nextLine().toLowerCase();
        if(inputWord.split(" ").length > 1){
            System.out.println("Input word is greater than one, taking the first word.");
            inputWord = inputWord.split(" ")[0];
        }
        System.out.print("Input text -> ");
        inputText = sc.nextLine();

        // Replacing all characters which are not alphanumerics
        inputText = inputText.replaceAll("[^a-zA-Z0-9]", " ").toLowerCase();
        // Splitting input on blank spaces
        wordsArray = inputText.split("\\s+");

        // Removing all whitespace
        inputText = inputText.replaceAll(" ", "");
        // Getting amount of characters in the input sentence
        amountSentenceCharacters = inputText.length();

        // Counting characters from inputText that are in inputWord
        for(i = 0; i < inputText.length(); i++){
            // If a character is from inputWord array, increment amountWordCharacters
            if(inputWord.indexOf(inputText.charAt(i)) != -1){
                amountWordCharacters++;
            }
        }

        // Iterating over every word from inputText
        for(String word : wordsArray) {
            indexArray = new int[inputWord.length()];
            Arrays.fill(indexArray, 0); //
            outputString = "{(";
            frequency = 0;
            // Iterating over an exact word to fill in indexArray as explained at starting part of the program
            for(i = 0; i < word.length(); i++){
                checkIndex = inputWord.indexOf(word.charAt(i));
                if(checkIndex != -1){
                    index = checkIndex;
                    indexArray[index]++;
                }
            }
            // Iterating over indexArray to fill in the outputString
            for(i = 0; i < indexArray.length; i++){
                if(indexArray[i] != 0){
                    outputString += "," + inputWord.charAt(i);
                    frequency += indexArray[i];
                    flag = true;
                }
            }
            // Flag is checked to know if any changes have been made in the previous part, if not, we go to the next iteration
            if(flag){
                // Preparing outputString for printing
                amount = Math.round((frequency/amountWordCharacters) * 100.0)/100.0;
                outputString = outputString.replaceFirst(",", "");
                outputString += "), " + word.length() + "} = " + amount + " (" + (int)frequency +  "/" + (int)amountWordCharacters + ")";
                outputList.add(outputString);
                flag = false; // Resetting a flag
            }
        }
        // Sorting part
        Collections.sort(outputList, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                // Parsing the string to get values
                double aValue = Double.parseDouble(a.split("=")[1].trim().split(" ")[0]);
                double bValue = Double.parseDouble(b.split("=")[1].trim().split(" ")[0]);
                // If frequency is the same, we sort based on the length of the word
                if(aValue == bValue){
                    double cValue = Integer.parseInt(a.split("}")[0].split(" ")[1]);
                    double dValue = Integer.parseInt(b.split("}")[0].split(" ")[1]);
                    return (int)(cValue - dValue);
                    // Else we sort based on frequency
                } else {
                    return (int)((aValue - bValue) * 100);
                }
            }
        });
        // Iterating over outputList to print out the solution
        for(String output : outputList){
            System.out.println(output);
        }
        // Printing out Total Frequency
        totalAmount = Math.round((amountWordCharacters/amountSentenceCharacters) * 100.0)/100.0;
        System.out.println("TOTAL Frequency: "
                + totalAmount + " (" + (int)amountWordCharacters +  "/" + (int)amountSentenceCharacters + ")");
    }
}

