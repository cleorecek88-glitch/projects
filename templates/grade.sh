#!/bin/bash

dir=${1:-'./tmp'}
if [ ! -d $dir ]; then
    echo "No test execution directory $dir"
    exit 2
fi

cp ./tests.py $dir/_tests.py
cd $dir

if [ -x prepare.sh ]; then
    echo "Detected prepare.sh script"
    ./prepare.sh
fi

if [ ! -e mastermind.py ]; then
    echo "mastermind.py was not found"
    exit 1
fi

# grading command(s) go here. e.g.
# python3 -m pytest ./_tests.py

# TODO: Put review into schema