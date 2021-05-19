# Banking API

## Explanation/Overview
The Banking API manages the bank accounts of its users. It is managed by the Bank's employees and admins. Employees and Admins count as Standard users with additional abilities.

This is a Java application that makes use of a PostgreSQL database to store information. JDBC is used to manage communication between the Java application and the database while servlets are used to handle connection with the users.

## Features
* Employees can view all customer information, but not modify in any way.
* Admins can both view all user information, as well as directly modify it.
* Standard users are able to register and login to see their account information. They can have either Checking or Savings accounts.
* All users are able to update their personal information, such as username, password, first and last names, as well as email.
* Accounts owned by users support withdrawal, deposit, and transfer.
* Transfer of funds is allowed between accounts owned by the same user, as well as between accounts owned by different users.

## Technologies Used
* Java
* JDBC
* PostgreSQL
* git

## Setup
When setting up this application, it is important to first set up your database. While you need to create a bank database yourself, bank_buiolder.sql should do most of the rest of the work for you, including creating your tables and setting up some dummy data. This can be run again if you need to "reset" your database. You should then edit ConnectionUtils.java with the relevant information so that your program can connect to the database. It is found in the com.bank.utils package. After that, if you are using Spring Tools Suite, all you should need to do is right click your project name and choose run as>run on server.

## Usage
You should use postman or some similar program to send/retrieve data to/from the server. The wiki has more details about specific endpoints.

## Contributors
* Michael Lee Brummitt
* Timothy Gattie