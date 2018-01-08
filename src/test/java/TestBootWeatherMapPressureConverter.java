import dream.development.controllers.BootWeatherMap;
import dream.development.models.WeatherInTime;
import dream.development.models.WeatherState;
import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBootWeatherMapPressureConverter {

    private BootWeatherMap bootWeatherMap;
    private WeatherState weatherState;
    private WeatherInTime weatherInTime;
    private List<WeatherInTime> weatherInTimeList;

    @Before
    public void setUp() {
        bootWeatherMap = new BootWeatherMap();
        weatherState = new WeatherState();
        weatherInTimeList = new ArrayList<>();
        weatherInTime = new WeatherInTime();
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyList() {
        bootWeatherMap.convertHPaToMmHg(weatherState);
    }

    @Test()
    public void testPressureConverter() {
        weatherInTime.setPressureUnit("hPa");
        weatherInTime.setPressureValue(1030.76F);
        weatherInTimeList.add(weatherInTime);
        weatherState.setWeatherInTimeList(weatherInTimeList);
        bootWeatherMap.convertHPaToMmHg(weatherState);
        assertEquals("mmHg", weatherState.getWeatherInTimeList().get(0).getPressureUnit());
        assertEquals(773.135F, Precision.round(weatherState.getWeatherInTimeList().get(0).getPressureValue(), 5), 0.001);
    }

    @Test()
    public void testPressureNullUnit() {
        weatherInTime.setPressureValue(1030.76F);
        weatherInTimeList.add(weatherInTime);
        weatherState.setWeatherInTimeList(weatherInTimeList);
        bootWeatherMap.convertHPaToMmHg(weatherState);
        assertEquals(null, weatherState.getWeatherInTimeList().get(0).getPressureUnit());
        assertEquals(773.135F, Precision.round(weatherState.getWeatherInTimeList().get(0).getPressureValue(), 5), 0.001);
    }

    @Test()
    public void testPressureNullValue() {
        weatherInTime.setPressureUnit("hPa");
        weatherInTimeList.add(weatherInTime);
        weatherState.setWeatherInTimeList(weatherInTimeList);
        bootWeatherMap.convertHPaToMmHg(weatherState);
        assertEquals("mmHg", weatherState.getWeatherInTimeList().get(0).getPressureUnit());
        assertEquals(0.0, Precision.round(weatherState.getWeatherInTimeList().get(0).getPressureValue(), 5), 0.001);
    }

}
