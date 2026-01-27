package Exercise3;

public class Banker {

    private static Loan firstLoan;
	private static Loan secondLoan;
    private static Loan thirdLoan;

    public static void banking()
	{
        firstLoan = new Loan();
        firstLoan.setAnnualInterestRate(5.5);
        firstLoan.setNumberOfYears(10);
        firstLoan.setLoanAmount(10000);

        secondLoan = new Loan();
        secondLoan.setAnnualInterestRate(2.0);
        secondLoan.setNumberOfYears(5);
        secondLoan.setLoanAmount(80);
        
        thirdLoan = new Loan();
        thirdLoan.setAnnualInterestRate(10.5);
        thirdLoan.setNumberOfYears(20);
        thirdLoan.setLoanAmount(250);

        printLoanInfo(firstLoan);
        printLoanInfo(secondLoan);
        printLoanInfo(thirdLoan);
        System.out.println("");
    }
	
	public static void printLoanInfo(Loan lo)
	{
        System.out.println("Interest rate:" + lo.getAnnualInterestRate());
        System.out.println("Loan years:" + lo.getNumberOfYears());
        System.out.println("Loan amount:" + lo.getLoanAmount());
        System.out.println("Monthly payment:" + String.format("%.3f", (lo.getMonthlyPayment())));
        System.out.println("Total payment:" + String.format("%.3f", lo.getTotalPayment()));
	}
}
