package dream.development.interfaces;

/**
 * Interface for setting URL for parsing OpenWeatherMap. Set one behavior, not matter from where URL get.
 */
public interface WeatherMapUserInformation {

    /**
     * Method for setting URL parsing.
     *
     * @param url String URL
     */
    void setURL(String url);

}
