javac -d out FirstProgram.java FirstPackage/FirstPackage.java
# javac --module FirstPackage -d . --module-source-path .
java -cp out FirstClass