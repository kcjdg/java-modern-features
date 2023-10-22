module dashboard {
    requires api;

    opens main to providers;

    uses weather.WeatherReport;
}