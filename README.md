# MonteCarloParallelization
In this java assignment a parallel solution is writen to solve a Monte Carlo method optimization problem of finding the minimum point on a 2D function with the x and y ranges provided as well as the number of rows and columns.

The Java class Parellel is the SearchParallel class and the MonteCarloParallelMinimization is the MonteCarloMinimizationParallel class. The name convention should not matter. The assignment instructions havent specified which files need to be handed in exactly so all the files have been handed in  -- Parallel.java; MonteCarloParallelMinimization.java and Terrain.java are used to run the program --

Note: The MakeFile pipes the SerialParallelData.csv !
# Installation

In order to run the assigment make sure java is downloaded on your system, in a Mac system this should be installed already. If using a windoes system you can download java as follows:

1. Use the link (https://www.oracle.com/za/java/technologies/downloads/) or for tge Java Development Kit (https://adoptopenjdk.net/releases.html)
2. Install JDK: Download the JDK and follow the set up wizzard.

The MakeFile is included in the file, this will be used to run the program. Use the make file like such:
# for the serial solution
    make serial

# for the parallel solution
    make parallel

You can change the inputs to the solution, change the numbers below to do such:
!!!Note: The MakeFile Pipes the output to the CSV file: SerialParallelData.csv

serial: $(CLASS_FILES)
	@echo "Run serial version $(NUM_RUNS) times and output to $(CSV_FILE)"
	@for i in `seq $(NUM_RUNS)`; do \
		$(JAVA) -Xmx6g -cp bin MonteCarloMini.MonteCarloMinimization 5000 5000 -5000 5000 -5000 5000 0.5 >> $(CSV_FILE);\
	done

parallel: $(CLASS_FILES)
	@echo "Run parallel version $(NUM_RUNS) times and output to $(CSV_FILE)"
	@for i in `seq $(NUM_RUNS)`; do \
		$(JAVA) -Xmx6g -cp bin MonteCarloMini.MonteCarloMinimization 5000 5000 -5000 5000 -5000 5000 0.5 >> $(CSV_FILE);\
	done