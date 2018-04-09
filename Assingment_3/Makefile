JFLAGS = -cp
JC = javac
JVM = java
TEST = .:hamcrest-core-1.3.jar:junit-4.11.jar org.junit.runner.JUnitCore :.
BUILD = junit-4.11.jar:.
ZIP = AdjMatrixEdgeWeightedDirectedGraph.java Bag.java CompetitionDijkstra.java CompetitionFloydWarshall.java CompetitionTests.java DijkstraSP.java DirectedEdge.java EdgeWeightedDirectedCycle.java EdgeWeightedDirectedGraph.java FloydWarshall.java IndexMinPQ.java
TXT = tinyEWD.txt NotValidFile.txt negCycle.txt invalid.txt 
.SUFFIXES: .java .class .jar

default:
	$(JC) -Xlint $(JFLAGS) $(BUILD) *.java
	$(JVM) $(JFLAGS) $(TEST) CompetitionTests

zip:
	zip Archive.zip $(ZIP) $(TXT)
