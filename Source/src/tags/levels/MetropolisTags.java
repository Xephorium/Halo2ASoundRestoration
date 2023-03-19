package tags.levels;

import tags.TagGroup;

public class MetropolisTags extends TagGroup {

    public MetropolisTags() {

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
    }
}
