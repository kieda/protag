package io.hostilerobot.protag.meta;

import io.hostilerobot.protag.lang.ProtagTokenType;
import io.hostilerobot.protag.lang.ast.CCommentNode;

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
    Range getRange();
    List<CCommentNode> getComments();
}
