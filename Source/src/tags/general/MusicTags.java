package tags.general;

import tags.TagGroup;

public class MusicTags extends TagGroup {

    public MusicTags() {

        recursePaths = new String[] {
                "/sound_remastered/music"
        };

        /* Wow! Weirdly delighted to be wrong in my expectation that
         * the remixed music is universally worse. There are very few
         * exceptions, but I've noted them below. <3
         */
        preservePaths = new String[] {

                /* Regret Underwater Ambience
                 *
                 * The classic track is a surprisingly loud bell toll that plays
                 * over and over again. On modern audio equipment, it's repetitive
                 * and unpleasant enough to become distracting after a few minutes.
                 * The new track is much more dynamic, with light harmonies and some
                 * gentle percussion fading in and out. Feels like a dreamy, Ocarina
                 * water temple inspired enhancement of the original ambience.
                 */
                "underwater\\uw",
                "underwater2\\uw2"
        };
    }
}