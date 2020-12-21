import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Set<String> knownWords = new HashSet<>();
        Set<String> text = new HashSet<>();

        int numOfWords = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numOfWords; i++) {
            knownWords.add(scanner.nextLine().toLowerCase());
        }

        int linesOfText = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < linesOfText; i++) {
            String[] lineOfText = scanner.nextLine().toLowerCase().split(" ");
            text.addAll(Arrays.asList(lineOfText));
        }

        text.removeAll(knownWords);

        text.forEach(System.out::println);
    }


}