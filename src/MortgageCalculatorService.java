public class MortgageCalculatorService {
    public static double calculateMortgage(int principal, double monthlyInterestRate, int period) {
        double partialFormula = Math.pow(1.0 + monthlyInterestRate, period);
        return (principal * (monthlyInterestRate * partialFormula) / (partialFormula - 1));
    }

    public static double calculateTotalPayment(double monthlyMortgage, int period) {
        return (monthlyMortgage * period);
    }

    public static double calculateTotalInterest(double totalPayment, int principal) {
        return (totalPayment - principal);
    }
}
