# Indice
```table-of-contents
```
 ---

## Sistemi concorrenti
Un software si dice concorrente se la sua computazione e' ottenuta dalla composizione di sotto-programmi eseguiti in modo sequenziale indipendenti l'uno dagli altri, i quali possono essere eseguiti in parallelo sotto regole specifiche che impongono una sequenzializzazione delle parti che li compongono.
La computazione di un sistema concorrente e' strutturato nei termini di un insieme di flussi eseguiti indipendentemente.
> Ogni flusso di esecuzione effettua una computazione sequenziale.

La descrizione statica di un software concorrente e' descritta nei termini di un insieme di programmi concorrenti:
- Ogni programma concorrente descrive alcuni dei flussi di esecuzione del sistema concorrente.
- Ogni programma concorrente descrive alcuni dei vincoli di sequenzializzazione dovuti durante i flussi di esecuzione.

La descrizione delle computazioni sequenziali associate con i flussi di esecuzione sono presenti in un programma che usa un linguaggio di programmazione.
Se il linguaggio di programmazione adottato permette la descrizione esplicita oltre ai vincoli di sequenzializzazione dovuti, allora questo linguaggio e' un linguaggio di programmazione concorrente.

[_Torna all'indice_](#indice)

---

## Sistemi concorrenti in Java
Java e' un linguaggio di programmazione concorrente. Offre costrutti del linguaggio per esprimere i vincoli di sequenzializzazione basici.
Pattern complessi di sequenzializzazione possono essere espressi come la combinazione di diversi costrutti.

### Processi 
Un processo (concorrente) e' un programma (concorrente) in esecuzione:
- Un programma e' un documento statico che descrive il comportamento in fase di esecuzione di un processo.
- Un processo e' una entita' dinamica che esegue un programma usando un insieme particolare di dati e risorse.
- Due o piu' processi possono eseguire lo stesso programma, ognuno dei quali usando le proprie risorse e i propri dati.

Un processo comprende sempre i seguenti componenti:
1. Un programma da essere eseguito.
2. I dati grazie ai quali il programma verra' eseguito.
3. Le risorse richieste da un processo a tempo di esecuzione.
4. Lo stato di un processo in esecuzione.

Un processo (in Java) viene eseguito in un ambiente astratto che gestisce i dati condivisi e isolati e le risorse attraverso la "community" dei processi. La Java Virtual Machine (JVM) offre questo ambiente astratto per i processi.
> Una JVM per ogni processo.
 
![[33.png]]

[_Torna all'indice_](#indice)

### Threads
Un thread e' il flusso di esecuzione di un processo:
- Ogni thread e' associato con un singolo processo.
- Molti thread possono essere associati ad un singolo processo.
- Un thread esegue parti di un programma associati con il suo processo e condividse dati e risorse  con quest'ultimo.
- Un thread alloca dinamicamente parti delle risorse di un processo per il suo fabbisogno computazionale.
- Ogni thread ha la sua area privata per salvare dati e strati.

Un processo sequenziale ha un singolo thread.
Una memoria condivisa e' visibile a tutti i threads di un processo.
> Condividono tutto tranne alcuni dati privati (es. PID).

I thread sono spesso chiamati processi leggeri perche' il carico di lavoro associato alla loro computazione e' ridotto rispetto a quello dei processi.

![[34.png]]

I thread hanno le seguenti proprieta':
1. Un thread inizia la sua esecuzione in uno specifico punto del programma.
2. Un thread esegue una sequenza di istruzioni ordinata e predefinita.
3. Un thread viene eseguito indipendentemente rispetto agli altri thread.
4. I thread sono nati per essere eseguiti in parallelo (se la macchina che li esegue lo permette).

![[35.png]]

[_Torna all'indice_](#indice)

---

## Parallelismo e concorrenza
I sistemi paralleli sono deployati su un insieme di CPU per eseguire in parallelo processi e thread nello stesso momento.
I sistemi concorrenti sono deployati su una singola CPU, ma sono strutturati come se fossero in un sistema parallelo.

![[36.png]]

[_Torna all'indice_](#indice)

---

## Thread in Java
Ci sono due modi per descrivere i thread in Java:
1. Estendendo la classe `java.lang.Thread` usando top-level classes o (anonime) inner class.
```java
public class ThreadA extends Thread {
	@Override
	public void run() {
		// Corpo del thread
	}
}
```
2. Implementando l'interfaccia `java.lang.Runnable` usando top-level classes, (anonime) inner class, lambda expression.
```java
public class ThreadB implements Runnable {
	@Override
	public void run() {
		// Corpo del thread
	}
}
```

In entrambi i casi, il metodo `run()` contiene la sezione del programma che il thread deve eseguire.
Il metodo `start()` e' usato per "startare" un thread e avviare l'esecuzione del metodo `run()`.

```java
// Estendendo la classe Thread
Thread a = new ThreadA();
a.start();

// Implementando l'interfaccia Runnable
Thread b = new Thread(new ThreadB());
b.start();
```

Un thread termina quando il suo metodo `run()` termina: non puo' essere terminato "con la forza".

[_Torna all'indice_](#indice)

---

### Stati di un thread
Gli stati del ciclo di vita di un thread sono strutturati in tre livelli:

![[37.png]]

Gli stati di un thread possono essere alterati:
- `sleep(millis)`: forza il thread ad andare nello stato blocked per un numero specifico di millisecondi.
- `interrupt()`: forza il thread ad andare nello stato running.
- `interrupted()`: verifica se il thread e' stato precedentemente bloccato.
- `yield()`: mantiene il thread nello stato running, ma avvisa il thread scheduler di dare il permesso agli altri thread per essere eseguiti.
- `join()`: forza il thread ad andare nello stato blocked e aspetta gli altri thread sul quale e' stato invocato di terminare.

In una singola CPU, i thread vengono eseguiti uno alla volta dando l'illusione di essere in parallelo.
La JVM implementa un algoritmo di scheduling veramente semplice per i thread chiamato _fixed priority scheduling_.

[_Torna all'indice_](#indice)

---

## Java Memory Model
I thread di un programam Java:
- Hanno uno stack privato che usano come supporto per le invocazioni dei metodi.
- Hanno un provato thread-local storage.
- Condividono l'oggetto heap.

La java memory model descrive come i thread accedono ai loro tre tipi di memoria, e descrive come il contenuto delle memorie e' effettivamente salvato nella gerarchia di memoria del sistema.

> JVM decide come eseguire i thread sui vari core.
> Tutti gli oggetti risiedono nell'heap. Solo i tipi primitivi stanno nello stack.
 
![[38.png]]

La JVM e' un "sistema operativo".
I thread in Java non sono necessariamente eseguiti sulla stessa CPU, questo perche' la JVM normalmente e' distribuita su più CPU. 

![[39.png]]

L'esecuzione di thread su multiple CPU puo' causare problemi di coerenza di memoria.
Un esempio e' la variabile `done` che abbiamo visto nell'esercizio 1: viene copiata nelle cache dei vari core e puo' assumere valori diversi in base ai vari thread. Questo provoca inconsistenza.

Questi problemi vengono risolti se l'accesso ai dati condivisi e' controllato.
> Un controllo dell'accesso alla memoria condivisa puo' causare un uso inefficente del parallelismo e delle risorse stesse.

> La memoria in Java e' di 64MB. Quando si sfora questa cifra entra in azione il garbage collector.
 
![[40.png]]

[_Torna all'indice_](#indice)

---

### Mutua esclusione in Java
Il problema della mutua esclusione e' uno dei piu' classici problemi della programmazione concorrente.
Un programma concorrente incontra un problema di mutua esclusione quando si deve assicurare che se, dato un insieme $M$ di sezioni mutualmente esclusive di un programma, 
- solo un thread alla volta puo' eseguire una delle sezioni del programma in $M$;
- i thread che non possono eseguire le sezioni mutualmente esclusive in $M$ sono bloccati e verranno ripresi il prima possibile.

> L'assegnamento degli oggetti in Java non esiste: viene copiata la reference.
> L'assegnamento avviene solo per i tipi primitivi.

La mutua esclusione in Java e' risolta utilizzando le sezioni critiche.
Una sezione critica e' una sezione di un programma associata ad un oggetto usato come un mutex (Mutual Exclusion device) per controllare l'accesso nella sezione critica.
- Un mutex puo' essere acquisito e rilasciato dai thread: e' posseduto da un thread dopo la sua acquisizione e prima del rilascio.
- Se un thread acquisisce un mutex, nessuno degli altri thread puo' acquisire quel mutex finche' non viene esplicitamente rilasciato.
- I thread sono forzati ad andare nello stato blocked quando non possono acquisire un mutex, e successivamente sono forzati a passare allo stato running quando possono finalmente acquisirlo.
- Un thread esegue la sua sezione critica solo quando possiede il mutex usato come guardia per quella sezione.
- Un mutex e' un oggetto qualsiasi dell'heap.
- Posso avere sezioni critiche diverse concorrenti che usano mutex diversi.

> L'acquisizione e il rilascio del mutex devono essere vicini per evitare molti bug.

L'acquisizione e il rilascio sono:
- Operazioni rientranti perché un mutex può essere acquisito e rilasciato più volte in cicli annidati senza bloccare il thread che possiede il mutex.
- Operazioni sincrone all'interno del processo perché i loro effetti sono condivisi in modo sincrono tra tutti i thread del processo, possibilmente tra più CPU.

> Java garantisce la mutua esclusione anche su CPU diverse.

![[41.png]]

Una sezione critica e' identificata da: 
- Il modificatore `synchronized` dei metodi, per affermare che il corpo del metodo è la sezione critica e che questa fa riferimento all'oggetto da utilizzare come mutex. In questo modo il mutex e' l'oggetto `this`.
``` java
public synchronized void myMethod() {
	// the critial section
}
```
- Il blocco `synchronized`, per affermare che il corpo del blocco è la sezione critica e che l'oggetto a cui si fa riferimento nella testa del blocco viene utilizzato come mutex. Con questo metodo identifico la guardia / mutex con l'oggetto `o`.
``` java
public void myMethod(Object o) {
	synchronized(o) {
		// the critial section
	}
}
```

La disponibilita' delle sezioni critiche in Java provvedono una effettiva soluzione per diversi problemi, tra cui:
- Interferenza tra thread: questo problema accade quando due operazioni, che vengono eseguite su diversi thread, agiscono sullo stesso dato causando una race condition.
- Coerenza della memoria: questo problema accade quando thread diversi hanno inconsistenti accessi a copie dello stesso dato attraverso diverse CPU (cache non "allineate").

[_Torna all'indice_](#indice)

---

### Happens-Before
Java definisce la relazione happens-before sulle operazioni in memoria come la lettura e la scrittura delle variabili condivise.
Il risultato di una scrittura di un thread e' garantita dall'essere visibile per una lettura di un altro thread se e solo se l'operazione di scrittura happen-before l'operazione di lettura.

Ogni azione in un thread avviene-prima di ogni altra azione di un altro thread che viene dopo in un programma.
Lo sblocco di un mutex avviene prima di ogni successivo blocco dello stesso mutex (vincolo multiprocessore).
> La relazione happens-before e' transitiva.

Una scrittura su un campo `volatile` avviene prima di ogni lettura successiva dello stesso campo.
> Si noti che il modificatore `volatile` non comporta la mutua esclusione.

Una chiamata a `start()` su un thread avviene prima di qualsiasi azione nel thread avviato.
Tutte le azioni in un thread si verificano prima che qualsiasi altro thread ritorni con successo da un `join()` su quel thread.

La keyword `synchronized` puo' essere usata effettivamente per risolvere i problemi di coerenza della memoria. L'utilizzo di `synchronized` su un oggetto `obj` assicura che tutte le modifiche dello stato di `obj` verranno propagate a tutti i thread interessati prima di un qualsiasi successivo accesso sincronizzatoi a `obj`.

> I campi `final`, che non possono essere modificati dopo che l'oggetto e' stato costruito, possono essere letti tranquillamente e in modo sicuro una volta che l'oggetto e' stato costruito.

Le operazioni atomiche in Java sono quelle operazioni di lettura e scrittura che non possono essere interrotte per sospendere l'attuale thread e attivarne un altro.

[_Torna all'indice_](#indice)

---

## Attesa e notifica di eventi
Il problema dell'attesa e della notifica degli eventi e' un altro dei classici problemi della programmazione concorrente.

Un thread affronta il problema dell'attesa degli eventi se ha bisogno di attendere gli eventi senza verificare attivamente il verificarsi degli eventi (busy waiting).
Un thread affronta il problema di notificare eventi se ha bisogno di comunicare il verificarsi di eventi a un altro thread, che probabilmente è in attesa di notifiche.

Java abbina il problema dell'attesa e della notifica degli eventi alle sezioni critiche perché le sezioni critiche forniscono già un mezzo per bloccare i thread (senza busy waiting).

Un thread che deve notificare eventi invoca il metodo `notifyAll()` su un oggetto `obj` per notificare a tutti gli oggetti che stavano aspettando su `obj` che un evento si e' verificato.

Un thread che deve aspettare eventi invoca il metodo `wait()` su `obj` per bloccarsi e aspettare gli eventi.

> Questi metodi vanno chiamati sugli oggetti guardia altrimenti si verifica una eccezione.

Nota che:
-  Il metodo `notifyAll()` notifica tutti i thread in attesa, che vengono tutti forzati allo stato di esecuzione anche se solo uno di loro entrera' nella sezione critica.
- Il metodo `notify()` notifica solo uno dei thread in attesa, ovvero il thread che eventualmente procederebbe nella sezione critica.
- L’uso del metodo `notify()` è intrinsecamente più efficiente, ma è anche soggetto a errori.
- Il metodo `wait(millis)` puo' essere usato per aspettare non piu' di `millis` millisecondi (attesa massima).

[_Torna all'indice_](#indice)

---

## Problemi di liveness
L'utilizzo superficiale o errato del supporto per la programmazione concorrente fornito da Java può comportare gravi problemi di liveness.
Alcuni di questi problemi lo sono:
- Deadlock, che è una situazione in cui due o più thread sono bloccati per sempre, in attesa l'uno dell'altro.
- Livelock, che è una situazione in cui due thread reagiscono continuamente agli eventi che ciascuno notifica all'altro.
- Starvation, ovvero una situazione in cui un thread non è in grado di ottenere un accesso regolare a una risorsa condivisa e, quindi, non è in grado di realizzare i progressi attesi.

---

## Astrazione ad alto livello per la concorrenza
Java offre meccanismi basici per la gestione della concorrenza e dei relativi problemi.
L'astrazione ad alto livello per la gestione dei problemi relativi alla concorrenza e' necessaria per:
- Migliorare la manutenzionalita' dei sistemi concorrenti.
- Migliorare la riusabilita' delle soluzioni dei problemi concorrenti.
- Migliorare il livello di comprensione delle caratteristiche non funzionali delle soluzioni (es: livello di parallelismo, tipi di controllo, problemi di liveness).

[_Torna all'indice_](#indice)

---

### Blocking Queues
Una queue (coda) e' una sequenza di elementi che cambiano dinamicamente e seguono una politica FIFO (First In First Out). Le azioni basiche delle code sono: creazione, distruzione, is empty test, is full test, enqueue (add item), dequeue (remove item).

Una blocking queue (coda bloccante) e' una coda utilizzata per l'uso concorrente.
Le operazioni vengono bloccate se non possono essere eseguite immediatamente:
- Enqueue puo' bloccare se la coda e' piena.
- Dequeue puo' bloccare se la coda e' vuota.

Le code bloccanti sono un'astrazione che possono essere usate per cordinare le attivita' senza un sistema concorrente.

> Nota bene: 
> 1. Tutte le code bloccanti possono possono bloccarsi sulla dequeue se sono vuote.
> 2. Solo le code bloccanti con capacita' limitata possono bloccarsi sulla enqueue se sono piene.

[_Torna all'indice_](#indice)

---

### Locks and Conditions
Un lock (esplicito) e' un'astrazione che puo' essere usata per assicurare la mutua esclusione.
> Svincoliamo l'idea della sezione critica.

Caratteristiche dei lock:
- Puo' essere esplicitamente locked (acquisito) o unlocked (rilasciato).
- Solo un thread alla volta puo' possedere il lock.
- Un thread puo' bloccarsi nel tentativo di acquisire un lock se e' gia' posseduto da un altro thread.
- Un thread bloccato nel tentativo di acquisire un lock verra' risvegliato quando il lock potra' essere di nuovo riacquisito.

> Corrispondono ai lock della PTHREAD Library.

Una condizione e' una astrazione che puo' essere usata per attendere e segnalare eventi:
- Un thread puo' segnalare che una condizione e' diventata vera.
- Un thread si puo' bloccare e ascpettare che una condizione venga segnalata.
- Un lock e' sempre necessario come guardia di una condizione.

Un lock e' normalmente associato con importanti condizioni:
- L'attesa e la segnalazione di condizioni e' possibile solo per i thread che possiedono i lock delle condizioni.
- Quando un thread aspetta che una condizione venga segnalata, rilascia il lock della condizione.
- Quando un thread segnala una condizione, deve esplicitamente rilasciare il lock della condizione.

> Lock rientrante: consente di acquisire piu' volte lo stesso lock $\to$ e' consentito acquisirlo di nuovo senza che si verifichi un deadlock.
> Un esempio di lock rientrante:
``` java
public class ReentrantLockExample {
    private Lock lock = new ReentrantLock();
    private int lockCount = 0;

    public void performTask() {
        lock.lock(); // Acquisizione del lock
        ++lockCount;

        try {
            // Blocco critico - codice che richiede accesso sincronizzato
            System.out.println("Task in corso...");

            // Chiamata ricorsiva
            performSubTask();
        } finally {
            --lockCount;
            if (lockCount == 0) {
                lock.unlock(); 
                // Rilascio del lock solo se è stato acquisito una sola volta
            }
        }
    }

    public void performSubTask() {
        lock.lock(); 
        // Acquisizione del lock anche se è già stato acquisito nel chiamante
        
        ++lockCount;

        try {
            // Blocco critico della sotto-operazione
            System.out.println("Sotto-task in corso...");
        } finally {
            --lockCount;
            if (lockCount == 0) {
                lock.unlock(); 
                // Rilascio del lock solo se è stato acquisito una sola volta
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        example.performTask();
    }
}

```

---

### Atomic references
Un riferimento atomico incapsula un riferimento a un oggetto e lo gestisce in mutua esclusione.

Esempio:
```java
UnaryOperator<Integer> operator = new UnaryOperator<Integer>() {
	@Override
	public Integer apply(Integer value) {
		return value + 1;
	}
};
```

Viene creato un oggetto `UnaryOperator<Integer>` utilizzando una classe anonima. `UnaryOperator` è una funzione che accetta un argomento dello stesso tipo e restituisce un risultato dello stesso tipo.
L'oggetto `operator` è una funzione che, quando applicata a un numero intero, restituisce il numero successivo incrementato di 1. <u>Questo è un esempio di programmazione funzionale</u> in Java, in cui le funzioni sono trattate come oggetti e possono essere passate come argomenti o assegnate a variabili.

[_Torna all'indice_](#indice)

---

### Pools di Risorse
I problemi di concorrenza sono spesso causati dalle risorse condivise.
- La corretta gestione degli accessi alle risorse condivise e' uno dei piu' classici problemi di concorrenza.

Un gruppo di risorse identiche e' chiamato pool (piscina) di risorse.
- Quando una pool e' creata/distrutta, tutte le risorse sono anche acquisite/rilasciate.
- Le risorse vengono assegnate per l'utilizzo su richiesta al pool.
- L'accesso controllato delle risorse e' garantito dalla pool.

Le pools di risorse sono normalmente usate per controllare l'ammontare delle risorse usate da un sistema concorrente:
- Per assicurare che un numero sufficiente di risorse sia disponibile.
- Per assicurare che le risorse siano usate in modo efficiente.

#### Thread Pools
I thread sono virtualmente le risorse piu' rileventai in un sistema concorrente.
La costruzione, distruzione, e l'accesso ai thread sono spesso controllati usando i thread pools.

Quando viene creata, una thread pool crea e attiva tutti i thread nella pool. Questo per assicurarsi che i thread siano immediatamente disponibili quando si avra' la necessita di usarli e per assicurarsi che il livello della concorrenza sia controllato.

[_Torna all'indice_](#indice)

---

### Executors
Un (semplice) executor e' un'astrazione che puo' essere usato per gestire tasks concorrenti:
- E' associato con una thread pool usata per eseguire tasks.
- Accoda le tasks che non possono essere avviate immediatamente.
- Fornisce modi per restituire i risultati delle tasks (se presenti).
- Fornisce modi per restituire le eccezioni che hanno causato l'errore della cessazione delle tasks (se presenti).
- Permette di interrompere le tasks e di terminare tutti i thread nella pool.
- (A volte) Fornisce una serie di politiche di scheduling.

Un executor offre tre possibili opzioni per eseguire le tasks:
1. _One way execution_: per il quale l'esecutore non fornisce un mezzo per sapere se l'attività è stata effettivamente eseguita e per leggere il risultato dell'attività (se presente).
2. _Execution with callback_: per il quale l'esecutore consente a un'attività di callback di utilizzare il risultato dell'attività (se presente), spesso nel thread che ha eseguito l'attività richiesta.
3. _Execution with future_: per il quale l'esecutore fornisce un futuro (una promessa) che gestisce il risultato dell'attività richiesta (se presente) quando diventa disponibile.

> Gli esecutori prevedono attività asincrone.

#### Callbacks
Una callback task e' una task che viene eseguita quando un executor conclude una task richiesta.
- La callback task riceve il risultato dell'attività richiesta (se presente).
- La callback task riceve l'eccezione che ha causato la terminazione dell'attività richiesta (se presente).
- (A volte) la callback task viene eseguita nello stesso thread che ha eseguito l'attività richiesta.

Le callback task vengono implementate come oggetti associati a una richiesta di esecuzione di una task.

#### Futures
Un future (o promessa) è un mezzo per gestire:
- Il risultato di un'attività asincrona (se presente).
- L'eccezione che ha causato la conclusione dell'attività asincrona (se presente).

I futures sono implementati come oggetti associati a una richiesta di esecuzione di un'attività:
- Sono associati dinamicamente al risultato dell'attività richiesta (se presente) quando diventa disponibile.
- Sono associati dinamicamente all'eccezione che ha causato la terminazione dell'attività richiesta (se presente) quando diventa disponibile.

Un future blocca il thread che tenta di accedere al suo valore incorporato (un risultato o un'eccezione) se il valore non è ancora disponibile:
- Il thread viene ripreso quando il risultato diventa disponibile (se presente) e il risultato viene restituito.
- Il thread viene ripreso quando diventa disponibile un'eccezione (se presente) e l'eccezione viene generata.

> Nota bene: un future non si blocca se il suo valore è già disponibile al momento della richiesta.

[_Torna all'indice_](#indice)

---

## Java Reflection
Java e Java Virtual Machine (JVM) forniscono un mezzo, il pacchetto `java.lang.reflect`, per rinviare alcune decisioni in fase di esecuzione.
Java rimane un linguaggio tipizzato staticamente, ma fornisce un mezzo puramente orientato agli oggetti per supportare:
- Collegamento dinamico delle classi
- Introspezione (dinamica) (degli oggetti)
- Creazione dinamica di oggetti
- Accesso dinamico ai campi
- Invocazione dinamica di metodi

Si noti che i seguenti fatti sono veri in Java:
- Ogni oggetto è associato alla classe che è stata utilizzata per crearlo, la cosiddetta classe factory.
- Ogni classe/interfaccia è rappresentata in fase di runtime da un oggetto, il cosiddetto oggetto classe (o descrittore di classe).

Data la classe/interfaccia `C`, la JVM fornisce un oggetto della classe `java.lang.reflect.Class<C>`.
Data la classe/interfaccia `C`, l'oggetto che rappresenta la classe/interfaccia può essere referenziato da `C.class`.

Ogni oggetto di classe è associato a un caricatore di classe, che è l'oggetto utilizzato per caricare il bytecode della classe.
Gli oggetti di classe sono il punto di ingresso della Java Reflection.

### Esempio riflessione
La sintassi `Class<?>` in Java fa parte del sistema di riflessione ed è utilizzata per rappresentare l'oggetto `Class` di una classe sconosciuta o non specificata.

-   `Class`: `Class` è una classe in Java che rappresenta metadati sulla classe di un oggetto, inclusi i dettagli sulla sua struttura, campi, metodi e altro ancora. È parte del sistema di riflessione di Java.
    
-   `<T>`: È un parametro generico che indica un tipo. Quando si utilizza `<T>` dopo `Class`, si sta dicendo a Java di lavorare con un oggetto `Class` che rappresenta il tipo specifico di classe. Ad esempio, `Class<String>` rappresenta l'oggetto `Class` di una classe di tipo `String`.
    
-   `<?>`: Questa è una wildcard (`?`) che indica "qualsiasi tipo". Quando combinata con `Class`, `Class<?>` significa un oggetto `Class` di una classe di tipo sconosciuto. Può essere qualsiasi classe.
    

In altre parole, `Class<?>` è un modo di indicare che stiamo lavorando con un oggetto `Class` di una classe, ma non stiamo specificando il tipo esatto di quella classe. Questo è utile in situazioni in cui il tipo della classe non è noto a compile time o è un parametro generico.

Ad esempio, se stai scrivendo una funzione che accetta un oggetto `Class` come argomento, ma non sai quale sarà la classe specifica, puoi dichiarare il parametro come `Class<?>`.

```java
public void esempio(Class<?> classe) {
    // ...
}
```

Questa dichiarazione consente di passare qualsiasi tipo di classe all'interno della funzione. L'utilizzo di `Class<?>` è comune quando si lavora con il sistema di riflessione e si desidera scrivere codice che possa gestire classi di tipo sconosciuto in modo generico.

> Guarda Esempio08 in Java.

[_Torna all'indice_](#indice)

---

## Class Objects
Di seguito sono riportati alcuni modi per ottenere un oggetto di classe:
- Dato un oggetto `o`, `o.getClass()` restituisce l'oggetto classe associato a `o`.
- Data una stringa `n` contenente il nome completo di una classe, `Class.forName(n)` restituisce l'oggetto classe corretto.
- Dato un caricatore di classi `l` e una stringa `n` contenente il nome completo di una classe, `l.loadClass(n)` restituisce l'oggetto classe corretto.

> Nota che è possibile accedere agli oggetti della classe per nome anche se non sono disponibili oggetti della classe. Potrebbe essere lanciata un'eccezione `ClassNotFoundException`.

Oltre alle funzionalità legate alla riflessione Java dinamica, un oggetto `c` della classe `Class<C>` può:
- Eseguire un cast di tipo dell'oggetto `o` sulla classe rappresentata, con `c.cast(o)`, che è equivalente a `(C)o`.
- Controlla se l'oggetto `o` è un'istanza della classe rappresentata, con `c.isInstance(o)`, che è equivalente a `o` istanza di `C`.
- Controlla se la classe/interfaccia di riferimento è la stessa o è una superclasse/superinterfaccia di una classe rappresentata da `k`, con `c.isAssignableFrom(k)`.

Un "class descriptor" è una rappresentazione testuale di una classe che viene utilizzata in vari contesti, come il caricamento delle classi, la riflessione e la firma delle classi.
> La classe `java.lang.String` ha il class descriptor `Ljava/lang/String;`.

[_Torna all'indice_](#indice)

---

## Introspection
Dato un oggetto c della classe `Class<C>`, è possibile ispezionare la struttura di `C` e, ad esempio:
- È possibile elencare i descrittori di campo dei campi visibili di `C`.
- È possibile elencare i descrittori dei costruttori visibili di `C`.
- È possibile elencare i descrittori di metodo dei metodi visibili di `C`.
- È possibile ottenere un riferimento alla classe oggetto della classe base (o superclasse) di `C`.
- È possibile ottenere riferimenti agli oggetti classe delle interfacce implementate dal `C`.

La parola introspezione si riferisce alla possibilità di un oggetto di ispezionare la sua classe.

[_Torna all'indice_](#indice)

---

## Creazione dinamica degli oggetti
Dato un oggetto `c` della classe `Class<C>`, e' possibile creare oggetti di classe `C`:
- Utilizzando `c.newInstance()`, anche se deprecato.
- Utilizzando `c` per accedere a uno dei costruttori descrittori di `c` e invocare il costruttore con qualche argomenti.

> Nota che, se `C` e' conosciuto, allora la creazione dinamica dell'oggetto non necessita' di un cast esplicito.

```java
// Creazione stringa vuota
String s = String.class.newInstance();
```

### Accesso dinamico ai campi
Dato un oggetto `c` della classe `Class<C>`, e' possibile accedere al descrittore del campo del campo visibile di `c`.
Oltre a descrivere i campi, i descrittori di campo possono essere utilizzati per ottenere o impostare il valore del campo per alcuni oggetti.

Dato un oggetto `c` della classe `Class<C>`, un descrittore di campo `f` ottenuto da `c`, e un oggetto `o` di classe `C`, e' possibile:
1. Usare `f.get(o)` per leggere il valore corrente del campo per `o`.
2. Usare `f.set(o, v)` per impostare il valore corrente del campo su `v` per `o`.

> La classe `java.lang.reflect.Field` non e' generica, e fino a prova contraria, non fornisce al compilatore il tipo del campo descritto.

### Invocazione dinamica dei metodi
Dato un oggetto `c` della classe `Class<C>`, e' possibile accedere al descrittore del metodo dei metodi visibili di `c`.
Oltre a descrivere i metodi, i descrittori di metodo possono essere utilizzati per invocare il metodo descritto con argomenti adeguati.

Dato un oggetto `c` di classe `Class<C>`, un descrittore di metodo `m` ottenuto da `c`, un oggetto `o` di classe `C` e un array di oggetti `a`, è possibile utilizzare `m.invoke(o, a)` per invocare il metodo descritto da `m` sull'oggetto `o` con argomenti `a`.

> La classe `java.lang.reflect.Method` non è generica e pertanto non fornisce al compilatore (ad esempio) il tipo restituito del metodo descritto.

[_Torna all'indice_](#indice)

### Dynamic Proxies
Dato un array `a` di oggetti di classe associati alle interfacce, un proxy dinamico è un oggetto che implementa le interfacce in `a` e richiama il codice utente dopo le invocazioni dei metodi.
> E' un oggetto che funge da rappresentante di un altro oggetto.

I proxies sono creati usando `java.lang.reflect.proxy.Proxy.newInstance`.
Il codice utente richiamato durante l'invocazione del metodo è arbitrario ed è un'implementazione dell'interfaccia funzionale `java.lang.reflect.InvocationHandler`.
Al codice utente viene fornito il descrittore del metodo richiamato.
Il codice utente può restituire un valore, che viene utilizzato come valore di ritorno dell'invocazione che ha attivato l'attivazione del codice utente.

I proxy dinamici possono essere utilizzati per una varietà di scopi, tra cui:
-   **Autenticazione e autorizzazione:** I proxy dinamici possono essere utilizzati per autenticare e autorizzare gli utenti prima di consentirgli di accedere a un oggetto reale.
-   **Logging:** I proxy dinamici possono essere utilizzati per registrare le interazioni di metodo con un oggetto reale.
-   **Profilatura:** I proxy dinamici possono essere utilizzati per profilare le prestazioni di un oggetto reale.
-   **Decoupling:** I proxy dinamici possono essere utilizzati per decouple un client da un oggetto reale.

Ecco un esempio di come utilizzare un proxy dinamico per l'autenticazione:
```java
public class AuthenticationProxy implements MyInterface {

    private MyObject realObject;
    private AuthenticationService authenticationService;

    public AuthenticationProxy(
			    MyObject realObject, 
			    AuthenticationService authenticationService) {
			    
        this.realObject = realObject;
        this.authenticationService = authenticationService;
    }

    @Override
    public void doSomething() {
        // Verifica l'autenticazione dell'utente
        if (!authenticationService.isAuthenticated()) {
            throw new AuthenticationException("Utente non autenticato");
        }

        // Invia la richiesta all'oggetto reale
        realObject.doSomething();
    }
}
```

In questo esempio, il proxy dinamico verifica l'autenticazione dell'utente prima di consentire all'utente di chiamare il metodo `doSomething()` sull'oggetto reale. Se l'utente non è autenticato, viene lanciata un'eccezione.

I proxy dinamici sono un potente strumento che può essere utilizzato per una varietà di scopi. Sono facili da usare e possono essere utilizzati per migliorare la sicurezza, la funzionalità e le prestazioni delle applicazioni.

[_Torna all'indice_](#indice)

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