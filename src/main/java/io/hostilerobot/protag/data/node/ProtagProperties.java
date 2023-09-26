package io.hostilerobot.protag.data.node;

import java.util.List;

public class ProtagProperties {

    private List<ProtagProperties> sections;

    public class ProtagSection {
        private ProtagNode name;
        private ProtagSequence payload;
    }


}
