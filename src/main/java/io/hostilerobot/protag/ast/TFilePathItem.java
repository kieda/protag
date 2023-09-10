package io.hostilerobot.protag.ast;

// used to represent that this node can be used as a file path item
public sealed interface TFilePathItem permits IFilePathSegment, LFileName, TCommonPathItem {
}
