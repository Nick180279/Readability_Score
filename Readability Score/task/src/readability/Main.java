package readability;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static int calculatedResult(double index) {
        int  result;
        switch ((int)Math.round(index + 0.5)) {
            case 1:
                result = 6;
                break;
            case 2:
                result = 7;
                break;
            case 3:
                result = 9;
                break;
            case 4:
                result = 10;
                break;
            case 5:
                result = 11;
                break;
            case 6:
                result = 12;
                break;
            case 7:
                result = 13;
                break;
            case 8:
                result = 14;
                break;
            case 9:
                result = 15;
                break;
            case 10:
                result = 16;
                break;
            case 11:
                result = 17;
                break;
            case 12:
                result = 18;
                break;
            case 13:
                result = 24;
                break;
            default:
                result = 25;
                break;
        }
        return result;
    }

    public static int getARI(int charsCounter, int wordsCounter, int sentencesCounter) {
        double index = 4.71 * (double)charsCounter / wordsCounter + 0.5 *(double)wordsCounter / sentencesCounter - 21.43;
        int result = calculatedResult(index);
        String showRes = result <= 24 ? String.valueOf(result) : "24+";
        System.out.printf("Automated Readability Index: %4.2f (about %s  year olds.) \n", index, showRes);
        return result;
    }

    public static int getFK(int syllablesCounter, int wordsCounter, int sentencesCounter) {
        double index = 0.39 * (double)wordsCounter / sentencesCounter + 11.8 * (double)syllablesCounter / wordsCounter - 15.59;
        int result = calculatedResult(index);
        String showRes = result <= 24 ? String.valueOf(result) : "24+";
        System.out.printf("Simple Measure of Gobbledygook: %4.2f (about %s  year olds.) \n", index, showRes);
        return result;
    }

    public static int getSMOG(int polysyllablesCounter, int sentencesCounter) {
        double index = 1.043 * Math.sqrt( 30 * (double)polysyllablesCounter / sentencesCounter) + 3.1291;
        int result = calculatedResult(index);
        String showRes = result <= 24 ? String.valueOf(result) : "24+";
        System.out.printf("Flesch–Kincaid readability tests: %4.2f (about %s  year olds.) \n", index, showRes);
        return result;
    }

    public static int getCL(int charsCounter, int wordsCounter, int sentencesCounter) {
        double index = 0.0588 * (100 * (double)charsCounter / wordsCounter) - 0.296 * (100 * (double)sentencesCounter / wordsCounter) - 15.8;
        int result = calculatedResult(index);
        String showRes = result <= 24 ? String.valueOf(result) : "24+";
        System.out.printf("Coleman–Liau index: %4.2f (about %s  year olds.) \n", index, showRes);
        return result;
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
        int indxARI;
        int indxFK;
        int indxSMOG;
        int indxCL;
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
                        indxARI = getARI(charsCounter, wordsCounter, sentencesCounter);
                        break;
                    case "FK":
                        indxFK = getFK(syllablesCounter, wordsCounter, sentencesCounter);
                        break;
                    case "SMOG":
                        indxSMOG = getSMOG(polysyllablesCounter, sentencesCounter);
                        break;
                    case "CL":
                        indxCL = getCL(charsCounter, wordsCounter, sentencesCounter);
                        break;
                    case "all":
                        indxARI = getARI(charsCounter, wordsCounter, sentencesCounter);
                        indxFK = getFK(syllablesCounter, wordsCounter, sentencesCounter);
                        indxSMOG = getSMOG(polysyllablesCounter, sentencesCounter);
                        indxCL = getCL(charsCounter, wordsCounter, sentencesCounter);
                        System.out.printf("This text should be understood in average by %4.2f (year olds.) \n", (double)(indxARI + indxFK + indxCL + indxSMOG) / 4);
                        break;
                }


            } catch (IOException ex) {
                System.out.println("Unknown file");
            }
        }
    }
}
