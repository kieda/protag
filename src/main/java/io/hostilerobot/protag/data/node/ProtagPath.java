package io.hostilerobot.protag.data.node;

import java.util.List;

public class ProtagPath {
    // path source source
    // segment list: path
    // node list: segment
    private ProtagSource source;

    private List<ProtagSegment> pathItems;

    public class ProtagSegment {
        List<ProtagNode> segmentItems;
    }

}
