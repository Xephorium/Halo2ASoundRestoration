package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;

public class TheArmoryTags extends TagGroup {

    public TheArmoryTags(RestorationPreferences prefs) {

        groupName = "The Armory";

        recursePaths = new String[] {

                // Computer Terminals
                "/sound_remastered/ambience/sound_scenery/computer1",
                "/sound_remastered/ambience/sound_scenery/computer2",
                "/sound_remastered/ambience/sound_scenery/computer3",
                "/sound_remastered/ambience/sound_scenery/computer4",
                "/sound_remastered/ambience/sound_scenery/computer5",
                "/sound_remastered/ambience/sound_scenery/computer6",
        };

        replacePaths = new String[] {

                // Shield Charger
                "/sound/ambience/device_machines/ss_armory_shield_charger/track2/in.sound",
                "/sound_remastered/ambience/device_machines/ss_armory_shield_charger/track2/in.sound",
                "/sound/ambience/device_machines/ss_armory_shield_charger/track2/loop.sound",
                "/sound_remastered/ambience/device_machines/ss_armory_shield_charger/track2/loop.sound",
                "/sound/ambience/device_machines/ss_armory_shield_charger/track2/out.sound",
                "/sound_remastered/ambience/device_machines/ss_armory_shield_charger/track2/out.sound",
        };
    }
}
