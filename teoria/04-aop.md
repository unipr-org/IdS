# Indice
```table-of-contents
```
---
## Aspect-Oriented Programming (AOP)
L'AOP è un paradigma di programmazione che consente di aggiungere funzionalità aggiuntive, chiamate **aspetti**, al codice esistente. Gli aspetti possono essere utilizzati per aggiungere funzionalità come la registrazione, la profilatura, la sicurezza e l'autenticazione.

L'AOP è stato proposto come il passo successivo alla programmazione orientata agli oggetti (OOP) sin dai primi anni 2000. L'AOP è principalmente inteso per promuovere il riutilizzo del codice.

Nell'ambito dell'OOP (quasi) puro sostenuto da Java, l'AOP può essere drasticamente semplificato:
- Gli aspetti sono caratteristiche degli oggetti che non sono prontamente fornite dalle loro classi.
- Un fornitore di aspetti è un oggetto che può collegare/scollegare un aspetto da un oggetto o che può creare un oggetto con un aspetto richiesto.

Gli aspetti sono caratteristiche di oggetti che non sono prontamente fornite dalle loro classi. Ad esempio, un aspetto potrebbe aggiungere la funzionalità di registrazione a un oggetto che non fornisce tale funzionalità per impostazione predefinita.

Gli aspetti dovrebbero essere:
-   **Indipendente dalle caratteristiche degli oggetti a cui sono collegati**. Ciò significa che gli aspetti dovrebbero essere in grado di funzionare con qualsiasi oggetto, indipendentemente dalle sue classi o interfacce.
-   **Liberamente componibili**. Ciò significa che un oggetto può essere collegato a più aspetti, ottenendo così più funzionalità.
-   **Ortogonali**. Ciò significa che la composizione di aspetti fornisce la somma di funzionalità indipendenti.

Il paradigma AOP può essere implementato in Java utilizzando proxy dinamici. I proxy dinamici sono oggetti che fungono da intermediari tra un client e un oggetto reale.
> Dato un oggetto `o`, un *fornitore di aspetti* associa l'aspetto a `o` tramite un proxy dinamico che intercetta tutte le invocazioni ai metodi pubblici di `o`.

Ecco un esempio di come utilizzare l'AOP per aggiungere la funzionalità di registrazione a un oggetto:
```java
public class MyObject {
    public void doSomething() {
        // Fai qualcosa
    }
}

@Aspect
public class LoggingAspect {

    @Around("execution(* doSomething(..))")
    public void logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        // Registra la chiamata al metodo
        System.out.println("Chiamata al metodo doSomething");

        // Prosegui con la chiamata al metodo reale
        joinPoint.proceed();
    }
}
```

In questo esempio, l'aspetto `LoggingAspect` viene utilizzato per registrare la chiamata al metodo `doSomething()`. L'aspetto utilizza l'annotazione `@Around` per definire un advice che verrà eseguito prima e dopo la chiamata al metodo reale.

Quando viene chiamato il metodo `doSomething()`, l'aspetto `LoggingAspect` registra la chiamata al metodo. Quindi, l'aspetto procede con la chiamata al metodo reale.

Alcuni aspetti generali che vengono spesso considerati quando si implementa l'AOP:
-   **Condivisione:** Gli aspetti di condivisione sono utilizzati per garantire che gli oggetti condivisi siano utilizzati in modo sicuro. Ad esempio, un aspetto di condivisione potrebbe implementare un mutex per garantire che un oggetto condiviso non venga modificato da più thread contemporaneamente.
	
-   **Registrazione:** Gli aspetti di registrazione vengono utilizzati per tracciare le interazioni con gli oggetti. Ad esempio, un aspetto di registrazione potrebbe registrare le chiamate ai metodi degli oggetti in un file di log.
	
-   **Persistenza:** Gli aspetti di persistenza vengono utilizzati per salvare gli oggetti in un database o in un altro sistema di archiviazione. Ad esempio, un aspetto di persistenza potrebbe implementare un metodo per salvare un oggetto quando viene creato o modificato.

