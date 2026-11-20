#!/bin/bash
rm -rf out && mkdir out
javac -d out $(find src -name "*.java")
cp -r src/assets out/assets
jar cfe docs/SustainabilityScramble.jar Main -C out .