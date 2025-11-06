echo "Copy solution tests to submission dir"
cp -rf ./src/test $1/src
cd $1

if [ ! -e pom.xml ]; then
    echo "pom.xml was not found - please ensure you have a valid Maven Pom file for your project"
    exit 1
fi

# Check for src/main/java directory
if [ ! -d "src/main/java" ]; then
    echo "src/main/java directory was not found - please ensure you have the correct Maven directory structure"
    exit 1
fi

# Check for at least one .java file in src/main/java or its subdirectories
if [ ! "$(find src/main/java -name "*.java" -type f 2>/dev/null)" ]; then
    echo "No .java files found in src/main/java - please ensure you have submitted your Java source files"
    exit 1
fi

echo "Directory structure validation passed"

mvn clean
echo "Running Maven Verify: mvn verify"
mvn verify
echo "Running Maven Test: mvn test"
mvn test
