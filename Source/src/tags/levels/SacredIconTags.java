package tags.levels;

import tags.TagGroup;

public class SacredIconTags extends TagGroup {

    public SacredIconTags() {

        groupName = "Sacred Icon";

        recursePaths = new String[] {

                // Ambience
                "/sound_remastered/ambience/sentinelhq/insidehq/details",

                // Background Hum (New track good, but too quiet - makes spaces feel dead)
                "/sound_remastered/ambience/sentinelhq/insidehq/track1",

                // Machinery
                "/sound_remastered/ambience/device_machines/shq__key",
                "/sound_remastered/ambience/device_machines/shq_conduit_light",
                //"/sound_remastered/ambience/device_machines/shq_door_bsp_5_swap", // New tesla-y, but old SO tinny :(
                //"/sound_remastered/ambience/device_machines/shq_door_dam", // New tesla-y, old tinny
                "/sound_remastered/ambience/device_machines/shq_door_piston",
                "/sound_remastered/ambience/device_machines/shq_key_plug",
                "/sound_remastered/ambience/device_machines/shq_plug_door",
                "/sound_remastered/ambience/device_machines/shq_sentinel_doors",
                "/sound_remastered/ambience/device_machines/shq_the_plug"
        };

        preservePaths = new String[] {

                // Ambience
                "distorted_roomtone.sound",
                "swells.sound"
        };

        deletePaths = new String[] {

                // Ambience
                "/sound_remastered/ambience/sentinelhq/insidehq/details/tesla_lightning.sound"
        };
    }
}
