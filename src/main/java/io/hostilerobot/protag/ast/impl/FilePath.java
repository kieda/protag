package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IFilePath;
import io.hostilerobot.protag.ast.IFilePathSegment;

import java.util.LinkedList;

public final class FilePath extends AbstractListLikeNode<IFilePathSegment> implements IFilePath {
    public FilePath(LinkedList<IFilePathSegment> segments) {
        super(segments);
    }
}
