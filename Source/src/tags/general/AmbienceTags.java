package tags.general;

import tags.TagGroup;

public class AmbienceTags extends TagGroup {

    public AmbienceTags() {

        recursePaths = new String[] {

                // General
                "/sound_remastered/ambience/device_machines",
        };

        replacePaths = new String[] {

                // High Charity Doors
                "/sound/ambience/device_machines/high_door_delay/a50_door_delay.sound",
                "/sound_remastered/ambience/device_machines/high_door_delay/high_door_delay.sound",

                // High Charity Lift Doors
                "/sound/ambience/device_machines/high_lift_doors_open_close/lift_doors_close.sound",
                "/sound_remastered/ambience/device_machines/high_lift_doors_open_close/close/highcharity_liftdoor_close.sound",
                "/sound/ambience/device_machines/high_lift_doors_open_close/lift_doors_open.sound",
                "/sound_remastered/ambience/device_machines/high_lift_doors_open_close/open/highcharity_liftdoor_open.sound",

                // High Sanctum Doors
                "/sound/ambience/device_machines/high_sanctum_door/close1.sound",
                "/sound_remastered/ambience/device_machines/high_sanctum_door/close1/sanctumdoor_close1.sound",
                "/sound/ambience/device_machines/high_sanctum_door/close2.sound",
                "/sound_remastered/ambience/device_machines/high_sanctum_door/close2/sanctumdoor_close2.sound",
                "/sound/ambience/device_machines/high_sanctum_door/closed.sound",
                "/sound_remastered/ambience/device_machines/high_sanctum_door/closed/sanctumdoor_closed.sound",
                "/sound/ambience/device_machines/high_sanctum_door/open1.sound",
                "/sound_remastered/ambience/device_machines/high_sanctum_door/open1/sanctumdoor_open1.sound",
                "/sound/ambience/device_machines/high_sanctum_door/open2.sound",
                "/sound_remastered/ambience/device_machines/high_sanctum_door/open2/sanctumdoor_open2.sound",
                "/sound/ambience/device_machines/high_sanctum_door/opened.sound",
                "/sound_remastered/ambience/device_machines/high_sanctum_door/opened/sanctumdoor_opened.sound",

                // High Sanctum Lift Stop
                "/sound/ambience/device_machines/high_sanctum_lift_stop/high_sanctum_lift_stop.sound",
                "/sound_remastered/ambience/device_machines/high_sanctum_lift_stop/sanctum_lift_stop.sound",
        };
    }
}
