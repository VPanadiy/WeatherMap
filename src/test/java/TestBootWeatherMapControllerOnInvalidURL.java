import dream.development.controllers.BootWeatherMap;
import dream.development.interfaces.impl.UserWeatherMapImpl;
import javafx.event.ActionEvent;
import org.junit.Before;
import org.junit.Test;

public class TestBootWeatherMapControllerOnInvalidURL {

    private UserWeatherMapImpl userWeatherMapImpl;
    private BootWeatherMap bootWeatherMap;
    private ActionEvent actionEvent;

    @Before
    public void setUp() {
        userWeatherMapImpl = new UserWeatherMapImpl();
        bootWeatherMap = new BootWeatherMap();
        actionEvent = new ActionEvent();
    }

    @Test(expected = NullPointerException.class)
    public void parseEmptyURL() {
        userWeatherMapImpl.setURL("");
        bootWeatherMap.mainController(actionEvent);
    }

    @Test(expected = NullPointerException.class)
    public void parseNullURL() {
        userWeatherMapImpl.setURL(null);
        bootWeatherMap.mainController(actionEvent);
    }

    @Test(expected = NullPointerException.class)
    public void parseInvalidSymbolURL() {
        userWeatherMapImpl.setURL("┆┍ⓙ⓫└☣");
        bootWeatherMap.mainController(actionEvent);
    }

    @Test(expected = NullPointerException.class)
    public void parseInvalidURL() {
        userWeatherMapImpl.setURL("www.test.com.ua");
        bootWeatherMap.mainController(actionEvent);
    }

}
