package telran;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CalcTest {
    private Calculator calc;

    @BeforeEach
    public void init() {
        calc = new Calculator();
    }

    /*
    @CsvSource({
            "2, 2, 4",
            "-2, -2, -4",
            "-2, 1, -1",
            "5, -3, 2"
    })
    */

    public static Stream<Arguments> dataForTestAdd(){
        List<Arguments> out = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int a = (int) (Math.random()*1000);
            int b = (int) (Math.random()*1000);
            int c = a+b;
            out.add(Arguments.arguments(a,b,c));
        }
        return out.stream();
    }

    @ParameterizedTest
    @MethodSource("dataForTestAdd")
    public void massTestAdd(int a, int b, int result){
        Assertions.assertEquals(result, calc.add(a, b));
    }

    /*
    @Test
    @Disabled
    public void testAdd() {
        Calculator calc = new Calculator();
        Assertions.assertEquals(4, calc.add(2, 2));
    }

    public void testAdd1() {
        Calculator calc = new Calculator();
        Assertions.assertEquals(-4, calc.add(-2, -2));
    }

    public void testAdd2() {
        Calculator calc = new Calculator();
        Assertions.assertEquals(-1, calc.add(-2, 1));
    }
*/

    @Test
    public void testSub() {
       // Calculator calc = new Calculator();
        Assertions.assertEquals(0, calc.sub(2, 2));
    }

    @Test
    public void testMul() {
       // Calculator calc = new Calculator();
        Assertions.assertEquals(9, calc.mul(3, 3));
    }

    @Test
    public void testDiv() {
       // Calculator calc = new Calculator();
        Assertions.assertEquals(2, calc.div(10, 5));
    }

}
