package Exercise3;

public class WeatherStation {
    private double temperature;
    private double pressure;

    public WeatherStation(double temperature, double pressure)
    {
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public double getTemperature()
    {
        return this.temperature;
    }
    public void setTemperature (double temperature)
    {
        this.temperature = temperature;
    }
    public double getPressure()
    {
        return this.pressure;
    }
    public void setPressure(double pressure)
    {
        this.pressure = pressure;
    }
}
