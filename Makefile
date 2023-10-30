NUM_RUNS = 5

CSV_FILE = SerialParallelData.csv 

JAVAC=/usr/bin/javac
JAVA=/usr/bin/java
.SUFFIXES: .java .class
SRCDIR=src/MonteCarloMini
BINDIR=bin

$(BINDIR)/%.class:$(SRCDIR)/%.java
	$(JAVAC) -d $(BINDIR)/ -cp $(BINDIR) $<

CLASSES=Directions.class \
TerrainArea.class \
Search.class \
MonteCarloMinimization.class \
Parallel.class \
MonteCarloParallelMinimization.class

CLASS_FILES=$(CLASSES:%.class=$(BINDIR)/%.class)

default: $(CLASS_FILES)

clean:
	rm $(BINDIR)/MonteCarloMini/*.class

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