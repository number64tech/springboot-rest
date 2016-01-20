package jp.number64.springbootrest.logmarker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class LogSuppress {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogSuppress.class.getName());

    /** ログ連続出力抑止対象 */
    private static final String LOG_MARKER_OF_SOMETHING_ERROR = "LOG-ID";

    static {
        Marker suppressRepeat = MarkerFactory.getMarker("LOGMARKERNAME");
        Marker markerOfSomethingError = MarkerFactory.getMarker(LOG_MARKER_OF_SOMETHING_ERROR);
        markerOfSomethingError.add(suppressRepeat);
    }

    public void outputLog(final String message) {
        LOGGER.debug("LOGID:{} {}", LOG_MARKER_OF_SOMETHING_ERROR, message);
    }
}
