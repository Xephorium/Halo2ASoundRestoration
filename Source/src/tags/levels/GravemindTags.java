package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

public class GravemindTags extends TagGroup {

    public GravemindTags(RestorationPreferences prefs) {

        groupName = "Gravemind";

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

        tagMods = new TagMod[]{

                // Dialog
                // "The demon has infiltrated the council chamber!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_1000_bth.sound", -1f),
                // "Protect the hierarchs! Seal the exits!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_1010_bth.sound", -1f),
                // "Reinforce all approaches to the holding pens!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_1020_bth.sound", -1f),
                // "Slay the demon on sight."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_1030_bth.sound", -1f),

                new TagMod( // (first room bassy groove)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_01.sound_looping",
                        prefs.getMusicGain() + -7f // Gain -2 from default
                ),
                new TagMod( // (outside light chorus)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_02.sound_looping",
                        prefs.getMusicGain() + -6f // Gain -1 from default
                ),
                new TagMod( // (freeing marines low pulse)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_03.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -0.5 from default
                ),
                new TagMod( // (peril)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_04.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain -0.5 from default
                ),
                new TagMod( // (?)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_05.sound_looping",
                        prefs.getMusicGain() + -4f // Gain 0 from default
                ),
                new TagMod( // (?)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_06.sound_looping",
                        prefs.getMusicGain() + -3f // Gain 0 from default
                ),
                new TagMod( // (?)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_07.sound_looping",
                        prefs.getMusicGain() + -11f // Gain 0 from default
                ),
                new TagMod( // (?)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_08.sound_looping",
                        prefs.getMusicGain() + -5f // Gain 0 from default
                )
        };
    }
}
