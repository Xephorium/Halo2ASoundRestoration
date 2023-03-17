package tags.general;

import tags.TagGroup;

public class CharacterTags extends TagGroup {

    public CharacterTags() {

        recursePaths = new String[] {

                "/sound_remastered/characters/sentinel"
        };

        replacePaths = new String[] {

                // Sentinel Enforcer Needles
                "/sound/characters/sentinel/needler_fire_sentinel_enforcer.sound",
                "/sound_remastered/characters/sentinel/sentinel_enforcer_laser.sound"
        };

        deletePaths = new String[] {

                // Sentinel Enforcer Rocket Fire
                "/sound_remastered/characters/sentinel/sentinel_rocket_launcher/sentinel_rocket/in.sound"
        };
    }
}
