package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractProtagNode;
import io.hostilerobot.protag.lang.ast.IProtagRegex;
import io.hostilerobot.protag.lang.ast.ProtagNode;
import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.List;

// number regexes are in the form `~PAYLOAD`
// valid operators are
// =, |, &, +, -, /, *, ^, !
// basically we produce a DFA to produce
// depending on the context, may resolve to a number, or to 0 (false), non-zero (true)
public final class ProtagNumberRegex extends AbstractProtagNode implements IProtagRegex {
    private final String value;
    public ProtagNumberRegex(String value) {
        this.value = value;
    }
    @Override
    public String getRawValue() {
        return value;
    }

    @Override
    public List<ProtagNode> getChildren() {
        return List.of();
    }
}
