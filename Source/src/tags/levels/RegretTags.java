package tags.levels;

import tags.TagGroup;

public class RegretTags extends TagGroup {

    public RegretTags() {

        recursePaths = new String[] {

                // Gondola
                "/sound_remastered/ambience/device_machines/gondola",
        };

        preservePaths = new String[] {

                // Gondola Water Spray
                "water_spray\\loop.sound"
        };

        replacePaths = new String[] {

                // Forerunner Submarine Doors
                "/sound/ambience/device_machines/delta_sub/sub_door/sub_door_close.sound",
                "/sound_remastered/ambience/device_machines/delta_sub/sub_door/close/delta_sub_elevator_door_close.sound",
                "/sound/ambience/device_machines/delta_sub/sub_door/sub_door_open.sound",
                "/sound_remastered/ambience/device_machines/delta_sub/sub_door/open/delta_sub_elevator_door_open.sound"
        };
    }
}
