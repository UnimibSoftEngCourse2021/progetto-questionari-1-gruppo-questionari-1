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

Per avviare la web app in locale è necessario aver installato Maven, Tomcat 7 e aver un server con MySql Workbench con all'interno il database presente nella Repository, infine si deve inserire l'username e la password di accesso al database all'interno del file persistence.xml, che si trova al percorso Survey\src\main\resources\META-INF, nel campo value di hibernate.connection.username e hibernate.connection.password .

Se si vuole avviare da terminale basta spostarsi tramite il comando ```cd``` all'interno della cartella dell'applicazione ed eseguire il seguente comando per poi spostarsi su un browser e recarsi alla pagina ```localhost:8080```.

```
mvn clean package tomcat7:war-run
```

Se si vuole avviare da Eclipse, è necessario:

1. Importare il progetto in Eclipse come Maven project
2. Cliccare con il tasto destro sul progetto, scegliere Maven > Update Project e aspettare che Maven importi tutte le dipendenze necessarie, 
3. Cliccare di nuovo con il testo destro sul progetto e scegliere Run As >  Run on Server e selezionare il server Tomcat installato.
4. Si aprirà in automatico una pagina web con indirizzo http://localhost:8080/Survey/, che si può visualizzare allo stesso indirizzo in ogni browser.


