package io.hostilerobot.protag.lang.ast;

// used to represent that this node can be used in a file path segment
public sealed interface TFilePathSegmentItem permits TCommonPathSegmentItem, LFileName {
}
