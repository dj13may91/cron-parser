package example.cron;

import java.util.ArrayList;
import java.util.List;

public class ParsedField {
    private final List<Integer> values;
    private final FieldType fieldType;

    public ParsedField(FieldType fieldType, List<Integer> values) {
        this.values = new ArrayList<>(values);
        this.fieldType = fieldType;
    }

    public List<Integer> getValues() {
        return values;
    }

    public FieldType getFieldType() {
        return fieldType;
    }
}
