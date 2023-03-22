
@echo off

:: Clean Project
call clean.bat

:: Compile Project (Targets Sent to "build" Directory)
javac -d build -cp src\dependencies\apacheio.jar src\*.java src\io\*.java src\io\utility\*.java src\tags\*.java src\tags\general\*.java src\tags\levels\*.java src\prefs\*.java

:: Print Update
:: echo Project compiled.
:: echo Project running.

:: Run Project (Binaries Read from "build" Directory)
java -cp build;src\dependencies\apacheio.jar Main