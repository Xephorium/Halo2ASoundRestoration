package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagModification;

public class DeltaHaloTags extends TagGroup {

    public DeltaHaloTags(RestorationPreferences prefs) {

        groupName = "Delta Halo";

        recursePaths = new String[] {

                // Forerunner Bridge
                "/sound_remastered/ambience/device_machines/da_bridge",

                // Prophet Holo Projector
                "/sound_remastered/ambience/sound_scenery/swirly_room"
        };

        tagModifications = new TagModification[]{

                // Music
                new TagModification(
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_01.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_02.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_03.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_04.sound_looping",
                        prefs.getMusicGain() + -5 // Gain +1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_05.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_06.sound_looping",
                        prefs.getMusicGain() + -7 // Gain -1 from default
                ),
        };
    }
}
