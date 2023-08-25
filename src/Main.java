import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int sum = getInputInt(scanner, "Введите сумму (от 1 до 10000): ", 1, 10000);
        int count = getInputInt(scanner, "Введите количество номиналов банкнот: ", 1, 100);

        int[] banknotes = new int[count];
        System.out.println("Введите номиналы банкнот:");
        for (int i = 0; i < count; i++) {
            banknotes[i] = getInputInt(scanner, "Номинал " + (i + 1) + " (от 1 до 10000): ", 1, 10000);
        }

        Bankomat bankomat = new Bankomat();

        if (canbeDecomposed(sum, banknotes)) {
            int[][] options = bankomat.getPossibleOptions(sum, banknotes);

            System.out.println("Возможные варианты:");
            for (int i = options.length - 1; i >= 0; i--) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < options[i].length; j++) {
                    sb.append(options[i][j]);
                    if (j < options[i].length - 1) {
                        sb.append(",");
                    }
                }
                System.out.println(sb.toString());
            }
        } else {
            System.out.println("Невозможно разложить данную сумму по заданным номиналам банкнот.");
        }
    }
    //Метод для проверки на возможность разложения.
    public static boolean canbeDecomposed(int sum, int[] banknotes) {
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for (int banknote : banknotes) {
            for (int i = banknote; i <= sum; i++) {
                if (dp[i - banknote]) {
                    dp[i] = true;
                }
            }
        }

        return dp[sum];
    }
    // Метод проверки допустимых значений.
    private static int getInputInt(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();

                if (input < min || input > max) {
                    System.out.println("Некорректный ввод. Пожалуйста, введите число от " + min + " до " + max + ".");
                } else {
                    return input;
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректный ввод. Пожалуйста, введите целое число.");
                scanner.nextLine();
            }
        }
    }
}
