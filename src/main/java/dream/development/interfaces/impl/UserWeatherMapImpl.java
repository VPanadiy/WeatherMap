package dream.development.interfaces.impl;

import dream.development.interfaces.WeatherMapUserInformation;
import dream.development.models.UserWeatherMap;

/**
 * Implementation of interface {@link WeatherMapUserInformation}
 */
public class UserWeatherMapImpl implements WeatherMapUserInformation {

    /**
     * New instance of class {@link UserWeatherMap}
     */
    private UserWeatherMap userWeatherMap = new UserWeatherMap();

    /**
     * Implementation of interface {@link WeatherMapUserInformation} method.
     *
     * @param url String URL
     */
    @Override
    public void setURL(String url) {
        userWeatherMap.setWeatherMapURL(url);
    }

    /**
     * Getter for instance object {@link UserWeatherMap}
     *
     * @return {@link UserWeatherMap}
     */
    public UserWeatherMap getUserWeatherMap() {
        return userWeatherMap;
    }

}
