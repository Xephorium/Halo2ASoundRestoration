package tags;

public class TagGroup {


    /*--- Base Class Variables ---*/

    // The logging name of the current group of tags.
    public String groupName = "";

    /* A list of directories to be recursively walked during restoration
     * of this group of tags. This list represents the first and core step
     * of audio restoration, with the lists below handling edge cases.
     */
    public String[] recursePaths = {};

    /* A list of substrings used to identify remastered sounds that
     * shouldn't be replaced during the above directory walk(s).
     */
    public String[] preservePaths = {};

    /* A list of file path *pairs* used to replace specific sounds during the
     * above directory walk(s). First of two is replacement, second is source.
     */
    public String[] replacePaths = {};

    // A list of file paths to be deleted after the above directory walk(s).
    public String[] deletePaths = {};

    // A list of tags that need to be edited and the edits to be performed.
    public TagMod[] tagMods = {};
}
