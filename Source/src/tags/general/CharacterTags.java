package tags.general;

import tags.TagGroup;
import tags.TagModification;

import static tags.TagModification.NO_CHANGE;

public class CharacterTags extends TagGroup {

    public CharacterTags() {

        groupName = "Characters";

        recursePaths = new String[] {

                // All Sentinel Character/Beam Sounds
                "/sound_remastered/characters/sentinel",

                // Jackal Shield Pop
                "/sound_remastered/characters/jackal"
        };

        replacePaths = new String[] {

                /* Sentinel Rocket Fire
                 *
                 * Enforcers are incredibly disorienting and frustrating to fight
                 * without a rocket fire sound. I've chosen to use the subtle, but
                 * audible remastered needle fire sound for this. It's the one
                 * addition the mod makes and I stand by its necessity.
                 */
                "/sound_remastered/characters/sentinel/sentinel_enforcer_laser.sound",
                "/sound_remastered/characters/sentinel/sentinel_rocket_launcher/sentinel_rocket/in.sound",

                // Sentinel Enforcer Needles
                "/sound/characters/sentinel/needler_fire_sentinel_enforcer.sound",
                "/sound_remastered/characters/sentinel/sentinel_enforcer_laser.sound",

                // Bugger Melee
                "/sound/characters/bugger/bugger_melees.sound",
                "/sound_remastered/characters/bugger/bugger_melees.sound",

                // Flood Carrier Pop
                "/sound/characters/flood/carrier_pop.sound",
                "/sound_remastered/characters/flood/carrier_pop.sound",

                // Flood Infection Pop
                "/sound/characters/flood/infection_form/infection_pop.sound",
                "/sound_remastered/characters/flood/infection_form/infection_pop.sound",

        };

        deletePaths = new String[] {

                // Sentinel Enforcer Rocket Fire (If not replacing above)
                //"/sound_remastered/characters/sentinel/sentinel_rocket_launcher/sentinel_rocket/in.sound"
        };

        tagModifications = new TagModification[] {

                // Sentinel Enforcer
                new TagModification(
                        "/sound_remastered/characters/sentinel/sentinel_enforcer_laser.sound",
                        NO_CHANGE,
                        25, // Min dist +20 from default
                        75, // Max dist +10 from default,
                        0   // Classic only false
                )
        };
    }
}
