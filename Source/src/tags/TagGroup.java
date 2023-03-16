package tags;

public class TagGroup {


    /*--- Base Class Variables ---*/

    /* A list of directories to be recursively walked during restoration
     * of this group of tags. This list represents the first and core step
     * of audio restoration, with the lists below handling edge cases.
     */
    public String[] recursePaths = {};

    /* A list of associated files to be preserved during the above directory walk(s).
     */
    public String[] preservePaths = {};

    /* A list of associated files to be replaced after the above directory walk(s).
     */
    public String[] replacePaths = {};

    /* A list of associated files to be deleted after the above directory walk(s).
     */
    public String[] deletePaths = {};
}
