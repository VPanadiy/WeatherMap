package dream.development.models;

/**
 * Class for creating URL for parsing.
 */
public class UserWeatherMap {

    /**
     * Full URL string
     */
    private String weatherMapURL;

    public UserWeatherMap() {
    }

    public UserWeatherMap(String weatherMapURL) {
        this.weatherMapURL = weatherMapURL;
    }

    public String getWeatherMapURL() {
        return weatherMapURL;
    }

    public void setWeatherMapURL(String weatherMapURL) {
        this.weatherMapURL = weatherMapURL;
    }

    @Override
    public String toString() {
        return "UserWeatherMap{" +
                "weatherMapURL='" + weatherMapURL + '\'' +
                '}';
    }

}
