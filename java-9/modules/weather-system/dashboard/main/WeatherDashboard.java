package main;

import weather.WeatherReport;

import java.util.ServiceLoader;

public class WeatherDashboard {
    public static void main(String[] args) {
        ServiceLoader<WeatherReport> loader = ServiceLoader.load(WeatherReport.class);

        loader.forEach(report -> {
            System.out.println(report.report());
        });
    }
}