-   **Attivazione:** Gli aspetti di attivazione vengono utilizzati per attivare o disattivare le funzionalità di un oggetto. Ad esempio, un aspetto di attivazione potrebbe implementare un metodo per disabilitare la funzionalità di logging quando un'applicazione è in modalità di test.
-   **Remotismo:** Gli aspetti di remoto vengono utilizzati per consentire agli oggetti di interagire tra loro in remoto. Ad esempio, un aspetto di remoto potrebbe implementare un metodo per inviare una richiesta a un oggetto remoto.
-   **Ricaricabilità:** Gli aspetti di ricaricabilità vengono utilizzati per aggiornare gli oggetti senza interrompere l'esecuzione dell'applicazione. Ad esempio, un aspetto di ricaricabilità potrebbe implementare un metodo per ricaricare un oggetto da un file di configurazione.

In genere, solo i metodi dalle interfacce implementate vengono considerati quando si implementano aspetti. Ciò è dovuto al fatto che gli aspetti vengono applicati agli oggetti in base alle loro interfacce, non alle loro classi.

[_Torna all'indice_](#indice)

---

### Shared Aspect
Un oggetto condiviso è un oggetto che deve garantire la mutua esclusione per l'esecuzione delle sue modalità.
Solo i metodi delle interfacce implementate sono interessanti perché sono i metodi esportati.
Un proxy dinamico è sufficiente per intercettare tutte le invocazioni a metodi interessanti:
- Il gestore di invocazione fornisce oggetti utilizzati come blocco di sincronizzazione.
- Il gestore di invocazione entra in una regione critica protetta dal lock prima di invocare il metodo di destinazione, ed esce dalla regione critica immediatamente dopo.
- Il gestore di invocazione esce dalla regione critica anche in caso di eccezioni.

Esempio: Un aspetto di condivisione potrebbe essere utilizzato per garantire che un oggetto condiviso non venga modificato da più thread contemporaneamente. Ad esempio, il seguente aspetto implementa un mutex per garantire che un oggetto `MyObject` possa essere utilizzato solo da un thread alla volta:
```java
@Aspect
public class SharingAspect {

    @Around("execution(* MyObject.*(..))")
    public void synchronize(ProceedingJoinPoint joinPoint) throws Throwable {
        // Ottieni un mutex per l'oggetto
        Object mutex = joinPoint.getThis();
        
        // Blocca il mutex
        synchronized (mutex) {
            // Esegui il metodo reale
            joinPoint.proceed();
        }
    }
}
```

[_Torna all'indice_](#indice)

---

### Logging Aspect
Un oggetto di registrazione è un oggetto che traccia le invocazioni ai suoi metodi in un registro dei messaggi.
Solo i metodi delle interfacce implementate sono interessanti perché sono i metodi esportati.
Un proxy dinamico è sufficiente per intercettare tutte le invocazioni a metodi interessanti:
- Il gestore delle chiamate registra prima e dopo aver invocato il metodo di destinazione.
- Il gestore delle chiamate registra anche in caso di eccezioni.

Esempio: Un aspetto di registrazione potrebbe essere utilizzato per tracciare le chiamate ai metodi di un oggetto. Ad esempio, il seguente aspetto registra le chiamate ai metodi di un oggetto `MyObject` in un file di log:
```java
@Aspect
public class LoggingAspect {

    @Around("execution(* MyObject.*(..))")
    public void logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        // Registra la chiamata al metodo
        System.out.println("Chiamata al metodo " + joinPoint.getSignature().getName());

        // Prosegui con la chiamata al metodo reale
        joinPoint.proceed();
    }
}

```

[_Torna all'indice_](#indice)

---

### Persistent Aspect
Un oggetto persistente è un oggetto che sopravvive allo spegnimento del sistema in cui è stato creato o modificato.
I proxy dinamici non sono necessari perché all'utente viene richiesto esplicitamente:
- Effettua il commit delle modifiche nell'archivio persistente.
- Modifiche di rollback (ricaricando i dati dall'archivio persistente).

È possibile ottenere una semplice persistenza caricando/salvando oggetti serializzabili su file:
- Gli oggetti che implementano `java.io.Serializable` possono essere facilmente serializzati con `java.io.ObjectOutputStream` e deserializzati con `java.io.ObjectInputStream`.
- Gli oggetti serializzabili forniscono un campo `serialVersionUID` privato per disambiguare diverse versioni delle loro classi.

	Esempio: Un aspetto di persistenza potrebbe essere utilizzato per salvare gli oggetti in un database. Ad esempio, il seguente aspetto salva un oggetto `MyObject` in un database quando viene creato o modificato:
```java
@Aspect
public class PersistenceAspect {

    @AfterReturning("execution(* MyObject.*(..))")
    public void saveObject(Object object) {
        // Salva l'oggetto nel database
        // ...
    }
}
```

[_Torna all'indice_](#indice)

---

### Active Aspect
Un oggetto attivo è un oggetto che esegue i propri metodi in un pool di thread dedicato. Ciò significa che i metodi di un oggetto attivo non vengono eseguiti nel thread che li ha invocati, ma in un thread diverso, che fa parte del pool di thread dedicato all'oggetto.
Solo i metodi che implementano interfacce sono interessati perche' questi sono metodi esportati. Un proxy dinamico  e' sufficiente per intercettare tutte le invocazioni dei metodi interessati.
Un'interfaccia attiva è un'interfaccia che fornisce metodi con firme simili ai metodi nelle interfacce implementate dall'oggetto attivo. La differenza principale è che i metodi di un'interfaccia attiva restituiscono i risultati utilizzando futures e callback.

Esempio: Consideriamo l'esempio di un oggetto attivo che rappresenta un server web. L'oggetto attivo implementa l'interfaccia `WebServer`, che definisce i metodi per gestire le richieste HTTP.

La seguente è una possibile implementazione dell'interfaccia `WebServer`:

```java
public interface WebServer {

    void handleRequest(HttpRequest request, HttpResponse response);
    void shutdown();

}
```

L'interfaccia `WebServer` non fornisce alcuna informazione sul modo in cui i metodi `handleRequest()` e `shutdown()` vengono eseguiti. Per specificare che questi metodi devono essere eseguiti in un pool di thread dedicato, possiamo creare un'interfaccia attiva che estende `WebServer`.
La seguente è una possibile implementazione dell'interfaccia attiva `ActiveWebServer`:

```java
public interface ActiveWebServer extends Active<WebServer> {

    Future<Void> handleRequest(HttpRequest request, HttpResponse response);
    void shutdown();

}
```

L'interfaccia `ActiveWebServer` fornisce due metodi aggiuntivi:

-   `Future<Void> handleRequest()`: questo metodo restituisce un oggetto `Future` che rappresenta il risultato dell'invocazione del metodo `handleRequest()`.
-   `void shutdown()`: questo metodo non restituisce alcun valore.

Per creare un oggetto attivo che implementi l'interfaccia `ActiveWebServer`, possiamo utilizzare un proxy dinamico. Un proxy dinamico è un oggetto che viene creato dinamicamente per rappresentare un altro oggetto. In questo caso, il proxy dinamico rappresenterà l'oggetto che implementa l'interfaccia `WebServer`.

La seguente è una possibile implementazione di un proxy dinamico per l'interfaccia `ActiveWebServer`:

```java
public class ActiveWebServerProxy implements ActiveWebServer {

    private WebServer server;

    public ActiveWebServerProxy(WebServer server) {
        this.server = server;
    }

    @Override
    public Future<Void> handleRequest(
		    HttpRequest request, HttpResponse response) {
        // Crea un oggetto Future per rappresentare il risultato dell'invocazione del metodo
        // handleRequest()
        Future<Void> future = new Future<>();

        // Esegue il metodo handleRequest() nell'oggetto server
        new Thread(() -> {
            try {
                server.handleRequest(request, response);
                future.setDone();
            } catch (Exception e) {
                future.setException(e);
            }
        }).start();

        return future;
    }

    @Override
    public void shutdown() {
        server.shutdown();
    }

}
```

Il proxy dinamico implementa tutti i metodi dell'interfaccia `ActiveWebServer`. Il metodo `handleRequest()` esegue il metodo `handleRequest()` dell'oggetto server in un thread separato. Il metodo `shutdown()` esegue il metodo `shutdown()` dell'oggetto server.

Per utilizzare l'oggetto attivo, possiamo creare un'istanza del proxy dinamico. Ad esempio:

```java
WebServer server = new WebServer();
ActiveWebServer activeServer = new ActiveWebServerProxy(server);
```

Quindi, possiamo invocare i metodi dell'oggetto attivo come se si trattasse di un normale oggetto. Ad esempio:

```java
activeServer.handleRequest(new HttpRequest(), new HttpResponse());
activeServer.shutdown();
```

In questo modo, possiamo garantire che i metodi dell'oggetto attivo vengano eseguiti in un pool di thread dedicato.

[_Torna all'indice_](#indice)

---

### Remote Aspect
Un oggetto remoto è un oggetto che può essere invocato da un altro oggetto che si trova in un altro processo o computer.

Per implementare un oggetto remoto, è necessario fornire un modo per i client di inviare richieste all'oggetto remoto e per l'oggetto remoto di inviare risposte ai client.

Una soluzione comune è utilizzare una comunicazione socket. In questo caso, l'oggetto remoto crea una socket per accettare richieste dai client. Quando un client invia una richiesta, l'oggetto remoto la riceve sulla socket e la elabora. Dopo aver elaborato la richiesta, l'oggetto remoto invia una risposta al client sulla socket.

L'oggetto remoto crea una socket per accettare richieste dai client. Quando un client invia una richiesta, l'oggetto remoto la riceve sulla socket e la elabora. Dopo aver elaborato la richiesta, l'oggetto remoto invia una risposta al client sulla socket.

Un oggetto remoto basato su eventi è un oggetto remoto che invia eventi ai client.

Un evento è un messaggio che viene inviato da un oggetto a un altro oggetto. Gli eventi possono essere utilizzati per notificare ai client che si è verificato un evento di interesse.

Per implementare un oggetto remoto basato su eventi, è necessario fornire un modo per i client di registrarsi per eventi e per l'oggetto remoto di inviare eventi ai client registrati.

Una soluzione comune è utilizzare un canale di eventi. In questo caso, l'oggetto remoto crea un canale di eventi. I client possono registrarsi per eventi sull'oggetto remoto passando un callback al canale di eventi. Quando l'oggetto remoto genera un evento, lo invia al canale di eventi. Il canale di eventi quindi invia l'evento ai client registrati.

Un oggetto remoto asincrono è un oggetto remoto che può essere invocato in modo asincrono.

Invocazione asincrona significa che l'invocazione del metodo non blocca il thread che ha invocato il metodo. Il metodo viene eseguito in un thread separato e il thread che ha invocato il metodo riceve una notifica quando il metodo è terminato.

Per implementare un oggetto remoto asincrono, è necessario utilizzare un meccanismo di chiamata asincrona. Una soluzione comune è utilizzare una coda di lavoro. In questo caso, l'oggetto remoto crea una coda di lavoro. I client possono inviare richieste all'oggetto remoto inviando le richieste alla coda di lavoro. La coda di lavoro quindi esegue le richieste in un thread separato.

L'oggetto remoto crea una coda di lavoro. I client possono inviare richieste all'oggetto remoto inviando le richieste alla coda di lavoro. La coda di lavoro quindi esegue le richieste in un thread separato.

Esempio: Consideriamo l'esempio di un oggetto remoto che rappresenta un servizio di calcolo. Il servizio di calcolo può essere utilizzato per eseguire calcoli complessi.

L'oggetto remoto implementa l'interfaccia `IComputationService`. L'interfaccia `IComputationService` definisce un metodo `compute()` che esegue un calcolo complesso.

La seguente è una possibile implementazione dell'interfaccia `IComputationService`:

```java
public interface IComputationService {
	double compute(double x, double y);
}    
```

La seguente è una possibile implementazione del servizio di calcolo in modo sincrono:

```java
public class ComputationService implements IComputationService {

    @Override
    public double compute(double x, double y) {
        // Esegue il calcolo complesso
        double result = Math.pow(x, 2) + Math.pow(y, 2);

        // Restituisce il risultato
        return result;
    }
}
```

In questa implementazione, il metodo `compute()` esegue il calcolo complesso nel thread che ha invocato il metodo. Ciò significa che il thread che ha invocato il metodo potrebbe essere bloccato per un periodo di tempo significativo, a seconda della complessità del calcolo.

Per implementare il servizio di calcolo in modo asincrono, possiamo utilizzare una coda di lavoro. La seguente è una possibile implementazione del servizio di calcolo in modo asincrono:

```java
public class ComputationService implements IComputationService {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public Future<Double> compute(double x, double y) {
        // Crea un oggetto Future per rappresentare il risultato del calcolo
        Future<Double> future = new Future<>();

        // Esegue il calcolo complesso in un thread separato
        executorService.submit(() -> {
            double result = Math.pow(x, 2) + Math.pow(y, 2);
            future.set(result);
        });

        // Restituisce l'oggetto Future
        return future;
    }
}
```

In questa implementazione, il metodo `compute()` crea un oggetto `Future` per rappresentare il risultato del calcolo. L'oggetto `Future` viene utilizzato per notificare il thread che ha invocato il metodo quando il calcolo è terminato.

Il metodo `compute()` esegue il calcolo complesso in un thread separato, utilizzando l'executor service. Ciò significa che il thread che ha invocato il metodo non viene bloccato.

**Client**

Il client può utilizzare l'oggetto remoto in modo sincrono o asincrono.

Per utilizzare l'oggetto remoto in modo sincrono, il client può semplicemente invocare il metodo desiderato. Ad esempio:

```java
IComputationService service = new ComputationService();

double result = service.compute(1.0, 2.0);
```

In questo caso, il thread del client verrà bloccato fino a quando il calcolo non sarà terminato.

Per utilizzare l'oggetto remoto in modo asincrono, il client può creare un oggetto `Future` per rappresentare il risultato del calcolo. Ad esempio:

```java
IComputationService service = new ComputationService();

Future<Double> future = service.compute(1.0, 2.0);

// Fai qualcos'altro...

double result = future.get();
```

In questo caso, il thread del client non verrà bloccato. Il thread del client potrà continuare a fare qualcos'altro mentre il calcolo viene eseguito in background. Quando il calcolo è terminato, il metodo `get()` restituirà il risultato del calcolo.

[_Torna all'indice_](#indice)

---

### Reloadable Aspect
L'aspetto ricaricabile, o Reloadable Aspect, fa riferimento alla capacità di ricaricare classi in un ambiente Java.

1.  **Classi e Class Loaders:**
    -   Le classi vengono caricate in memoria tramite i class loader, che creano i descrittori di classe e rendono le classi disponibili per la JVM.
    -   Nel contesto di Java, i class loader hanno alcune limitazioni:
        -   Non possono scaricare classi (unloading).
        -   Classi caricate da class loader diversi sono considerate diverse, anche se hanno lo stesso nome completamente qualificato.

2.  **Ricaricabilità:**
    -   Per consentire la ricarica dinamica delle classi (hot swapping), è necessario utilizzare un nuovo class loader per ogni ricarica.
    -   Inoltre, è necessario gestire le dipendenze delle classi in modo coerente durante la ricarica.

3.  **Oggetti Ricaricabili:**    
    -   Anche se le singole istanze di oggetti non possono essere ricaricate, le istanze di una classe ricaricabile sono oggetti ricaricabili.
    -   La factory class (classe che crea un'istanza di un oggetto) di un oggetto non può essere cambiata.

**Esempio:** Supponiamo di avere una classe `ReloadableExample` che vogliamo rendere ricaricabile:

```java
public class ReloadableExample {
    private int counter = 0;

    public void increment() {
        counter++;
        System.out.println("Counter: " + counter);
    }
}
```

1.  **Implementazione Reloadable Aspect:**    
    -   Creiamo un nuovo class loader quando desideriamo ricaricare la classe `ReloadableExample`.
    -   Gestiamo attentamente le dipendenze per evitare problemi durante la ricarica.

2.  **Ricarica della Classe:**
    -   Quando vogliamo aggiornare la logica della classe `ReloadableExample`, creiamo un nuovo class loader.
    -   Carichiamo la nuova versione della classe e istanziamo un nuovo oggetto `ReloadableExample` con la nuova logica.
    -   I vecchi oggetti rimangono invariati finché non vengono sostituiti.


```java
ClassLoader newClassLoader = new URLClassLoader(new URL[] { 
					new URL("file:/path/to/classes/")
				});

Class<?> reloadedClass = newClassLoader.loadClass("ReloadableExample");

ReloadableExample reloadedInstance = 
						(ReloadableExample) reloadedClass.newInstance();

reloadedInstance.increment();  // Utilizza la nuova logica della classe
```

Questo approccio richiede una gestione attenta delle dipendenze, e non tutte le classi possono essere facilmente rendibili ricaricabili. Inoltre, alcune librerie potrebbero avere difficoltà a gestire classi ricaricabili. La ricarica dinamica delle classi è spesso utilizzata in ambienti di sviluppo o server in esecuzione continua per consentire modifiche del codice senza dover riavviare l'applicazione.

[_Torna all'indice_](#indice)