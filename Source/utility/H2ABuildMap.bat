
:: H2ABuildMap <mapName>
::
:: Takes campaign level's internal name as single parameter and uses 'tool'
:: to package all tags into an MCC compatible map file.

@echo off

:: Get MapName
set mapName=%1

:: Change to H2EK Directory
D:
cd "D:\Games\Installed - Steam\steamapps\common\H2EK"

:: Temporarily Restore Manually Updated Tags
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\weapons"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\restored_bak\weapons" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\weapons" /E /NFL /NDL /NJH /NJS /nc /ns /np > NUL
rmdir /q /s "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\characters\sentinel"
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\restored_bak\sentinel" "D:\Games\Installed - Steam\steamapps\common\H2EK\tags\sound_remastered\characters\sentinel" /E /NFL /NDL /NJH /NJS /nc /ns /np > NUL

@echo on

:: Build Map
tool build-cache-file scenarios\solo\%mapName%\%mapName% dx11_64 remastered_support

@echo off

:: Update MCC Map
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\h2_maps_win64_dx11" "D:\Games\Installed - Steam\steamapps\common\Halo The Master Chief Collection\halo2\h2_maps_win64_dx11" "%mapName%.map" /E /NFL /NDL /NJH /NJS /nc /ns /np

echo /////////////////////
echo // MCC Map Updated //
echo /////////////////////

:: Change back to Project Directory
E:
cd "E:\Programming\Halo 2A Sound Restoration"

:: Play Boop-Beep
powershell -c (New-Object Media.SoundPlayer 'E:\Programming\Halo 2A Sound Restoration\Source\utility\BuildComplete.wav').PlaySync();