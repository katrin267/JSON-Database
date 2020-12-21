import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Integer> newspaperMap = new HashMap<>();

        String[] newspaperWords = scanner.nextLine().split(" ");
        for (String word : newspaperWords) {
            if (!newspaperMap.containsKey(word)) {
                newspaperMap.put(word, 1);
            } else {
                int newValue = newspaperMap.get(word) + 1;
                newspaperMap.replace(word, newValue);
            }
        }

        String[] letterWords = scanner.nextLine().split(" ");

        for (String word : letterWords) {
            if (newspaperMap.containsKey(word)) {
                if (newspaperMap.get(word) == 1) {
                    newspaperMap.remove(word);
                } else {
                    int newValue = newspaperMap.get(word) - 1;
                    newspaperMap.replace(word, newValue);
                }
            } else {
                System.out.println("You are busted");
                return;
            }
        }

        System.out.println("You get money");
    }
}