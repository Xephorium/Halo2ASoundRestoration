package prefs;

public class RestorationPreferences {


    /*--- Variables ---*/

    /* Note: Everything is too loud in the remaster. Weapons, vehicles, environment
     *       effects - the gain is cranked way up. If the user wants *only* the music
     *       restored to classic in Anniversary edition, it's inaudible beneath these
     *       other sounds. Since I can't be bothered to rebalance the remastered audio,
     *       this increases every classic music track by 1.25db to compete with the rest
     *       of the game's soundscape.
     */
    public static final float MUSIC_ONLY_GAIN_BOOST = 1.25f;

    private final float musicGain;
    private final boolean musicOnly;


    /*--- Constructor ---*/

    public RestorationPreferences(int musicGainValue, boolean isMusicOnly) {
        musicGain = musicGainValue;
        musicOnly = isMusicOnly;
    }


    /*--- Accessor Methods ---*/

    public float getMusicGain() {
        return musicGain;
    }

    public boolean isMusicOnly() {
        return musicOnly;
    }
}
