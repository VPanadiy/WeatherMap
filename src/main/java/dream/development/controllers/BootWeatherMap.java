package dream.development.controllers;

import dream.development.interfaces.impl.UserWeatherMapImpl;
import dream.development.models.WeatherInTime;
import dream.development.models.WeatherState;
import dream.development.models.enums.PrintFormat;
import dream.development.parser.ParseXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import org.apache.commons.math3.util.Precision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.filechooser.FileSystemView;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.net.URL;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static dream.development.models.consts.Constants.*;

/**
 * Main controller for application
 */
public class BootWeatherMap implements Initializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(BootWeatherMap.class);

    private UserWeatherMapImpl userWeatherMapImpl = new UserWeatherMapImpl();
    private ResourceBundle resourceBundle;

    @FXML
    private Button btnURL;

    @FXML
    private TextField txtURL;

    @FXML
    private Button btnLaunch;

    @FXML
    private TextArea txtAreaStatus;

    /**
     * Initialize parameters after stage creating
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        txtURL.setText(DEFAULT_WEATHER_MAP_URL);
        userWeatherMapImpl.setURL(txtURL.getText());
    }

    /**
     * Method for setting URL by clicking on button btnURL
     */
    public void setURLString() {
        userWeatherMapImpl.setURL(txtURL.getText());
        LOGGER.info("WeatherMap URL changed to: " + userWeatherMapImpl.getUserWeatherMap().getWeatherMapURL());
        txtAreaStatus.appendText("WeatherMap URL changed to: " + userWeatherMapImpl.getUserWeatherMap().getWeatherMapURL());
    }

    /**
     * Method for parsing URL by clicking on button btnLaunch
     *
     * @param actionEvent Event listener
     */
    public void mainController(ActionEvent actionEvent) {

        LOGGER.info("Start running BootWeatherMap...");
        txtAreaStatus.appendText("Start running BootWeatherMap...\n");

        try {
            LOGGER.info("Check for removable disk...");
            txtAreaStatus.appendText("Check for removable disk...\n");

            File file = checkDisks();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            ParseXML parseXML = new ParseXML();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
            FileChooser fileWeatherForecast = setFileProperties("Properties files (*.properties)", "*.properties", "WeatherState " + dateFormat.format(new Date()));
            FileChooser tempWeatherFile = setFileProperties("Temp files (*.temp)", "*.temp", dateFormat.format(new Date()));

            file = chooseFolder(file, fileWeatherForecast, actionEvent);

            LOGGER.info("Start getting information from www.BootWeatherMap.org ...");
            txtAreaStatus.appendText("Start getting information from www.BootWeatherMap.org ...\n");
            saxParser.parse(userWeatherMapImpl.getUserWeatherMap().getWeatherMapURL(), parseXML);
            LOGGER.info("Getting information from www.BootWeatherMap.org finished successfully!");
            txtAreaStatus.appendText("Getting information from www.BootWeatherMap.org finished successfully!\n");

            if (file != null) {

                LOGGER.info("Serializing information start...");
                txtAreaStatus.appendText("Serializing information start...\n");
                serialize(parseXML.getWeatherState(), tempWeatherFile, file);
                LOGGER.info("Serializing finished successfully!");
                txtAreaStatus.appendText("Serializing finished successfully!\n");

                LOGGER.info("Method converting temperature from Kelvin to Celsius executed.");
                txtAreaStatus.appendText("Method converting temperature from Kelvin to Celsius executed.\n");
                convertKelvinToCelsius(parseXML.getWeatherState());

                LOGGER.info("Method converting pressure from hPa to mmHg executed.");
                txtAreaStatus.appendText("Method converting pressure from hPa to mmHg executed.\n");
                convertHPaToMmHg(parseXML.getWeatherState());

                LOGGER.info("Saving information to file start...");
                txtAreaStatus.appendText("Saving information to file start...\n");
                SaveFile(parseXML.getWeatherState().printWeatherInformation(PrintFormat.SHORT), file);
                LOGGER.info("Saving finished successfully!");
                txtAreaStatus.appendText("Saving finished successfully!\n");

            } else {
                LOGGER.info("Saving canceled by user!");
                txtAreaStatus.appendText("Saving canceled by user!\n");
            }

        } catch (Exception e) {
            LOGGER.info("[ERROR]: While parsing information from www.BootWeatherMap.org! " + e.getMessage());
            txtAreaStatus.appendText("[ERROR]: While parsing information from www.BootWeatherMap.org! " + e.getMessage() + "\n");
        }

        LOGGER.info("Program finished.");
        txtAreaStatus.appendText("Program finished.\n");

    }

    /**
     * Convert temperature from Kelvin to Celsius
     *
     * @param weatherState WeatherState object getting from {@link ParseXML}
     */
    public void convertKelvinToCelsius(WeatherState weatherState) {

        for (WeatherInTime weatherInTime : weatherState.getWeatherInTimeList()) {

            if (weatherInTime.getTemperatureUnit() != null) {
                weatherInTime.setTemperatureUnit("Celsius");
            }

            if (weatherInTime.getTemperatureValue() != 0.0) {
                weatherInTime.setTemperatureValue(Precision.round(weatherInTime.getTemperatureValue() - 273.15F, 2));
            }

            if (weatherInTime.getTemperatureMin() != 0.0) {
                weatherInTime.setTemperatureMin(Precision.round(weatherInTime.getTemperatureMin() - 273.15F, 2));
            }

            if (weatherInTime.getTemperatureMax() != 0.0) {
                weatherInTime.setTemperatureMax(Precision.round(weatherInTime.getTemperatureMax() - 273.15F, 2));
            }

        }

    }

    /**
     * Convert pressure hPa Kelvin to mmHg
     *
     * @param weatherState WeatherState object getting from {@link ParseXML}
     */
    public void convertHPaToMmHg(WeatherState weatherState) {

        for (WeatherInTime weatherInTime : weatherState.getWeatherInTimeList()) {

            if (weatherInTime.getPressureUnit() != null) {
                weatherInTime.setPressureUnit("mmHg");
            }

            if (weatherInTime.getPressureValue() != 0.0) {
                weatherInTime.setPressureValue(Precision.round(weatherInTime.getPressureValue() * MM_H_G_VALUE, 5));
            }

        }

    }

    /**
     * Method for setting parameters for saving file
     *
     * @param fileDescription File extension description
     * @param fileExtensions  File extension
     * @param fileName        File name
     * @return FileChooser
     */
    private FileChooser setFileProperties(String fileDescription, String fileExtensions, String fileName) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose location To Save Report");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(fileDescription, fileExtensions);
        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setInitialFileName(fileName);

        return fileChooser;

    }

    /**
     * Method for setting folder for saving file. If method {@link BootWeatherMap#checkDisks()} return null, showSaveDialog method from FileChooser executed.
     *
     * @param file        Getting file path from method {@link BootWeatherMap#checkDisks()}
     * @param fileChooser Parameters for saving file from method {@link BootWeatherMap#setFileProperties(String, String, String)}
     * @param actionEvent Event listener
     * @return File
     */
    private File chooseFolder(File file, FileChooser fileChooser, ActionEvent actionEvent) {

        File selectedFile = new File(file + fileChooser.getInitialFileName() + String.valueOf(fileChooser.getExtensionFilters().get(0).getExtensions().get(0)).replace("*", ""));
        if (file == null) {

            file = fileChooser.showSaveDialog(((Node) actionEvent.getTarget()).getScene().getWindow());

            if (file != null) {
                LOGGER.info("Folder set to disk: " + file.getParent());
                txtAreaStatus.appendText("Folder set to disk: " + file.getParent() + "\n");
                return file;
            } else {
                return null;
            }

        }

        return selectedFile;

    }

    /**
     * Method for saving file of short information in extension .properties.
     *
     * @param content String of parsing URL
     * @param file    File folder and file name for saving
     */
    private void SaveFile(String content, File file) {

        try {

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();

        } catch (IOException e) {
            LOGGER.info("[ERROR]: While saving file! " + e.getMessage());
            txtAreaStatus.appendText("[ERROR]: While saving file! " + e.getMessage() + "\n");
        }

    }

    /**
     * Method for saving serializable file of full information in extension .temp
     *
     * @param weatherState WeatherState object getting from {@link ParseXML}
     * @param fileChooser  Parameters for saving file from method {@link BootWeatherMap#setFileProperties(String, String, String)}
     * @param file         Getting file path from method {@link BootWeatherMap#checkDisks()}
     * @throws IOException File not found
     */
    public void serialize(WeatherState weatherState, FileChooser fileChooser, File file) throws IOException {

        FileOutputStream fos;
        if ('\\' == file.getParent().charAt(file.getParent().length() - 1)) {
            fos = new FileOutputStream(file.getParent() + fileChooser.getInitialFileName() + ".temp");
        } else {
            fos = new FileOutputStream(file.getParent() + '\\' + fileChooser.getInitialFileName() + ".temp");
        }
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(weatherState);
        oos.flush();
        oos.close();

    }

    /**
     * Method for searching Removable Disk with file system FAT32
     *
     * @return File
     * @throws IOException Exception of FileStore
     */
    private File checkDisks() throws IOException {

        FileSystemView fsv = FileSystemView.getFileSystemView();
        File[] listRoots = File.listRoots();
        Path path = null;
        FileStore fileStore = null;

        for (File fileSystem : listRoots) {

            if (fsv.getSystemTypeDescription(fileSystem) != null && fsv.getSystemTypeDescription(fileSystem).equals(resourceBundle.getString("bootWeatherMap.removableDisk"))) {

                path = Paths.get(String.valueOf(fileSystem));
                fileStore = Files.getFileStore(path);

                if (fileStore.type().equals(FILE_SYSTEM_FAT32)) {

                    if (fileSystem.canWrite() && fileSystem.getUsableSpace() >= USED_SPACE) {
                        LOGGER.info("Location set to disk " + fileSystem);
                        txtAreaStatus.appendText("Location set to disk " + fileSystem + "\n");
                        return fileSystem;
                    } else {
                        LOGGER.info("[Warning]: File location is locked or not enough space!");
                        txtAreaStatus.appendText("[Warning]: File location is locked or not enough space!\n");
                    }

                }

            }

        }

        LOGGER.info("[Warning]: Location not found!");
        txtAreaStatus.appendText("[Warning]: Location not found!\n");
        return null;

    }

}
