package jp.number64.springbootrest.logmarker;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class SuppressRepeatMarkerFilter extends Filter<ILoggingEvent> {
    private static final Map<Marker, Long> LAST_SUPPRRESSED_AT = new HashMap<>();

    private String marker;
    private String suppressSeconds;
    private Long suppressMillis;
    private Marker suppressRepeatMarker;

    public void setMarker(String markerName) {
        this.marker = markerName;
        this.suppressRepeatMarker = MarkerFactory.getMarker(marker);
    }

    public void setSuppressSeconds(String suppressSecondsSetting) {
        suppressSeconds = suppressSecondsSetting;
        this.suppressMillis = Long.parseLong(suppressSeconds) * 1_000L;
    }

    @Override
    public FilterReply decide(ILoggingEvent event) {
        Marker marker = event.getMarker();
        if (marker == null || !marker.contains(suppressRepeatMarker)) {
            return FilterReply.NEUTRAL;
        }
        long now = System.currentTimeMillis();
        Long val = LAST_SUPPRRESSED_AT.get(marker);
        if (val != null) {
            try {
                if (now - val < suppressMillis) {
                    return FilterReply.DENY;
                }
            } catch (NumberFormatException e) {
                ; // do nothing (= don't suppress).
            }
        }
        LAST_SUPPRRESSED_AT.put(marker, now);
        return FilterReply.NEUTRAL;
    }

}
