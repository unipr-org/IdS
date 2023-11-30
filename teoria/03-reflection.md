# Indice
```table-of-contents
```
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
