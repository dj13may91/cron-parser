package example.cron;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CronFieldParserTest {

    private final CronFieldParser parser = new CronFieldParser();

    @Test
    public void testParseAllMinutes() {
        ParsedField minuteField = parser.parseStarExpression(FieldType.MINUTE);
        assertEquals(60, minuteField.getValues().size());
    }

    @Test
    public void testParseStepExpression() {
        ParsedField minuteField = parser.parseStepExpression("*/15", FieldType.MINUTE);
        assertEquals(List.of(0, 15, 30, 45), minuteField.getValues());
    }

    @Test
    public void testParseRangeExpression() {
        ParsedField hourField = parser.parseRangeExpression("1-5", FieldType.HOUR);
        assertEquals(List.of(1, 2, 3, 4, 5), hourField.getValues());
    }

    @Test
    public void testParseListExpression() {
        ParsedField dayOfMonthField = parser.parseCommaSeparatedValues("1,15", FieldType.DAY_OF_MONTH);
        assertEquals(List.of(1, 15), dayOfMonthField.getValues());
    }

    @Test
    public void testParseInvalidFieldRange() {
        assertThrows(RuntimeException.class, () -> parser.convertExpressionToField("X123", FieldType.MINUTE));
    }
}
