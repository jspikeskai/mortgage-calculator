import module java.base;

public class MortgageCalculatorApp {
    void main() {
        final String QUIT_KEYWORD = ColoredText.color(ColoredText.RED, "quit");
        final String WELCOME_MESSAGE = ColoredText.color(ColoredText.BLUE, "Welcome to Mortgage Calculator!");
        final String INFO_MESSAGE = WELCOME_MESSAGE + "\nTo exit the program type '" + QUIT_KEYWORD + "'\n";
        final String QUIT_MESSAGE = ColoredText.color(ColoredText.CYAN, "Thanks for using Mortgage Calculator!");
        final String INVALID_MESSAGE = ColoredText.color(ColoredText.RED, "Invalid input: ");

        boolean isQuitting = false;

        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                IO.println(INFO_MESSAGE);

                int principal = MortgageParameters.MINIMUM_PRINCIPAL;
                while (true) {
                    IO.print("Principal: ");
                    Number input = ConsoleReader.readInput(scanner);

                    if (input.equals(-1)) {
                        isQuitting = true;
                        break;
                    }

                    principal = input.intValue();

                    boolean validData = ConsoleReader.validateData(principal, MortgageParameters.MINIMUM_PRINCIPAL, MortgageParameters.MAXIMUM_PRINCIPAL);
                    if (validData) {
                        break;
                    } else {
                        String minPrincipal = ColoredText.color(ColoredText.YELLOW, Short.toString(MortgageParameters.MINIMUM_PRINCIPAL));
                        String maxPrincipal = ColoredText.color(ColoredText.YELLOW, Integer.toString(MortgageParameters.MAXIMUM_PRINCIPAL));

                        IO.println(INVALID_MESSAGE + "Number must be between " + minPrincipal + " and " + maxPrincipal);
                    }
                }

                if (isQuitting) {
                    break;
                }

                final int MAXIMUM_DOWN_PAYMENT = principal - 1_000;

                int downPayment = MortgageParameters.MINIMUM_DOWN_PAYMENT;
                while (true) {
                    IO.print("Down Payment: ");
                    Number input = ConsoleReader.readInput(scanner);

                    if (input.equals(-1)) {
                        isQuitting = true;
                        break;
                    }

                    downPayment = input.intValue();

                    boolean validData = ConsoleReader.validateData(downPayment, MortgageParameters.MINIMUM_DOWN_PAYMENT, MAXIMUM_DOWN_PAYMENT);
                    if (validData) {
                        break;
                    } else {
                        String minDownPayment = ColoredText.color(ColoredText.YELLOW, Byte.toString(MortgageParameters.MINIMUM_DOWN_PAYMENT));
                        String maxDownPayment = ColoredText.color(ColoredText.YELLOW, Integer.toString(MAXIMUM_DOWN_PAYMENT));

                        IO.println(INVALID_MESSAGE + "Number must be between " + minDownPayment + " and " + maxDownPayment);
                    }
                }

                if (isQuitting) {
                    break;
                }

                principal -= downPayment;

                double annualInterestRate = MortgageParameters.MINIMUM_INTEREST_RATE;
                double monthlyInterestRate = annualInterestRate / MortgageParameters.PERCENT / MortgageParameters.MONTHS_IN_YEAR;

                while (true) {
                    IO.print("Interest Rate: ");
                    Number input = ConsoleReader.readInput(scanner);

                    if (input.equals(-1)) {
                        isQuitting = true;
                        break;
                    }

                    annualInterestRate = input.doubleValue();

                    boolean validData = ConsoleReader.validateData(annualInterestRate, MortgageParameters.MINIMUM_INTEREST_RATE, MortgageParameters.MAXIMUM_INTEREST_RATE);
                    if (validData) {
                        monthlyInterestRate = annualInterestRate / MortgageParameters.PERCENT / MortgageParameters.MONTHS_IN_YEAR;
                        break;
                    } else {
                        String minInterestRate = ColoredText.color(ColoredText.YELLOW, Byte.toString(MortgageParameters.MINIMUM_INTEREST_RATE));
                        String maxInterestRate = ColoredText.color(ColoredText.YELLOW, Byte.toString(MortgageParameters.MAXIMUM_INTEREST_RATE));

                        IO.println(INVALID_MESSAGE + "Number must be between " + minInterestRate + " and " + maxInterestRate);
                    }
                }

                int period = MortgageParameters.MINIMUM_PERIOD;
                while (true) {
                    IO.print("Period (Years): ");
                    Number input = ConsoleReader.readInput(scanner);

                    if (input.equals(-1)) {
                        isQuitting = true;
                        break;
                    }

                    period = input.intValue();

                    boolean validData = ConsoleReader.validateData(period, MortgageParameters.MINIMUM_PERIOD, MortgageParameters.MAXIMUM_PERIOD);
                    if (validData) {
                        period *= MortgageParameters.MONTHS_IN_YEAR;
                        break;
                    } else {
                        String minPeriod = ColoredText.color(ColoredText.YELLOW, Byte.toString(MortgageParameters.MINIMUM_PERIOD));
                        String maxPeriod = ColoredText.color(ColoredText.YELLOW, Byte.toString(MortgageParameters.MAXIMUM_PERIOD));

                        IO.println(INVALID_MESSAGE + "Number must be between " + minPeriod + " and " + maxPeriod);
                    }
                }

                if (isQuitting) {
                    break;
                }

                double mortgage = MortgageCalculatorService.calculateMortgage(principal, monthlyInterestRate, period);

                NumberFormat currency = NumberFormat.getCurrencyInstance();
                String formattedMortgage = currency.format(mortgage);

                String mortgageMessage = ColoredText.color(ColoredText.BLUE, "Monthly Mortgage: ");
                String mortgageAmount = ColoredText.color(ColoredText.GREEN, formattedMortgage);

                IO.println(mortgageMessage + mortgageAmount);
                IO.println();
            }
            IO.println(QUIT_MESSAGE);
        }
    }
}
