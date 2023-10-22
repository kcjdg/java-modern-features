package reporters;


import temperature.TemperatureConverter;
import weather.WeatherReport;

public class CityWeather implements WeatherReport {
    private final String city;
    private final double temperatureCelsius;

    public CityWeather() {
        this.city = "Default Region";
        this.temperatureCelsius = 0d;
    }

    public CityWeather(String city, double temperatureCelsius) {
        this.city = city;
        this.temperatureCelsius = temperatureCelsius;
    }

    @Override
    public String report() {
        double fahrenheit  = TemperatureConverter.celsiusToFahrenheit(temperatureCelsius);
        return String.format("Weather in %s: %.2f°C (%.2f°F)", city, temperatureCelsius, fahrenheit);
    }
}
