package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagMod;

public class MetropolisTags extends TagGroup {

    public MetropolisTags(RestorationPreferences prefs) {

        groupName = "Metropolis";

        recursePaths = new String[] {

                // Scarab
                "/sound_remastered/ambience/device_machines/scarab"
        };

        preservePaths = new String[] {

                // Scarab Sounds to Definitely Preserve
                "scarab\\piston_stop.sound",
                "scarab\\scarab_steps.sound",

                // Scarab Maybe Preserve (Classic steps sound a bit weak w/o, gotta test)
                "scarab\\scarab_steps_lfe.sound",
                "scarab\\scarab_steps_swtnr.sound",

        };

        deletePaths = new String[] {

                // Scarab Engine Extra
                "/sound_remastered/ambience/device_machines/scarab/scarab_engine_loop_swtnr/loop.sound"
        };

        tagMods = new TagMod[] {

                // Music
                new TagMod(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_01.sound_looping",
                        prefs.getMusicGain() + -2 // Gain +1 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_02.sound_looping",
                        prefs.getMusicGain() + -3 // Gain +2 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_03.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain -1.5 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_04.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain +0.5 from default
                ),

                // Scarab Movement (Gain -2 from default)
                new TagMod("/sound_remastered/ambience/device_machines/scarab/scarab_engine_loop/scarab_engine_loop/loop.sound", -2),
                new TagMod("/sound_remastered/ambience/device_machines/scarab/scarab_walk_move.sound", -5),
                new TagMod("/sound_remastered/ambience/device_machines/scarab/scarab_walk_move_long.sound", -5),
                new TagMod("/sound_remastered/ambience/device_machines/scarab/scarab_walk_move_short.sound", -5),
        };
    }
}
