
:: H2AResetTags
::
:: Reverts all changes to the 'H2EK\tag' directory, restoring
:: the game to its pre-mod, vanilla state.

@echo off

:: Weapon Tags
echo Reverting Weapons...
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\weapons"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\weapons" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\weapons" /E /NFL /NDL /NJH /NJS /nc /ns /np > NUL

:: Vehicle Tags
echo Reverting Vehicles...
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\vehicles"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\vehicles" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\vehicles" /E /NFL /NDL /NJH /NJS /nc /ns /np > NUL

:: Sentinel Tags
echo Reverting Characters...
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\characters\sentinel"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\characters\sentinel" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\characters\sentinel" /E /NFL /NDL /NJH /NJS /nc /ns /np > NUL

:: UI Tags
echo Reverting UI...
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\ui"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\ui" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\ui" /E /NFL /NDL /NJH /NJS /nc /ns /np > NUL

:: Effects Tags
echo Reverting Effects...
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\visual_effects"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\visual_effects" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\visual_effects" /E /NFL /NDL /NJH /NJS /nc /ns /np > NUL

:: Ambience Tags
echo Reverting Ambience...
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\ambience\spacestation"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\ambience\spacestation" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\ambience\spacestation" /E /NFL /NDL /NJH /NJS /nc /ns /np > NUL


:: Music Tags
echo Reverting Music...
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\music"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\tags_bak\sound_remastered\music" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\music" /E /NFL /NDL /NJH /NJS /nc /ns /np > NUL

echo Done.
