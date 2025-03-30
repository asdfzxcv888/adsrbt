all:
	javac Main.java RedBlackTree.java 	
	jar cfe plateMgmt.jar Main *.class

run:
	java -cp plateMgmt.jar Main input.txt

big:
	java -cp plateMgmt.jar Main big_input.txt

clean:
	rm -f *.class plateMgmt.jar
