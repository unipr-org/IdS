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