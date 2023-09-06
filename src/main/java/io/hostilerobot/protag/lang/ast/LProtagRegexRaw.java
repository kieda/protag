package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.List;

// raw representation of a regex
public final class LProtagRegexRaw extends AbstractProtagNode implements IProtagRegex {
    private final String regexVal;
    public LProtagRegexRaw(ASTMetaInfo meta, String regexVal) {
        super(meta);
        this.regexVal = regexVal;
    }
    @Override
    public String getRawValue() {
        return regexVal;
    }
}
