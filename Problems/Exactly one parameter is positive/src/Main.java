import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int num = 0;
        for (int i = 0; i < 3; i++) {
            if (scanner.nextInt() > 0) {
                num++;
            }
        }
        System.out.println(num == 1);
    }
}