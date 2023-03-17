package tags.general;

import tags.TagGroup;

public class WeaponTags extends TagGroup {

    public WeaponTags() {

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
                "sound_remastered/ambience/device_machines/scarab/scarab_fire_swtnr/out.sound"
        };
    }
}
