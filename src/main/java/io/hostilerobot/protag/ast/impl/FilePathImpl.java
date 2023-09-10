package io.hostilerobot.protag.ast.impl;

import io.hostilerobot.protag.ast.AbstractListLikeNode;
import io.hostilerobot.protag.ast.IFilePath;
import io.hostilerobot.protag.ast.IFilePathSegment;

import java.util.LinkedList;

public final class FilePathImpl extends AbstractListLikeNode<IFilePathSegment> implements IFilePath {
    public FilePathImpl(LinkedList<IFilePathSegment> segments) {
        super(segments);
    }
}
