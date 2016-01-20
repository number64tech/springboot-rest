package jp.number64.springbootrest.logmarker;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogSuppressTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogSuppressTest.class);

    @Test
    public void case01() {
        LOGGER.debug("** case01");
        LogSuppress supLogger = new LogSuppress();
        for (int i=0 ; i<100 ; i++) {
            supLogger.outputLog("Hello LogTest! COUNT:" + Integer.toString(i));
        }
    }
}
