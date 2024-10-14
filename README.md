# finacPlus

This framework provides solution to the given assignment 

Scenario 1:
1. Navigate to the site Saucedemo.com
2. Login to the site (credentials are provided on the home page)
3. Verify that successful login will land the user on Products page
4. Get the first product item name and price, store it in a text file
5. Click on the add cart
6. Navigate to add cart and verify that cart page contains the product which was added in the above step
7. Logout

Scenario 2:
1. Make a get request
2. Validate whether the response code is 200 or not.

The tools and techstack used in the framework is as mentioned below:

1. Java
2. Selenium 
3. TestNG Test Runner
4. Maven Build Tool
5. Rest Assured library
6. Allure Reporting Mechanism

The Framework Structure is as mentioned below:

Directory
1. allure-results - Holds all the report json files

Packages(Src/Main/Java):
1. ApplicationPages - Holds multiple pages of the application with the element identifiers
2. Base - Holds the base class for variable initializations used accross project
3. CommonMethodAndUtilities - Holds a common class with all the common methods used accross project
4. DriverManager - Holds a class with Singleton design pattern to control the driver instance(s)
5. Listeners - Holds a Environment Config Interface to read/modify test data at run time and Listner class to perform required function based on test start, fail etc
6. restAPis - Holds the APIs skeleton such that it is generic.

Properties File (Src/Main/resources):
1. ProductDetails.txt - Test File to append the test product details - Name and Price
2. project.properties - common urls and or test data used accross project

Packages(Src/Test/Java):
1. ApplicationsTests - Holds the test classes with all the tests
2. SuiteFiles - Holds the suite files. It can be used to run the tests through mvn cmd line

Maven command to run the tests through cmd line:
1. mvn clean test -DsuiteXmlFile=SuiteFiles/e2e.xml
2. mvn clean test -DsuiteXmlFile=SuiteFiles/api.xml






