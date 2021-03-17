# Employee Reimbursement System App - Project 1

## Description
The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time.
All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests.
Finance managers can log in and view all reimbursement requests and past history for all employees in the company.
Finance managers are authorized to approve and deny requests for expense reimbursement.

## Purpose
We want to see that you can operate in the front-end, back-end and in between as a full stack developer.
The database has been created using DBeaver, and the rest uses the Eclipse IDE.

Additionally, test cases are implemented using JUnit in the practice of Test-Driven Development.
Note: JUnit testing requires a TRUNCATE on ers_users and ers_reimbursements tables with RESTART IDENTITY CASCADE
in order to pass hard-coded assertion statements in DatabaseIntegrationTests file
(primary key of id for both tables is generated always as identity)

## Tech Stack
* Java 8
* HTML
* CSS
* JavaScript
* XML
* JDBC
* Oracle database
* Maven
* JUnit 4.12
* Log4j 1.2
* Mockito 3.7
* DBeaver
* TomCat Apache Server v9

## Requirements
* The application shall employ the DAO design pattern, and properly separate your code into the appropriate layers
* The back-end system shall use JDBC to connect to an Oracle database.
*   Use at least one of each of our three statements - Statement, PreparedStatement, and CallableStatement
* The application shall deploy onto a TomCat Server
* The middle tier shall use Servlet technology for dynamic Web application development
* The front-end view can use JavaScript and use AJAX to call server-side components. The web pages should look presentable
    (try using CSS and bootstrap); I'd rather not see a website from 1995.
* Use Log4J and JUnit. There should be 75% code coverage of your service methods
* (OPTIONAL) Passwords should be encrypted in Java nad securely stored in the database
* (OPTIONAL) Users can upload a document or image of their receipt when submitting reimbursements
* (OPTIONAL) The application will send an email to employees letting them know that they have been registered as a new user,
    giving them their temporary password

## Dates
* 03/04/21 - Start: Project 1 specs released
* 03/19/21 - Completion: Project 1 presentations

* **Please take the deadline seriously.
* **Do NOT spend too much time stuck on a single blocker without asking a batch-mate for help.
* **Lastly, setting up your database shouldn't take very long (a few days max); and if one
    third of the time has passed (Wednesday 03/10/21 ish) and you have not completed at least
    the login functionality then you may want to pick up the pace.
