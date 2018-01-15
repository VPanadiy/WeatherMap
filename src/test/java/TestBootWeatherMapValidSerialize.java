import dream.development.controllers.BootWeatherMap;
import dream.development.interfaces.impl.UserWeatherMapImpl;
import dream.development.models.WeatherState;
import dream.development.models.enums.PrintFormat;
import dream.development.parser.ParseXML;
import javafx.stage.FileChooser;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import static dream.development.models.consts.Constants.DEFAULT_WEATHER_MAP_URL;
import static org.junit.Assert.assertEquals;

public class TestBootWeatherMapValidSerialize {

    private UserWeatherMapImpl userWeatherMapImpl;
    private SAXParser saxParser;
    private ParseXML parseXML;
    private BootWeatherMap bootWeatherMap;
    private FileChooser fileChooser;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() throws ParserConfigurationException, SAXException {
        userWeatherMapImpl = new UserWeatherMapImpl();
        userWeatherMapImpl.setURL(DEFAULT_WEATHER_MAP_URL);
        SAXParserFactory factory = SAXParserFactory.newInstance();
        saxParser = factory.newSAXParser();
        parseXML = new ParseXML();
        bootWeatherMap = new BootWeatherMap();
        fileChooser = new FileChooser();
    }

    @Test
    public void deserialize() throws SAXException, IOException, ClassNotFoundException {
        File createdFolder = folder.getRoot();
        fileChooser.setInitialFileName("serializable");

        saxParser.parse(userWeatherMapImpl.getUserWeatherMap().getWeatherMapURL(), parseXML);

        bootWeatherMap.serialize(parseXML.getWeatherState(), fileChooser, createdFolder);

        FileInputStream fis = new FileInputStream(createdFolder.getParent()+ '\\' + "serializable.temp");
        ObjectInputStream oin = new ObjectInputStream(fis);
        WeatherState weatherState = (WeatherState) oin.readObject();

        assertEquals(parseXML.getWeatherState().printWeatherInformation(PrintFormat.FULL), weatherState.printWeatherInformation(PrintFormat.FULL));
    }

}
