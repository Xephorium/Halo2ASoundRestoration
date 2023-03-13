
:: H2AResetTags
::
:: Reverts all changes to the 'H2EK\tag' directory, restoring
:: the game to its pre-mod, vanilla state.

@echo off

:: Weapon Tags
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\weapons"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\weapons" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\weapons" /E /NFL /NDL /NJH /NJS /nc /ns /np

:: Sentinel Tags
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\characters\sentinel"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\characters\sentinel" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\characters\sentinel" /E /NFL /NDL /NJH /NJS /nc /ns /np

:: UI Tags
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\ui"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\ui" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\ui" /E /NFL /NDL /NJH /NJS /nc /ns /np

:: Music Tags
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\music"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\music" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\music" /E /NFL /NDL /NJH /NJS /nc /ns /np


