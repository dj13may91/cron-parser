package example.cron;

import java.util.ArrayList;
import java.util.List;

public class CronFieldParser {

    public ParsedField convertExpressionToField(String expression, FieldType fieldType) {
        if (expression.contains(",")) {
            return parseCommaSeparatedValues(expression, fieldType);
        } else if (expression.trim().equals("*")) {
            return parseStarExpression(fieldType);
        } else if (expression.contains("-")) {
            return parseRangeExpression(expression, fieldType);
        } else if (expression.contains("/")) {
            return parseStepExpression(expression, fieldType);
        } else {
            int value = Integer.parseInt(expression);
            return validateAndGetParsedField(fieldType, value);
        }
    }

    public ParsedField parseStepExpression(String expression, FieldType fieldType) {
        Field field = Field.getFieldWithRange(fieldType);
        String[] steps = expression.split("/");
        int start = steps[0].equals("*") ? field.min : Integer.parseInt(steps[0]);
        int jumps = Integer.parseInt(steps[1]);
        List<Integer> values = new ArrayList<>();
        for (int i = start; i < field.max; i = i + jumps)
            if (field.isInRange(i))
                values.add(i);
        return new ParsedField(fieldType, values);
    }

    public ParsedField parseRangeExpression(String expression, FieldType fieldType) {
        String[] range = expression.split("-");
        if (range.length != 2)
            throwOutOfBoundsException(expression, fieldType);
        int leftBound = Integer.parseInt(range[0]);
        int rightBound = Integer.parseInt(range[1]);
        Field field = Field.getFieldWithRange(fieldType);
        if (!field.isInRange(leftBound, rightBound))
            throwOutOfBoundsException(expression, fieldType);
        List<Integer> values = new ArrayList<>();
        for(int i=leftBound; i<= rightBound; i++) values.add(i);
        return new ParsedField(fieldType, values);
    }

    public void throwOutOfBoundsException(String expression, FieldType fieldType) {
        throw new RuntimeException("Invalid input bounds for expression " + expression + " for fieldType: " + fieldType);
    }

    public ParsedField parseStarExpression(FieldType fieldType) {
        Field field = Field.getFieldWithRange(fieldType);
        List<Integer> value = new ArrayList<>();
        for (int i = field.min; i <= field.max; i++) value.add(i);
        return new ParsedField(fieldType, value);
    }

    public ParsedField parseCommaSeparatedValues(String expression, FieldType fieldType) {
        List<Integer> value = new ArrayList<>();
        for (String s : expression.split(",")) value.add(Integer.parseInt(s.trim()));
        Field field = Field.getFieldWithRange(fieldType);
        for (int i : value)
            if (!field.isInRange(i)) throwOutOfBoundsException(String.valueOf(i), fieldType);
        return new ParsedField(fieldType, value);
    }

    public ParsedField validateAndGetParsedField(FieldType fieldType, int value) {
        Field field = Field.getFieldWithRange(fieldType);
        if (!field.isInRange(value))
            throwOutOfBoundsException(String.valueOf(value), fieldType);
        return new ParsedField(fieldType, List.of(value));
    }
}
