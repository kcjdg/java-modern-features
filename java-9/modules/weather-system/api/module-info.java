module api{
    exports weather;

    requires transitive utils;

    uses weather.WeatherReport;

}