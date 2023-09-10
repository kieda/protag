package io.hostilerobot.protag.parser;

import io.hostilerobot.protag.ast.ESignType;

public record XInteger(ESignType sign, int nat) {
    public int toInteger() {
        return sign.negate(nat);
    }
}
