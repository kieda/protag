package io.hostilerobot.protag.lang.ast.impl;

import io.hostilerobot.protag.lang.ast.IFilePath;
import io.hostilerobot.protag.lang.ast.IFilePathSegment;
import io.hostilerobot.protag.lang.ast.IProtagPathSegment;
import io.hostilerobot.protag.lang.ast.ProtagNode;

import java.util.List;

public record FilePath(ProtagNode parent, List<ProtagNode> pathSegmentItems) implements IFilePath {
    @Override
    public List<ProtagNode> getItems() {
        return pathSegmentItems;
    }

    @Override
    public ProtagNode getParent() {
        return parent;
    }
}
