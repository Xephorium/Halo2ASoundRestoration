
@echo off

rmdir /q /s "E:\Programming\Halo 2A Sound Restoration\Source\input\tags\sound_remastered\weapons"

robocopy "E:\Programming\Halo 2A Sound Restoration\Source\input\tags_bak\sound_remastered\weapons" "E:\Programming\Halo 2A Sound Restoration\Source\input\tags\sound_remastered\weapons" /E /NFL /NDL /NJH /NJS /nc /ns /np