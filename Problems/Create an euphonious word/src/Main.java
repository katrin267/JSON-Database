import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[] chars = scanner.nextLine().toCharArray();

        int numVowels = 0;
        int numConsonants = 0;
        int lettersToInsert = 0;

        for (char letter : chars) {
            switch (letter) {
                case 'a':
                case 'e':
                case 'i':
                case 'o':
                case 'u':
                case 'y':
                    numVowels++;
                    numConsonants = 0;
                    if (numVowels == 3) {
                        numVowels = 1;
                        lettersToInsert++;
                    }
                    break;
                default:
                    numConsonants++;
                    numVowels = 0;
                    if (numConsonants == 3) {
                        numConsonants = 1;
                        lettersToInsert++;
                    }
                    break;
            }
        }
        System.out.println(lettersToInsert);
    }
}