<img align="left" width="120" src="Project Resources/Icon/Halo2AIcon.png" alt="Halo 2A Sound Restoration Icon">

# Halo 2A Sound Restoration
A script for restoring classic audio to the entire Halo 2 Anniversary campaign.

</br>

### Why a Script

Even with official mod tools, reverting every piece of audio in Halo 2's Anniversary campaign requires updating 700+ files. Completing this process once is agonizing. Doing it again each time an official update touches the campaign and breaks the previous mod is absurd. This is why, even years after launch, the game is without an up-to-date, comprehensive audio restoration mod.

This project aims to break the cycle. With one click, it generates anniversary maps from fresh tag files with all classic audio restored, ready to play in MCC. 

</br>

### Running the Project

#### Prerequisites
- Install Java
  - Find & run installer with default settings
  - Add `bin` folder to `PATH` environment variable
- Install Halo 2 Editing Kit
  - Find & install Halo 2 Editing Kit via Steam
  - Run `H2EK (Extract).bat` to extract tags
- Setup project config file
  - Create `Config.txt` at `<repository>/Source/input/Config.txt`
  - Add `TAGS_DIRECTORY=<dir>` to file, where `<dir>` is the tags folder you extracted in the previous step.
  - **Note:** Do *not* use quotes or commas. A correct example file:
  
    ```
    TAGS_DIRECTORY=D:\My Games\Steam\steamapps\common\H2EK\tags
    ```
  
 #### Run the Project
- Once project is setup, execute `run.bat` to restore classic audio tags
- The final step is to use the command line utility `tool` to package these tags into a `.map` file that can be played in the Master Chief Collection (script to do this automatically for all campaign levels coming soon)
