package tags.levels;

import prefs.RestorationPreferences;
import tags.TagGroup;
import tags.TagMod;

import static tags.TagMod.NO_CHANGE;

public class MetropolisTags extends TagGroup {

    public MetropolisTags(RestorationPreferences prefs) {

        groupName = "Metropolis";

        replacePaths = new String[] {

                /* Bridge Overhead Light Explosions
                 *
                 * The remaster adds an explosion sound to the lights you can blow up along
                 * the bridge, but it doesn't sound quite right to me (regardless of volume).
                 * So, I'm replacing it with the classic turret explosion sound.
                 */
                "/sound/vehicles/damage_effects/h_turret_explosion.sound",
                "/sound_remastered/visual_effects/bridge_light_destroyed.sound"
        };

        tagMods = new TagMod[] {

                // Music
                new TagMod(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_01.sound_looping",
                        prefs.getMusicGain() + -2.5f // Gain +0.5 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_02.sound_looping",
                        prefs.getMusicGain() + -3.5f // Gain +1.5 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_03.sound_looping",
                        prefs.getMusicGain() + -4.5f // Gain -1.5 from default
                ),
                new TagMod(
                        "/sound_remastered/scenarios/solo/03b_newmombasa/03b_music/03b_04.sound_looping",
                        prefs.getMusicGain() + -5.5f // Gain -0.5 from default
                ),

                // Bridge Overhead Light Explosions (Gain -2 from default)
                new TagMod("/sound_remastered/visual_effects/bridge_light_destroyed.sound", -8, 2, 20, NO_CHANGE),
        };
    }
}
