javac -Xlint:unchecked ./javacrypt/MyCryptMain.java
jar cfm JavaCrypt.jar ./javacrypt/makefile.txt javacrypt/*.class
chmod +x JavaCrypt.jar
