package io.hostilerobot.protag.ast;

/**
 * used to represent a path
 */
public sealed interface CPathNode extends CListLikeNode permits IFilePath, IJavaPath, IProtagPath{
    EPathType getPathType();
}
