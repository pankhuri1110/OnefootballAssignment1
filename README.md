API Automation for BestBuy

This Test Automation Framework is created using Serenity BDD and covers API for testing Best Buy APIs.

Prerequisites Software/Tools

1) Git [ http://git-scm.com/downloads ]
2) Gradle
3) Java Development Kit 8
4) Java Runtime Environment 8
5) IDE (Preferably Intellij)

Getting Started :

Starting Server:
1) Make sure you have NodeJS installed (we require version 4 or newer)
2) git clone https://github.com/bestbuy/api-playground/
3) cd api-playground
4) npm install
5) npm start
# Best Buy API Playground started at http://localhost:3030

Running Test Cases:
1) Open the command prompt 'cmd' and clone the project using below command on the target directory: git repo link
2) Once the project is cloned successfully go the the project directory.
3) Run the below command to start the tests.
    gradle test aggregate

Report Generation :

Once tests suite run is completed, a report(index.html) is generated at below path in project location:
BestBuyAPI\target\site\serenity
