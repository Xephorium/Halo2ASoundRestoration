package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;

public class RegretTags extends TagGroup {

    public RegretTags(RestorationPreferences prefs) {

        groupName = "Regret";

        recursePaths = new String[] {

                // Gondola
                "/sound_remastered/ambience/device_machines/gondola",

                // Quad Wing Birdie bOi
                "/sound_remastered/ambience/deltatemple/delta_quadwing",

                // Underwater Creaks
                "/sound_remastered/ambience/deltatemple/deltatemple_underwater/details",
        };

        preservePaths = new String[] {

                // Gondola Water Spray
                "water_spray\\loop.sound",

                /* Gondola High Pass
                 *
                 * This is one of the few sound effects that just sounds bad in classic.
                 * Based on the name, I wonder if someone at Bungie felt the same. Regardless,
                 * it can't actually be completely removed. The classic gondola launch sound
                 * has some of this mixed in and, although it's obnoxious, it's still better
                 * than the remaster sound's distorted tesla coil nonsense.
                 */
                "gondola_loop\\hi_shit",

                // Whale Calls
                "details\\distant_whalecall"
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
