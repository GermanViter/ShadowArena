# Variables
JAVAC = javac
JAVA = java
SRC_DIR = src
BIN_DIR = out
MAIN_CLASS = com.rpg.main.Main

# Find all .java files
SOURCES := $(shell find $(SRC_DIR) -name "*.java")

# Targets
.PHONY: all clean run

all: compile

compile: $(SOURCES)
	@mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SOURCES)

run: compile
	$(JAVA) -cp $(BIN_DIR) $(MAIN_CLASS)

clean:
	rm -rf $(BIN_DIR)
