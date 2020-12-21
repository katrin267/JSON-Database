import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] nums = new int[scanner.nextInt()];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = scanner.nextInt();
        }

        int n = scanner.nextInt();
        int i = 0;

        for (var num : nums) {
            if (num == n) {
                i++;
            }
        }

        System.out.println(i);
    }
}