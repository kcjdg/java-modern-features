module providers {
    requires api;

    provides weather.WeatherReport with reporters.CityWeather, reporters.RegionWeather;
}