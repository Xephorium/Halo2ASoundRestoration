package tags.general;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagMod;

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

        tagMods = new TagMod[] {

                // Covenant Portable Shield (Gain -1 from default)
                new TagMod("/sound_remastered/visual_effects/cov_portable_shield_recharge/cov_portable_shield_rech.sound", -3),
                new TagMod("/sound_remastered/visual_effects/jail_shield_death/jail_shield_death.sound", -4),

                // Concrete Barriers (Gain -2 from default [-1])
                new TagMod("/sound_remastered/materials/hard/concrete_barriers.sound", -3),
        };
    }
}
