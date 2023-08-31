package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IProtagRegex;
import io.hostilerobot.protag.lang.ast.ProtagNode;

// number regexes are in the form `~PAYLOAD`
// valid operators are
// =, |, &, +, -, /, *, ^, !
// basically we produce a DFA to produce
// depending on the context, may resolve to a number, or to 0 (false), non-zero (true)
public record ProtagNumberRegex(ProtagNode parent, String value) implements IProtagRegex {
    @Override
    public String getRawValue() {
        return value;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
