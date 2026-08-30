@echo off

:: Change directory to sorce code folder
cd src

:: Compile the Java file
javac Game/*.java Model/*.java View/*.java Controller/*.java Assets/*.java Utilities/*.java Textures/*.java

:: Check if compilation was successful
if %errorlevel% neq 0 (
    echo Compilation failed.
    pause
    exit /b
)

:: Run the Java program
java Game/Game

pause