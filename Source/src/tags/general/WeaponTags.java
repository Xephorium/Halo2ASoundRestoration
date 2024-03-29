package tags.general;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

import static tags.TagMod.NO_CHANGE;

public class WeaponTags extends TagGroup {

    public WeaponTags(RestorationPreferences prefs) {

        groupName = "Weapons";

        recursePaths = new String[] {

                // General Weapon Sounds
                "/sound_remastered/weapons",

                // Scarab Gun in Ambience Directory
                "/sound_remastered/ambience/device_machines/scarab/scarab_fire"

                /* NOTE: Sentinel Beam weapon sounds are stored in the character
                 *       directory and handled by the CharacterTags class.
                 */
        };

        replacePaths = new String[] {

                // Beam Rifle NPC Fire
                "/sound_remastered/weapons/beam_rifle/beam_rifle_fire.sound",
                "/sound_remastered/weapons/beam_rifle/beam_rifle_fire_noplayer.sound",

                // Beam Rifle Zoom In/Out
                "/sound/weapons/beam_rifle/beam_rifle_zoom_in.sound",
                "/sound_remastered/weapons/beam_rifle/beamrifle_zoom/in.sound",
                "/sound/weapons/beam_rifle/beam_rifle_zoom_out.sound",
                "/sound_remastered/weapons/beam_rifle/beamrifle_zoom/out.sound",

                // Rocket Explosion
                "/sound/weapons/rocket_launcher/rocket_expl.sound",
                "/sound_remastered/weapons/rocket_launcher/projectile_exp/close.sound",

                // Sentinel Beam Fire
                "/sound/characters/sentinel/sentinel_gun/sent_gun/in.sound",
                "/sound_remastered/characters/sentinel/sentinel_gun_lod/sent_gun/in.sound",
                "/sound/characters/sentinel/sentinel_gun/sent_gun/loop.sound",
                "/sound_remastered/characters/sentinel/sentinel_gun_lod/sent_gun/loop.sound",
                "/sound/characters/sentinel/sentinel_gun/sent_gun/out.sound",
                "/sound_remastered/characters/sentinel/sentinel_gun_lod/sent_gun/out.sound",

                // Turret NPC Fire
                "/sound_remastered/weapons/chain_gun/turret_fire.sound",
                "/sound_remastered/weapons/chain_gun/turret_fire_mono.sound",

                // Sword Ready
                "/sound/weapons/energy_sword/sword_ready.sound",
                "/sound_remastered/weapons/energy_sword/energy_sword_ready.sound",

                // Regret Throne Cannon (Yes, the directories are right here - 343i is an anthill.)
                "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/in.sound",
                "/sound_remastered/vehicles/gravity_throne/weapon_fire/in.sound",
                "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/loop.sound",
                "/sound_remastered/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_prophet/loop.sound",
                "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/out.sound",
                "/sound_remastered/vehicles/gravity_throne/weapon_fire/out.sound",

                // Regret Throne Cannon Safety Fix (Just in case 343i audits their tiny ant tunnels.)
                "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/in.sound",
                "/sound_remastered/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_prophet/in.sound",
                "/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone/out.sound",
                "/sound_remastered/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_prophet/out.sound",
        };

        deletePaths = new String[] {

                // Plasma Rifle Bottom Fire
                "/sound_remastered/weapons/plasma_rifle/plas_rifle_fire_bottom.sound",

                // Covenant Carbine Impact Sound
                "/sound_remastered/weapons/impacts_riccs/cov_carbine_imp.sound",

                // Warthog Turret Spin
                "/sound_remastered/weapons/chain_gun/chaingun_spin.sound",

                // Scarab Gun Extra Fire
                "/sound_remastered/ambience/device_machines/scarab/scarab_fire_swtnr/out.sound"
        };

        tagMods = new TagMod[] {

                // Sniper Rifle
                new TagMod("/sound_remastered/weapons/sniper_rifle/fire.sound", -5, 2, 60, NO_CHANGE), // Gain +1 from default
                new TagMod("/sound_remastered/weapons/sniper_rifle/sniper_ready.sound", -2), // Gain +1 from default
                new TagMod("/sound_remastered/weapons/sniper_rifle/sniper_reload.sound", -3), // Gain 0 from default

                // Battle Rifle
                new TagMod("/sound_remastered/weapons/battle_rifle/fire_burst.sound", -5.5f, 2, 60, NO_CHANGE), // Gain -1.5 from default
                new TagMod("/sound_remastered/weapons/battle_rifle/battle_rifle_ready.sound", -7), // Gain +2 from default
                new TagMod("/sound_remastered/weapons/battle_rifle/battle_rifle_reload.sound", -1.5f), // Gain +1 from default
                new TagMod("/sound_remastered/weapons/battle_rifle/battle_rifle_reload_half.sound", 0), // Gain +1 from default

                // SMG
                new TagMod("/sound_remastered/weapons/smg/fire.sound", -4, 2, 60, NO_CHANGE), // Gain -1 from default
                new TagMod("/sound_remastered/weapons/smg/smg_ready.sound", -10), // Gain -1 from default
                new TagMod("/sound_remastered/weapons/smg/smg_dual_ready.sound", -10), // Gain -1 from default
                new TagMod("/sound_remastered/weapons/smg/smg_reload.sound", -4), // Gain -1 from default
                new TagMod("/sound_remastered/weapons/smg/smg_reload_full.sound", -4), // Gain -1 from default
                new TagMod("/sound_remastered/weapons/smg/smg_dual_reload_left.sound", -4), // Gain -1 from default
                new TagMod("/sound_remastered/weapons/smg/smg_dual_reload_right.sound", -4), // Gain -1 from default
                new TagMod("/sound/weapons/smg/smg_ammo.sound", -4), // Gain -4 from default
                new TagMod("/sound_remastered/weapons/smg/smg_drop.sound", -4), // Gain -1 from default

                // Magnum
                new TagMod("/sound_remastered/weapons/magnum/magnum_fire.sound", -6.5f, 2, 60, NO_CHANGE), // Gain -3.5 from default
                new TagMod("/sound_remastered/weapons/magnum/magnum_ready.sound", -4 ), // Gain -1 from default

                // Shotgun
                new TagMod("/sound_remastered/weapons/shotgun/fire.sound", -3.5f, 2, 60, NO_CHANGE), // Gain -0.5 from default
                new TagMod("/sound_remastered/weapons/shotgun/shotgun_reload_loop.sound", -3.5f), // Gain +1.5 from default

                // Rocket Launcher
                new TagMod("/sound_remastered/weapons/rocket_launcher/rocket_reload.sound", -1), // Gain +2 from default
                new RecursiveTagMod("/sound_remastered/weapons/rocket_launcher/tracking_locked", -1), // Gain -1 from default
                new RecursiveTagMod("/sound_remastered/weapons/rocket_launcher/tracking_locking", -1), // Gain -1 from default

                // Chain Gun
                new TagMod("/sound_remastered/weapons/chain_gun/fire.sound", -2, 2, 60, NO_CHANGE), // Gain -1 from default
                new TagMod("/sound_remastered/weapons/chain_gun/turret_fire.sound", -2, 3, 75, NO_CHANGE), // Gain -1 from default
                new TagMod("/sound_remastered/weapons/chain_gun/turret_fire_mono.sound", -2, 3, 75, NO_CHANGE), // Gain -1 from default
                new TagMod("/sound_remastered/weapons/chain_gun/scorpion_gun_fire.sound", -6, 3, 75, NO_CHANGE), // Gain -3 from default
                new TagMod("/sound_remastered/weapons/chain_gun/warthog_gun.sound", -3f, 3, 75, NO_CHANGE), // Gain -3 from default

                // Gauss Rifle (Gain -1 from default [-3])
                new TagMod("/sound_remastered/weapons/gauss_rifle/fire.sound", -4),

                // Human Weapon Shells (Gain -4 from default for entire directory)
                // Concrete Defaults: 308=-18, 50cal=-18, browning=-23, shotgun=-12
                // Dirt Defaults: 308=-22, 50cal=-9, browning=-9, shotgun=-18
                new RecursiveTagMod("/sound_remastered/materials/particles/shell_casings", -4),
                new RecursiveTagMod("/sound_remastered/materials/particles/shell_casings/concrete/308.sound", -1.0f),

                // Covenant Weapons (Min/max distance adjustment)
                new TagMod("/sound_remastered/weapons/beam_rifle/beam_rifle_fire.sound", 1, 300),
                new TagMod("/sound_remastered/weapons/covenant_carbine/fire.sound", -4.5f, 3, 75, NO_CHANGE), // Gain -0.5 from default
                new TagMod("/sound_remastered/weapons/covenant_carbine/ready.sound", -11.5f), // Gain +0.5 from default
                new TagMod("/sound_remastered/weapons/plasma_rifle/plasma_rifle_fire.sound", 1, 3, 75, NO_CHANGE), // Gain +1 from default
                new TagMod("/sound_remastered/weapons/needler/fire.sound", -3, 3, 75, NO_CHANGE), // Gain -3 from default
                new TagMod("/sound_remastered/weapons/needler/needler_reload.sound", 0), // Gain +3 from default
                new TagMod("/sound_remastered/weapons/needler/needler_dual_reload_l.sound", 0), // Gain +3 from default
                new TagMod("/sound_remastered/weapons/needler/needler_dual_reload_r.sound", 0), // Gain +3 from default
                new TagMod("/sound_remastered/weapons/plasma_pistol/fire.sound", -8, 3, 75, NO_CHANGE), // Gain +1 from default
                new TagMod("/sound_remastered/weapons/plasma_pistol/chargefire.sound", -8, 3, 75, NO_CHANGE), // Gain +1 from default
                new TagMod("/sound_remastered/weapons/plasma_pistol/startcharge.sound", -8, 3, 75, NO_CHANGE), // Gain +1 from default
                new TagMod("/sound_remastered/weapons/flak_cannon/flak_fire.sound", 4, 100),
                new TagMod(
                        "/sound_remastered/weapons/plasma_turret/fire.sound",
                        -12, // Gain -7 from default
                        3,
                        75,
                        NO_CHANGE
                ),

                // Sentinel Gun (Min/max distance adjustment)
                new TagMod("/sound_remastered/characters/sentinel/sentinel_gun/sent_gun/in.sound", 5, 100),
                new TagMod("/sound_remastered/characters/sentinel/sentinel_gun/sent_gun/loop.sound", 5, 100),
                new TagMod("/sound_remastered/characters/sentinel/sentinel_gun/sent_gun/out.sound", 5, 100),

                // Sentinel Gun Fire (Gain -4 from default)
                new TagMod("/sound_remastered/characters/sentinel/sentinel_gun/sentinel_gun.sound_looping", -7f),

                // Brute Shot
                new TagMod("/sound_remastered/weapons/brute_shot/brute_round_explode.sound", -4.5f), // Gain -1.5 from default
                //new TagMod("/sound_remastered/weapons/brute_shot/brute_shot_fire.sound", -3.5f), // Gain -1.0 from default

                // Regret Throne Cannon (Gain -1.5 from default)
                new TagMod("/sound_remastered/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_loop_gravitythrone.sound_looping", -4.5f),
        };

        // Rebalance Classic Weapon Audio
        generateClassicTagMods("weapons/", new String[] { "turret_fire_mono", "loop_gravitythrone"});
        generateClassicTagMods("characters/");
        addClassicTagMods(new TagMod[] {
                new TagMod("/sound/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_gravitythrone_loop.sound_looping", -4.5f),
        });

        if (prefs.isMusicOnly()) clearAllButMusicChanges();
    }
}










