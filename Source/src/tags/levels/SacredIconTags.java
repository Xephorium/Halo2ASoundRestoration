package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

public class SacredIconTags extends TagGroup {

    public SacredIconTags(RestorationPreferences prefs) {

        groupName = "Sacred Icon";

        recursePaths = new String[] {

                // Ambience
                "/sound_remastered/ambience/sentinelhq/insidehq/details",

                // Background Hum (New track good, but too quiet - makes spaces feel dead)
                "/sound_remastered/ambience/sentinelhq/insidehq/track1",

                // Machinery
                "/sound_remastered/ambience/device_machines/shq__key",
                "/sound_remastered/ambience/device_machines/shq_conduit_light",
                //"/sound_remastered/ambience/device_machines/shq_door_bsp_5_swap", // New tesla-y, but old SO tinny :(
                //"/sound_remastered/ambience/device_machines/shq_door_dam", // New tesla-y, old tinny
                "/sound_remastered/ambience/device_machines/shq_door_piston",
                "/sound_remastered/ambience/device_machines/shq_key_plug",
                "/sound_remastered/ambience/device_machines/shq_plug_door",
                "/sound_remastered/ambience/device_machines/shq_sentinel_doors",
                "/sound_remastered/ambience/device_machines/shq_the_plug"
        };

        preservePaths = new String[] {

                // Ambience
                "distorted_roomtone.sound",
                "swells.sound"
        };

        deletePaths = new String[] {

                // Ambience
                "/sound_remastered/ambience/sentinelhq/insidehq/details/tesla_lightning.sound"
        };

        tagMods = new TagMod[] {

                // Ambience
                new RecursiveTagMod("/sound_remastered/ambience/sound_scenery/blue_sparks", -4f),

                /*  Metal Clangs
                 *
                 *  Enforcer Debris is WAY too loud in this mission. Especially when on an elevator
                 *  or platform, it drowns out all other audio. However, actually fixing it has
                 *  been a nightmare. I dropped the gain of all sound files in "/sound/materials"
                 *  by 20db to no effect, which implies either the sounds are elsewhere, or gain
                 *  isn't used as expected for them. Wither way, the most similar audio I've found
                 *  is in "/sound/materials/hard/meta_thick" and "/sound/materials/hard/meta_thin".
                 */

                // Music
                new TagMod( // (first tube drop sting + ambient creepy loop)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_01.sound_looping",
                        prefs.getMusicGain() + -6f // Gain -1 from default
                ),
                new TagMod( // (stingers - swell6)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_02.sound_looping",
                        prefs.getMusicGain() + -3f // Gain 0 from default
                ),
                new TagMod( // (techno loop)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_03.sound_looping",
                        prefs.getMusicGain() + -8f // Gain 0 from default
                ),
                new TagMod( // (passing the wall)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_04.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -1.5 from default
                ),
                new TagMod( // (flood arrival drumline)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_05.sound_looping",
                        prefs.getMusicGain() + -6f // Gain 0 from default
                ),
                new TagMod( // (dark flood room)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_06.sound_looping",
                        prefs.getMusicGain() + -5f // Gain 0 from default
                ),
                new TagMod( // (other scary flood song)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_07.sound_looping",
                        prefs.getMusicGain() + -4f // Gain 0 from default
                ),
                new TagMod( // (very scary drop down final chute)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_08.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain -0.5 from default
                ),
                new TagMod( // (stinger)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_09.sound_looping",
                        prefs.getMusicGain() + -4f // Gain -1 from default
                ),
                new TagMod( // (strings w/ view of quarantine zone)
                        "/sound_remastered/scenarios/solo/06a_sentinelwalls/06a_music/06a_10.sound_looping",
                        prefs.getMusicGain() + -3.5f // Gain +0.5 from default
                ),
        };

        generateClassicMusicTagMods();

        if (prefs.isMusicOnly()) clearAllButMusicChanges();
    }
}
