package telran.homework20;

/**
 * Java. Homework №20.
 *
 * @author Yurii Kuzmenko java_39e
 * @version 23.01.2023 - 29.01.2023
 */

/*
public class Homework20_1 {
    int [] array = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4, 5, 6, 7, 8};
    int [] newArray;
    public int array4() {
        for (int i=0;) {
            if () {
                newArray[i] = array[i];
            }
        }
    };

}
*/

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Homework20_1 {
    public static final String ERROR_NOT_CONTAIN = "The array doesn't contain a number: ";
    /*
    * 1. Вернуть фрагмент массива после заданного значения, встречающегося в последним (если оно не одно)
     */
    public int[] selectPartArrayFromNumber(int[] array, int number) {

        //находим индекс последнего элемента == number
        //классический вариант
        int index = -1;
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == number) {
                index = i;
                break;
            }
        }

        //заданного числа нет, бросаем исключение
        if (index <0) {
            throw new RuntimeException(ERROR_NOT_CONTAIN + number);
        }

        //решение другим способом
        //используем stream
        index = IntStream.range(0, array.length)
                .map(i-> array.length - i - 1)
                .filter(i -> array[i] == number)
                .findFirst().orElseThrow(() -> new RuntimeException(ERROR_NOT_CONTAIN + number));

        // создаем результирующий массив
        int[] result = new int[array.length - index - 1];
        // еще вариант от Сергея
        // int[] result = Arrays.copyOfRange(array, index + 1, array.length);

        // заполняем массив
        System.arraycopy(array, index+1, result, 0, array.length - index - 1);

        return result;
    }

    /*
     * 2. Массив должен содержать:
     * - только значения numberOne и numberTwo, иначе false
     * - хотя бы одно numberOne и numberTwo, иначе false
     */
    public boolean checkArrayForNumbersClassic(int[] array, int numberOne, int numberTwo){
        int countOne = 0;
        int countTwo = 0;
        for (int i : array) {
            if (i == numberOne ) {
                countOne++;
            } else  if (i == numberTwo) {
                countTwo++;
            } else {
                return false;
            }
        }
        return countOne > 0 && countTwo > 0;
    }

    /*
     * Решение задачи #2 с использованием Collections
     */
    public boolean checkArrayForNumbersCollections(int[] array, int numberOne, int numberTwo) {
        List<Integer> arrayList = Arrays.stream(array).boxed().collect(Collectors.toList());
        Set<Integer> arraySet = new HashSet<>(arrayList);
        Set<Integer> numbersSet = new HashSet<>(Arrays.asList(numberOne, numberTwo));
        return arraySet.equals(numbersSet);
    }

    /*
     * Решение задачи #2 с использованием stream
     */
    public boolean checkArrayForNumbersStream(int[] array, int numberOne, int numberTwo) {
        boolean containsNotAllowNumbers = Arrays.stream(array)
                .filter(i -> (i != numberOne && i != numberTwo))
                .count() != 0;
        boolean containsNumberOne = Arrays.stream(array)
                .anyMatch(i -> i == numberOne);
        boolean containsNumberTwo = Arrays.stream(array)
                .anyMatch(i -> i == numberTwo);
        return  !containsNotAllowNumbers && containsNumberOne && containsNumberTwo;
    }
}