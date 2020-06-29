package readability;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void getARI(int charsCounter, int wordsCounter, int sentencesCounter) {
        String result;
        double index;

        index = 4.71 * (double)charsCounter / wordsCounter + 0.5 *(double)wordsCounter / sentencesCounter - 21.43;
        switch ((int)Math.round(index + 0.5)) {
            case 1:
                result = "6";
                break;
            case 2:
                result = "7";
                break;
            case 3:
                result = "9";
                break;
            case 4:
                result = "10";
                break;
            case 5:
                result = "11";
                break;
            case 6:
                result = "12";
                break;
            case 7:
                result = "13";
                break;
            case 8:
                result = "14";
                break;
            case 9:
                result = "15";
                break;
            case 10:
                result = "16";
                break;
            case 11:
                result = "17";
                break;
            case 12:
                result = "18";
                break;
            case 13:
                result = "24";
                break;
            case 14:
                result = "24+";
                break;
            default:
                result = "24+";
                break;
        }
        System.out.printf("Automated Readability Index: %4.2f (about %s  year olds.)", index, result);
    }

    public static void getFK() {

    }

    public static void getSMOG() {

    }

    public static void getCL() {

    }

    public static int getSyllables(String str) {
        int counter = 0;
        if (str.charAt(str.length()-1) == 'e') str = str.substring(0, str.length() - 1);
        str = str.replaceAll("[eyouaiEYOUAI]{1,3}", " ");
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == ' ') counter++;
        return (counter == 0)? 1 : counter;
    }

    public static void main(String[] args) {
        String result;
        String pathToFile;
        String syllables;
        String choice;
        int sentencesCounter = 0;
        int wordsCounter = 1;
        int charsCounter = 0;
        int syllablesCounter = 0;
        int polysyllablesCounter = 0;
        Scanner inputScanner = new Scanner(System.in);
        double index;

        if (args.length != 0) {
            pathToFile = args[0];
            File file = new File(pathToFile);
            StringBuilder stringBuilder = new StringBuilder();
            try (Scanner scanner = new Scanner(file)) {
                System.out.println("The text is:");
                while (scanner.hasNext()) {
                    result = scanner.nextLine();
                    System.out.println(result);
                    stringBuilder.append(result);
                    stringBuilder.append(" ");
                }
                result = stringBuilder.toString();
                result = result.trim();
                result = result.replaceAll("  "," ");
                // We count sentences
                for (int i = 0; i < result.length(); i++)
                {
                    if (result.charAt(i) == '.' || result.charAt(i) == '!' || result.charAt(i) == '?') {
                        sentencesCounter++;
                    }
                }
                if ((result.charAt(result.length() - 1) == '.' ||
                        result.charAt(result.length() - 1) == '!' ||
                        result.charAt(result.length() - 1) == '?') == false) {
                    sentencesCounter++;
                }
                //We count words
                for (int i = 0; i < result.length(); i++)
                {
                    if (result.charAt(i) == ' ') {
                        wordsCounter++;
                    }
                }

                //We count syllables and polysyllables
                syllables = result;
                //Remove all and leave only words
                syllables = syllables.replaceAll("[\\.,!?-]","");
                syllables = syllables.replaceAll("  "," ");
                //Split all text into words
                String[] wordsArray = syllables.split(" ");
                for (String str : wordsArray) {
                    int nSyllables = getSyllables(str);
                    syllablesCounter +=nSyllables;
                    if (nSyllables >= 3) polysyllablesCounter++;
                }

                //We count chars
                result = result.replaceAll(" ","");
                charsCounter = result.length();


                System.out.println();
                System.out.println("Words: " + wordsCounter);
                System.out.println("Sentences: " + sentencesCounter);
                System.out.println("Characters: " + charsCounter);
                System.out.println("Syllables: " + syllablesCounter);
                System.out.println("Polysyllables: " + polysyllablesCounter);
                System.out.print("Enter the score you want to calculate (ARI, FK, SMOG, CL, all): ");
                switch (inputScanner.next()) {
                    case "ARI":
                        getARI(charsCounter, wordsCounter, sentencesCounter);
                        break;
                    case "FK":
                        getFK();
                        break;
                    case "SMOG":
                        getSMOG();
                        break;
                    case "CL":
                        getCL();
                        break;
                    case "all":
                        getARI(charsCounter, wordsCounter, sentencesCounter);
                        getFK();
                        getSMOG();
                        getCL();
                        break;
                }


            } catch (IOException ex) {
                System.out.println("Unknown file");
            }
        }
    }
}
