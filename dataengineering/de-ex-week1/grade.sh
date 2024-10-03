cp ./solution_output.csv $1/solution_output.csv
cp ./orders.csv $1/orders.csv
cp ./users.csv $1/users.csv
cd $1

if [ -e prepare.sh ]; then
    echo "Detected prepare.sh script"
    ./prepare.sh
fi

if [ ! -e wtc_week1.ipynb ]; then
    echo "wtc_week1.ipynb was not found"
    exit 1
fi

#Execute notebook
jupyter execute wtc_week1.ipynb

#compare output CSV file
file1="output.csv"
file2="solution_output.csv"

# Check if both files exist
if [ ! -f "$file1" ]; then
    echo "Error: File $file1 does not exist."
    exit 2  # Exit with code 2 for file not found
fi

if [ ! -f "$file2" ]; then
    echo "Error: File $file2 does not exist."
    exit 2  # Exit with code 2 for file not found
fi

# Compare the files
if cmp -s "$file1" "$file2"; then
    echo "The CSV files are identical."
    exit 0  # Exit with code 0 if files are the same
else
    echo "The CSV files are different."

    # Optional: Show the differences
    echo "Differences:"
    diff "$file1" "$file2"

    exit 1  # Exit with code 1 if files are different
fi
