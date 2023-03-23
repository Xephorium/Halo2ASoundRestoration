package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagModification;

public class TheOracleTags extends TagGroup {

    public TheOracleTags(RestorationPreferences prefs) {

        groupName = "The Oracle";

        recursePaths = new String[] {

                // Silo Arm
                "/sound_remastered/ambience/device_machines/silo_arm",

                // Silo Elevator
                "/sound_remastered/ambience/device_machines/silo_elevator_loop",

                // Cable Elevator
                "/sound_remastered/ambience/device_machines/control_lift",
        };

        tagModifications = new TagModification[]{

                // Music
                new TagModification(
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_01.sound_looping",
                        prefs.getMusicGain() + -5 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_02.sound_looping",
                        prefs.getMusicGain() + -3 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_04.sound_looping",
                        prefs.getMusicGain() + -6 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_05.sound_looping",
                        prefs.getMusicGain() + -9 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_06.sound_looping",
                        prefs.getMusicGain() + -5 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_07.sound_looping",
                        prefs.getMusicGain() + 0 // Gain 0 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_08.sound_looping",
                        prefs.getMusicGain() + -5 // Gain 0 from default
                ),
        };
    }
}
