<img align="left" width="120" src="Project Resources/Icon/Halo2AIcon.png" alt="Halo 2A Sound Restoration Icon">

# Halo 2A Sound Restoration
A script for restoring classic audio to the entire Halo 2 Anniversary campaign.

</br>


### Directions

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
