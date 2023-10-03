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

        tagMods = new TagMod[] {

                // All Cortana Dialog
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission", 3.0f, "_cor"),

                // All Tartarus Dialog
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission", -2.5f, "_bth"),

                // All Truth Dialog
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission", -1.0f, "_pot"),

//                // TartarusDialog
//                // "The demon has infiltrated the council chamber!"
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_1000_bth.sound", -2.5f),
//                // "Protect the hierarchs! Seal the exits!"
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_1010_bth.sound", -2.5f),

                // Cortana Adjustments
                // Cortana Brute Exclamations
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2070_cor.sound", -1.5f),
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2080_cor.sound", -1.5f),
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2090_cor.sound", -1.5f),
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2100_cor.sound", -1.5f),
                // "Oh, I don't think so."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2190_cor.sound", -1.5f),
                // "Put me down on one of the pedestals near the door."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2110_cor.sound", -1.5f),
                // "That prophet, Truth - he has the index! You've got to take it from him."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2150_cor.sound", -2.0f),
                // "Let me get these doors."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2150_cor.sound", -2.0f),
//                // "Go. It'll be easier to track Truth if I stay in the network."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2140_cor.sound", -0.5f),
//                // "Don't worry, you can pick me up later."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2180_cor.sound", -0.5f),
//                // "Right this way."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2060_cor.sound", -0.5f),
                // "Truth is moving through the lower levels of the tower."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2210_cor.sound", 1.0f),
                // "I'll reverse this grav lift. Drop down, try to cut him off."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2220_cor.sound", 1.0f),
                // "It's safe really, just step in!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2230_cor.sound", 1.0f),
                // "After that stunt on the Cairo, I know you're not afraid of heights."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2240_cor.sound", 1.0f),
                // "Fine! I won't watch. Meet you aat the bottom, okay?"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2250_cor.sound", 1.0f),
//                // "Reinforce all approaches to the holding pens!"
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_1020_bth.sound", -1f),
//                // "Slay the demon on sight."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_1030_bth.sound", -1.5f),
                // "They're beefing up their patrols. Stay sharp."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2260_cor.sound", 1.0f),
//                // "Wait a minute! I'm reading marine IFF transponders."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2270_cor.sound", 1f),
//                // "The signals are originating somewhere below your position.
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2280_cor.sound", 1f),
//                // "There's another lift in the next room.
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2290_cor.sound", 1f),
//                // "Here Chief, jump in!" - ok
//                // "There are two groups of marines in this detention block."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2310_cor.sound", 0.5f),
//                // "I'll zero their locations. You neutralize the guards... Quietly."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2320_cor.sound", 0.5f),
//                // "Chief, come to the lower level."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2370_cor.sound", 0.5f),
//                // "Come to the middle level, chief."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2350_cor.sound", 1f),
//                // "The marines are just inside - careful of the guards."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2360_cor.sound", 1f),
//                // "Listen up marines! The Chief's hunting a prophet. And you're going to help him kill it."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2390_cor.sound", 1f),
//                // "Chief, come to the lower level."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2370_cor.sound", 1f),
//                // "More guards - get ready."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2380_cor.sound", 1f),
//                // "That's all of the marines, Chief. Good work."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2410_cor.sound", 1f),
//                // "We'll get out of here the same way we came in: the central grav lift."
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2420_cor.sound", 1f),
//                // "Hostile reinforcements, coming down the lift!"
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2430_cor.sound", 1f),
//                // "Get moving" phrases
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2440_cor.sound", 1f),
//                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2450_cor.sound", 1f),
//                // "Fear not, my brothers. The sacred icon is secure."
//                // "It was Tartarus and the brutes who took the icon from the flood. For that, they have our thanks."
                // "Excellent, Truth is broadcasting on the move! It'll make him much easier to track.
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2460_cor.sound", 0.5f),
                // "This way, Chief."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2000_cor.sound", 1.0f),

                // Truth Adjustments
                // "Fear not my brothers, the Sacred Icon is secure."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0140_pot.sound", 0.5f),
                // "It was Tartarus and his brutes who took the Icon from the flood. For that they have our thanks."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0150_pot.sound", 0.5f),
                // "Who would doubt the prophets? What have we foretold that has not come to pass?"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0300_pot.sound", 0.25f),

                // Music
                new TagMod( // (first room bassy groove)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_01.sound_looping",
                        prefs.getMusicGain() + -7.75f // Gain -2.75 from default
                ),
                new TagMod( // (outside light chorus)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_02.sound_looping",
                        prefs.getMusicGain() + -7f // Gain -2 from default
                ),
                new TagMod( // (freeing marines low pulse)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_03.sound_looping",
                        prefs.getMusicGain() + -6f // Gain -1 from default
                ),
                new TagMod( // (peril)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_04.sound_looping",
                        prefs.getMusicGain() + -4f // Gain 0 from default
                ),
                new TagMod( // (In Amber Clad arrival strings)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_05.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -1.5 from default
                ),
                new TagMod( // (ethereal platform voices <3)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_06.sound_looping",
                        prefs.getMusicGain() + -3.5f // Gain -0.5 from default
                ),
                new TagMod( // (rumpus room)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_07.sound_looping",
                        prefs.getMusicGain() + -11.5f // Gain -0.5 from default
                ),
                new TagMod( // (final strings)
                        "/sound_remastered/scenarios/solo/07a_highcharity/07a_music/07a_08.sound_looping",
                        prefs.getMusicGain() + -6f // Gain -1.0 from default
                )
        };
    }
}
