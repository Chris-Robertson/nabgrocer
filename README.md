# nabGrocer
A simple apple-tracking application.

Author: Chris Robertson

chrisxrobertson@gmail.com

## Quick Start
There's a bash script that will start the backend Spring Boot application and
the front end Angular application and open it in your browser.

`sudo chmod +x ./start.sh`
`./start.sh`

Just close the script with CTRL-C to stop both applications.

## UI
An Angular 8 application. 

## API
A Java Spring Boot application.

### Postman Collection
https://www.getpostman.com/collections/b4bb4947e19706911ca9

### Build
The API uses [Gradle](https://gradle.org/) for build automation and dependency
management.

From the `nab-grocer-api` directory:

#### Build Application
`./gradlew clean build`

#### Run Application Locally
`./gradlew clean bootRun`

### Framework
The API uses Spring Boot framework `2.1.6.RELEASE`

### Database
- NabGrocer users an embedded, in-memory [H2 SQL database](https://www.h2database.com/html/main.html)
- Data can be seeded into the H2 database on application startup. This can be 
handy for running locally, but tests should do their own seeding. To enable, 
just remove `DISABLED` from `src/main/resources/data.sql.DISABLED`

### Static Code Analysis
A suite of tools are used for static code analysis. I take the approach of 
leaving most checks enabled, but not letting these tasks fails builds, except
in the case of SpotBugs.

Tools used:
- [SpotBugs](https://spotbugs.github.io/)
- [PMD](https://pmd.github.io/)
- [CheckStyle](https://checkstyle.org/)

### Style
I use CheckStyle mostly to maintain consistency. I am not religious about any
of my choices.
I use the [Google Java StyleGuide](https://google.github.io/styleguide/javaguide.html)
as reference, with a few changes.
- 4 space indents. 2 is a bit cramped.
- Javadocs. I turn this check off as I don't think Javadoc comments are suitable 
everywhere, all the time.

### TODO
*Stuff I wanted to implement but ran out of time*
- Spring HATEOAS
- OpenAPI specification
- JavaDoc comments in controllers and services. I don't add them while developing quickly as the 
methods can change a lot.
- Unit tests for services
- Configure a separate H2 DB for tests that can be blown away after each spec
- Suppress PMD warnings at method or class level if I don't want to turn off the check completely
- Builder for `ErrorResponseBody`

### Potential Bugs and Edge Cases
- Adding a new grocery item via POST when one with same name already exists
- Update item via PUT with no changes causes SQL index error. GroceryItem index auto increment type?
- GET requests where repo returns null are returning 200 OK! Don't know how I missed that...
