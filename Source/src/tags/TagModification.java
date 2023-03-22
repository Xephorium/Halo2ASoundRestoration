package tags;

/* Halo 2A Sound Restoration                         Chris Cruzen
 * TagModification                                     03.20.2023
 *
 * TagModification is a container class that represents a single
 * Halo 2 sound tag and all information needed to modify that tag
 * at runtime.
 */

public class TagModification {


    /*--- Constants ---*/

    public static int NO_CHANGE = 26400;


    /*--- Base Class Variables ---*/

    /* Path of sound tag to be modified
     * Values: <String containing relative path within root tag directory>
     */
    public String path = "";

    /* Base volume of sound
     * Values: NO_CHANGE or <any int>
     */
    public int gain = NO_CHANGE;

    /* Distance at which sound begins to fade
     * Values: NO_CHANGE or <any int>
     */
    public int minDist = NO_CHANGE;

    /* Distance at which sound can no longer be heard
     * Values: NO_CHANGE or <any int>
     */
    public int maxDist = NO_CHANGE;

    /* Whether sound is to be played in classic Halo 2 only
     * Values: NO_CHANGE or [0,1]
     */
    public int classicOnly = NO_CHANGE;


    /*--- Constructors ---*/

    public TagModification(String tagPath, int tagGain, int tagMinDist, int tagMaxDist, int classic) {
        path = tagPath;
        gain = tagGain;
        minDist = tagMinDist;
        maxDist = tagMaxDist;
        classicOnly = classic;
    }

    public TagModification(String tagPath, int tagMinDist, int tagMaxDist) {
        this(tagPath, NO_CHANGE, tagMinDist, tagMaxDist, NO_CHANGE);
    }

    public TagModification(String tagPath, int tagGain) {
        this(tagPath, tagGain, NO_CHANGE, NO_CHANGE, NO_CHANGE);
    }
}
