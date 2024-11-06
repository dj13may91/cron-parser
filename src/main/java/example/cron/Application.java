package example.cron;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        CronExpressionParser parser = new CronExpressionParser(input);
        LogFormatter.printParsedExpression(parser);
    }
}