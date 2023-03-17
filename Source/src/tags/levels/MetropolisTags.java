package tags.levels;

import tags.TagGroup;

public class MetropolisTags extends TagGroup {

    public MetropolisTags() {

        recursePaths = new String[] {

                // Scarab
                "/sound_remastered/ambience/device_machines/scarab"
        };

        preservePaths = new String[] {

                // Scarab
                "scarab\\piston_stop.sound",
                "scarab\\scarab_steps.sound"

        };

        deletePaths = new String[] {

                // Scarab Engine Extra
                "sound_remastered/ambience/device_machines/scarab/scarab_engine_loop_swtnr/loop.sound"
        };
    }
}
