package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

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

        tagMods = new TagMod[] {

                // Johnson Dialog (Gain +0.5 from default)
                new TagMod("/sound/dialog/levels/01_spacestation/mission/l01_0010_jon.sound", -2.5f),
                new TagMod("/sound/dialog/levels/01_spacestation/mission/l01_0020_jon.sound", -2.5f),
                new TagMod("/sound/dialog/levels/01_spacestation/mission/l01_0030_jon.sound", -2.5f),
                new TagMod("/sound/dialog/levels/01_spacestation/mission/l01_0040_jon.sound", -2.5f),
                new TagMod("/sound/dialog/levels/01_spacestation/mission/l01_0050_jon.sound", -2.5f),
                new TagMod("/sound/dialog/levels/01_spacestation/mission/l01_0051_jon.sound", -2.5f),
                new TagMod("/sound/dialog/levels/01_spacestation/mission/l01_1810_jon.sound", -2.5f),

                // Tram Sounds (Gain -1 from default)
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/ss_door_tram", -1),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/ss_tram_car", -1),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/ss_tram_start", -1),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/tram_stop", -1),

                // Music (Gain +0.5 from default)
                new TagMod("/sound_remastered/scenarios/solo/01a_tutorial/01a_music/01a_01.sound_looping", -2.5f)
        };
    }
}
