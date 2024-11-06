cp ./solution.dump $1/solution.dump
cd $1

echo "Comparing output in dir $1"

file1="local_sqlite_database.db"
file2="solution.dump"

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

# Compare the files
if diff "submit.dump" "solution.dump"; then
    echo "The databases contain the same data."
    exit 0  # Exit with code 0 if files are the same
else
    echo "The databases are different."
    exit 1  # Exit with code 1 if files are different
fi

#if cmp -s "submit.dump" "solution.dump"; then
#    echo "The data files are identical."
#    exit 0  # Exit with code 0 if files are the same
#else
#    echo "The data files are different."
#    exit 1  # Exit with code 1 if files are different
#fi

# Clean up temporary dump file
rm "submit.dump"
