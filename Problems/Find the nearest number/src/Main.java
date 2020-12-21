import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> numList = new ArrayList<>();
        ArrayList<Integer> closeNumList = new ArrayList<>();

        while (scanner.hasNext()) {
            numList.add(scanner.nextInt());
        }

        int numToCompare = numList.get(numList.size() - 1);
        numList.remove(numList.size() - 1);

        int distance = Integer.MAX_VALUE;

        for (Integer num : numList) {
            int newDistance = Math.abs(numToCompare - num);
            if (newDistance < distance) {
                closeNumList.clear();
                closeNumList.add(num);
                distance = newDistance;
            } else if (newDistance == distance) {
                closeNumList.add(num);
            }
        }

        Collections.sort(closeNumList);
        for (var num : closeNumList) {
            System.out.print(num + " ");
        }
    }
}