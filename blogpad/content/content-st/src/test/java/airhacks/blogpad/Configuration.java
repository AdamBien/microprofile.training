package airhacks.blogpad;

import org.eclipse.microprofile.config.ConfigProvider;

public interface Configuration {

    static String getValue(String key) {
        var config = ConfigProvider.getConfig();
        return config.getValue(key, String.class);
    }

    static boolean getBooleanValue(String key) {
        var config = ConfigProvider.getConfig();
        return config.
        getOptionalValue(key, Boolean.class).
        orElse(false);
    }
    
}