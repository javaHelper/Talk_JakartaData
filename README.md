# Talk: Jakarta Data - Das Ende des Boilerplate Code?

Hat dich die Menge an Boilerplate Code, die Jakarta Persistence erfordert, auch schon immer gestört? 

Dann ist die neue Jakarta-Data-Spezifikation genau das Richtige für dich! 
Bisher mussten wir auf proprietäre Frameworks ausweichen oder selbst aktiv werden, um mit einem Repository die Details unserer Datenbankzugriffe zu abstrahieren. 
Das ändert sich mit Jakarta Data nun endlich. 

Die neue Spezifikation in Jakarta EE 11 bietet einfach zu nutzende Repositories auf Basis von Jakarta Persistence und Jakarta NoSQL. 
Und deren Definition könnte einfacher und flexibler kaum sein. 
Wie all das genau funktioniert und wie wenig Boilerplate Code du in Zukunft schreiben wirst, zeige ich dir in diesem Vortrag.

## Working with the example projects
The example projects require a database. GitHub Codespace or an IDE supporting Development Containers start up the database automatically. Or you can download and start the Docker configuration manually.

### GitHub Codespace or Development Containers integration
The easiest way to work with the example projects is to use a [free GitHub Codespace](https://github.com/features/codespaces) or the Development Containers of your IDE (supported by VSCode and IntelliJ). Both automatically start up the required database in a Docker container.

[Open in GitHub Codespaces](https://codespaces.new/Persistence-Hub/Talk_JakartaData)

### Manual Docker setup

I’m using a PostgreSQL database in all code samples and exercises of this course. I’ve prepared a docker-compose configuration that starts the database on localhost:5432 and a pgAdmin on localhost:80.

To run the docker-compose command, please make sure that docker is installed on your machine and open a command line in the project root folder. You can then start the environment by executing the following commands:
- docker-compose build
- docker-compose up

After you start the docker-compose configuration, you can access your database at localhost:5432 using the user postgres and the password postgres.