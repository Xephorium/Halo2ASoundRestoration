package tags.general;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagMod;

public class DialogTags extends TagGroup {

    public DialogTags(RestorationPreferences prefs) {

        groupName = "Dialog";

        tagMods = new TagMod[] {

                /* Note: While boosting the gain of level dialog works as expected,
                 *       the changes seem to have no effect on combat dialog.
                 */

                // Level Dialog (Gain +0 from default)
                //new TagModification("/sound/dialog/levels/03_earthcity/mission", 0, true),
        };

        if (prefs.isMusicOnly()) clearAllButMusicChanges();
    }
}
