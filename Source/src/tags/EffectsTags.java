package tags;

public class EffectsTags extends TagGroup {

    public EffectsTags() {

        recursePaths = new String[] {

                "/sound_remastered/visual_effects"
        };

        preservePaths = new String[] {

                // Cairo Welder
                "ss_arc_welder\\welder\\in.sound",
                "ss_arc_welder\\welder\\loop.sound",

                // Orbital Platform Explosions
                "malta_explode.sound",
                "ss_distship_explode.sound",

                // Cable Cutting
                "alphacable_swtnr",

                // Airlock
                "airlock.sound",
                "airlock_repressurize.sound",
                "ss_airlock_explode.sound",

                // Steam
                "scenery_steam\\loop.sound",

                // Sentinel Factory Explosion
                "factory_explode.sound"
        };

        replacePaths = new String[] {

                // In Amber Clad Slipspace Rupture
                "/sound/visual_effects/inamberclad_flyby/slipspace.sound",
                "/sound_remastered/visual_effects/inamberclad_flyby/slipspace/highcharity_slipspace.sound",

                // Perplexingly, removing these sounds will brick the build? Replacing
                // them with the quietest sound I can find in the classic tags.
                // TODO: Add silent tag to project.
                "/sound/materials/soft/cloth_hits/cloth_hits.sound",
                "/sound_remastered/visual_effects/ss_smalldoor_explode.sound",
        };
    }
}
