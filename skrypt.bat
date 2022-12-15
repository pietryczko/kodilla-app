call gradle clean build
call java -jar .\build\libs\tasks-0.0.1-SNAPSHOT.jar
call rundll32 url.dll,FileProtocolHandler http://localhost:8080/crud/v1/task/tasks