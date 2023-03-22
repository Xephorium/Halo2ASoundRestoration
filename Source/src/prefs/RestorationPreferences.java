package prefs;

public class RestorationPreferences {


    /*--- Variables ---*/

    private int musicGain = 0;


    /*--- Constructor ---*/

    public RestorationPreferences(int musicGainValue) {
        musicGain = musicGainValue;
    }


    /*--- Accessor Methods ---*/

    public int getMusicGain() {
        return musicGain;
    }
}
