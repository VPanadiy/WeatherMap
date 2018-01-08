package dream.development.models;

import dream.development.models.enums.PrintFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Class for serializing information of weather. Consider information from class {@link WeatherInTime}.
 */
public class WeatherState implements Serializable {

    private String country_name;
    private String type;
    private String locale;
    private String timezone;
    private float altitude;
    private float latitude;
    private float longitude;
    private String geobase;
    private int geobaseid;
    private String credit;
    private LocalDateTime lastUpdate;
    private float calcTime;
    private LocalDateTime nextUpdate;
    private LocalDateTime sunRise;
    private LocalDateTime sunSet;
    private List<WeatherInTime> weatherInTimeList;

    public WeatherState() {
    }

    public WeatherState(String country_name, String type, String locale, String timezone, float altitude, float latitude, float longitude, String geobase, int geobaseid, String credit, LocalDateTime lastUpdate, float calcTime, LocalDateTime nextUpdate, LocalDateTime sunRise, LocalDateTime sunSet, List<WeatherInTime> weatherInTimeList) {
        this.country_name = country_name;
        this.type = type;
        this.locale = locale;
        this.timezone = timezone;
        this.altitude = altitude;
        this.latitude = latitude;
        this.longitude = longitude;
        this.geobase = geobase;
        this.geobaseid = geobaseid;
        this.credit = credit;
        this.lastUpdate = lastUpdate;
        this.calcTime = calcTime;
        this.nextUpdate = nextUpdate;
        this.sunRise = sunRise;
        this.sunSet = sunSet;
        this.weatherInTimeList = weatherInTimeList;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public float getAltitude() {
        return altitude;
    }

    public void setAltitude(float altitude) {
        this.altitude = altitude;
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

    public String getGeobase() {
        return geobase;
    }

    public void setGeobase(String geobase) {
        this.geobase = geobase;
    }

    public int getGeobaseid() {
        return geobaseid;
    }

    public void setGeobaseid(int geobaseid) {
        this.geobaseid = geobaseid;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public float getCalcTime() {
        return calcTime;
    }

    public void setCalcTime(float calcTime) {
        this.calcTime = calcTime;
    }

    public LocalDateTime getNextUpdate() {
        return nextUpdate;
    }

    public void setNextUpdate(LocalDateTime nextUpdate) {
        this.nextUpdate = nextUpdate;
    }

    public LocalDateTime getSunRise() {
        return sunRise;
    }

    public void setSunRise(LocalDateTime sunRise) {
        this.sunRise = sunRise;
    }

    public LocalDateTime getSunSet() {
        return sunSet;
    }

    public void setSunSet(LocalDateTime sunSet) {
        this.sunSet = sunSet;
    }

    public List<WeatherInTime> getWeatherInTimeList() {
        return weatherInTimeList;
    }

    public void setWeatherInTimeList(List<WeatherInTime> weatherInTimeList) {
        this.weatherInTimeList = weatherInTimeList;
    }

    /**
     * Switcher between printing format. By default print FULL format
     *
     * @param format {@link PrintFormat}
     * @return String
     */
    public String printWeatherInformation(PrintFormat format) {
        switch (format) {
            case SHORT: {
                return printShortWeatherInformation();
            }
            case FULL: {
                return printFullWeatherInformation();
            }
            default: {
                return printFullWeatherInformation();
            }
        }
    }

    /**
     * Method for getting information of weather in full form. Consider information from class {@link WeatherInTime}.
     *
     * @return String
     */
    private String printFullWeatherInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append("WeatherState {\n");
        builder.append("    Location {\n");
        builder.append("        Name = ").append(getCountry_name()).append("\n");
        builder.append("        Type = ").append(getType()).append("\n");
        builder.append("        Country = ").append(getLocale()).append("\n");
        builder.append("        Timezone = ").append(getTimezone()).append("\n");
        builder.append("        Location {\n");
        builder.append("            Altitude = ").append(getAltitude()).append("\n");
        builder.append("            Latitude = ").append(getLatitude()).append("\n");
        builder.append("            Longitude = ").append(getLongitude()).append("\n");
        builder.append("            GeoBase = ").append(getGeobase()).append("\n");
        builder.append("            GeoBaseId = ").append(getGeobaseid()).append("\n");
        builder.append("        }\n");
        builder.append("    }\n");
        builder.append("    Credit = ").append(getCredit()).append("\n");
        builder.append("    Meta {\n");
        builder.append("        LastUpdate = ").append(getLastUpdate()).append("\n");
        builder.append("        LastCalcTime = ").append(getCalcTime()).append("\n");
        builder.append("        NextUpdate = ").append(getNextUpdate()).append("\n");
        builder.append("    }\n");
        builder.append("    Sun {\n");
        builder.append("        Rise = ").append(getSunRise()).append("\n");
        builder.append("        Set = ").append(getSunSet()).append("\n");
        builder.append("    }\n");
        builder.append("    Forecast {\n");
        weatherInTimeList.forEach(time -> builder.append(time.getFullInformation()));
        builder.append("        }\n");
        builder.append("    }\n");
        builder.append("}\n");
        return builder.toString();
    }

    /**
     * Method for getting information of weather in short form. Consider information from class {@link WeatherInTime}.
     *
     * @return String
     */
    private String printShortWeatherInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append("Forecast {\n");
        weatherInTimeList.forEach(time -> builder.append(time.getShortInformation()));
        builder.append("    }\n");
        builder.append("}\n");
        return builder.toString();
    }

}
