package io.hostilerobot.protag.lang;

import io.hostilerobot.protag.lang.ast.ProtagNode;
import java_cup.runtime.Symbol;

import java.io.StringReader;

public class TestProtag {
    static String test = """
            @hello
            @world.frant"cools"asdf
            @rodl(hello)asdf # comment    
                .frant.cool["asdf", 123]"asf"
            """;
    static String test1 = "\"hello\"";
    static String test2 = """
            [ "hello", "world", ([3.4, "", [ ]]) ]
            #12 1.2 # commentR
            #[ 123, 3.5, 3 /5, (3.5), ( 4 /4) ]
            
            #- # comas  
            [3.5, (3 /5), 
                [ 
                    (["hello"]), "world" , `~tjk`,
                    wskad
                ], "lol" 
            ]
             #321# jsjdk
            #((hello)) world
            #"asdf"
            #`trying new things!`
             #12 
            # 213 & -3/  5""";
    public static void main(String[] args) throws Exception{
        System.out.println(test.length());

        ProtagLexer pl = new ProtagLexer(new StringReader(test));
        ProtagParser pp = new ProtagParser(pl);

//        pp.
         ProtagNode node = pp.parse();
        System.out.println(node);
//        System.out.println(node.value);

    }
}
