package io.hostilerobot.protag.parser;

import io.hostilerobot.protag.ast.CProtagNode;

public class ProtagParser implements Parser<ProtagSymbol, CProtagNode, ProtagLexer>{
    private ProtagParserGen internal;
    private ProtagLexer lexer;
    public ProtagParser(ProtagLexer lexer) {
        this.lexer = lexer;
        internal = new ProtagParserGen(lexer);
    }

    @Override
    public void reInit(ProtagLexer tokenManager) {
        internal.ReInit(tokenManager);
    }

    @Override
    public ProtagLexer getTokenSource() {
        return lexer;
    }

    @Override
    public ProtagSymbol getToken(int idx) {
        return (ProtagSymbol) internal.getToken(idx);
    }

    @Override
    public CProtagNode parse() throws ParseException {
        return internal.program();
    }
}
