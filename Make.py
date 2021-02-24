import os
 
COLFile = "./homer.col"

os.system("javac -d classes -sourcepath src -cp classes src/main/Main.java")
#os.system("clear")
os.system("java --class-path classes main/Main " + COLFile)