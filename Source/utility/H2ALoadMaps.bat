
:: H2ALoadMaps
::
:: Reads all compiled map files from 'H2EK\h2_maps_win64_dx11' output
:: directory and uses them to replace the official maps in MCC's
:: installation directory.

@echo off

robocopy "D:\Games\Installed - Steam\steamapps\common\H2EK\h2_maps_win64_dx11" "D:\Games\Installed - Steam\steamapps\common\Halo The Master Chief Collection\halo2\h2_maps_win64_dx11" /E /NFL /NDL /NJH /NJS /nc /ns /np