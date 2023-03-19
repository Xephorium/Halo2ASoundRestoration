package tags.levels;

import tags.TagGroup;

public class DeltaHaloTags extends TagGroup {

    public DeltaHaloTags() {

        groupName = "Delta Halo";

        recursePaths = new String[] {

                // Forerunner Bridge
                "/sound_remastered/ambience/device_machines/da_bridge",

                // Prophet Holo Projector
                "/sound_remastered/ambience/sound_scenery/swirly_room"
        };
    }
}
