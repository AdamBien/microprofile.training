package airhacks.blogpad;

import java.net.URI;

import org.eclipse.microprofile.config.ConfigProvider;

public interface Configuration {

    static URI getValue(String key) {
        var config = ConfigProvider.getConfig();
        return config.getValue(key, URI.class);
    }

    static boolean getBooleanValue(String key) {
        var config = ConfigProvider.getConfig();
        return config.
        getOptionalValue(key, Boolean.class).
        orElse(false);
    }
    
}