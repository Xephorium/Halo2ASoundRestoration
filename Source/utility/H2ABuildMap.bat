
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

@echo on

:: Build Map
tool build-cache-file scenarios\solo\%mapName%\%mapName% dx11_64 remastered_support

@echo off

:: Update MCC Map
robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\h2_maps_win64_dx11" "D:\Games\Installed - Steam\steamapps\common\Halo The Master Chief Collection\halo2\h2_maps_win64_dx11" "%mapName%.map" /E /NFL /NDL /NJH /NJS /nc /ns /np

echo /////////////////////
echo // MCC Map Updated //
echo /////////////////////