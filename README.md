<img align="left" width="130" src="Project Resources/Icon/Halo2AIconNew.png" alt="Halo 2A Sound Restoration Icon">

# Halo 2A Sound Restoration
A script for restoring classic audio to the entire Halo 2 Anniversary campaign.


</br>

### Why a Script

Even with official mod tools, reverting every piece of audio in Halo 2's Anniversary campaign requires updating 800+ files. Completing this process once is agonizing. Doing it again each time an official update touches the campaign and breaks the previous mod is prohibitively frustrating. This is why, even years after launch, the game is without an up-to-date, comprehensive audio restoration mod.

This project breaks the cycle. With two clicks, it generates anniversary maps from fresh tag files with all classic audio restored, ready to play in MCC.

</br>

### Restoration Options

Everyone who grew up with Halo 2 has strong feelings about its soundscape. I've tuned the default restoration settings to match classic Halo 2 as closely as possible. But to accommodate differing perspectives, the project includes a `config.txt` file that gives you control over some of the more contentious details. The options are below.

  | Restoration Option | Description       | Valid&nbsp;Input |
| :-------------: | --------------- | :---------: |
  | `MUSIC_VOLUME` | Controls music volume. Decibel integer added to the gain of all music campaign tracks. | `[-10, 10]` |

</br>

### Running the Project

#### Prerequisites
- Install Java
  - Download and run [JDK installer](https://www.oracle.com/java/technologies/downloads) with default settings.
  - Add `"<your jdk install path>\bin"` to `PATH` environment variable.
  - Test installation by verifying `java -version` on the command line produces java version.
- Install Halo 2 Mod Tools
  - Install [Halo 2 Mod Tools](https://store.steampowered.com/app/1613450/Halo_2_Mod_Tools__MCC/) via Steam.
  - Navigate to the `H2EK` installation directory and run `H2EK (Extract).bat` to extract tags.

#### Project Setup
- Clone this repository.
- Create `Config.txt` at `<repository>/Source/input/Config.txt`.
- Copy the following to the file (replacing the value of `TAGS_DIRECTORY` with your own from the last step - no quotes, just paste).
  ```kotlin
  TAGS_DIRECTORY=D:\My Games\Steam\steamapps\common\H2EK\tags
  
  MUSIC_VOLUME=0
  ```
- Edit restoration preferences to your liking.
  
 #### Restoration
- Once the project is setup, simply run the script `run.bat` on command line to restore all classic audio.
- Then, follow the directions the script provides to package the final map files and move them to MCC.
- That's it! You're ready to play. 🎮

