JFLAGS = -cp
JC = javac
JVM = java
TEST = hamcrest-core-1.3.jar:junit-4.11.jar:. 
BUILD = junit-4.11.jar:. 

.SUFFIXES: .java .class .jar

default:
	$(JC) $(JFLAGS) $(BUILD) *.java

run:
	$(JVM) $(JFLAGS) $(TEST) TestRunner

time:
	$(JVM) $(JFLAGS) $(TEST) -Xss10m SortComparisonTest

clean:
	$(RM) *.class
grep:
	ls -la | grep -e Sort
##Need To compile project with the juinity jar
##To run need to run the Test file with the hamcrest and Junit

