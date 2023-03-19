package tags.levels;

import tags.TagGroup;

public class GravemindTags extends TagGroup {

    public GravemindTags() {

        recursePaths = new String[] {

                // Machinery
                "/sound_remastered/ambience/device_machines/council_pedestal",
                "/sound_remastered/ambience/device_machines/high_door",
                "/sound_remastered/ambience/device_machines/high_sanctum_lift",
                "/sound_remastered/ambience/device_machines/high_small_door",

                // Effects
                "sound_remastered/visual_effects/jail_shield_death"
        };

        replacePaths = new String[] {

                // High Charity Door Delay
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

                // In Amber Clad Slipspace Rupture
                "/sound/visual_effects/inamberclad_flyby/slipspace.sound",
                "/sound_remastered/visual_effects/inamberclad_flyby/slipspace/highcharity_slipspace.sound",

                // In Amber Clad Flyby
                "/sound/visual_effects/inamberclad_flyby/inamberclad_flyby.sound",
                "/sound_remastered/visual_effects/inamberclad_flyby/flyby/inamberclad_flyby.sound",

                // Cortana Appear
                "/sound/visual_effects/cortana_appear.sound",
                "/sound_remastered/visual_effects/cortana_appear.sound",
        };
    }
}
