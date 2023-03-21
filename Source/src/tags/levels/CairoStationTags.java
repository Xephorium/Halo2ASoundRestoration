package tags.levels;

import tags.TagModification;
import tags.TagGroup;

import static tags.TagModification.NO_CHANGE;

public class CairoStationTags extends TagGroup {

    public CairoStationTags() {

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

                // Music
                new TagModification(
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_01.sound_looping",
                        -10 // Gain -7 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_02.sound_looping",
                        -4 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_03.sound_looping",
                        -4 // Gain -1 from default
                ),
                new TagModification(
                        "/sound_remastered/scenarios/solo/01b_spacestation/01b_music/01b_04.sound_looping",
                        -4 // Gain -1 from default
                )
        };
    }
}
