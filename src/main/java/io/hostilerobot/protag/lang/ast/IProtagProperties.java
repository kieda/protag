package io.hostilerobot.protag.lang.ast;

import java.util.List;

// properties is a list of sections
public interface IProtagProperties extends CMapLikeNode {
    @Override
    List<IProtagSection> getItems();
}
