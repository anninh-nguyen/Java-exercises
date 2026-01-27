package Exercise2;

public class Stock {
    private String symbol;
    private String name;
    private double previousPrice;
    private double currentPrice;

    private static int numberOfStocks = 0;

    public Stock(String Symbol, String Name, double PreviousPrice, double CurrentPrice)
    {
        symbol = Symbol;
        name = Name;
        previousPrice = PreviousPrice;
        currentPrice = CurrentPrice;
        numberOfStocks++;
    }
    
    public double getChange()
    {
        return Math.abs(currentPrice - previousPrice);
    }

    public int getNoS()
    {
        return numberOfStocks;
    }
}
