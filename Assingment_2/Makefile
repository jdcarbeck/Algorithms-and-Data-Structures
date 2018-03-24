JFLAGS = -cp
JC = javac
JVM = java
TEST = hamcrest-core-1.3.jar:junit-4.11.jar:json-simple-1.1.jar:.
BUILD = junit-4.11.jar:json-simple-1.1.jar:.

.SUFFIXES: .java .class .jar

default:
	$(JC) $(JFLAGS) $(BUILD) *.java

kmp: 
	$(JVM) $(JFLAGS) $(TEST) TestRunnerKMP

kmpTest:
	$(JVM) $(JFLAGS) $(TEST) KMPSearchTest

tst: 
	$(JVM) $(JFLAGS) $(TEST) TestRunnerTST

tstTest:
	$(JVM) $(JFLAGS) $(TEST) TSTTest

clean:
	$(RM) *.class
