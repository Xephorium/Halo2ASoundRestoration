package tags.general;

import tags.TagGroup;

public class InterfaceTags extends TagGroup {

    public InterfaceTags() {

        groupName = "Interface";

        recursePaths = new String[] {

                "/sound_remastered/ui"
        };

        deletePaths = new String[] {

                // Shield Pop
                "/sound_remastered/ui/shield_pop.sound"
        };
    }
}
