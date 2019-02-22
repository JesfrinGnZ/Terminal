#! /bin/bash 
echo "STARTING JFLEX COMPILING"
java -jar /home/jesfrin/Documentos/LibreriasJava/jflex-1.7.0/lib/jflex-full-1.7.0.jar -d /home/jesfrin/NetBeansProjects/Terminal1/src/backend/analizadores/ flexArchivo.flex

echo "STARTING CUP COMPILING"
java -jar /home/jesfrin/Documentos/LibreriasJava/cup/java-cup-11b.jar parserArchivo.cup 
mv parser.java /home/jesfrin/NetBeansProjects/Terminal1/src/backend/analizadores/parser.java
mv sym.java /home/jesfrin/NetBeansProjects/Terminal1/src/backend/analizadores/sym.java
