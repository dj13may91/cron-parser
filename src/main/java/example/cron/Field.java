package example.cron;

public class Field {
    private final FieldType fieldType;
    int min;
    int max;

    public Field(FieldType fieldType, int min, int max) {
        this.fieldType = fieldType;
        this.min = min;
        this.max = max;
    }

    public boolean isInRange(int value) {
        return value >= min && value <= max;
    }

    public boolean isInRange(int left, int right) {
        return isInRange(left) && isInRange(right);
    }

    public static Field getFieldWithRange(FieldType fieldType) {
        return switch (fieldType) {
            case MINUTE, HOUR -> new Field(fieldType, 0, 59);
            case DAY_OF_MONTH -> new Field(fieldType, 1, 31);
            case MONTH -> new Field(fieldType, 1, 12);
            case DAY_OF_WEEK -> new Field(fieldType, 0, 6);
        };
    }
}
