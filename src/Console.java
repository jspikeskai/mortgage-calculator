import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Scanner;

public class Console {
    public static final String INVALID_MESSAGE = ColoredText.color(ColoredText.RED, "Invalid input: ");
    public static final String QUIT_STRING = "quit";


    public static <T extends Number> boolean validateData(T data, int min, int max) {
        double value = data.doubleValue();
        if (value >= min && value <= max) {
            return true;
        } else {
            String minAmount = ColoredText.color(ColoredText.YELLOW, Integer.toString(min));
            String maxAmount = ColoredText.color(ColoredText.YELLOW, Integer.toString(max));

            IO.println(INVALID_MESSAGE + "Number must be between " + minAmount + " and " + maxAmount);
            return false;
        }
    }

    public static Number readInput(String prompt, Scanner scanner) throws Exception {
        IO.print(prompt);

        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase(QUIT_STRING)) {
                MortgageCalculatorApp.isQuitting = true;
                throw new Exception("Program has quit");
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
