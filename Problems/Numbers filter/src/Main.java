import java.util.Scanner;

class NumbersFilter extends Thread {

    /* use it to read numbers from the standard input */
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void run() {
        int i = scanner.nextInt();
        while (i != 0) {
            if (i % 2 == 0) {
                System.out.println(i);
            }
            i = scanner.nextInt();
        }
    }
}