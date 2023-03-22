package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;

public class TheGreatJourneyTags extends TagGroup {

    public TheGreatJourneyTags(RestorationPreferences prefs) {

        groupName = "The Great Journey";

        recursePaths = new String[] {

                // Control Room Grav Lift
                "/sound_remastered/ambience/device_machines/tartarus_grav_lift",

                // Tartarus Hammer
                "/sound_remastered/characters/brute/tatarus_hammer_hits"
        };

        replacePaths = new String[] {

                // Tartarus Hammer Hit (Yes, tartarus is spelled wrong in the dest path.)
                "/sound/characters/brute/tartarus_hits.sound",
                "/sound_remastered/characters/brute/tatarus_hammer_hits/hits_positional.sound"
        };

        deletePaths = new String[] {

                // Control Room Grav Lift Extra
                "/sound_remastered/ambience/device_machines/tartarus_grav_lift/grav_lift/in.sound",
                "/sound_remastered/ambience/device_machines/tartarus_grav_lift/grav_lift/out.sound",
        };
    }
}
