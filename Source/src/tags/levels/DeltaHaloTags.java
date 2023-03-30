package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

public class DeltaHaloTags extends TagGroup {

    public DeltaHaloTags(RestorationPreferences prefs) {

        groupName = "Delta Halo";

        recursePaths = new String[] {

                // Forerunner Bridge
                "/sound_remastered/ambience/device_machines/da_bridge",

                // Prophet Holo Projector
                "/sound_remastered/ambience/sound_scenery/swirly_room"
        };

        replacePaths = new String[] {

                /* Bird Calls
                 *
                 * Anniversary edition completely removes ambient bird calls from most of Delta Halo.
                 * I consider this a pretty fundamental misunderstanding of the intended atmosphere
                 * of Halo 2 and have made the difficult decision to replace a delightful ambient leaf
                 * rustling track to restore the bird calls. Long live the birds!
                 */
                "/sound_remastered/ambience/sound_scenery/outdoor_birds/details/outdoor_birds_delta.sound",
                "/sound_remastered/ambience/sound_scenery/stereo_leaves_details/details/stereo_leaves.sound"
        };

        tagMods = new TagMod[] {

                // Ambience
                new RecursiveTagMod("/sound_remastered/ambience/deltatemple/light_mountain_exterior", +35, false, true), // Gain + 30 from default

                // Waterfalls & Rivers (Gain -2/-2.5 from default)
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/delta_approach_waterfall_close", -2.5f),
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/delta_approach_waterfall_far", -2.5f),
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/delta_river_medium", -2),
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/delta_river_small", -2),
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/delta_waterfall_close_copy", -2.5f),
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/delta_waterfall_temple", -2.5f),

                // Gusty Wind (Gain +3 from default)
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/gusty_wind", 2),
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/monogusty_wind", 2),

                // Bird Calls (Gain +7/+0.5 from default)
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/stereo_leaves_details", 7),
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/outdoor_birds", 0.5f),

                // Cortana Dialog
                new TagMod("/sound/dialog/levels/05_deltatemple/mission/l05_0340_cor.sound", -0), // Gain +2 [-2] - "easier said than done, inbound phantoms chief"
                new TagMod("/sound/dialog/levels/05_deltatemple/mission/l05_0360_cor.sound", -0), // Gain +2 [-2] - "hold here until the pelican arrives, chief"

                // Music
                new TagMod( // peril (clearing the first temple)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_01.sound_looping",
                        prefs.getMusicGain() + -7.5f // Gain -1.5 from default
                ),
                new TagMod( // (warthog mount up drumline)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_02.sound_looping",
                        prefs.getMusicGain() + -8f // Gain -2 from default
                ),
                new TagMod( // swirly room (bridge control room)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_03.sound_looping",
                        prefs.getMusicGain() + -8.5f // Gain -2.5 from default
                ),
                new TagMod( // (rolling thunder)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_04.sound_looping",
                        prefs.getMusicGain() + -5.25f // Gain +0.75 from default
                ),
                new TagMod( // (waterfall groove)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_05.sound_looping",
                        prefs.getMusicGain() + -7 // Gain -1 from default
                ),
                new TagMod( // (delta tower view strings)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_06.sound_looping",
                        prefs.getMusicGain() + -8f // Gain -2 from default
                ),

                // Bridge (Gain -8 from default)
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/da_bridge/da_bridge_energy", -8),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/da_bridge/da_bridge_loop_left", -8),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/da_bridge/da_bridge_loop_left_02", -8),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/da_bridge/da_bridge_loop_right", -8),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/da_bridge/da_bridge_loop_right_02", -8),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/da_bridge/rings_new", -8),
                new TagMod("/sound_remastered/ambience/device_machines/da_bridge/blue_shoots_across.sound", -11),
                new TagMod("/sound_remastered/ambience/device_machines/da_bridge/opened.sound", -8),
                new TagMod("/sound_remastered/ambience/device_machines/da_bridge/rings_stop.sound", -11),
                new TagMod("/sound_remastered/ambience/device_machines/da_bridge/stop.sound", -8),
                new TagMod("/sound_remastered/ambience/device_machines/da_bridge/stopped.sound", -8),

                // Bridge Button (Gain +4 from default [-3])
                new TagMod("/sound_remastered/ambience/device_machines/da_bridge/stopped.sound", 1),
        };
    }
}
