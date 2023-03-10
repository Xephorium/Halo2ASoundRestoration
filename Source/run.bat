
@echo off

:: Clean Project
call clean.bat

:: Compile Project (Targets Sent to "build" Directory)
javac -d build src\*.java

:: Print Update
echo Project compiled.
echo Project running.

:: Run Project (Binaries Read from "build" Directory)
java -cp build Main