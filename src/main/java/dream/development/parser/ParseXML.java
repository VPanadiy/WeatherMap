package dream.development.parser;

import dream.development.models.WeatherInTime;
import dream.development.models.WeatherState;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Parse information of concrete URL from API in XML format. URL get from class {@link dream.development.models.UserWeatherMap}.
 * Save information in class {@link WeatherState} which considered information from class {@link WeatherInTime}
 * Implementation of default SAX parser.
 */
public class ParseXML extends DefaultHandler {

    private boolean bLocation = false;
    private boolean bName = false;
    private boolean bType = false;
    private boolean bCountry = false;
    private boolean bTimezone = false;
    private boolean bCredit = false;
    private boolean bMeta = false;
    private boolean bLastUpdate = false;
    private boolean bCalcTime = false;
    private boolean bNextUpdate = false;
    private boolean bSun = false;
    private boolean bForecast = false;
    private boolean bTime = false;
    private boolean bSymbol = false;
    private boolean bPrecipitation = false;
    private boolean bWindDirection = false;
    private boolean bWindSpeed = false;
    private boolean bTemperature = false;
    private boolean bPressure = false;
    private boolean bHumidity = false;
    private boolean bClouds = false;
    private WeatherState weatherState = new WeatherState();
    private WeatherInTime weatherInTime = new WeatherInTime();
    private List<WeatherInTime> weatherInTimeList = new ArrayList<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {

        if (qName.equalsIgnoreCase("location")) {
            setLocationState(attributes);
        } else if (qName.equalsIgnoreCase("name")) {
            bName = true;
        } else if (qName.equalsIgnoreCase("type")) {
            bType = true;
        } else if (qName.equalsIgnoreCase("country")) {
            bCountry = true;
            bType = false;
        } else if (qName.equalsIgnoreCase("timezone")) {
            bTimezone = true;
        } else if (qName.equalsIgnoreCase("credit")) {
            bCredit = true;
        } else if (qName.equalsIgnoreCase("meta")) {
            bMeta = true;
            bCredit = false;
        } else if (qName.equalsIgnoreCase("lastupdate")) {
            bLastUpdate = true;
        } else if (qName.equalsIgnoreCase("calctime")) {
            bCalcTime = true;
            bLastUpdate = false;
        } else if (qName.equalsIgnoreCase("nextupdate")) {
            bNextUpdate = true;
        } else if (qName.equalsIgnoreCase("sun")) {
            setSunState(attributes);
        } else if (qName.equalsIgnoreCase("forecast")) {
            bForecast = true;
        } else if (qName.equalsIgnoreCase("time")) {
            setWeatherTimeMeasuring(attributes);
        } else if (qName.equalsIgnoreCase("symbol")) {
            setSymbolState(attributes);
        } else if (qName.equalsIgnoreCase("precipitation")) {
            setPrecipitation(attributes);
        } else if (qName.equalsIgnoreCase("windDirection")) {
            setWindDirectionState(attributes);
        } else if (qName.equalsIgnoreCase("windSpeed")) {
            setWindSpeedState(attributes);
        } else if (qName.equalsIgnoreCase("temperature")) {
            setTemperatureState(attributes);
        } else if (qName.equalsIgnoreCase("pressure")) {
            setPressureState(attributes);
        } else if (qName.equalsIgnoreCase("humidity")) {
            setHumidityState(attributes);
        } else if (qName.equalsIgnoreCase("clouds")) {
            setCloudsState(attributes);
        }

    }

    private void setLocationState(Attributes attributes) {
        bLocation = true;
        bTimezone = false;

        String altitude = attributes.getValue("altitude");
        if (altitude != null) {
            weatherState.setAltitude(Float.parseFloat(altitude));
        }

        String latitude = attributes.getValue("latitude");
        if (altitude != null) {
            weatherState.setLatitude(Float.parseFloat(latitude));
        }

        String longitude = attributes.getValue("longitude");
        if (altitude != null) {
            weatherState.setLongitude(Float.parseFloat(longitude));
        }

        String geobase = attributes.getValue("geobase");
        if (altitude != null) {
            weatherState.setGeobase(geobase);
        }

        String geobaseid = attributes.getValue("geobaseid");
        if (altitude != null) {
            weatherState.setGeobaseid(Integer.parseInt(geobaseid));
        }
    }

    private void setSunState(Attributes attributes) {
        bSun = true;
        bNextUpdate = false;

        String rise = attributes.getValue("rise");
        if (rise != null) {
            weatherState.setSunRise(LocalDateTime.parse(rise));
        }

        String set = attributes.getValue("set");
        if (set != null) {
            weatherState.setSunSet(LocalDateTime.parse(set));
        }
    }

    private void setWeatherTimeMeasuring(Attributes attributes) {
        bTime = true;

        String from = attributes.getValue("from");
        if (from != null) {
            weatherInTime.setWeatherTimeFrom(LocalDateTime.parse(from));
        }

        String to = attributes.getValue("to");
        if (to != null) {
            weatherInTime.setWeatherTimeTo(LocalDateTime.parse(to));
        }
    }

    private void setSymbolState(Attributes attributes) {
        bSymbol = true;
        String number = attributes.getValue("number");
        if (number != null) {
            weatherInTime.setSymbolNumber(Integer.parseInt(number));
        }

        String name = attributes.getValue("name");
        if (name != null) {
            weatherInTime.setSymbolName(name);
        }

        String var = attributes.getValue("var");
        if (var != null) {
            weatherInTime.setSymbolVar(var);
        }
    }

