package io.hostilerobot.protag.lang.ast;

// JPathSegment = JNAME|JBODY|CommonPathSegment

/**
 * marker class to signify that this node may be used in a java path
 */
public sealed interface TJavaPathSegmentItem permits TCommonPathSegmentItem, LJavaName, LJavaBody {
}
