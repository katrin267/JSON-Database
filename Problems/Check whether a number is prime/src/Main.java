import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); 
        

        while (scanner.hasNext()) {
            int number = scanner.nextInt();
            executor.submit(new PrintIfPrimeTask(number));
        }
        executor.shutdown();
    }
}

class PrintIfPrimeTask implements Runnable {
    private final int number;

    public PrintIfPrimeTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        if (number == 1) {
            return;
        }
        for (int divisor = 2; divisor <= number / 2; divisor++) {
            if (number % divisor == 0) {
                return; // num is not a prime, no reason to continue checking
            }
        }
        System.out.println(number);
    }
}
