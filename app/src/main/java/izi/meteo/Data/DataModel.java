package izi.meteo.Data;

/**
 * Created by Antoine on 04/02/2015.
 */
public class DataModel {

    private int id;
    private float latitude,longitude,temperature,humidity,pressure,temp_max,temp_min,windSpeed;
    private String name,country,weatherMain,weatherDescription;

    public DataModel(int id, float latitude, float longitude, float temperature, float humidity, float pressure, float temp_max, float temp_min, float windSpeed, String name, String country, String weatherMain, String weatherDescription) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp_max = temp_max;
        this.temp_min = temp_min;
        this.windSpeed = windSpeed;
        this.name = name;
        this.country = country;
        this.weatherMain = weatherMain;
        this.weatherDescription = weatherDescription;
    }

    public DataModel(String name) { this.name = name; }


    public DataModel(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setPressure(float pressure) {
        this.pressure = pressure;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(float windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWeatherMain() {
        return weatherMain;
    }

    public void setWeatherMain(String weatherMain) {
        this.weatherMain = weatherMain;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }
}