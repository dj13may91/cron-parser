package example.cron;

import java.util.HashMap;
import java.util.Map;

public class CronExpressionParser {
    private CommandExecutor commandExecutor;
    private final Map<FieldType, ParsedField> typeFieldMap = new HashMap<>();
    private final CronFieldParser cronFieldParser = new CronFieldParser();

    public Map<FieldType, ParsedField> getTypeFieldMap() {
        return typeFieldMap;
    }

    public CommandExecutor getCommandExecutor() {
        return commandExecutor;
    }

    public CronExpressionParser(String expression) {
        if (expression.trim().split(" ").length < 6) {
            System.err.println("Invalid expression");
            return;
        }
        String[] input = expression.split(" ");
        convertExpressionToField(FieldType.MINUTE, input[0]);
        convertExpressionToField(FieldType.HOUR, input[1]);
        convertExpressionToField(FieldType.DAY_OF_MONTH, input[2]);
        convertExpressionToField(FieldType.DAY_OF_WEEK, input[3]);
        convertExpressionToField(FieldType.MONTH, input[4]);

        StringBuilder command = new StringBuilder();
        for (int i = 5; i < input.length; i++)// default last part is command
            command.append(input[i]).append(" ");
        commandExecutor = new CommandExecutor(command.toString());
    }

    private void convertExpressionToField(FieldType fieldType, String expression) {
        ParsedField parsedField = cronFieldParser.convertExpressionToField(expression, fieldType);
        typeFieldMap.put(fieldType, parsedField);
    }
}
