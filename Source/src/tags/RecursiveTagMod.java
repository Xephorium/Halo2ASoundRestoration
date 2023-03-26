package tags;

/* Halo 2A Sound Restoration                      Chris Cruzen
 * RecursiveTagMod                                  03.26.2023
 *
 * RecursiveTagMod is a container class that represents a directory
 * full of Halo 2 sound tags and all information needed to modify
 * the tags in that directory at runtime.
 */

public class RecursiveTagMod extends TagMod {


    /*--- Class Variables ---*/

    public boolean shouldModifySoundFiles;

    public boolean shouldModifyLoopFiles;


    /*--- Constructors ---*/

    public RecursiveTagMod(String path, float gainAdjustment) {
        super(path, gainAdjustment);
        this.shouldModifySoundFiles = true;
        this.shouldModifyLoopFiles = false;
    }

    public RecursiveTagMod(String path, float gainAdjustment, boolean modSoundFiles, boolean modLoopFiles) {
        super(path, gainAdjustment);
        this.shouldModifySoundFiles = modSoundFiles;
        this.shouldModifyLoopFiles = modLoopFiles;
    }


    /*--- Public Methods ---*/

    public RecursiveTagMod cloneWithPath(String path) {
        return new RecursiveTagMod(path, this.gain, this.shouldModifySoundFiles, this.shouldModifyLoopFiles);
    }
}
