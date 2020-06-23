import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void inverseFlags(boolean [] flags) {
//        int middle = flags.length / 2;
//        boolean temp;
//        for (int i = 0; i < middle; i++){
//            temp = flags[i];
//            flags[i] = flags[flags.length - i -1];
//            flags[flags.length - i -1] = temp;
            for(int i = 0; i < flags.length;i++)
                flags[i] = !flags[i];
        }


    /* Do not change code below */
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final List<Boolean> booleans = Arrays
                .stream(scanner.nextLine().split("\\s+"))
                .map(Boolean::parseBoolean)
                .collect(Collectors.toList());
        final boolean[] flags = new boolean[booleans.size()];
        for (int i = 0; i < flags.length; i++) {
            flags[i] = booleans.get(i);
        }
        inverseFlags(flags);
        final String representation = Arrays.toString(flags)
                .replace("[", "")
                .replace("]", "")
                .replace(",", "");
        System.out.println(representation);
    }
}