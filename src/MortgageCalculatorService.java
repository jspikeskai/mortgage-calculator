public class MortgageCalculatorService {
    public static double calculateMortgage(int principal, double monthlyInterestRate, int period) {
        double partialFormula = Math.pow(1.0 + monthlyInterestRate, period);
        return (principal * (monthlyInterestRate * partialFormula) / (partialFormula - 1));
    }
}
