# File Management App

This is a file management application to manage different types of files(PDF, doc, TXT, PNG) inside documents.

Every user needs to authenticate first using the authentication service to be able to use the file management service.

For each file added, we fire an event "FILE_CREATION" and we publish it to a Kafka topic.

The Communication service listens for it to send it to the notification service.

the notification service listens for notifications on a Kafka Topic and then manage to send it.

For notifications, we are using FCM. User should register first by sending its Registration token which we store in a Redis DB.

# Use Project

- Clone the project

      git clone https://github.com/ImaneFaith/filemanagementApp.git

- you need to have mysql server

- you need to run a Redis Docker Container

       docker run -p 16379:6379 -d redis:6.0 redis-server --requirepass "mypass"

- Run each service main function.

# Documentation endpoints

## File Manager Service :

> http://localhost:9890/swagger-ui/index.html

## Authentication Service :

> http://localhost:8081/swagger-ui/index.html

### For loging use :

> http://localhost:8081/login
