cp ./solution_sqlite_database.db $1/solution_sqlite_database.db
cd $1

echo "Comparing output in dir $1"

#if [ -e prepare.sh ]; then
#    echo "Detected prepare.sh script"
#    ./prepare.sh
#fi
#
#if [ ! -e wtc_week1.ipynb ]; then
#    echo "wtc_week1.ipynb was not found"
#    exit 1
#fi
#
##Execute notebook
#echo "Executing Notebook wtc_week1.ipynb"
#jupyter execute wtc_week1.ipynb

#compare output CSV file
file1="local_sqlite_database.db"
file2="solution_sqlite_database.db"

# Check if both files exist
if [ ! -f "$file1" ]; then
    echo "Error: File $file1 does not exist."
    exit 2  # Exit with code 2 for file not found
fi

if [ ! -f "$file2" ]; then
    echo "Error: File $file2 does not exist."
    exit 2  # Exit with code 2 for file not found
fi

# Dump the databases to temporary files
sqlite3 "$file1" .dump > "submit.dump"
sqlite3 "$file2" .dump > "solution.dump"

# Compare the files
if cmp -s "submit.dump" "solution.dump"; then
    echo "The data files are identical."
    exit 0  # Exit with code 0 if files are the same
else
    echo "The data files are different."

    exit 1  # Exit with code 1 if files are different
fi
