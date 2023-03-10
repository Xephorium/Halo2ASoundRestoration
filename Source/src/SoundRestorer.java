

/* Halo 2A Sound Restoration                         Chris Cruzen
 * SoundRestorer                                       03.10.2023
 *
 * SoundRestorer is the primary driver class of the Halo 2A Sound
 * Restoration project.
 *
 * This file restores the campaign's classic audio in 3 steps:
 *
 *   1. Read the user-specified tag directory from the project's
 *      configuration file.
 *
 *   2. Iterate through that directory's `sound` folder, replacing
 *      and deleting tags as necessary to restore the campaign's
 *      original audio.
 *
 *   3. Use the abysmally named command line utility `tool` to
 *      package the updated tags into .map files that can finally
 *      be played in the Master Chief Collection.
 *
 * For more info, see 
 */

public class SoundRestorer {

    public void restoreSound() {
        System.out.println("Test");
    }
}
