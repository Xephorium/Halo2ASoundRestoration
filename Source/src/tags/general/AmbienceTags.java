package tags.general;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagModification;

/* Ambient noises I've found that don't clearly belong
 * to a specific campaign level, but need restoration.
 */
public class AmbienceTags extends TagGroup {

    public AmbienceTags(RestorationPreferences prefs) {

        groupName = "Ambience";

        recursePaths = new String[] {

                // Conduit
                "/sound_remastered/ambience/sound_scenery/conduit",

                // Covenant Portable Shield
                "sound_remastered/visual_effects/cov_portable_shield_recharge"
        };

        tagModifications = new TagModification[] {

                // Concrete Barriers (Gain -2 from default [-1])
                new TagModification("/sound_remastered/materials/hard/concrete_barriers.sound", -3),
        };
    }
}
