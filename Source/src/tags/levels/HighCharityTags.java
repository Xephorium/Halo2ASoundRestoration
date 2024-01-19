package tags.levels;

import prefs.RestorationPreferences;
import tags.RecursiveTagMod;
import tags.TagGroup;
import tags.TagMod;

public class HighCharityTags extends TagGroup {

    public HighCharityTags(RestorationPreferences prefs) {

        groupName = "High Charity";

        recursePaths = new String[] { };

        tagMods = new TagMod[] {

                // Sanctum Lift
                new RecursiveTagMod(
                        "/sound_remastered/ambience/device_machines/high_sanctum_lift/high_sanctum_lift.sound_looping",
                        -2.5f,
                        false,
                        true
                ),

                // Dialog
                // "Flood controlled dropships are touching down all over the city!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2680_cor.sound", 3f),
                // "That creature beneath the library, that Gravemind? Used us. You were just a diversion - In Amber Clad was always its intended vector."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2690_cor.sound", 3f),
                // "There's a conduit connecting this tower to the ship - head back inside, I'll lead you to it."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2710_cor.sound", 3f),
                // TODO - "We don't have time for this Chief! Truth's phantom is nearing the Forerunner ship!"
                // "I'll disable this lift once you reach the top - that'll slow them down, I hope."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2720_cor.sound", 3.5f),
                // "Shall we let the flood consume our holy city? ..."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0280_pot.sound", -1.0f),
                // "No enemy has ever withstood our might! The flood too shall fail."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0200_pot.sound", -1.5f),
                // "I'll do what I can to low the launch sequence..."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2740_cor.sound", 4.0f),
                // "For a Covenant construct, it's unusually formidable."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2750_cor.sound", 4.0f),
                // "The parasite did not defeat the forerunners. And it shall not defeat us!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0190_pot.sound", -0.5f),
                // "Arrogant creature! Your deaths will be instantaneous while we shall suffer the progress of infinitude."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_5010_grv.sound", 1.0f),
                // "The security systems in this part of the tower are particularly robust."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2760_cor.sound", 4.0f),
                // "Whosoever is gripped by fear, take head! I am the prophet of Truth and I am not afraid."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0210_pot.sound", 2.0f),
                // "Noble Mercy is here at my side. His wise counsel ever in my ears."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0270_pot.sound", 2.0f),
                // "We exist together now - two corpses, one grave."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_5000_grv.sound", 1.5f),
                // "Apparently these are the prohpet hierarch's private quarters - their inner sanctum."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2770_cor.sound", 4.0f),
                // "This crisis will not be the end of us! It is but..."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0190_pot.sound", 0.5f),
                // "Brute and elite ships are engaging one another all around High Charity."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2880_cor.sound", 4.5f),
                // "I'm running out of options, Chief! I can't stall the launch sequence much longer. The next lift will take you up to the conduit - hurry!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2910_cor.sound", 4.0f),
                // "I knew the covenant was good at repurposing forerunner technology, but..."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2920_cor.sound", 4.0f),
                // "The ship isn't so much launching as disengaging..."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2930_cor.sound", 4.0f),
                // "Stopping Truth - that's all that matters."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2980_cor.sound", 4.0f),
                // "Now is the time of our unworlding - the moment we shall all become as gods."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0330_pot.sound", -0.5f),
                // "You know the plan - you've got to stop Truth, I've got to handle the index. Go!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_3030_cor.sound", 4.0f),
                // "If you would falter, know this! One final effort is all that remains."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_0260_pot.sound", -1.0f),
                // "Forget about the flood - you've got to get aboard!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2960_cor.sound", 4.0f),
                // "Ignore the Brutes - get into the conduit!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2970_cor.sound", 4.0f),
                // "Jump in Chief, now!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_2990_cor.sound", 4.0f),
                // "Chief, leave me!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_3000_cor.sound", 4.0f),
                // "I can't go with you!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_3010_cor.sound", 4.0f),
                // "I have to stay here, Chief."
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_3020_cor.sound", 4.0f),
                // "We're out of time Chief, into the conduit!"
                new RecursiveTagMod("/sound/dialog/levels/07_highcharity/mission/l07_3040_cor.sound", 4.0f),


                // Music
                new TagMod( // (opening strings)
                        "/sound_remastered/scenarios/solo/07b_forerunnership/07b_music/07b_01.sound_looping",
                        prefs.getMusicGain() + -3.5f // Gain -0.5 from default
                ),
                new TagMod( // (opening groovy techno beat)
                        "/sound_remastered/scenarios/solo/07b_forerunnership/07b_music/07b_02.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain -0.5 from default
                ),
                new TagMod( // (spooky low moans)
                        "/sound_remastered/scenarios/solo/07b_forerunnership/07b_music/07b_03.sound_looping",
                        prefs.getMusicGain() + -4f // Gain -1.0 from default
                ),
                new TagMod( // (spooky women's choir)
                        "/sound_remastered/scenarios/solo/07b_forerunnership/07b_music/07b_04.sound_looping",
                        prefs.getMusicGain() + -3.5f // Gain -0.5 from default
                ),
                new TagMod( // (spooky background pulse)
                        "/sound_remastered/scenarios/solo/07b_forerunnership/07b_music/07b_05.sound_looping",
                        prefs.getMusicGain() + -3.5f // Gain -0.5 from default
                ),
                new TagMod( // (spooky distorted percussion)
                        "/sound_remastered/scenarios/solo/07b_forerunnership/07b_music/07b_06.sound_looping",
                        prefs.getMusicGain() + -5f // Gain -2.0 from default
                ),
        };

        generateClassicMusicTagMods();

        if (prefs.isMusicOnly()) clearAllButMusicChanges();
    }
}
