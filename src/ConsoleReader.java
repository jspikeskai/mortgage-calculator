import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

public class ConsoleReader {
    public static boolean validateData(int data, int min, int max) {
        return (data >= min && data <= max);
    }

    public static boolean validateData(double data, int min, int max) {
        return (data >= min && data <= max);
    }

    public static Number readInput(Scanner scanner) {
        final String QUIT_STRING = "quit";

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase(QUIT_STRING)) {
                return -1;
            } else {
                try {
                    return NumberFormat.getInstance().parse(input);
                } catch (NumberFormatException | ParseException e) {
                    String invalidMessage = ColoredText.color(ColoredText.RED, "Invalid Input: ");
                    System.out.println(invalidMessage + "Must enter a number value");
                }
            }
        }
    }
}
