package tags;

public class CairoStationTags extends TagGroup {

    public CairoStationTags() {

        recursePaths = new String[] {

                "/sound_remastered/ambience/spacestation",
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
    }
}
