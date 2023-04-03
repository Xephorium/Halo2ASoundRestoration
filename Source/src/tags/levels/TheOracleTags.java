package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

public class TheOracleTags extends TagGroup {

    public TheOracleTags(RestorationPreferences prefs) {

        groupName = "The Oracle";

        recursePaths = new String[] {

                // Silo Arm
                "/sound_remastered/ambience/device_machines/silo_arm",

                // Silo Elevator
                "/sound_remastered/ambience/device_machines/silo_elevator_loop",

                // Ambient Core Proximity Sounds
                "/sound_remastered/ambience/sound_scenery/alphagas_closetocore",

                // Cable Elevator
                "/sound_remastered/ambience/device_machines/control_lift",
        };

        replacePaths = new String[] {

                /* Flood Room Spooky Song
                 *
                 * So there's some weirdness here. This song plays in the room where you fight waves of
                 * flood mid-mission and is wrong in the remaster. Classic sounds like a spooky ambient
                 * track from Alien (static, light percussion) and anniversary is more of a light electronic
                 * pulse. I tried replacing the new track with classic track below and it fixes the room!
                 * But apparently the same sound loop is used later in the mission when you *should* hear
                 * the electric pulse when confronting the Heretic. So to preserve that moment, I've
                 * chosen to just leave the flood room alone. Not sure why the remaster consolidates these
                 * two very different tracks to one, but that's the situation. :/
                 */
//                "/sound_remastered/music/ambient/flashback/fb/loop.sound",
//                "/sound_remastered/music/06_bdfull/bdfull/loop.sound",
//                "/sound_remastered/music/ambient/flashback/fb/alt_loop.sound",
//                "/sound_remastered/music/06_bdfull/bdfull/alt_loop.sound"
        };

        tagMods = new TagMod[] {

                // Ambience
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/alphagas_interior_implode", -1),
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/alphagas_falling_exterior", -1),
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/floodlab_tank/floodlab_tank", -1.5f, false, true),
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/energyroom", -0.5f),
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/alphagas_closetocore/closetocore/loop.sound", -0.5f),
                new RecursiveTagMod("/sound_remastered/visual_effects/alphagas_explosions.sound", -0.5f),

                // Cable Snaps
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/cable_snaps", -0.5f),
                new RecursiveTagMod("/sound_remastered/visual_effects/alphagas_cablelist", -1.5f),

                // Shipmaster Dialog
                // "All my phantoms are in the air, Arbiter! Go ahead - cut the cable."
                new RecursiveTagMod("/sound/dialog/levels/04_gasgiant/mission/l04_0170_soc.sound", 1),
                // "That's one. By the prophets, look at the station list!"
                new RecursiveTagMod("/sound/dialog/levels/04_gasgiant/mission/l04_1380_soc.sound", 2),
                // "One final cable, Arbiter."
                new RecursiveTagMod("/sound/dialog/levels/04_gasgiant/mission/l04_1400_soc.sound", 2),
                // "That did it! The station is in free-fall."
                new RecursiveTagMod("/sound/dialog/levels/04_gasgiant/mission/l04_0180_soc.sound", 3),
                // "The heretic leader is on the move. DO NOT let him escape! We'll stay with you as long as we can."
                new RecursiveTagMod("/sound/dialog/levels/04_gasgiant/mission/l04_0190_soc.sound", 2),
                // "Are you still alive, Arbiter?"
                new RecursiveTagMod("/sound/dialog/levels/04_gasgiant/mission/l04_0770_soc.sound", 2),
                // "We're keeping pace as best we can."
                new RecursiveTagMod("/sound/dialog/levels/04_gasgiant/mission/l04_0780_soc.sound", 2),
                // "What lunacy! He'll never escape this maelstrom in a banshee."
                new RecursiveTagMod("/sound/dialog/levels/04_gasgiant/mission/l04_0790_soc.sound", 3),
                // "Wait, the hangar! There was a seraph fighter inside. Arby, you know what to do!"
                new RecursiveTagMod("/sound/dialog/levels/04_gasgiant/mission/l04_1410_soc.sound", 3),

                // Music
                new TagMod( // (heretic holograph)
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_01.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -0.5f from default
                ),
                new TagMod( // (elevator women creepy vocals)
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_02.sound_looping",
                        prefs.getMusicGain() + -6.5f // Gain -3.5 from default
                ),
                new TagMod( // (flood room loop)
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_04.sound_looping",
                        prefs.getMusicGain() + -6.5f // Gain -0.5 from default
                ),
                new RecursiveTagMod("/sound_remastered/music/06_bdfull/bdfull/alt_loop.sound", -1f),
                new TagMod( // (rattlesnake while heading up to the cable)
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_05.sound_looping",
                        prefs.getMusicGain() + -9 // Gain 0 from default
                ),
                new TagMod( // (vocals before final cable cut)
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_06.sound_looping",
                        prefs.getMusicGain() + -3.5f // Gain +1.5 from default
                ),
                new TagMod( // (cable snap stinger)
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_07.sound_looping",
                        prefs.getMusicGain() + 0.5f // Gain +0.5 from default
                ),
                new TagMod( // (final hallway run)
                        "/sound_remastered/scenarios/solo/04b_floodlab/04b_music/04b_08.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -0.5 from default
                ),
                new RecursiveTagMod("/sound_remastered/music/19_rhythmshad/19rshd/alt_loop.sound", -0.5f),
                new RecursiveTagMod("/sound_remastered/music/19_rhythmshad/19rshd/alt_out.sound", -0.5f),
                new RecursiveTagMod("/sound_remastered/music/19_rhythmshad/19rshd/alt_trans_in.sound", -0.5f),
        };
    }
}
