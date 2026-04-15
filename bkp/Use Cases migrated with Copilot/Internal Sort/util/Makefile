
        .PHONY: all build run clean test
        all: build
        build:
		javac SortDta.java
        run: build
		java SortDta sample_input.txt sample_output.txt
        test: run
		@echo "Sample output:" && cat sample_output.txt
        clean:
		rm -f *.class sample_output.txt