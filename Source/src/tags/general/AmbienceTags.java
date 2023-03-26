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
                "/sound_remastered/visual_effects/cov_portable_shield_recharge",
                "/sound_remastered/visual_effects/jail_shield_death"
        };

        tagModifications = new TagModification[] {

                // Covenant Portable Shield
                new TagModification("/sound_remastered/visual_effects/jail_shield_death/jail_shield_death.sound", -3.5f), // Gain -0.5 from default [-3]

                // Concrete Barriers (Gain -2 from default [-1])
                new TagModification("/sound_remastered/materials/hard/concrete_barriers.sound", -3),
        };
    }
}
