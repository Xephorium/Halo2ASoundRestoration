package tags;

import io.TagModifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static prefs.RestorationPreferences.MUSIC_ONLY_GAIN_BOOST;

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


    /*--- Protected Class Methods ---*/

    protected void generateClassicMusicTagMods() {

        // Generate Classic Music TagMod List
        List<TagMod> classicMusicTagMods = new ArrayList<TagMod>();
        for (TagMod tagMod : tagMods) {
            if (tagMod.path.contains("sound_remastered") && tagMod.path.contains("_music")) {
                TagMod classicMod = new TagMod(tagMod);
                classicMod.path = tagMod.path.replace("sound_remastered/", "");
                classicMod.gain += TagModifier.CLASSIC_MUSIC_BOOST;
                classicMusicTagMods.add(classicMod);
            }
        }

        // Combine Array & List
        TagMod[] result = Arrays.copyOf(tagMods, tagMods.length + classicMusicTagMods.size());
        System.arraycopy(classicMusicTagMods.toArray(new TagMod[0]), 0, result, tagMods.length, classicMusicTagMods.size());

        // Assign to TagMods
        tagMods = result;
    }

    protected void generateClassicTagMods(String filterString) {
        generateClassicTagMods(filterString, new String[] {});
    }

    protected void generateClassicTagMods(String filterString, String[] excludeStrings) {

        // Generate Classic Weapon TagMod List
        List<TagMod> classicWeaponTagMods = new ArrayList<TagMod>();
        for (TagMod tagMod : tagMods) {
            boolean containsExcludeString = Arrays.stream(excludeStrings).anyMatch(tagMod.path::contains);
            if (tagMod.path.contains("sound_remastered") && tagMod.path.contains(filterString) && !containsExcludeString) {
                TagMod classicMod;
                if (tagMod instanceof RecursiveTagMod) classicMod = new RecursiveTagMod((RecursiveTagMod) tagMod);
                else classicMod = new TagMod(tagMod);
                classicMod.path = tagMod.path.replace("sound_remastered/", "sound/");
                classicWeaponTagMods.add(classicMod);
            }
        }

        // Combine Array & List
        TagMod[] result = Arrays.copyOf(tagMods, tagMods.length + classicWeaponTagMods.size());
        System.arraycopy(classicWeaponTagMods.toArray(new TagMod[0]), 0, result, tagMods.length, classicWeaponTagMods.size());

        // Assign to TagMods
        tagMods = result;
    }

    protected void addClassicTagMods(TagMod[] tagMods) {

        // Convert To ArrayList
        List<TagMod> classicTagMods = new ArrayList<>();
        for (TagMod tagMod : tagMods) {
            TagMod classicMod;
            if (tagMod instanceof RecursiveTagMod) classicMod = new RecursiveTagMod((RecursiveTagMod) tagMod);
            else classicMod = new TagMod(tagMod);
            classicTagMods.add(classicMod);
        }

        // Combine Array & List
        TagMod[] combined = Arrays.copyOf(tagMods, tagMods.length + classicTagMods.size());
        System.arraycopy(classicTagMods.toArray(new TagMod[0]), 0, combined, tagMods.length, classicTagMods.size());

        // Assign to TagMods
        tagMods = combined;
    }

    protected void clearAllButMusicChanges() {
        recursePaths = new String[] {};
        preservePaths = new String[] {};
        replacePaths = new String[] {};
        deletePaths = new String[] {};

        // Generate List of Only Music Tag Mods
        List<TagMod> musicTagModList = new ArrayList<TagMod>();
        for (TagMod tagMod : tagMods) {
            if (tagMod.path.contains("_music")) {

                if (tagMod.path.contains("sound_remastered")) {

                    // Boost Remastered Music Gain to Complete With Loud Soundscape
                    TagMod mod = tagMod;
                    mod.gain += MUSIC_ONLY_GAIN_BOOST;
                    musicTagModList.add(mod);

                } else {
                    musicTagModList.add(tagMod);
                }
            }
        }

        // Convert Music TagMod List to Array
        TagMod[] musicTagModArray = new TagMod[musicTagModList.size()];
        musicTagModList.toArray(musicTagModArray);

        tagMods = musicTagModArray;
    }
}
