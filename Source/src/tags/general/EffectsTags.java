package tags.general;

import tags.TagGroup;

public class EffectsTags extends TagGroup {

    public EffectsTags() {

        recursePaths = new String[] {

                "/sound_remastered/visual_effects"
        };

        preservePaths = new String[] {

                // Steam
                "scenery_steam\\loop.sound",

                // Sentinel Factory Explosion
                "factory_explode.sound"
        };

        replacePaths = new String[] {

                // Restore Classic In Amber Clad Slipspace Rupture
                "/sound/visual_effects/inamberclad_flyby/slipspace.sound",
                "/sound_remastered/visual_effects/inamberclad_flyby/slipspace/highcharity_slipspace.sound",
        };
    }
}
