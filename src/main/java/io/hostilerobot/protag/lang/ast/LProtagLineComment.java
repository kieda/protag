package io.hostilerobot.protag.lang.ast;

import io.hostilerobot.protag.meta.ASTMetaInfo;

import java.util.Optional;

/**
 *
 * comment: # asdf - attached to thing on the same line
 *          # continuation of previous comment
 * # comment for the following line
 * hello!
 *
 * val # comment1
 * # comment2
 * val
 *
 * section:
 *      val1 # comment1 (belongs to val1)
 *
 *      # comment2 (belongs to section)
 *
 *      # comment3 (belongs to val2)
 *      val2
 * section2:
 *      val1 # comment1 (belongs to val1)
 *      # comment2 (belongs to section2)
 *
 *      # comment3 (belongs to val2)
 *      val2
 *      node ( # comment (belongs to val2)
 *        val2
 *      )
 *      node ( # comment (belongs to precedence)
 *
 *          val2
 *      )
 *
 *
 */
public final class LProtagLineComment extends AbstractProtagNode implements CCommentNode{
    private final CharSequence comment;
    public LProtagLineComment(ASTMetaInfo meta, CharSequence comment) {
        super(meta);
        this.comment = comment;
    }

    @Override
    public CharSequence getRawComment() {
        return comment;
    }
}
