package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

public class TheArbiterTags extends TagGroup {

    public TheArbiterTags(RestorationPreferences prefs) {

        groupName = "The Arbiter";

        recursePaths = new String[] {

                // Doors
                "/sound_remastered/ambience/device_machines/door_tron_lg",
                "/sound_remastered/ambience/device_machines/door_tron_lg_containment_shield",
                "/sound_remastered/ambience/device_machines/door_tron_sm",

                // Holographic Buttons
                "/sound_remastered/ambience/device_machines/holo_button_push",

                // Holographic Drone
                "/sound_remastered/ambience/device_machines/holo_drone",
                "/sound_remastered/ambience/device_machines/holo_drone_death",

                // Elevator
                "/sound_remastered/ambience/device_machines/delta_elevator",

                // Hangar Door
                "/sound_remastered/ambience/device_machines/hangar_door",

                // Ambient Storm Lightning
                "/sound_remastered/ambience/alphagasgiant/alpha_storm/details",

                // Ambient Conveyor Rooms
                "/sound_remastered/ambience/alphagasgiant/alphagas_conveyrooms",

                // Other Ambient
                "/sound_remastered/ambience/sound_scenery/alphagas_closetocore",
                "/sound_remastered/ambience/sound_scenery/alphagas_core",
                "/sound_remastered/ambience/device_machines/alphagas_generator_plates",
        };

        tagMods = new TagMod[]{

                // Ambience
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/alpha_storm/details", -1),
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/alpha_storm/storm", -0.5f),
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/alphagas_bigmachineroom", -0.5f),
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/alphagas_exterior", -0.5f),
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/alphagas_exterior/details/alphagas_exterior_windwhistle.sound", -1f),
                new RecursiveTagMod("/sound_remastered/ambience/alphagasgiant/alphagas_inside/details", -0.5f),
                new TagMod("/sound_remastered/ambience/alphagasgiant/alphagas_inside/details/alphagas_inside_details.sound", -7f), // Gain -1 from default

                // Doors
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/door_tron_lg", -1),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/door_tron_lg_containment_shield", -1f),
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/door_tron_sm", -1f),

                // Music
                new TagMod( // (spooky hangar music)
                        "/sound_remastered/scenarios/solo/04a_gasgiant/04a_music/04a_01.sound_looping",
                        prefs.getMusicGain() + -3.5f // Gain -0.5 from default
                ),
                new TagMod( // (Battery room jam)
                        "/sound_remastered/scenarios/solo/04a_gasgiant/04a_music/04a_02.sound_looping",
                        prefs.getMusicGain() + -4f // Gain +1 from default
                ),
                new TagMod( // (heretic hops in a banshee)
                        "/sound_remastered/scenarios/solo/04a_gasgiant/04a_music/04a_03.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain -0.5 from default
                ),
                new TagMod( // (banshee battle)
                        "/sound_remastered/scenarios/solo/04a_gasgiant/04a_music/04a_04.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -2.5 from default
                ),
        };

        generateClassicMusicTagMods();

        if (prefs.isMusicOnly()) clearAllButMusicChanges();
    }
}
