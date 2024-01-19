package tags.general;

import prefs.RestorationPreferences;
import tags.TagGroup;

public class InterfaceTags extends TagGroup {

    public InterfaceTags(RestorationPreferences prefs) {

        groupName = "Interface";

        recursePaths = new String[] {

                "/sound_remastered/ui"
        };

        deletePaths = new String[] {

                // Shield Pop
                "/sound_remastered/ui/shield_pop.sound"
        };

        if (prefs.isMusicOnly()) clearAllButMusicChanges();
    }
}
