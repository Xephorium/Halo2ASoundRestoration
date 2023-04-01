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

                // Other Ambient
                "/sound_remastered/ambience/sound_scenery/alphagas_closetocore",
                "/sound_remastered/ambience/sound_scenery/alphagas_core",
                "/sound_remastered/ambience/device_machines/alphagas_generator_plates",
        };

        tagMods = new TagMod[]{

                // Holographic Button (Gain +2 from default)
                new RecursiveTagMod("/sound_remastered/ambience/device_machines/holo_button_push", 2f),

                // Music
                new TagMod(
                        "/sound_remastered/scenarios/solo/04a_gasgiant/04a_music/04a_01.sound_looping",
                        prefs.getMusicGain() + -3 // Gain 0 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/04a_gasgiant/04a_music/04a_02.sound_looping",
                        prefs.getMusicGain() + -3 // Gain +2 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/04a_gasgiant/04a_music/04a_03.sound_looping",
                        prefs.getMusicGain() + -5 // Gain -1 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/04a_gasgiant/04a_music/04a_04.sound_looping",
                        prefs.getMusicGain() + -5 // Gain -2 from default
                ),
        };
    }
}