    private void setPrecipitation(Attributes attributes) {
        bPrecipitation = true;
        String unit = attributes.getValue("unit");
        if (unit != null) {
            weatherInTime.setPrecipitationUnit(unit);
        }

        String value = attributes.getValue("value");
        if (value != null) {
            weatherInTime.setPrecipitationValue(Float.parseFloat(value));
        }

        String type = attributes.getValue("type");
        if (type != null) {
            weatherInTime.setPrecipitationType(type);
        }
    }

    private void setWindDirectionState(Attributes attributes) {
        bWindDirection = true;
        String deg = attributes.getValue("deg");
        if (deg != null) {
            weatherInTime.setWindDirectionDeg(Float.parseFloat(deg));
        }

        String code = attributes.getValue("code");
        if (code != null) {
            weatherInTime.setWindDirectionCode(code);
        }

        String name = attributes.getValue("name");
        if (name != null) {
            weatherInTime.setWindDirectionName(name);
        }
    }

    private void setWindSpeedState(Attributes attributes) {
        bWindSpeed = true;
        String mps = attributes.getValue("mps");
        if (mps != null) {
            weatherInTime.setWindSpeedMps(Float.parseFloat(mps));
        }

        String name = attributes.getValue("name");
        if (name != null) {
            weatherInTime.setWindSpeedName(name);
        }
    }

    private void setTemperatureState(Attributes attributes) {
        bTemperature = true;
        String unit = attributes.getValue("unit");
        if (unit != null) {
            weatherInTime.setTemperatureUnit(unit);
        }

        String value = attributes.getValue("value");
        if (value != null) {
            weatherInTime.setTemperatureValue(Float.parseFloat(value));
        }
        String min = attributes.getValue("min");
        if (min != null) {
            weatherInTime.setTemperatureMin(Float.parseFloat(min));
        }

        String max = attributes.getValue("max");
        if (max != null) {
            weatherInTime.setTemperatureMax(Float.parseFloat(max));
        }
    }

    private void setPressureState(Attributes attributes) {
        bPressure = true;
        String unit = attributes.getValue("unit");
        if (unit != null) {
            weatherInTime.setPressureUnit(unit);
        }

        String value = attributes.getValue("value");
        if (value != null) {
            weatherInTime.setPressureValue(Float.parseFloat(value));
        }
    }

    private void setHumidityState(Attributes attributes) {
        bHumidity = true;
        String value = attributes.getValue("value");
        if (value != null) {
            weatherInTime.setHumidityValue(Float.parseFloat(value));
        }

        String unit = attributes.getValue("unit");
        if (unit != null) {
            weatherInTime.setHumidityUnit(unit);
        }
    }

    private void setCloudsState(Attributes attributes) {
        bClouds = true;
        String value = attributes.getValue("value");
        if (value != null) {
            weatherInTime.setCloudsValue(value);
        }
        String all = attributes.getValue("all");
        if (all != null) {
            weatherInTime.setCloudsAll(Integer.parseInt(all));
        }
        String unit = attributes.getValue("unit");
        if (unit != null) {
            weatherInTime.setCloudsUnit(unit);
        }

        weatherInTimeList.add(weatherInTime);
        weatherInTime = new WeatherInTime();
    }

    @Override
    public void endElement(String uri, String localName, String qName) {

        if (qName.equalsIgnoreCase("weatherdata")) {
            weatherState.setWeatherInTimeList(weatherInTimeList);
        }

    }

    @Override
    public void characters(char ch[], int start, int length) {

        if (bLocation) {
            bLocation = false;
        }

        if (bName) {
            bName = false;
            weatherState.setCountry_name(new String(ch, start, length));
        } else if (bType) {
            bType = false;
            weatherState.setType(new String(ch, start, length));
        } else if (bCountry) {
            bCountry = false;
            weatherState.setLocale(new String(ch, start, length));
        } else if (bTimezone) {
            bTimezone = false;
            weatherState.setTimezone(new String(ch, start, length));
        } else if (bCredit) {
            bCredit = false;
            weatherState.setCredit(new String(ch, start, length));
        } else if (bMeta) {
            bMeta = false;
        }

        if (bLastUpdate) {
            bLastUpdate = false;
            weatherState.setLastUpdate(LocalDateTime.parse(new String(ch, start, length)));
        } else if (bCalcTime) {
            bCalcTime = false;
            weatherState.setCalcTime(Float.parseFloat(new String(ch, start, length)));
        } else if (bNextUpdate) {
            bNextUpdate = false;
            weatherState.setNextUpdate(LocalDateTime.parse(new String(ch, start, length)));
        } else if (bSun) {
            bSun = false;
        } else if (bForecast) {
            bForecast = false;
        } else if (bTime) {
            bTime = false;
        } else if (bSymbol) {
            bSymbol = false;
        } else if (bPrecipitation) {
            bPrecipitation = false;
        } else if (bWindDirection) {
            bWindDirection = false;
        } else if (bWindSpeed) {
            bWindSpeed = false;
        } else if (bTemperature) {
            bTemperature = false;
        } else if (bPressure) {
            bPressure = false;
        } else if (bHumidity) {
            bHumidity = false;
        } else if (bClouds) {
            bClouds = false;
        }

    }

    public WeatherState getWeatherState() {
        return weatherState;
    }

}
