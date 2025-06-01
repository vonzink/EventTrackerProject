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
 - JavaScript
 - Bootstrap
 - html/CSS


 ## SQL Diagram

![Diagram](/Users/zacharyzink/SD/Java/EventTracker/images/diagram.png)

 ## Entities & Relationships


    1. User: Loan officers and underwriters login to manage applications
    2. Borrower: Stores personal data and contact info
    3. Application: Linked to borrower, user, and status
    4. Status: Tracks stage transitions with timestamps and notes
    5. Documentation: Manages uploaded and pending document flags
    6. Underwriting: Stores findings, decisions, and reviewer info
    7. Approved: Captures final approval details and notes
    8. ClearToClose / Closed / Declined / Funded: Final status entities


 ## REST API Endpoints



| Entity       | HTTP Verb | URI                            | Request Body             | Response Body        | Status Codes                      |
|--------------|-----------|--------------------------------|--------------------------|-----------------------|----------------------------------|
| Borrower     | GET       | `/api/borrowers`               |                          | List of borrowers     | 200 OK                           |
| Borrower     | GET       | `/api/borrowers/{id}`          |                          | Single borrower       | 200 OK, 404 Not Found            |
| Borrower     | POST      | `/api/borrowers`               | JSON of new borrower     | Created borrower      | 201 Created, 400 Bad Request     |
| Borrower     | PUT       | `/api/borrowers/{id}`          | JSON to update borrower  | Updated borrower      | 200 OK, 400, 404                 |
| Borrower     | DELETE    | `/api/borrowers/{id}`          |                          |                       | 204 No Content, 404              |
| Application  | GET       | `/api/applications`            |                          | List of applications  | 200 OK                           |
| Application  | GET       | `/api/applications/{id}`       |                          | Single application    | 200 OK, 404 Not Found            |
| Application  | POST      | `/api/applications`            | JSON of new application  | Created application   | 201 Created, 400 Bad Request     |
| Application  | PUT       | `/api/applications/{id}`       | JSON to update           | Updated application   | 200 OK, 400, 404                 |
| Application  | DELETE    | `/api/applications/{id}`       |                          |                       | 204 No Content, 404              |
| Underwriter  | GET       | `/api/underwriters`            |                          | List of underwriters  | 200 OK                           |
| Underwriter  | GET       | `/api/underwriters/{id}`       |                          | Single underwriter    | 200 OK, 404 Not Found            |
| Underwriter  | POST      | `/api/underwriters`            | JSON of new underwriter  | Created underwriter   | 201 Created, 400 Bad Request     |
| Underwriter  | PUT       | `/api/underwriters/{id}`       | JSON to update           | Updated underwriter   | 200 OK, 400, 404                 |
| Underwriter  | DELETE    | `/api/underwriters/{id}`       |                          |                       | 204 No Content, 404              |
| User         | GET       | `/api/users`                   |                          | List of users         | 200 OK                           |
| User         | GET       | `/api/users/{id}`              |                          | Single user           | 200 OK, 404 Not Found            |
| User         | POST      | `/api/users`                   | JSON of new user         | Created user          | 201 Created, 400 Bad Request     |
| User         | PUT       | `/api/users/{id}`              | JSON to update           | Updated user          | 200 OK, 400, 404                 |
| User         | DELETE    | `/api/users/{id}`              |                          |                       | 204 No Content, 404              |
| Status       | GET       | `/api/statuses`                |                          | List of statuses      | 200 OK                           |
| Status       | GET       | `/api/statuses/{id}`           |                          | Single status         | 200 OK, 404 Not Found            |
| Status       | POST      | `/api/statuses`                | JSON of new status       | Created status        | 201 Created, 400 Bad Request     |
| Status       | PUT       | `/api/statuses/{id}`           | JSON to update           | Updated status        | 200 OK, 400, 404                 |
| Status       | DELETE    | `/api/statuses/{id}`           |                          |                       | 204 No Content, 404              |

 ## Lessons Learned

 * I need to spend more time planning out my tables and JOINS to prevent having to go back to workbench and updated every class. 

  * Mapping structure in the controller is a big deal and needs to be precise. 

  * Mapping lessions - you needs to add both the name and the name with a slash in this format. { "applications", "applications/" }
      - @PathVariable - when passing in a precise variable dont forget ("id")
      - @ReqestBody - when updating the whole entity
      - @RequestParam - when searching for a specific method

   * When returning ServletResponses you can just type in the number of the error. 

   * I keep writing more repos then i actually need. 

   * just map every table to prevent mistakes
         `` @Entity
            @Table(name = "approved")
            public class Approved {``

   * Use Optional for collecting by individual pieces of infomration. 

   * Use a try/catch when updating body with @RequestBody

   * JavaScript:
      - Document Object Model — the browser’s tree-like, programmable representation of an HTML page that lets JavaScript read, add, remove, and modify elements, attributes, and content while the page is running.
      - window.EventListener
         - load - waits for everytihng to load
      - document.querySelector
         - returns the first element in the DOM that matches a given CSS selector.
      - "function ()" and "() => are interchangable
      - buildPayload(){return {}}; - standard function for getting current value of a form
      - ?? checks for nuul and undefined (value !== null && value !== undefined) ? value : '';
      - forEach is an easier loop option

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

