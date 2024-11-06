package example.cron;

public enum FieldType {
    MINUTE("minute"),
    HOUR("hour"),
    DAY_OF_MONTH("day of month"),
    MONTH("month"),
    DAY_OF_WEEK("day of week");

    final String text;

    FieldType(String text) {
        this.text = text;
    }
}
