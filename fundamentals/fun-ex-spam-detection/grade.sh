if [ -e "$1/prepare.sh" ]; then
    echo "Detected prepare.sh script"
    ./prepare.sh
fi

if [ ! -e "$1/spam.py" ]; then
    echo "spam.py was not found"
    exit 1
fi

python3 ./accuracy.py "$1/spam.py"