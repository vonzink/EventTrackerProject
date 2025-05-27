# EventTrackerProject

## Description

LoanTracker is a full-stack Spring Boot application designed to manage the lifecycle of mortgage loan applications. It supports multiple stages in the loan process, from submission to funding, and allows users to track progress, update documentation, and handle underwriting and approvals. Built with Java, JPA, MySQL, and RESTful principles, LoanTracker is structured for scalability, auditability, and easy integration with frontend frameworks. 

## technologies used
 - Java
 - Eclipse
 - Git/GitHub
 - Sublime Text Editor
 - zsh
 - Springboot
 - Postman
 - SQL Workbench
 - JPA/Hibernate
 - Gradle

 ## SQL Diagram

![Diagram](/Users/zacharyzink/SD/Java/EventTracker/images/diagram.jpg)

 ## Entities & Relationships


    1. User: Loan officers and underwriters login to manage applications
    2. Borrower: Stores personal data and contact info
    3. Application: Linked to borrower, user, and status
    4. Status: Tracks stage transitions with timestamps and notes
    5. Documentation: Manages uploaded and pending document flags
    6. Underwriting: Stores findings, decisions, and reviewer info
    7. Approved: Captures final approval details and notes
    8. ClearToClose / Closed / Declined / Funded: Final status entities

 ## Lessons Learned

 - 


 ## Notes
*    Project is modular, separated by controller, service, repo, and entity layers.
*   REST routes are designed to be frontend-consumable with clean JSON responses.
*   Optional fields are wrapped in Optional<> when a nullable response is expected.
*   Logging and debugging are enabled in Spring Boot startup logs.
*   All relationships are managed using JPA annotations with lazy loading where applicable.

 ## How to Run
Clone the repo:
``git clone https://github.com/your-org/LoanTracker.git``

Set up MySQL database `loantrackerdb`

Configure `application.properties`:

``spring.datasource.url=jdbc:mysql://localhost:3306/loantrackerdb
spring.datasource.username=loantracker
spring.datasource.password=loantracker
``

API at `http://localhost:8083/api`

[MSFG](https://msfg.us)

ZVZ

