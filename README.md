# Survey.io

## Tecnologie adottate

Per questo progetto abbiamo deciso di utilizzare Spring MVC come framework, il che ci ha consentito di sviluppare una webapp con architettura MVC (Model-View-Controller). Abbiamo usato Maven come Build System che ci ha permesso di gestire tutte le dipendenza abbastanza comodamente dal file *pom.xml* e dal lato di persistenza abbiamo deciso di utilizzare il framework *Hibernate* con *jpa* che ha semplificato la mappatura delle classi all'interno del database MySql.

Tecnologie adottate :

- Spring MVC
- Maven
- Hibernate 
- JPA
- Tomcat 7

Software utilizzato :

- Eclipse / IntelliJ

- Git

- MySql WorkBench

- Sonarcube 

- Visual Paradigm

  

## Come Avviare l'App

Per avviare l'app in locale e necessario aver installato Maven, Tomcat 7 e aver un server con MySql Workbench con all'interno il database presente nella Repository.

Se si vuole avviare da terminale basta spostarsi tramite il comando ```cd``` all'interno della cartella dell'applicazione ed eseguire il seguente comando per poi spostarsi su un browser e recarsi alla pagina ```localhost:8080```.

```
mvn clean package tomcat7:war-run
```

Se si vuole avviare da Eclipse



