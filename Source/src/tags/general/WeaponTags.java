package tags.general;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagModification;

import static tags.TagModification.NO_CHANGE;

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

        tagModifications = new TagModification[] {

                // Human Weapons (Min/max distance adjustment except where expanded)
                new TagModification(
                        "/sound_remastered/weapons/sniper_rifle/fire.sound",
                        -5, // Gain +1 from default
                        3,
                        75,
                        NO_CHANGE
                ),
                new TagModification(
                        "/sound_remastered/weapons/battle_rifle/fire_burst.sound",
                        -6, // Gain -2 from default
                        3,
                        75,
                        NO_CHANGE
                ),
                new TagModification(
                        "/sound_remastered/weapons/smg/fire.sound",
                        -4, // Gain -1 from default
                        3,
                        75,
                        NO_CHANGE
                ),
                new TagModification("/sound_remastered/weapons/shotgun/fire.sound", 3, 75),
                new TagModification(
                        "/sound_remastered/weapons/magnum/magnum_fire.sound",
                        -5, // Gain -2 from default
                        3,
                        75,
                        NO_CHANGE
                ),
                new TagModification(
                        "/sound_remastered/weapons/chain_gun/fire.sound",
                        -8, // Gain -7 from default
                        3,
                        75,
                        NO_CHANGE
                ),

                // Human Weapon Shells (Gain -2 from default for entire directory)
                // Concrete Defaults: 308=-18, 50cal=-18, browning=-23, shotgun=-12
                // Dirt Defaults: 308=-22, 50cal=-9, browning=-9, shotgun=-18
                new TagModification("sound_remastered/materials/particles/shell_casings", -2, true),

                // Covenant Weapons (Min/max distance adjustment)
                new TagModification("/sound_remastered/weapons/beam_rifle/beam_rifle_fire.sound", 5, 300),
                new TagModification("/sound_remastered/weapons/covenant_carbine/fire.sound", 4, 100),
                new TagModification("/sound_remastered/weapons/plasma_rifle/plasma_rifle_fire.sound", 3, 75),
                new TagModification("/sound_remastered/weapons/needler/fire.sound", 3, 75),
                new TagModification("/sound_remastered/weapons/plasma_pistol/fire.sound", 3, 75),
                new TagModification("/sound_remastered/weapons/plasma_pistol/chargefire.sound", 3, 75),
                new TagModification("/sound_remastered/weapons/plasma_pistol/startcharge.sound", 3, 75),
                new TagModification("/sound_remastered/weapons/flak_cannon/flak_fire.sound", 4, 100),
                new TagModification(
                        "/sound_remastered/weapons/plasma_turret/fire.sound",
                        -12, // Gain -7 from default
                        3,
                        75,
                        NO_CHANGE
                ),

                // Sentinel Gun (Min/max distance adjustment)
                new TagModification("/sound_remastered/characters/sentinel/sentinel_gun/sent_gun/in.sound", 5, 100),
                new TagModification("/sound_remastered/characters/sentinel/sentinel_gun/sent_gun/loop.sound", 5, 100),
                new TagModification("/sound_remastered/characters/sentinel/sentinel_gun/sent_gun/out.sound", 5, 100),

                // Sentinel Gun Fire (Gain -2 from default)
                new TagModification("/sound_remastered/characters/sentinel/sentinel_gun/sentinel_gun.sound_looping", -5),

                // Regret Throne Cannon (Gain -2 from default)
                new TagModification("/sound_remastered/vehicles/gravity_throne/weapon_fire/in.sound", -3),
                new TagModification("/sound_remastered/weapons/hunter_cannon/hunter_cannon_loop/hunter_cannon_prophet/loop.sound", -3),
                new TagModification("/sound_remastered/vehicles/gravity_throne/weapon_fire/out.sound", -3),

                // Scarab Explosion (Gain -5 from default)
                new TagModification("/sound_remastered/ambience/device_machines/scarab/cov_damage_large_scarab.sound", -15),
        };
    }
}










