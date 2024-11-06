package example.cron;

import java.util.stream.Collectors;

public class LogFormatter {

    public static void printParsedExpression(CronExpressionParser cronExpressionParser) {
        print(cronExpressionParser, FieldType.MINUTE);
        print(cronExpressionParser, FieldType.HOUR);
        print(cronExpressionParser, FieldType.DAY_OF_MONTH);
        print(cronExpressionParser, FieldType.MONTH);
        print(cronExpressionParser, FieldType.DAY_OF_WEEK);
        System.out.printf("%-14s %s%n", "command", cronExpressionParser.getCommandExecutor().command);
    }

    private static void print(CronExpressionParser parser, FieldType fieldType) {
        String values = parser.getTypeFieldMap().get(fieldType).getValues().stream()
                .map(Object::toString)
                .collect(Collectors.joining(", "));
        System.out.printf("%-14s %s%n", fieldType.text, values);
    }
}
