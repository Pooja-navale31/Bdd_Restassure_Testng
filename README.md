Bdd cucumber RestAssured framewor

This project is involve Bdd cucmber framework with datadriven apache POI  excel file and TestNG for execution with Extent report capability.
We have to run this project in our local json server of port :http://localhost:3000
and before running this project you have start json server in your local machine by the commanad with following steps:
**How to install JSON Server  **
1.You need Node.js & npm installed on your machine.
  node -v
  npm -v
2. If not, download & install Node.js: https://nodejs.org/
3. npm install -g json-server
4.  Create a db.json file
5. Put this db.json file in your working folder.
6.  Start the server
7.  json-server --watch db.json
8.   By default, it runs on:
📌 http://localhost:3000
![image](https://github.com/user-attachments/assets/25a19e1a-000b-493c-9f48-4e13888a153b)

**Extent Report:**
![image](https://github.com/user-attachments/assets/cd662f3b-dd4f-4023-a446-89848458f33c)


**Allure Report:**
How to run Allure report from cmd:

D:\Study\RestAssured_Basic\RestAssured1_Restful_Hooker>allure generate allure-results -o allure-report --clean
Report successfully generated to allure-report
D:\Study\RestAssured_Basic\RestAssured1_Restful_Hooker>allure open allure-report
Starting web server...
Server started at <http://127.0.0.1:55225>. Press <Ctrl+C> to exit

Allure Report looks: ![image](https://github.com/user-attachments/assets/0926aefc-423b-48a6-bf9f-72633569a676)
