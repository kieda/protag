package io.hostilerobot.protag.lang.ast;

// raw representation of a regex
public final class LProtagRegexRaw extends AbstractProtagNode implements IProtagRegex {
    private final String regexVal;
    public LProtagRegexRaw(String regexVal) {
        this.regexVal = regexVal;
    }
    @Override
    public String getRawValue() {
        return regexVal;
    }

    @Override
    public String toString() {
        return "%s[%s]".formatted(getClass().getSimpleName(), regexVal);
    }
}
