package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagModification;

public class OutskirtsTags extends TagGroup {

    public OutskirtsTags(RestorationPreferences prefs) {

        groupName = "Outskirts";

        recursePaths = new String[] {

                // Elite Drop Pods
                "/sound_remastered/ambience/device_machines/insertion_pods"
        };

        tagModifications = new TagModification[] {

                // Distant Battle (Gain -2 from default [-6 all but mono_battle_details, which -5] for directory)
                new TagModification("/sound_remastered/ambience/earthcity/old_mombasa_quiet/details", -2, true),

                // Other Distant Battle (Gain -2 from default [-3])
                new TagModification("/sound_remastered/ambience/earthcity/old_mombasa_quiet/no_details_front.sound", -5),
                new TagModification("/sound_remastered/ambience/earthcity/old_mombasa_quiet/no_details_rear.sound", -5),

                // Music
                new TagModification( // (phantom arrival strings)
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_01.sound_looping",
                        prefs.getMusicGain() + -4 // Gain -1 from default
                ),
                new TagModification( // (hunter arrival jam)
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_02.sound_looping",
                        prefs.getMusicGain() + -7 // Gain -4 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_03.sound_looping",
                        prefs.getMusicGain() + -7 // Gain -4 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_04.sound_looping",
                        prefs.getMusicGain() + -4 // Gain -1 from default
                ),
                new TagModification( // (drumline for warthog breach run)
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_05.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagModification( // (soft voices connecting drumlines)
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_06.sound_looping",
                        prefs.getMusicGain() + -4 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_065.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagModification( // (scarab stinger)
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_066.sound_looping",
                        prefs.getMusicGain() + -3 // Gain -3 from default
                ),
                new TagModification( // (post scarab strings)
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_067.sound_looping",
                        prefs.getMusicGain() + -3 // Gain +2 from default
                ),
                new TagModification( // (final shadow parade)
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_07.sound_looping",
                        prefs.getMusicGain() + -6 // Gain -1 from default
                ),

                // Cortana Wakeup Dialog (Gain -1 from default [-3])
                new TagModification("/sound/dialog/levels/03_earthcity/mission/l03_9030_cor.sound", -4),

                // Cortana Tunnel Dialog (Gain +1/2 from default [all 0])
                new TagModification("/sound/dialog/levels/03_earthcity/mission/l03_0580_cor.sound", 1),
                new TagModification("/sound/dialog/levels/03_earthcity/mission/l03_0070_cor.sound", 2),
                new TagModification("/sound/dialog/levels/03_earthcity/mission/l03_0080_cor.sound", 2),
                new TagModification("/sound/dialog/levels/03_earthcity/mission/l03_0590_cor.sound", 2),
        };
    }
}
