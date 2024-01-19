package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

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

        deletePaths = new String[] {

                // Regret Extra Sounds
                "/sound_remastered/vehicles/gravity_throne/regret_teleport_reflection.sound",
        };

        tagMods = new TagMod[] {

                // Ambience
                //new RecursiveTagMod("/sound_remastered/ambience/deltatemple/deltatemple_ext1", +60, false, true), // Gain +15 from default
                //new RecursiveTagMod("/sound_remastered/ambience/deltatemple/deltatemple_ext1/rear_speakers", +60), // Gain +15 from default

                // Gondola
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/gondola/gondola_launch", -1.5f),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/gondola/water_spray", -4.5f),

                // Stone Doors
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/stone_door_temple", 0.5f),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/stone_doors_loop", 0.5f),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/stone_doors_underwater", 0.5f),

                // Music
                new TagMod( // (opening bop)
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_01.sound_looping",
                        prefs.getMusicGain() + -9f // Gain -3 from default
                ),
                new TagMod( // (pelican arrives)
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_02.sound_looping",
                        prefs.getMusicGain() + -5.75f // Gain +0.25 from default
                ),
                new TagMod( // (gondola approach)
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_03.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagMod( // (gondola ride 1)
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_04.sound_looping",
                        prefs.getMusicGain() + -6.5f // Gain -0.5f from default
                ),
                new TagMod( // (submarine vocals 1)
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_05.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagMod( // (underwater ambience 1)
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_06.sound_looping",
                        prefs.getMusicGain() + -6f // Gain -3 from default
                ),
                new TagMod( // (underwater ambience 2)
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_07.sound_looping",
                        prefs.getMusicGain() + -6f // Gain -3 from default
                ),
                new TagMod( // (submarine vocals 2)
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_08.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagMod( // (gondola ride 2)
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_09.sound_looping",
                        prefs.getMusicGain() + -7.25f // Gain -0.25 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/05b_deltatowers/05b_music/05b_10.sound_looping",
                        prefs.getMusicGain() + -3 // Gain 0 from default
                ),
        };

        generateClassicMusicTagMods();

        if (prefs.isMusicOnly()) clearAllButMusicChanges();
    }
}






