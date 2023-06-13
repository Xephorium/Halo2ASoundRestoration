package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagMod;

public class QuarantineZoneTags extends TagGroup {

    public QuarantineZoneTags(RestorationPreferences prefs) {

        groupName = "Quarantine Zone";

        recursePaths = new String[] {};

        preservePaths = new String[] {};

        deletePaths = new String[] {};

        tagMods = new TagMod[] {

                // Music
                new TagMod( // (insane guitar shredding)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_01.sound_looping",
                        prefs.getMusicGain() + -7f // Gain 0 from default
                ),
                new TagMod( // (tank tunnel drumline)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_02.sound_looping",
                        prefs.getMusicGain() + -7f // Gain -1 from default
                ),
                new TagMod( // (flood ruins strings)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_03.sound_looping",
                        prefs.getMusicGain() + -5f // Gain 0 from default
                ),
                // (tank chasm soothing air)
                // INCORRECT IN REMASTER - Alt needs to be manually changed to "sound\music\sadstrings_nl\notaloop\loop.sound_loop"
                // with loop cleared
                // ---
                // DAMMIT, they did it again! Removed 06b_04, moved the song references to 06b_05, and
                // then consolidated both. I don't understand and hate this. 06b_05 plays before the
                // tram cinematic and then through the whole ride, so I have no choice but to use the
                // proper track. RIP beautiful chasm melody.
                // ---
                // Trying to directly restore 06b_04 with the chasm melody. Pray for me...
                // ---
                // Nope. sadstrings is dead. :'(
                new TagMod(
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_05.sound_looping",
                        prefs.getMusicGain() + -3f // Gain +1 from default
                ),
                new TagMod( // (tram drums)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_06.sound_looping",
                        prefs.getMusicGain() + -5f // Gain -1 from default
                ),
                new TagMod( // (icon room choir)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_07.sound_looping",
                        prefs.getMusicGain() + -3f // Gain 0 from default
                ),
        };
    }
}
