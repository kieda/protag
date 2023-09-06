package io.hostilerobot.protag.lang;

import io.hostilerobot.protag.meta.Location;
import java_cup.runtime.Symbol;

public sealed abstract class ProtagSymbol extends Symbol permits ProtagSpecialToken, ProtagNonTerminalSymbol, EOFToken, StartToken, ProtagStringToken, ProtagNaturalToken {
    // location (start), string length, location (end)
    // String value (?)
    // ProtagTokenType tokenType
    private final ProtagTokenType type;
    private final Location start;
    private final Location end;

//  *** Constructor: Special Symbols (START) ***
    protected ProtagSymbol(int id, ProtagTokenType type, int state) {
        super(id);
        super.parse_state = state;
        this.type = type;
        this.start = null;
        this.end = null;
    }
//  *** Constructor: All other Symbols (DOT, Properties, LITERAL) ***
    public ProtagSymbol(int id, ProtagTokenType type, Location start, Location end, Object val) {
        super(id, start.offset(), end.offset(), val);
        this.type =  type;
        this.start = start;
        this.end = end;
    }

    public Location getStart() {return start; }
    public Location getEnd() {
        return end;
    }

    public abstract CharSequence getSymbol();
    public ProtagTokenType getType() {
        return type;
    }

    @Override
    public String toString() {
        String first;
        if(type != null) {
            first = "%s#%d(%s)".formatted(type, super.sym, getSymbol());
        } else {
            first = "#%d".formatted(super.sym);
        }

        if(start != null) {
            if(end == null || start.equals(end)) {
                return first + "[%s]".formatted(start);
            } else {
                return first + "[%s, %s]".formatted(start, end);
            }
        }
        return first;
    }
}


// implementations: fixed symbol
//