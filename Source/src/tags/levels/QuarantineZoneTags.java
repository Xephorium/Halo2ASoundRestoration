package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagMod;

public class QuarantineZoneTags extends TagGroup {

    public QuarantineZoneTags(RestorationPreferences prefs) {

        groupName = "Quarantine Zone";

        tagMods = new TagMod[] {

                // Music
                new TagMod( // (insane guitar shredding)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_01.sound_looping",
                        prefs.getMusicGain() + -6.5f // Gain +0.5 from default
                ),
                new TagMod( // (tank tunnel drumline)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_02.sound_looping",
                        prefs.getMusicGain() + -7.5f // Gain -1.5 from default
                ),
                new TagMod( // (flood ruins strings)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_03.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain +0.5 from default
                ),
                /* RIP Sadstrings
                 *
                 * 06b_04, or "sound\music\sadstrings_nl\notaloop\loop.sound" is the track that *should* play
                 * when the player passes through a tunnel near the end of the mission to arrive at a wide
                 * chasm. It's where the shipmaster is dropped off again with a new spectre and provides a
                 * tonal reprieve as the mission approaches its end. However, Saber removed this track and
                 * consolidated the gameplay hook with 06b_05, the track that plays when the Arbiter arrives
                 * at the forerunner tram. Given that 06b_05 plays through a significant portion of the end
                 * of the mission, I need to leave it alone. You will be missed, chasm sadstrings. <3
                 */
                new TagMod( // (tram ethereal melody)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_05.sound_looping",
                        prefs.getMusicGain() + -3f // Gain +1 from default
                ),
                new TagMod( // (tram drums)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_06.sound_looping",
                        prefs.getMusicGain() + -5f // Gain -1 from default
                ),
                new TagMod( // (icon room choir)
                        "/sound_remastered/scenarios/solo/06b_floodzone/06b_music/06b_07.sound_looping",
                        prefs.getMusicGain() + -2.5f // Gain +0.5 from default
                ),
        };

        generateClassicMusicTagMods();
    }
}
