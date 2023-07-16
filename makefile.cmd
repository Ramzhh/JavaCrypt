@echo off
javac -Xlint:unchecked ./jexample/MyCryptMain.java

$manifestPath = "./javacrypt/makefile.txt"
$classFilesPath = "javacrypt/*.class"
$jarFilePath = "JavaCrypt.jar"

jar cfm $jarFilePath $manifestPath $classFilesPath

@echo on