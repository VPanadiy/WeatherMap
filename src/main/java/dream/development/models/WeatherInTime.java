package dream.development.models;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Class for serializing information of weather by time
 */
public class WeatherInTime implements Serializable {

    private LocalDateTime weatherTimeFrom;
    private LocalDateTime weatherTimeTo;
    private int symbolNumber;
    private String symbolName;
    private String symbolVar;
    private String precipitationUnit;
    private float precipitationValue;
    private String precipitationType;
    private float windDirectionDeg;
    private String windDirectionCode;
    private String windDirectionName;
    private float windSpeedMps;
    private String windSpeedName;
    private String temperatureUnit;
    private float temperatureValue;
    private float temperatureMin;
    private float temperatureMax;
    private String pressureUnit;
    private float pressureValue;
    private float humidityValue;
    private String humidityUnit;
    private String cloudsValue;
    private int cloudsAll;
    private String cloudsUnit;

    public WeatherInTime() {
    }

    public WeatherInTime(LocalDateTime weatherTimeFrom, LocalDateTime weatherTimeTo, int symbolNumber, String symbolName, String symbolVar, String precipitationUnit, float precipitationValue, String precipitationType, float windDirectionDeg, String windDirectionCode, String windDirectionName, float windSpeedMps, String windSpeedName, String temperatureUnit, float temperatureValue, float temperatureMin, float temperatureMax, String pressureUnit, float pressureValue, float humidityValue, String humidityUnit, String cloudsValue, int cloudsAll, String cloudsUnit) {
        this.weatherTimeFrom = weatherTimeFrom;
        this.weatherTimeTo = weatherTimeTo;
        this.symbolNumber = symbolNumber;
        this.symbolName = symbolName;
        this.symbolVar = symbolVar;
        this.precipitationUnit = precipitationUnit;
        this.precipitationValue = precipitationValue;
        this.precipitationType = precipitationType;
        this.windDirectionDeg = windDirectionDeg;
        this.windDirectionCode = windDirectionCode;
        this.windDirectionName = windDirectionName;
        this.windSpeedMps = windSpeedMps;
        this.windSpeedName = windSpeedName;
        this.temperatureUnit = temperatureUnit;
        this.temperatureValue = temperatureValue;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.pressureUnit = pressureUnit;
        this.pressureValue = pressureValue;
        this.humidityValue = humidityValue;
        this.humidityUnit = humidityUnit;
        this.cloudsValue = cloudsValue;
        this.cloudsAll = cloudsAll;
        this.cloudsUnit = cloudsUnit;
    }

    public LocalDateTime getWeatherTimeFrom() {
        return weatherTimeFrom;
    }

    public void setWeatherTimeFrom(LocalDateTime weatherTimeFrom) {
        this.weatherTimeFrom = weatherTimeFrom;
    }

    public LocalDateTime getWeatherTimeTo() {
        return weatherTimeTo;
    }

    public void setWeatherTimeTo(LocalDateTime weatherTimeTo) {
        this.weatherTimeTo = weatherTimeTo;
    }

    public int getSymbolNumber() {
        return symbolNumber;
    }

