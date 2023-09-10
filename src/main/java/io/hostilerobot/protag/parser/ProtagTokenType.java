package io.hostilerobot.protag.parser;

import io.hostilerobot.protag.lang.ProtagParserGenConstants;

public enum ProtagTokenType {
    // terminal string tokens
    COMMENT(),
    WHITESPACE(),
    REGEX(),
    LITERAL(),
    JNAME(),
    JBODY(),
    FNAME(),
    NATURAL(),
    PNAME(),
    PBODY(),
    // terminal special tokens
    JPATH_START("@"),
    FPATH_START("!"),
    DOT("."),
    SLASH("/"),
    PLUS("+"),
    MINUS( "-"),
    AND( "&"),
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
    // special symbols
    BAD_CHARACTER(),
    ERROR_ELEMENT(),
    $START(null),
    EOF(null),
    // non-terminal symbols
//    ProtagInteger(false),
//    ProtagReal(false),
//    ProtagWhitespace(false),
//    ProtagComment(false)


    ;
    private final String tokenSymbol;
    private final boolean isFixed;
    private final int terminalId;
//    private final boolean isTerminal;

    public int getTerminalId() {
        return terminalId;
    }

    private static int getTerminalId(String name) {
        for(int id = 0; id < ProtagParserGenConstants.tokenImage.length; id++) {
            if(name.equals(ProtagParserGenConstants.tokenImage[id])) {
                System.out.printf("name:%s = id:%d\n", name, id);
                return id;
            }
        }
        System.out.printf("name:%s id unknown\n", name);
        return -1;
    }

    ProtagTokenType()/* {
        this(true);
    }
    ProtagTokenType(boolean isTerminal) */{
        this.isFixed = false;
        this.tokenSymbol = null;
//        this.isTerminal = isTerminal;
        this.terminalId = getTerminalId(getImage());
    }
    ProtagTokenType(String tokenSymbol) {
        this.isFixed = true;
//        this.isTerminal = true;
        this.tokenSymbol = tokenSymbol;
        this.terminalId = getTerminalId(getImage());
    }
    public String getTokenSymbol() {
        return tokenSymbol;
    }
    public String getImage() {
        return "<" + name() + ">";
    }

    /**
     * @return true if this is a fixed token
     * false if there are more than one sequence that may adhere to the token type
     */
    public boolean isFixed() {
        return isFixed;
    }
}
