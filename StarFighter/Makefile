.PHONY: all build run test reports

JAR = build/libs/StarFighter-1.0-SNAPSHOT.jar
MAIN = ar.edu.unrc.dc.Main

all: build run

build:
	./gradlew clean build

test:
	./gradlew test

reports:
	./gradlew jacocoTestReport && xdg-open ./build/jacocoHtml/index.html

run: $(JAR)
	java -cp $(JAR) $(MAIN)

