package io.hostilerobot.protag.lang;

import java_cup.runtime.Symbol;

import java.io.StringReader;

public class TestProtag {
    static String test = """
            123 1.2 # commentR
            - # comas  
            35""";
    public static void main(String[] args) throws Exception{
        System.out.println(test.length());

        ProtagLexer pl = new ProtagLexer(new StringReader(test));
        ProtagParser pp = new ProtagParser(pl);

//        pp.
//        Symbol s = pp.parse();
        //System.out.println(s);
        //System.out.println(s.value);

    }
}
