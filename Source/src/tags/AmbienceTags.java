package tags;

public class AmbienceTags extends TagGroup {

    public AmbienceTags() {

        recursePaths = new String[] {

                // General
                "/sound_remastered/ambience/device_machines",

                // Cairo Station
                "/sound_remastered/ambience/spacestation",
                "/sound_remastered/ambience/sound_scenery/computer1",
                "/sound_remastered/ambience/sound_scenery/computer2",
                "/sound_remastered/ambience/sound_scenery/computer3",
                "/sound_remastered/ambience/sound_scenery/computer4",
                "/sound_remastered/ambience/sound_scenery/computer5",
                "/sound_remastered/ambience/sound_scenery/computer6",
        };

        preservePaths = new String[] {

                // Cairo Station Background Loops
                "spacestation\\loop.sound",
                "smallroom\\loop.sound",

                // Cairo Station Tech
                "ss_"
        };

        replacePaths = new String[] {

                // Perplexingly, removing this sound will brick the build? Replacing
                // it with the quietest sound I can find in the classic tags.
                // TODO: Add silent tag to project.
                "/sound/materials/soft/cloth_hits/cloth_hits.sound",
                "/sound_remastered/ambience/spacestation/docking_clangs/dockingcov_incoming.sound",

                // Cairo Station Muffled Boarding Action
                "/sound/ambience/spacestation/ss_behind_bulkhead/behind_bulkhead/loop.sound",
                "/sound_remastered/ambience/spacestation/behind_bulkhead/loop.sound",

                // Cairo Station Shield Charger
                "/sound/ambience/device_machines/ss_armory_shield_charger/track2/in.sound",
                "/sound_remastered/ambience/device_machines/ss_armory_shield_charger/track2/in.sound",
                "/sound/ambience/device_machines/ss_armory_shield_charger/track2/loop.sound",
                "/sound_remastered/ambience/device_machines/ss_armory_shield_charger/track2/loop.sound",
                "/sound/ambience/device_machines/ss_armory_shield_charger/track2/out.sound",
                "/sound_remastered/ambience/device_machines/ss_armory_shield_charger/track2/out.sound",

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
