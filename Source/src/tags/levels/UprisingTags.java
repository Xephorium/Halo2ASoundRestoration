package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

import java.util.List;

public class UprisingTags extends TagGroup {

    public UprisingTags(RestorationPreferences prefs) {

        groupName = "Uprising";

        recursePaths = new String[] {

                // Stone Doors
                "/sound_remastered/ambience/device_machines/stone_doors_loop"
        };

        tagMods = new TagMod[] {

                // Dialog
                // "By the prophets, what have these brutes done?"
                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0300_dog.sound", 8.0f),
                // "They have shed our brothers' blood and for that, they must die."
                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0310_dog.sound", 8.0f),
                // "A Zealot? So much for a stealthy advance."
                new RecursiveTagMod("/sound/dialog/levels/08_controlroom/mission/l08_0310_dog.sound", 9.0f),

                // Music
                // There are some weird music transitions in the beginning of this mission that seem to have
                // nothing to do with my changes. The volume of all music, regardless of gain level suddenly
                // drops when you approach the first door of the level. Then, the music abruptly picks up
                // volume again on the other side of the tunnel. The behavior even exists in classic. I wonder
                // if this is an MCC bug?
                new TagMod( // (opening strings)
                        "/sound_remastered/scenarios/solo/08a_deltacliffs/08a_music/08a_01.sound_looping",
                        prefs.getMusicGain() + -6.5f // Gain -1.5 from default
                ),
                new TagMod( // (drum overlay on opening strings)
                        "/sound_remastered/scenarios/solo/08a_deltacliffs/08a_music/08a_02.sound_looping",
                        prefs.getMusicGain() + -8.5f // Gain -1.5 from default
                ),
                new TagMod( // (ghost ride)
                        "/sound_remastered/scenarios/solo/08a_deltacliffs/08a_music/08a_03.sound_looping",
                        prefs.getMusicGain() + -6.5f // Gain -1.5 from default
                ),
                new TagMod( // (drums w/ strings)
                        "/sound_remastered/scenarios/solo/08a_deltacliffs/08a_music/08a_04.sound_looping",
                        prefs.getMusicGain() + -6.5f // Gain -1.5 from default
                ),
                new TagMod( // (final cinematic)
                        "/sound_remastered/scenarios/solo/08a_deltacliffs/08a_music/08a_05.sound_looping",
                        prefs.getMusicGain() + -3f // Gain 0 from default
                ),
        };
    }
}
