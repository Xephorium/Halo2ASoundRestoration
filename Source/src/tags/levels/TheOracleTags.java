package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;

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
    }
}
