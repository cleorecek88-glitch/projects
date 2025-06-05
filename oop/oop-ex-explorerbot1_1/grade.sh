echo "Copy solution tests to submission dir"

cp -rf ./src/test $1/src

echo "Changing to dir $1"
cd $1

if [ ! -e pom.xml ]; then
    echo "pom.xml was not found - please ensure you have a valid Maven Pom file for your project"
fi

mvn clean

echo "Running Maven Verify: mvn verify"
mvn verify

echo "Running Maven Test: mvn test"
mvn test