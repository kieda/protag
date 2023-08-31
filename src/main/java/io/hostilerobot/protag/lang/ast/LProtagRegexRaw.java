package io.hostilerobot.protag.lang.ast;

// raw representation of a regex
public record LProtagRegexRaw(ProtagNode parent, String regexVal) implements IProtagRegex {
    @Override
    public String getRawValue() {
        return regexVal;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
