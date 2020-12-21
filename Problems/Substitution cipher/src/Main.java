import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String keys = scanner.nextLine();
        String values = scanner.nextLine();
        String lineToEncode = scanner.nextLine();
        String lineToDecode = scanner.nextLine();

        Map<Character, Character> encodeMap = new HashMap<>();
        Map<Character, Character> decodeMap = new HashMap<>();

        for (int i = 0; i < keys.length(); i++) {
            encodeMap.put(keys.charAt(i), values.charAt(i));
            decodeMap.put(values.charAt(i), keys.charAt(i));
        }

        StringBuilder encodedLine = new StringBuilder();
        for (int i = 0; i < lineToEncode.length(); i++) {
            encodedLine.append(encodeMap.get(lineToEncode.charAt(i)));
        }

        StringBuilder decodedLine = new StringBuilder();
        for (int i = 0; i < lineToDecode.length(); i++) {
            decodedLine.append(decodeMap.get(lineToDecode.charAt(i)));
        }

        System.out.println(encodedLine);
        System.out.println(decodedLine);

    }
}