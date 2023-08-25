import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;


public class Bankomat {

    //Метод поиска доступности разложения, систематизации и отправки пользователю.
    // int sum - сумма, денег, которую клиент хочет снять в банке.
    // int [] banknotes - масссив номиналов банкнот, которые есть в наличии в данном банкомате.
    // Stack<Integer> idStack - стек для хранения индексов номиналов
    // Stack<Integer> sumStack - cтек для хранения оставшихся сумм
    //  Stack<Stack<Integer>> possibleOptionStack - Стек для хранения текущей комбинации номиналов
    public int[][] getPossibleOptions(int sum, int[] banknotes) {
        Arrays.sort(banknotes);
        List<int[]> options = new ArrayList<>();
        Stack<Integer> possibleOption = new Stack<>();

        Stack<Integer> idStack = new Stack<>();
        idStack.push(banknotes.length - 1);

        Stack<Integer> sumStack = new Stack<>();
        sumStack.push(sum);

        Stack<Stack<Integer>> possibleOptionStack = new Stack<>();
        possibleOptionStack.push(possibleOption);

        //Цикл, отвечающий за нахождение и систематизацию всех возможных комбинаций номиналов.
        while (!idStack.isEmpty()) {
            int startId = idStack.pop();
            int remainingSum = sumStack.pop();
            Stack<Integer> current = possibleOptionStack.pop();

            if (remainingSum == 0) {
                int[] option = new int[current.size()];
                for (int i = 0; i < current.size(); i++) {
                    option[i] = current.get(i);
                }
                options.add(option);
            } else {
                for (int i = startId; i >= 0; i--) {
                    if (remainingSum >= banknotes[i]) {
                        Stack<Integer> newOption = new Stack<>();
                        newOption.addAll(current);
                        newOption.push(banknotes[i]);

                        idStack.push(i);
                        sumStack.push(remainingSum - banknotes[i]);
                        possibleOptionStack.push(newOption);
                    }
                }
            }
        }

        return options.toArray(new int[0][]);
    }
}