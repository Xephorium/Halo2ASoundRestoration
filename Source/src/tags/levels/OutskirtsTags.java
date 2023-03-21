package tags.levels;

import tags.TagGroup;
import tags.TagModification;

public class OutskirtsTags extends TagGroup {

    public OutskirtsTags() {

        groupName = "Outskirts";

        recursePaths = new String[] {

                // Elite Drop Pods
                "/sound_remastered/ambience/device_machines/insertion_pods"
        };

        tagModifications = new TagModification[] {

                // Music
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_01.sound_looping",
                        -4 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_02.sound_looping",
                        -7 // Gain -4 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_03.sound_looping",
                        -7 // Gain -4 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_04.sound_looping",
                        -4 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_05.sound_looping",
                        -8 // Gain -2 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_06.sound_looping",
                        -4 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_065.sound_looping",
                        -8 // Gain -2 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_066.sound_looping",
                        -1 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_067.sound_looping",
                        -7 // Gain -2 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/03a_oldmombasa/03a_music/03a_07.sound_looping",
                        -8 // Gain -3 from default
                )
        };
    }
}
