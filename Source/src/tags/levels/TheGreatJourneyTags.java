package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

import static tags.TagMod.NO_CHANGE;

public class TheGreatJourneyTags extends TagGroup {

    public TheGreatJourneyTags(RestorationPreferences prefs) {

        groupName = "The Great Journey";

        recursePaths = new String[] {

                // Control Room Grav Lift
                "/sound_remastered/ambience/device_machines/tartarus_grav_lift",
        };

        replacePaths = new String[] {

                // Tartarus Hammer Hit (Yes, tartarus is spelled wrong in the dest path.)
                "/sound/characters/brute/tartarus_hits.sound",
                "/sound_remastered/characters/brute/tatarus_hammer_hits/hits_positional.sound"
        };

        deletePaths = new String[] {

                // Control Room Grav Lift Extra
                "/sound_remastered/ambience/device_machines/tartarus_grav_lift/grav_lift/in.sound",
                "/sound_remastered/ambience/device_machines/tartarus_grav_lift/grav_lift/out.sound",
        };

        tagMods = new TagMod[] {

                // Tartarus Hammer Hits
                new TagMod("/sound_remastered/characters/brute/tatarus_hammer_hits/hits_positional.sound", -2f, 5f, 120f, NO_CHANGE),
                new TagMod("/sound_remastered/characters/brute/tatarus_hammer_hits/hits_stereo.sound", -2f, 5f, 120f, NO_CHANGE),

                // Johnson Beam Rifle (Gain +2 from default)
                new TagMod("/sound_remastered/weapons/beam_rifle/beam_rifle_fire_noplayer.sound", -1f, 1f, 300f, NO_CHANGE),

                // Dialog
                // Note: Dialog gain changes once again seem to have no effect. I'm tired of messing with
                //       it and moving on. Ready to call this project DONE.
                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission", 5.0f),
                // "There, Arbiter! That scarab's main gun will break the control room's doors."
                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0520_soc.sound", 1.0f),
                // "At the far end of the beach..."
                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0530_soc.sound", 1.0f),
                // "The Brutes control that cruiser, Arbiter! I'm going to remain here..."
                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0540_soc.sound", 1.0f),
                // "Go! Kill Tartarus, for the Elites, for the Covenant!"
                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0550_soc.sound", 1.0f),
//                // "The arbiter? I thought he was dead! Hold your fire!"
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0560_dog.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0560_scl.sound", 2.0f),
//                // "The hunters have come to our aid, Arbiter. They will fight by our side."
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0570_dog.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0570_scl.sound", 2.0f),
//                // "Free our brothers! Death to the Brutes!"
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0610_dog.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0610_scl.sound", 2.0f),
//                // "Mine will do. Kill the others."
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0050_tar.sound", 2.0f),
//                // "Yes, chieftan!"
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0060_bth.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0060_crl.sound", 2.0f),
//                // "A day's rations says I do this in one cut."
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0070_bth.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0070_crl.sound", 2.0f),
//                // "Two cuts at least!"
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0090_bth.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0090_crl.sound", 2.0f),
//                // "Done."
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0100_bth.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0100_crl.sound", 2.0f),
//                // "Wait! Movement."
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0110_bth.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0110_crl.sound", 2.0f),
//                // "Elites!"
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0400_bth.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0400_crl.sound", 2.0f),
//                // "Take my banshee, Arbiter!"
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0800_dog.sound", 2.0f),
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0800_scl.sound", 2.0f),
//                // "Stay clear of the door."
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0840_jon.sound", 2.0f),
//                // "Hey, bastards. Knock knock."
//                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0850_jon.sound", 2.0f),

                // Music
                new TagMod( // (rolling thunder)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_01.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain -0.5 from default
                ),
                new TagMod( // (end of rolling thunder strings)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_02.sound_looping",
                        prefs.getMusicGain() + -4f // Gain -1 from default
                ),
                new TagMod( // (hunter bridge spooky ambient)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_03.sound_looping",
                        prefs.getMusicGain() + -3f // Gain 0 from default
                ),
                new TagMod( // (hunter bridge drums)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_04.sound_looping",
                        prefs.getMusicGain() + -4f // Gain 0 from default
                ),
                new TagMod( // (prison room pulse)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_05.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -1.5 from default
                ),
                new TagMod( // (scarab walk)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_06.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain -0.5 from default
                ),
                new TagMod( // (scarab fire at door stinger)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_07.sound_looping",
                        prefs.getMusicGain() + -5f // Gain -2 from default
                ),
                new TagMod( // (landing - delusions & grandeur)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_08.sound_looping",
                        prefs.getMusicGain() + -2.5f // Gain +0.5 from default
                ),
                new TagMod( // (ominous chamber entrance fight music)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_09.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain -1.5 from default
                ),
                new TagMod( // (more ominous chamber entrance fight drums)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_10.sound_looping",
                        prefs.getMusicGain() + -7.5f // Gain -2.5 from default
                ),
                new TagMod( // (tartarus fight - before jump)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_11.sound_looping",
                        prefs.getMusicGain() + -3f // Gain 0 from default
                ),
                new TagMod( // (tartarus fight - spooky women's choir after jump)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_12.sound_looping",
                        prefs.getMusicGain() + -7.5f // Gain -0.5 from default
                ),
                new TagMod( // (tartarus fight - low pulse)
                        "/sound_remastered/scenarios/solo/08b_deltacontrol/08b_music/08b_13.sound_looping",
                        prefs.getMusicGain() + -5f // Gain -1 from default
                ),
        };

        generateClassicMusicTagMods();
    }
}
