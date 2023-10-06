package tags.general;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

import static tags.TagMod.NO_CHANGE;

public class VehicleTags extends TagGroup {

    public VehicleTags(RestorationPreferences prefs) {

        groupName = "Vehicles";

        recursePaths = new String[] {

                // Most Vehicles
                "/sound_remastered/vehicles",

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

        replacePaths = new String[] {

                // Warthog Crash
                "/sound/vehicles/warthog/jeep_crash_hard.sound",
                "/sound_remastered/vehicles/warthog/warthog_suspension.sound",

                // Scorpion Fire
                "/sound/vehicles/scorpion/scorpion_explosion_up.sound",
                "/sound_remastered/vehicles/scorpion/projectile_exp/close.sound",

                // Scorpion Crash
                "/sound/vehicles/scorpion/scorpion_crash.sound",
                "/sound_remastered/vehicles/scorpion/scorpion_suspension.sound",

                // Ghost Fire
                "/sound/vehicles/ghost/ghost_fire.sound",
                "/sound_remastered/vehicles/ghost/ghost_fire/fire.sound",

                // Wraith Fire
                "/sound/vehicles/wraith/wraith_fire_mortar.sound",
                "/sound_remastered/vehicles/wraith/wraith_fire_mortar/fire.sound",

                // Wraith Explode
                "/sound/vehicles/wraith/wraith_plasma_expl.sound",
                "/sound_remastered/vehicles/wraith/wraith_mortar_explode/explode.sound",

                // Banshee Fire
                "/sound/vehicles/banshee/banshee_fire.sound",
                "/sound_remastered/vehicles/banshee/banshee_fire/fire.sound",

                // Phantom Extra Fire
                "/sound_remastered/vehicles/phantom/phantom_turret_fire.sound",
                "/sound_remastered/vehicles/phantom/phantom_turret_fire_turret.sound"
        };

        deletePaths = new String[] {

                // Scorpion Reload
                "/sound_remastered/vehicles/scorpion/scorpion_reload.sound",

                // Scorpion Turret Move
                "/sound_remastered/vehicles/scorpion/scorp_turret_move.sound_looping",
                "/sound_remastered/vehicles/scorpion/turret_move/in.sound",
                "/sound_remastered/vehicles/scorpion/turret_move/loop.sound",
                "/sound_remastered/vehicles/scorpion/turret_move/out.sound",

                // Ghost Extra Boost
                "/sound_remastered/vehicles/ghost/ghost_boost_left/track2/loop.sound",
                "/sound_remastered/vehicles/ghost/ghost_boost_right/track2/loop.sound",

                // Spectre Extra Boost
                "/sound_remastered/vehicles/spectre/spectre_boost_l/track2/loop.sound",
                "/sound_remastered/vehicles/spectre/spectre_boost_r/track2/loop.sound",

                // Phantom Turret Animation
                "/sound_remastered/vehicles/phantom/phantom_turret_anims/lower.sound",
                "/sound_remastered/vehicles/phantom/phantom_turret_anims/raise.sound",

                // Scarab Engine Extra
                "/sound_remastered/ambience/device_machines/scarab/scarab_engine_loop_swtnr/loop.sound"
        };

        tagMods = new TagMod[] {

                // Phantom Turrets
                new TagMod("/sound_remastered/vehicles/phantom/phantom_turret_fire.sound", -9, 10, 75, NO_CHANGE), // Gain +1 from default
                new TagMod("/sound_remastered/vehicles/phantom/phantom_turret_fire_turret.sound", -9, 10, 75, NO_CHANGE), // Gain +1 from default
                new TagMod("/sound_remastered/vehicles/phantom/phantom_bolt_impact.sound", -9), // Gain -2 from default

                // Ghost Fire (Gain -1 from default)
                new TagMod("/sound_remastered/vehicles/ghost/ghost_fire/fire.sound", -7),

                // Ghost Explosion (Gain -3 from default)
                new TagMod("/sound_remastered/vehicles/ghost/ghost_initial_destroyed.sound", -6),

                // Banshee
                new TagMod("/sound_remastered/vehicles/banshee/banshee_cannon_expl.sound", -3.5f), // Gain -0.5 from default
                new TagMod("/sound_remastered/vehicles/banshee/banshee_cannon_fire.sound", -4), // Gain -1 from default
                new TagMod("/sound_remastered/vehicles/banshee/banshee_windup.sound", -5), // Gain -2 from default

                /* All Warthog Sounds (Gain -2 from default for all)
                 *
                 * For reasons I can't discern, directly adjusting gain on the warthog '.sound' files
                 * breaks them. I've discovered that making the same change in Guerilla rewrites the
                 * files almost entirely. There must be something more going on here. But as a
                 * workaround, the code simply adjusts the loop files instead.
                 */
                new RecursiveTagMod("/sound_remastered/vehicles/warthog", -1.5f, false, true),
                new TagMod("/sound_remastered/vehicles/warthog/warthog_suspension.sound", -1.5f),
                new TagMod("/sound_remastered/vehicles/warthog/warthog_horn/warthog_horn.sound_looping", -3.5f),

                // Warthog Hubcaps (Gain -1 from default [-3])
                new TagMod("/sound/materials/havok/hubcap_hit.sound", -4),
                new TagMod("/sound/materials/havok/hubcap_looping/hubcap_looping.sound_looping", -4),

                // All Scorpion Sounds
                new RecursiveTagMod("/sound_remastered/vehicles/scorpion", -2), // Gain -2 from default
                new RecursiveTagMod("/sound_remastered/vehicles/scorpion/scorpion_drop", -1), // Gain -1 from default

                // All Wraith Sounds (Gain -2 from default for all)
                new RecursiveTagMod("/sound_remastered/vehicles/wraith", -2),

                // Vehicle Damage Effects (Gain -3 from default for all)
                new RecursiveTagMod("/sound_remastered/vehicles/damage_effects", -3),
                new RecursiveTagMod("/sound/vehicles/damage_effects", -3),

                // Vehicle Crash Boost
                new RecursiveTagMod("/sound_remastered/vehicles/damage_effects/hum_crashes_separate", 0.5f),
                new TagMod("/sound_remastered/vehicles/damage_effects/cov_crashes.sound", 0.5f),
                new TagMod("/sound_remastered/vehicles/damage_effects/hum_crashes.sound", 0.5f),

                // Scarab Movement (Gain -2 from default)
                new TagMod("/sound_remastered/ambience/device_machines/scarab/scarab_engine_loop/scarab_engine_loop/loop.sound", -2),
                new TagMod("/sound_remastered/ambience/device_machines/scarab/piston_stop.sound", -5),
                new TagMod("/sound_remastered/ambience/device_machines/scarab/scarab_steps_swtnr.sound", -1), // Gain -1 from default
                new TagMod("/sound_remastered/ambience/device_machines/scarab/scarab_walk_move.sound", -5),
                new TagMod("/sound_remastered/ambience/device_machines/scarab/scarab_walk_move_long.sound", -5),
                new TagMod("/sound_remastered/ambience/device_machines/scarab/scarab_walk_move_short.sound", -5),

                // Scarab Explosion (Gain -3 from default)
                new TagMod("/sound_remastered/ambience/device_machines/scarab/cov_damage_large_scarab.sound", -13),
        };

        // Rebalance Classic Vehicle Audio
        generateClassicTagMods("vehicles/", new String[] {
                "scorpion_drop",
                "phantom_turret_fire_turret",
                "ghost_fire",
                "warthog_suspension"
        });
        addClassicTagMods(new TagMod[] {
                new TagMod("/sound/vehicles/phantom/phantom_turret_fire_c_ap.sound", -9, 10, 75, NO_CHANGE),
                new TagMod("/sound/vehicles/ghost/ghost_fire.sound", -7),
        });
        generateClassicTagMods("ambience/", new String[] { "_swtnr" });
    }
}
