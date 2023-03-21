package tags.levels;

import tags.TagGroup;
import tags.TagModification;

public class MetropolisTags extends TagGroup {

    public MetropolisTags() {

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

        tagModifications = new TagModification[] {

                // Music
                new TagModification(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_01.sound_looping",
                        -4 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_02.sound_looping",
                        -6 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_03.sound_looping",
                        -4 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_04.sound_looping",
                        -6 // Gain -1 from default
                ),
        };
    }
}
