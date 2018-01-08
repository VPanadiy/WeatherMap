import dream.development.interfaces.impl.UserWeatherMapImpl;
import org.junit.Before;
import org.junit.Test;

import static dream.development.models.consts.Constants.DEFAULT_WEATHER_MAP_URL;
import static org.junit.Assert.assertEquals;

public class TestWeatherMapUserInformation {

    private UserWeatherMapImpl userWeatherMapImpl;

    @Before
    public void setUp() {
        userWeatherMapImpl = new UserWeatherMapImpl();
    }

    @Test
    public void setDefaultURL() {
        userWeatherMapImpl.setURL(DEFAULT_WEATHER_MAP_URL);
        assertEquals(userWeatherMapImpl.getUserWeatherMap().getWeatherMapURL(), DEFAULT_WEATHER_MAP_URL);
    }

    @Test
    public void setEmptyURL() {
        userWeatherMapImpl.setURL("");
        assertEquals(userWeatherMapImpl.getUserWeatherMap().getWeatherMapURL(), "");
    }

    @Test
    public void notSetURL() {
        assertEquals(userWeatherMapImpl.getUserWeatherMap().getWeatherMapURL(), null);
    }

    @Test
    public void setSymbolsToURL() {
        userWeatherMapImpl.setURL("┆┍ⓙ⓫└☣");
        assertEquals(userWeatherMapImpl.getUserWeatherMap().getWeatherMapURL(), "┆┍ⓙ⓫└☣");
    }

}
