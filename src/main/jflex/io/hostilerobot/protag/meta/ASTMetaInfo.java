package io.hostilerobot.protag.meta;

import io.hostilerobot.protag.lang.ProtagSymbol;
import io.hostilerobot.protag.lang.ProtagTokenType;
import io.hostilerobot.protag.lang.ast.CCommentNode;

import java.util.Deque;
import java.util.List;

/**
 * todo - multiline comment handling: how should we handle more than one?
 *
 * 123 / *
 *
 *
 * asdf: (% ... %)
 *       /* ... * /
 *       (%
 *        %
 *        %
 *        %)
 *       <% block
 *        % comment
 *        %>
 *       <<% doc
 *         % @return x = v
 *         %>>
 *       asdf
 *  ((* comment *) x ) = val
 *  (x (* comment *)) = val
 */
public interface ASTMetaInfo {
    ProtagSymbol getSymbol();

    Range getRange();
    Deque<CCommentNode>[] getComments();
}
