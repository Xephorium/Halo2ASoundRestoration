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

        tagModifications = new TagModification[] {

                // Cortana Dialog
                new TagModification("/sound/dialog/levels/05_deltatemple/mission/l05_0340_cor.sound", -0), // Gain +2 [-2] - "easier said than done, inbound phantoms chief"
                new TagModification("/sound/dialog/levels/05_deltatemple/mission/l05_0360_cor.sound", -0), // Gain +2 [-2] - "hold here until the pelican arrives, chief"

                // Music
                new TagModification( // peril (clearing the first temple)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_01.sound_looping",
                        prefs.getMusicGain() + -7.5f // Gain -1.5 from default
                ),
                new TagModification( // (warthog mount up drumline)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_02.sound_looping",
                        prefs.getMusicGain() + -7.5f // Gain -1.5 from default
                ),
                new TagModification( // swirly room (bridge control room)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_03.sound_looping",
                        prefs.getMusicGain() + -7.5f // Gain -1.5 from default
                ),
                new TagModification( // (rolling thunder)
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_04.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_05.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/05a_deltaapproach/05a_music/05a_06.sound_looping",
                        prefs.getMusicGain() + -7 // Gain -1 from default
                ),

                // Bridge
                new TagModification("/sound_remastered/ambience/device_machines/da_bridge", -5, true), // Gain -5 from default for all
        };
    }
}
