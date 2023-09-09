package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.AbstractListLikeNode;
import io.hostilerobot.protag.lang.ast.IFilePath;
import io.hostilerobot.protag.lang.ast.IFilePathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.LinkedList;

public final class FilePath extends AbstractListLikeNode<IFilePathSegment> implements IFilePath {
    public FilePath(LinkedList<IFilePathSegment> segments) {
        super(segments);
    }
}
