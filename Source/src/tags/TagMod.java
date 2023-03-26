package tags;

/* Halo 2A Sound Restoration                      Chris Cruzen
 * TagMod                                           03.20.2023
 *
 * TagMod is a container class that represents a single Halo 2
 * sound tag and all information needed to modify that tag at
 * runtime.
 */

public class TagMod {


    /*--- Constants ---*/

    public static int NO_CHANGE = 26400;


    /*--- Base Class Variables ---*/

    /* Path of sound tag or directory to be modified
     * Values: <String containing relative path within root tag directory>
     */
    public String path = "";

    /* Base volume of sound
     * Values: NO_CHANGE or <any int>
     */
    public float gain = NO_CHANGE;

    /* Distance at which sound begins to fade
     * Values: NO_CHANGE or <any int>
     */
    public float minDist = NO_CHANGE;

    /* Distance at which sound can no longer be heard
     * Values: NO_CHANGE or <any int>
     */
    public float maxDist = NO_CHANGE;

    /* Whether sound is to be played in classic Halo 2 only
     * Values: NO_CHANGE or [0,1]
     */
    public int classicOnly = NO_CHANGE;


    /*--- Constructors ---*/

    public TagMod(String tagPath, float tagGain, float tagMinDist, float tagMaxDist, int classic) {
        path = tagPath;
        gain = tagGain;
        minDist = tagMinDist;
        maxDist = tagMaxDist;
        classicOnly = classic;
    }

    public TagMod(String tagPath, float tagMinDist, float tagMaxDist) {
        this(tagPath, NO_CHANGE, tagMinDist, tagMaxDist, NO_CHANGE);
    }

    public TagMod(String tagPath, float tagGain) {
        this(tagPath, tagGain, NO_CHANGE, NO_CHANGE, NO_CHANGE);
    }
}
