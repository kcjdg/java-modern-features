package reporters;

import weather.WeatherReport;

public class RegionWeather implements WeatherReport {
    private final String region;
    private final WeatherReport[] cityReports;

    public RegionWeather() {
        this.region = "Default Region";
        this.cityReports = new WeatherReport[0];
    }

    public RegionWeather(String region, WeatherReport... cityReports) {
        this.region = region;
        this.cityReports = cityReports;
    }

    @Override
    public String report() {
        StringBuilder report = new StringBuilder("Weather report for " + region + ":\n");
        for (WeatherReport cityReport : cityReports) {
            report.append(cityReport.report()).append("\n");
        }
        return report.toString();
    }
}
