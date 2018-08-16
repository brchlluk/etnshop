# etnshop

A simple web app made for applying for a junior Java developer in Etnetera.

### Prerequisites
* Java >= 1.8
* Gradle
* MySQL

### Usage
* Clone the repository
```
git clone https://github.com/lukasbrchl/etnshop.git
cd etnshop
```
* Create MySQL database
```
mysql> create database etnshop;
mysql> use etnshop;
mysql> source etnshop.sql;
```
* Change your database credentials in src/main/resources/application.properties

* Run with
```
gradle jettyRun
```
* Open browser URL
```
http://localhost:8080/etnshop/
```

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details
