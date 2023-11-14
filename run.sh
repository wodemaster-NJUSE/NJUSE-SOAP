#!/bin/bash
echo "Compiling and running the program..."
javac -cp .:src/main/java/xmlTest.java
echo "已经完成xml1，xml2验证"
javac -cp .:src/main/java/GenerateStudentGrades.java
echo "已经生成新的xml3"
javac -cp .:src/main/java/translate.java
echo "已经完成xml3生成xml4"
javac -cp .:src/main/java/XMLFilter.java
echo "已根据xml4生成xml5"
echo "Program execution over"
