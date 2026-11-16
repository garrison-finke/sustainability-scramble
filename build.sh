#!/bin/bash
rm -rf out && mkdir out
javac -d out $(find src -name "*.java")
jar cfe docs/SustainabilityScramble.jar Main -C out .