package io.hostilerobot.protag.lang.ast;

/**
 * marker class to signal that this node may be used as an item in a java path
 */
public sealed interface TJavaPathItem permits IJavaPathSegment, LJavaName, TCommonPathItem {
}
