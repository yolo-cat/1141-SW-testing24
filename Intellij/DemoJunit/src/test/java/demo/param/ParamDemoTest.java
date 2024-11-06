package demo.param;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;

public class ParamDemoTest {

    @ParameterizedTest
    @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba", "abxa" })
    void palindromes(String candidate) {
        assertTrue(StringUtils.isPalindrome(candidate));
    }

    @ParameterizedTest
    @ValueSource(ints = { 1, 2, 3 })
    void testWithValueSource(int argument) {
        assertTrue(argument > 0 && argument < 4);
    }

    @ParameterizedTest
    @ValueSource(ints={2,3,5,7,11})
    void testPrime(int argument) {
        assertTrue(MathUtils.isPrime((argument)));
    }

    @ParameterizedTest
    @ValueSource(ints={1,4,6,9,51})
    void testNotPrime(int argument) {
        assertFalse(MathUtils.isPrime(argument));
    }

    // csv 是用 , 分開
    // 如果內容是有空格的字串，要用 '' 來框起來
    // 注意型態：第二欄位的資料是數字
    @ParameterizedTest
    @CsvSource({
            "apple,         1",
            "banana,        2",
            "'lemon, lime', 0xF1",
            "strawberry,    700_000"
    })
    void testWithCsvSource(String fruit, int rank) {
        assertNotNull(fruit);
        assertNotEquals(0, rank);
    }

    // 把資料放在 test/resources下的 two-columns.csv
    // 第一行是提示字，不會當成測試資料
    // CSV 是用 , 分開
    @ParameterizedTest
    @CsvFileSource(resources = "/two-columns.csv", numLinesToSkip = 1)
    void testWithCsvFileSourceFromClasspath(String country, int reference) {
        assertNotNull(country);
        assertNotEquals(0, reference);
    }

    // 分別會輸入 null, empty, 還有空格、等等資料進去測試
    @ParameterizedTest
    @NullSource
    @EmptySource
    @ValueSource(strings = { " ", "   ", "\t", "\n" })
    void nullEmptyAndBlankStrings(String text) {
        System.out.println(text);
        assertTrue(text == null || text.trim().isEmpty());
    }

    // @EnumSource provides a convenient way to use Enum constants.
    // ChronoUnit- 時間單位
    @ParameterizedTest
    @EnumSource(ChronoUnit.class)
    void testWithEnumSource(TemporalUnit unit) {
        System.out.println(unit);
        assertNotNull(unit);
    }

    // 同上，可以省略 EnumSource 的指定
    @ParameterizedTest
    @EnumSource
    void testWithEnumSourceWithAutoDetection(ChronoUnit unit) {
        System.out.println(unit);
        assertNotNull(unit);
    }

    @ParameterizedTest
    @EnumSource(names = { "DAYS", "HOURS" })
    void testWithEnumSourceInclude(ChronoUnit unit) {
        System.out.println(unit);
        assertTrue(EnumSet.of(ChronoUnit.DAYS, ChronoUnit.HOURS).contains(unit));
    }


}
