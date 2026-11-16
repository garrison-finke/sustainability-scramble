#!/bin/bash
rm -rf out && mkdir out
javac -d out $(find src -name "*.java")
jar cfe web/SustainabilityScramble.jar Main -C out .