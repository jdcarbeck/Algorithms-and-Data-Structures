JFLAGS = -cp
JC = javac
JVM = java
TEST = hamcrest-core-1.3.jar:junit-4.11.jar:.1.jar:.
BUILD = junit-4.11.jar:.
ZIP = AdjMatrixEdgeWeightedDirectedGraph.java Bag.java CompetitionDijkstra.java CompetitionFloydWarshall.java CompetitionTests.java DijkstraSP.java DirectedEdge.java EdgeWeightedDirectedCycle.java EdgeWeightedDirectedGraph.java FloydWarshall.java IndexMinPQ.java

.SUFFIXES: .java .class .jar

default:
	$(JC) -Xlint $(JFLAGS) $(BUILD) *.java
	$(JVM) $(JFLAGS) $(TEST) TestRunner

zip:
	zip Archive.zip $(ZIP)
