package airhacks.blogpad.logging.boundary;

import java.lang.System.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggingSystem {

    @Produces
    public Logger produceLogger(InjectionPoint ip) {
        var loggerName = ip.getMember().getDeclaringClass().getName();
        return System.getLogger(loggerName);
    }
    
}