package io.hostilerobot.protag.lang.ast;

// PathSegment := (JPathSegment|PathSegment|FPathSegment)

/**
 * used to represent a path segment
 */
public sealed interface CPathSegmentNode extends CListLikeNode
    permits IJavaPathSegment, IProtagPathSegment, IFilePathSegment {

}
