package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagMod;
import tags.TagGroup;

public class CairoStationTags extends TagGroup {

    public CairoStationTags(RestorationPreferences prefs) {

        groupName = "Cairo Station";

        recursePaths = new String[] {

                // Ambience
                "/sound_remastered/ambience/spacestation",

                // Mac Gun
                "/sound_remastered/ambience/macgun",
                "/sound_remastered/ambience/device_machines/ss_gun_loader2",

                // Glass Break
                "/sound_remastered/materials/brittle/glass",

                /* Warning Alarm
                 *
                 * I've been mulling over whether to replace this sound since the project
                 * started. The remastered sound is *much* louder than the classic. But
                 * having played the remastered level a few times, I feel it adds to the
                 * urgency and sense that the station is in imminent danger. Gonna leave
                 * the path here regardless so I can live in indecision forever.
                 */
                "/sound_remastered/ambience/sound_scenery/klaxon"
        };

        preservePaths = new String[] {

                // Station Background Loops
                "spacestation\\loop.sound",
                "smallroom\\loop.sound",
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
        };

        tagMods = new TagMod[] {

                // Loud Dialog
                new TagMod("/sound/dialog/levels/01_spacestation/mission/l01_0470_ahp.sound", -4), // Gain -1 from default

                // Ambience
                new TagMod("/sound_remastered/ambience/spacestation/spacestation_bigroom/spacestation_bigroom.sound_looping", 0), // Gain +2 from default
                new TagMod("/sound_remastered/ambience/spacestation/spacestation_smallroom/spacestation_smallroom.sound_looping", -5), // Gain +3 from default

                // Muffled Boarding Action
                new TagMod("/sound_remastered/ambience/spacestation/behind_bulkhead/loop.sound", 2), // Gain +2 from default [0]

                // Station Doors (Gain -1 from default)
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/ss_door_int_mech_small", -1),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/ss_door_int_mech_large", -1),

                // Gun Racks
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/ss_gun_rack", 1),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/ss_gun_rack_slim", 1),

                // Mac Reload Volume (Gain -2.5 from default)
                new TagMod("/sound_remastered/ambience/device_machines/ss_gun_loader2/portclose/portclose.sound", -8.5f),
                new TagMod("/sound_remastered/ambience/device_machines/ss_gun_loader2/portopen/portopen.sound", -8.5f),
                new TagMod("/sound_remastered/ambience/device_machines/ss_gun_loader2/slugload/slugload.sound", -5.5f),
                new TagMod("/sound_remastered/ambience/device_machines/ss_gun_loader2/start_go.sound", -8.5f),
                new TagMod("/sound_remastered/ambience/device_machines/ss_gun_loader2/start_go2.sound", -8.5f),
                new TagMod("/sound_remastered/ambience/device_machines/ss_gun_loader2/stop.sound", -8.5f),
                new TagMod("/sound_remastered/ambience/device_machines/ss_gun_loader2/unlatch.sound", -8.5f),

                // Music
                new TagMod( // (moody ambience before door breach)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_01.sound_looping",
                        prefs.getMusicGain() + -15 // Gain -12 from default
                ),
                new TagMod( // (drumline after door breach)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_02.sound_looping",
                        prefs.getMusicGain() + -3 // Gain 0 from default
                ),
                new TagMod( // (melody after malta destroyed)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_03.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -2.5 from default
                ),
                new TagMod( // (athens stinger)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_04.sound_looping",
                        prefs.getMusicGain() + -5 // Gain -2 from default
                ),
                new TagMod( // (after gunny death)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_05.sound_looping",
                        prefs.getMusicGain() + -2f // Gain +1 from default [-3]
                ),
                new TagMod( // (spacewalk)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_06.sound_looping",
                        prefs.getMusicGain() + 3.5f // Gain +3.5 from default
                ),
                new TagMod( // (bugger stinger)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_07.sound_looping",
                        prefs.getMusicGain() + -7 // Gain -1 from default
                ),
                new TagMod( // (bomb showdown jam)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_08.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -1.5 from default
                )
        };

        generateClassicMusicTagMods();
    }
}
