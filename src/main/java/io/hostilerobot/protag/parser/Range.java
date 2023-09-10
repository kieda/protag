package io.hostilerobot.protag.parser;

public record Range(Location start, Location end) {
    public static Range UNKNOWN = new Range(Location.UNKNOWN, Location.UNKNOWN);
    public boolean isUnknown() {
        return start.isUnknown() || end.isUnknown();
    }
}
