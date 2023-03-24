package tags.general;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagModification;

import static tags.TagModification.NO_CHANGE;

public class VehicleTags extends TagGroup {

    public VehicleTags(RestorationPreferences prefs) {

        groupName = "Vehicles";

        recursePaths = new String[] {

                "/sound_remastered/vehicles"
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

                // Regret Extra Sounds
                "/sound_remastered/vehicles/gravity_throne/regret_teleport_reflection.sound",
        };

        tagModifications = new TagModification[] {

                // Phantom Turrets (Gain 0 from default)
                new TagModification("/sound_remastered/vehicles/phantom/phantom_turret_fire.sound", -10, 10, 75, NO_CHANGE),
                new TagModification("/sound_remastered/vehicles/phantom/phantom_turret_fire_turret.sound", -10, 10, 75, NO_CHANGE),
                new TagModification("/sound_remastered/vehicles/phantom/phantom_bolt_impact.sound", -9), // Gain -2 from default

                // Ghost Fire (Gain -1 from default)
                new TagModification("/sound_remastered/vehicles/ghost/ghost_fire/fire.sound", -7),

                // Ghost Explosion (Gain -2 from default)
                new TagModification("/sound_remastered/vehicles/ghost/ghost_initial_destroyed.sound", -5)
        };
    }
}
