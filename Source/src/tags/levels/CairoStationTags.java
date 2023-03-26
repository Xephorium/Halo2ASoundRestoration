package tags.levels;

import prefs.RestorationPreferences;
import tags.TagModification;
import tags.TagGroup;

import static tags.TagModification.NO_CHANGE;

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

        tagModifications = new TagModification[] {

                // Ambience
                new TagModification(
                        "/sound_remastered/ambience/spacestation/spacestation_bigroom/spacestation_bigroom.sound_looping",
                        -1 // Gain +1 from default
                ),
                new TagModification(
                        "/sound_remastered/ambience/spacestation/spacestation_smallroom/spacestation_smallroom.sound_looping",
                        -7 // Gain +1 from default
                ),

                // Muffled Boarding Action
                new TagModification("/sound_remastered/ambience/spacestation/behind_bulkhead/loop.sound", 1), // Gain +1 from default [0]

                // Small Doors (Gain -1 from default [-7] for entire directory)
                new TagModification("/sound_remastered/ambience/device_machines/ss_door_int_mech_small", -1, true),

                // Mac Reload Volume (Gain -2 from default)
                new TagModification("/sound_remastered/ambience/device_machines/ss_gun_loader2/portclose/portclose.sound", -8),
                new TagModification("/sound_remastered/ambience/device_machines/ss_gun_loader2/portopen/portopen.sound", -8),
                new TagModification("/sound_remastered/ambience/device_machines/ss_gun_loader2/slugload/slugload.sound", -5),
                new TagModification("/sound_remastered/ambience/device_machines/ss_gun_loader2/start_go.sound", -8),
                new TagModification("/sound_remastered/ambience/device_machines/ss_gun_loader2/start_go2.sound", -8),
                new TagModification("/sound_remastered/ambience/device_machines/ss_gun_loader2/stop.sound", -8),
                new TagModification("/sound_remastered/ambience/device_machines/ss_gun_loader2/unlatch.sound", -8),

                // Music
                new TagModification( // (moody ambience before door breach)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_01.sound_looping",
                        prefs.getMusicGain() + -10 // Gain -7 from default
                ),
                new TagModification( // (drumline after door breach)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_02.sound_looping",
                        prefs.getMusicGain() + -2.5f // Gain +0.5 from default
                ),
                new TagModification( // (melody after malta destroyed)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_03.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -2.5 from default
                ),
                new TagModification( // (athens stinger)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_04.sound_looping",
                        prefs.getMusicGain() + -5 // Gain -2 from default
                ),
                new TagModification( // (after gunny death)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_05.sound_looping",
                        prefs.getMusicGain() + -1.5f // Gain +1.5 from default [-3]
                ),
                new TagModification( // (spacewalk)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_06.sound_looping",
                        prefs.getMusicGain() + 3 // Gain +3 from default
                ),
                new TagModification( // (bugger stinger)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_07.sound_looping",
                        prefs.getMusicGain() + -7 // Gain -1 from default
                ),
                new TagModification( // (bomb showdown jam)
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_08.sound_looping",
                        prefs.getMusicGain() + -5 // Gain -1 from default
                )
        };
    }
}
