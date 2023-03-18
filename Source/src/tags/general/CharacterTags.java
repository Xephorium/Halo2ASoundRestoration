package tags.general;

import tags.TagGroup;

public class CharacterTags extends TagGroup {

    public CharacterTags() {

        recursePaths = new String[] {

                "/sound_remastered/characters/sentinel",

                "/sound_remastered/characters/jackal"
        };

        replacePaths = new String[] {

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

                // Sentinel Enforcer Rocket Fire
                "/sound_remastered/characters/sentinel/sentinel_rocket_launcher/sentinel_rocket/in.sound"
        };
    }
}
