import dream.development.controllers.BootWeatherMap;
import dream.development.models.WeatherInTime;
import dream.development.models.WeatherState;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TestBootWeatherMapTemperatureConverter {

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

    @Test()
    public void testPopulateList() {
        weatherInTimeList.add(weatherInTime);
        weatherInTimeList.add(weatherInTime);
        weatherInTimeList.add(weatherInTime);
        weatherState.setWeatherInTimeList(weatherInTimeList);
        assertEquals(3, weatherState.getWeatherInTimeList().size());
    }

    @Test(expected = NullPointerException.class)
    public void testEmptyList() {
        bootWeatherMap.convertKelvinToCelsius(weatherState);
    }

    @Test()
    public void testTemperatureConverter() {
        weatherInTime.setTemperatureUnit("kelvin");
        weatherInTime.setTemperatureValue(276.15F);
        weatherInTime.setTemperatureMin(275.15F);
        weatherInTime.setTemperatureMax(277.15F);
        weatherInTimeList.add(weatherInTime);
        weatherState.setWeatherInTimeList(weatherInTimeList);
        bootWeatherMap.convertKelvinToCelsius(weatherState);
        assertEquals("Celsius", weatherState.getWeatherInTimeList().get(0).getTemperatureUnit());
        assertEquals(3.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureValue(), 0.00);
        assertEquals(2.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureMin(), 0.00);
        assertEquals(4.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureMax(), 0.00);
    }

    @Test()
    public void testNullUnit() {
        weatherInTime.setTemperatureValue(276.15F);
        weatherInTime.setTemperatureMin(275.15F);
        weatherInTime.setTemperatureMax(277.15F);
        weatherInTimeList.add(weatherInTime);
        weatherState.setWeatherInTimeList(weatherInTimeList);
        bootWeatherMap.convertKelvinToCelsius(weatherState);
        assertEquals(null, weatherState.getWeatherInTimeList().get(0).getTemperatureUnit());
        assertEquals(3.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureValue(), 0.00);
        assertEquals(2.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureMin(), 0.00);
        assertEquals(4.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureMax(), 0.00);
    }

    @Test()
    public void testNullTemperatureValue() {
        weatherInTime.setTemperatureUnit("kelvin");
        weatherInTime.setTemperatureMin(275.15F);
        weatherInTime.setTemperatureMax(277.15F);
        weatherInTimeList.add(weatherInTime);
        weatherState.setWeatherInTimeList(weatherInTimeList);
        bootWeatherMap.convertKelvinToCelsius(weatherState);
        assertEquals("Celsius", weatherState.getWeatherInTimeList().get(0).getTemperatureUnit());
        assertEquals(0.0, weatherState.getWeatherInTimeList().get(0).getTemperatureValue(), 0.00);
        assertEquals(2.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureMin(), 0.00);
        assertEquals(4.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureMax(), 0.00);
    }

    @Test()
    public void testNullTemperatureMin() {
        weatherInTime.setTemperatureUnit("kelvin");
        weatherInTime.setTemperatureValue(276.15F);
        weatherInTime.setTemperatureMax(277.15F);
        weatherInTimeList.add(weatherInTime);
        weatherState.setWeatherInTimeList(weatherInTimeList);
        bootWeatherMap.convertKelvinToCelsius(weatherState);
        assertEquals("Celsius", weatherState.getWeatherInTimeList().get(0).getTemperatureUnit());
        assertEquals(3.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureValue(), 0.00);
        assertEquals(0.0, weatherState.getWeatherInTimeList().get(0).getTemperatureMin(), 0.00);
        assertEquals(4.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureMax(), 0.00);
    }

    @Test()
    public void testNullTemperatureMax() {
        weatherInTime.setTemperatureUnit("kelvin");
        weatherInTime.setTemperatureValue(276.15F);
        weatherInTime.setTemperatureMin(275.15F);
        weatherInTimeList.add(weatherInTime);
        weatherState.setWeatherInTimeList(weatherInTimeList);
        bootWeatherMap.convertKelvinToCelsius(weatherState);
        assertEquals("Celsius", weatherState.getWeatherInTimeList().get(0).getTemperatureUnit());
        assertEquals(3.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureValue(), 0.00);
        assertEquals(2.0F, weatherState.getWeatherInTimeList().get(0).getTemperatureMin(), 0.00);
        assertEquals(0.0, weatherState.getWeatherInTimeList().get(0).getTemperatureMax(), 0.00);
    }

}
