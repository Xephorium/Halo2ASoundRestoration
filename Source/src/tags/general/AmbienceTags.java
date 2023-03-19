package tags.general;

import tags.TagGroup;

/* Ambient noises I've found that don't clearly belong
 * to a specific campaign level, but need restoration.
 */
public class AmbienceTags extends TagGroup {

    public AmbienceTags() {

        recursePaths = new String[] {

                // Conduit
                "/sound_remastered/ambience/sound_scenery/conduit",

                // Covenant Portable Shield
                "sound_remastered/visual_effects/cov_portable_shield_recharge"
        };
    }
}
