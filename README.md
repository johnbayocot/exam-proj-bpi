# exam-proj-bpi

### Setup to run the application:
 
* Using Meven and Java 17
* Open application.properties
  * Update spring.datasource.username=root and update spring.datasource.url if you have different port for mysql 
  * Create new database with name bpi_exam
* Run the mvn clean install command
* Run "mvn spring-boot:run" command

### API endpoints:

* POST (Create Task) - http://localhost:8081/api/task/create
* GET (Get Task) - http://localhost:8081/api/task/{id}
* GET (Get All Tasks) - http://localhost:8081/api/task/get-all-tasks
* POST (Create Project) - http://localhost:8081/api/project/create
* GET (Get All Projects) - http://localhost:8081/api/project/get-all-projects
* GET (Get Project) - http://localhost:8081/api/project/{id}
* GET (Calculate Task) - http://localhost:8081/api/project/schedule/{id}
  
### For testing:

    1. Test create project POST method. Task may be defined to a project
    2. Test get all projects GET method to obtain project id or get the ID from database 
    3. Test create task POST method, create multiple tasks and define dependencies if needed
    4. Test get all tasks GET method to obtain task id or get the ID from database
    5. Use the Calculate Project Schedules method to assign start date and end date according to the dependencies of the tasks in a specific project.
    6. Response will be shown in postman, consolve and saved to database

### Postman Test
[Postman Collection](https://api.postman.com/collections/38575219-3b793f10-45e8-478c-8b4e-b25b69868045?access_key=PMAT-01J8MD5WF57J7YY9ZEKF7FFWS4)