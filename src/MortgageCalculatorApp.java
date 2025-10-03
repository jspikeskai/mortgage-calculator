import module java.base;

public class MortgageCalculatorApp {
    public static boolean isQuitting = false;

    static void main() {
        final String QUIT_KEYWORD = ColoredText.color(ColoredText.RED, "quit");
        final String WELCOME_MESSAGE = ColoredText.color(ColoredText.BLUE, "Welcome to Mortgage Calculator!");
        final String INFO_MESSAGE = WELCOME_MESSAGE + "\nTo exit the program type '" + QUIT_KEYWORD + "'\n";
        final String QUIT_MESSAGE = ColoredText.color(ColoredText.CYAN, "Thanks for using Mortgage Calculator!");

        try (Scanner scanner = new Scanner(System.in)) {
            while (!isQuitting) {
                IO.println(INFO_MESSAGE);

                int principal = MortgageParameters.MINIMUM_PRINCIPAL;
                while (!isQuitting) {
                    Number input = Console.readInput("Principal: ", scanner);

                    principal = input.intValue();

                    boolean validData = Console.validateData(principal, MortgageParameters.MINIMUM_PRINCIPAL, MortgageParameters.MAXIMUM_PRINCIPAL);
                    if (validData) {
                        break;
                    }
                }

                final int MAXIMUM_DOWN_PAYMENT = principal - 1_000;

                int downPayment;
                while (true) {
                    Number input = Console.readInput("Down Payment: ", scanner);

                    downPayment = input.intValue();

                    boolean validData = Console.validateData(downPayment, MortgageParameters.MINIMUM_DOWN_PAYMENT, MAXIMUM_DOWN_PAYMENT);
                    if (validData) {
                        break;
                    }
                }

                principal -= downPayment;

                double annualInterestRate;
                double monthlyInterestRate;

                while (true) {
                    Number input = Console.readInput("Interest Rate: ", scanner);

                    annualInterestRate = input.doubleValue();

                    boolean validData = Console.validateData(annualInterestRate, MortgageParameters.MINIMUM_INTEREST_RATE, MortgageParameters.MAXIMUM_INTEREST_RATE);
                    if (validData) {
                        monthlyInterestRate = annualInterestRate / MortgageParameters.PERCENT / MortgageParameters.MONTHS_IN_YEAR;
                        break;
                    }
                }

                int period;
                while (true) {
                    Number input = Console.readInput("Period (Years): ", scanner);

                    period = input.intValue();

                    boolean validData = Console.validateData(period, MortgageParameters.MINIMUM_PERIOD, MortgageParameters.MAXIMUM_PERIOD);
                    if (validData) {
                        period *= MortgageParameters.MONTHS_IN_YEAR;
                        break;
                    }
                }

                double mortgage = MortgageCalculatorService.calculateMortgage(principal, monthlyInterestRate, period);

                NumberFormat currency = NumberFormat.getCurrencyInstance();
                String formattedMortgage = currency.format(mortgage);

                String mortgageMessage = ColoredText.color(ColoredText.BLUE, "Monthly Mortgage: ");
                String mortgageAmount = ColoredText.color(ColoredText.GREEN, formattedMortgage);

                IO.println(mortgageMessage + mortgageAmount);
                IO.println();
            }
        } catch (Exception e) {
            IO.println(QUIT_MESSAGE);
        }
    }
}
