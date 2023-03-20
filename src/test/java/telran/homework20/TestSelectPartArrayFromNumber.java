package telran.homework20;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static telran.homework20.Homework20_1.ERROR_NOT_CONTAIN;

public class TestSelectPartArrayFromNumber {
    Homework20_1 hw;

    @BeforeEach
    public void init() { hw = new Homework20_1(); }

    static int[][] sourceArrays = {
            {1, 2, 3, 4, 5, 6, 7},
            {1, 2, 3, 4},
            {4, 3, 2, 1}
    };
    static int[][] expectedArray = {
            {5, 6, 7},
            {},
            {3, 2, 1}
    };
    int[] badArray = {1, 2, 3};
    static int number = 4;

    public static Stream<Arguments> dataForTestSelectPartArray() {
        List<Arguments> out = new ArrayList<>();
        for (int i = 0; i < sourceArrays.length; i++) {
            out.add(Arguments.arguments(sourceArrays[i], number, expectedArray[i]));
        }
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForTestSelectPartArray")
    public void testSelectPartArrayFromNumber(int[] source, int number, int[] expected) {
        int[] result = hw.selectPartArrayFromNumber(source, number);
        Assertions.assertArrayEquals(result, expected);
    }

    @Test
    public void testSelectPartArrayFromNumberFail() {
        RuntimeException ex = Assertions.assertThrows(RuntimeException.class, () -> hw.selectPartArrayFromNumber(badArray, number));
        Assertions.assertEquals(ERROR_NOT_CONTAIN + String.valueOf(number), ex.getMessage());
    }
}
