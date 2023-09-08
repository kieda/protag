package io.hostilerobot.protag.lang;

import io.hostilerobot.protag.lang.ast.ESignType;

public record XInteger(ESignType sign, int nat) {
    public int toInteger() {
        return sign.negate(nat);
    }
}
