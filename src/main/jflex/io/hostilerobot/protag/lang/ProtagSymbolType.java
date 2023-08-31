package io.hostilerobot.protag.lang;

public enum ProtagSymbolType {
    COMMENT(),
    WHITESPACE(),
    REGEX(),
    LITERAL(),
    YNAME(),
    JPATH_START("@"),
    DOT("."),
    JNAME(),
    JBODY(),
    FPATH_START("!"),
    SLASH("/"),
    FNAME(),
    NATURAL(),
    PNAME(),
    PBODY(),
    PLUS("+"),
    MINUS("-"),
    AND("&"),
    PROPERTY_SEP(":"),
    MAP_START("{"),
    MAP_END("}"),
    LIST_SEP(","),
    LIST_START("["),
    LIST_END("]"),
    PRECEDENCE_START("("),
    PRECEDENCE_END(")"),
    PAIR_SEP("="),
    TRANSITION_SEP_L("<-"),
    TRANSITION_SEP_R("->"),
    BAD_CHARACTER(),
    ERROR_ELEMENT();
    private final String tokenSymbol;
    private final boolean isFixed;
    ProtagSymbolType() {
        this.isFixed = false;
        this.tokenSymbol = null;
    }
    ProtagSymbolType(String tokenSymbol) {
        this.isFixed = true;
        this.tokenSymbol = tokenSymbol;
        if(tokenSymbol == null)
            throw new NullPointerException("Cannot have null tokenSymbol for fixed type %s".formatted(this));

    }
    public String getTokenSymbol() {
        return tokenSymbol;
    }

    /**
     * @return true if this is a fixed token
     * false if there are more than one sequence that may adhere to the token type
     */
    public boolean isFixed() {
        return isFixed;
    }
}
