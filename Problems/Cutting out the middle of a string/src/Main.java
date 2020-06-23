import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        int inputLength = input.length();
        int delta = inputLength % 2 == 0 ? 1 : 0;
        input = input.substring(0, inputLength / 2 - delta) +
                input.substring(inputLength / 2 + 1, inputLength);
        System.out.println(input);
    }
}