    public void setSymbolNumber(int symbolNumber) {
        this.symbolNumber = symbolNumber;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getSymbolVar() {
        return symbolVar;
    }

    public void setSymbolVar(String symbolVar) {
        this.symbolVar = symbolVar;
    }

    public String getPrecipitationUnit() {
        return precipitationUnit;
    }

    public void setPrecipitationUnit(String precipitationUnit) {
        this.precipitationUnit = precipitationUnit;
    }

    public float getPrecipitationValue() {
        return precipitationValue;
    }

    public void setPrecipitationValue(float precipitationValue) {
        this.precipitationValue = precipitationValue;
    }

    public String getPrecipitationType() {
        return precipitationType;
    }

    public void setPrecipitationType(String precipitationType) {
        this.precipitationType = precipitationType;
    }

    public float getWindDirectionDeg() {
        return windDirectionDeg;
    }

    public void setWindDirectionDeg(float windDirectionDeg) {
        this.windDirectionDeg = windDirectionDeg;
    }

    public String getWindDirectionCode() {
        return windDirectionCode;
    }

    public void setWindDirectionCode(String windDirectionCode) {
        this.windDirectionCode = windDirectionCode;
    }

    public String getWindDirectionName() {
        return windDirectionName;
    }

    public void setWindDirectionName(String windDirectionName) {
        this.windDirectionName = windDirectionName;
    }

    public float getWindSpeedMps() {
        return windSpeedMps;
    }

    public void setWindSpeedMps(float windSpeedMps) {
        this.windSpeedMps = windSpeedMps;
    }

    public String getWindSpeedName() {
        return windSpeedName;
    }

    public void setWindSpeedName(String windSpeedName) {
        this.windSpeedName = windSpeedName;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public void setTemperatureUnit(String temperatureUnit) {
        this.temperatureUnit = temperatureUnit;
    }

    public float getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(float temperatureValue) {
        this.temperatureValue = temperatureValue;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getPressureUnit() {
        return pressureUnit;
    }

    public void setPressureUnit(String pressureUnit) {
        this.pressureUnit = pressureUnit;
    }

    public float getPressureValue() {
        return pressureValue;
    }

    public void setPressureValue(float pressureValue) {
        this.pressureValue = pressureValue;
    }

    public float getHumidityValue() {
        return humidityValue;
    }

    public void setHumidityValue(float humidityValue) {
        this.humidityValue = humidityValue;
    }

    public String getHumidityUnit() {
        return humidityUnit;
    }

    public void setHumidityUnit(String humidityUnit) {
        this.humidityUnit = humidityUnit;
    }

    public String getCloudsValue() {
        return cloudsValue;
    }

    public void setCloudsValue(String cloudsValue) {
        this.cloudsValue = cloudsValue;
    }

    public int getCloudsAll() {
        return cloudsAll;
    }

    public void setCloudsAll(int cloudsAll) {
        this.cloudsAll = cloudsAll;
    }

    public String getCloudsUnit() {
        return cloudsUnit;
    }

    public void setCloudsUnit(String cloudsUnit) {
        this.cloudsUnit = cloudsUnit;
    }

    /**
     * Method for getting information of weather by time in full form
     *
     * @return String
     */
    public String getFullInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append("        Time {\n");
        builder.append("            From = ").append(getWeatherTimeFrom()).append("\n");
        builder.append("            To = ").append(getWeatherTimeTo()).append("\n");
        builder.append("                {\n");
        builder.append("                    Symbol {\n");
        builder.append("                        Number = ").append(getSymbolNumber()).append("\n");
        builder.append("                        Name = ").append(getSymbolName()).append("\n");
        builder.append("                        Var = ").append(getSymbolVar()).append("\n");
        builder.append("                    }\n");
        builder.append("                    Precipitation {\n");
        builder.append("                        Unit = ").append(getPrecipitationUnit()).append("\n");
        builder.append("                        Value = ").append(getPrecipitationValue()).append("\n");
        builder.append("                        Type = ").append(getPrecipitationType()).append("\n");
        builder.append("                    }\n");
        builder.append("                    WindDirection {\n");
        builder.append("                        Deg = ").append(getWindDirectionDeg()).append("\n");
        builder.append("                        Code = ").append(getWindDirectionCode()).append("\n");
        builder.append("                        Name = ").append(getWindDirectionName()).append("\n");
        builder.append("                    }\n");
        builder.append("                    WindSpeed {\n");
        builder.append("                        Mps = ").append(getWindSpeedMps()).append("\n");
        builder.append("                        Name = ").append(getWindSpeedName()).append("\n");
        builder.append("                    }\n");
        builder.append("                    Temperature {\n");
        builder.append("                        Unit = ").append(getTemperatureUnit()).append("\n");
        builder.append("                        Value = ").append(getTemperatureValue()).append("\n");
        builder.append("                        Min = ").append(getTemperatureMin()).append("\n");
        builder.append("                        Max = ").append(getTemperatureMax()).append("\n");
        builder.append("                    }\n");
        builder.append("                    Pressure {\n");
        builder.append("                        Unit = ").append(getPressureUnit()).append("\n");
        builder.append("                        Value = ").append(getPressureValue()).append("\n");
        builder.append("                    }\n");
        builder.append("                    Humidity {\n");
        builder.append("                        Value = ").append(getHumidityValue()).append("\n");
        builder.append("                        Unit = ").append(getHumidityUnit()).append("\n");
        builder.append("                    }\n");
        builder.append("                    Clouds {\n");
        builder.append("                        Value = ").append(getCloudsValue()).append("\n");
        builder.append("                        All = ").append(getCloudsAll()).append("\n");
        builder.append("                        Unit = ").append(getCloudsUnit()).append("\n");
        builder.append("                    }\n");
        builder.append("                }\n");
        builder.append("            }\n");
        return builder.toString();
    }

    /**
     * Method for getting information of weather by time in short form
     *
     * @return String
     */
    public String getShortInformation() {
        StringBuilder builder = new StringBuilder();
        builder.append("    Time {\n");
        builder.append("        From = ").append(getWeatherTimeFrom()).append("\n");
        builder.append("        To = ").append(getWeatherTimeTo()).append("\n");
        builder.append("            {\n");
        builder.append("                Temperature {\n");
        builder.append("                    Unit = ").append(getTemperatureUnit()).append("\n");
        builder.append("                    Value = ").append(getTemperatureValue()).append("\n");
        builder.append("                    Min = ").append(getTemperatureMin()).append("\n");
        builder.append("                    Max = ").append(getTemperatureMax()).append("\n");
        builder.append("                }\n");
        builder.append("                Humidity {\n");
        builder.append("                    Value = ").append(getHumidityValue()).append("\n");
        builder.append("                    Unit = ").append(getHumidityUnit()).append("\n");
        builder.append("                }\n");
        builder.append("                Pressure {\n");
        builder.append("                    Unit = ").append(getPressureUnit()).append("\n");
        builder.append("                    Value = ").append(getPressureValue()).append("\n");
        builder.append("                }\n");
        builder.append("                WindSpeed {\n");
        builder.append("                    Mps = ").append(getWindSpeedMps()).append("\n");
        builder.append("                    Name = ").append(getWindSpeedName()).append("\n");
        builder.append("                }\n");
        builder.append("                WindDirection {\n");
        builder.append("                    Deg = ").append(getWindDirectionDeg()).append("\n");
        builder.append("                    Code = ").append(getWindDirectionCode()).append("\n");
        builder.append("                    Name = ").append(getWindDirectionName()).append("\n");
        builder.append("                }\n");
        builder.append("            }\n");
        builder.append("        }\n");
        return builder.toString();
    }

}
