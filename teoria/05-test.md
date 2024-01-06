# Indice
```table-of-contents
```
---

## Test-Driven Development (TDD)
1.  **Costi nello sviluppo del software:**
    -   I costi del software spesso dominano i costi complessivi di un sistema, superando i costi hardware.
    -   I costi di manutenzione, distribuiti nel corso degli anni di utilizzo del sistema, costituiscono una parte significativa delle spese legate al software.
2.  **Costi di manutenzione:**
    -   I costi di manutenzione superano i costi di sviluppo nei progetti software.
    -   Il riutilizzo strutturato di framework e librerie software ben testati e documentati mira a ridurre i costi di manutenzione.
3.  **Introduzione del Test-Driven Development (TDD):**
    -   Il TDD viene introdotto per sottolineare l'importanza di un testing adeguato per tutte le parti di un sistema software.
    -   Enfatizza che i componenti software non possono essere efficacemente riutilizzati se non sono stati adeguatamente testati.

### Differenza tra Framework e Libreria
1.  **Framework:**
    -   Un framework è una struttura pre-costruita e riutilizzabile che fornisce una base per la creazione di applicazioni software.
    -   Offre un insieme di regole, linee guida e astrazioni che gli sviluppatori seguono nella creazione di un'applicazione.
    -   L'inversione del controllo è una caratteristica comune dei framework, in cui il framework detta il flusso di controllo dell'applicazione.
    -   Gli sviluppatori estendono e personalizzano il framework fornendo implementazioni specifiche per funzioni o metodi predefiniti.
2.  **Libreria:**
    -   Una libreria è una raccolta di codice pre-scritto e routine che gli sviluppatori possono utilizzare nelle loro applicazioni.
    -   Fornisce funzioni o metodi specifici che possono essere chiamati dall'applicazione per svolgere determinati compiti.
    -   Il controllo del flusso dell'applicazione rimane nelle mani dello sviluppatore; la libreria viene invocata quando necessario.
    -   Le librerie sono tipicamente meno prescrittive rispetto ai framework, consentendo agli sviluppatori una maggiore flessibilità nella progettazione dell'applicazione.

**Esempio:**

-   Supponiamo di costruire un'applicazione web. Un framework per applicazioni web (ad esempio, Django, Ruby on Rails) fornisce un modo strutturato per costruire l'intera applicazione, inclusa la gestione delle richieste, la gestione dei database e la definizione delle route.
-   D'altra parte, una libreria come jQuery fornisce funzioni specifiche per compiti come la manipolazione del DOM in JavaScript. Gli sviluppatori possono utilizzare queste funzioni in modo selettivo, senza una struttura globale prescritta per l'intera applicazione.

[_Torna all'indice_](#indice)

---

## Costi dei Sistemi Software
1.  **Divisione dei costi:**
    -   I costi di un sistema software sono tipicamente suddivisi in due categorie principali:
        -   **Costi diretti:** Direttamente associati alle attività svolte per il sistema.
            -   Esempi: Costi degli sviluppatori, costi degli strumenti di sviluppo, costi di librerie e framework esterni, ecc.
        -   **Costi Indiretti:** Associati a tutte le attività necessarie per supportare le attività svolte per il sistema.
            -   Esempi: Costi di consulenti amministrativi, costi di consulenti legali, ecc.
2.  **Rapporto tra costi diretti e indiretti:**
    -   Di solito, i costi indiretti rappresentano una percentuale significativa rispetto ai costi diretti.
    -   La percentuale di costi indiretti rispetto ai costi diretti può variare, ma generalmente oscilla tra il 50% (per le piccole e medie imprese) e il 100% (per le grandi imprese).

**Esempio:**

-   Supponiamo di sviluppare un'applicazione software per un'azienda. I costi diretti includeranno i salari degli sviluppatori, l'acquisto di strumenti di sviluppo e l'utilizzo di framework esterni. D'altra parte, i costi indiretti potrebbero includere le spese per consulenti amministrativi o legali che supportano le attività di sviluppo. La consapevolezza di questi costi aiuta a gestire il bilancio complessivo del progetto software.

[_Torna all'indice_](#indice)

---

## Evoluzione e Manutenzione del Software

1.  **Necessità dell'evoluzione del software:**
    -   I sistemi software devono evolvere perché:
        -   I requisiti iniziali non sono stati catturati correttamente.
        -   I requisiti cambiano durante il ciclo di vita del sistema.
2.  **Inevitabilità dell'evoluzione:**
    -   L'evoluzione di un sistema software è inevitabile, anche se i requisiti iniziali sono stati catturati correttamente e lo sviluppo iniziale ha prodotto un buon prodotto.
3.  **Attività di manutenzione:**
    -   Le attività di manutenzione sono svolte per far evolvere un sistema software contemporaneamente al suo utilizzo. Queste attività comprendono:
        -   **Manutenzione Correttiva (Corrective Maintenance):** Risolvere anomalie o difetti emersi durante l'uso.
        -   **Manutenzione Perfezionante (Perfective Maintenance):** Migliorare le qualità rilevanti del sistema, anche se non ci sono difetti evidenti.
        -   **Manutenzione Adattativa (Adaptive Maintenance):** Adattare il sistema ai cambiamenti dell'ambiente circostante.

> **Esempio:** Supponiamo che un'applicazione software per la gestione di un magazzino abbia inizialmente previsto solo la gestione di prodotti fisici, ma in seguito i requisiti cambiano, e ora è necessario gestire anche prodotti digitali. In questo caso, l'evoluzione del sistema richiederà attività di manutenzione adattativa per adattare il sistema alle nuove esigenze.

4.  **Costi della Manutenzione:**
    -   I costi di manutenzione sono spesso più del 50% dei costi totali associati a un sistema software nell'intero ciclo di vita.
        -   Spesso si avvicinano al 75%.
        -   Sono rilevanti anche a causa del lungo periodo di tempo in cui il sistema è operativo.
5.  **Ripartizione dei Costi di Manutenzione:**
    -   I costi di manutenzione sono normalmente suddivisi nei seguenti modi:
        -   **Costi di manutenzione correttiva:** Circa il 20%.
        -   **Costi di manutenzione perfettiva:** Circa il 60%.
        -   **Costi di manutenzione adattativa:** Circa il 20%.
6.  **Riduzione dei Costi di manutenzione perfettiva:**
    -   È quindi necessario dedicare molto sforzo alla riduzione dei costi di manutenzione perfezionante assicurando un'analisi accurata e test del software prima del suo rilascio.

> **Esempio:** Supponiamo che un software di contabilità aziendale venga costantemente aggiornato per soddisfare nuove regolamentazioni fiscali. Questo processo di adattamento richiederà costi di manutenzione adattativa. Tuttavia, una fase di analisi e test accurati iniziali potrebbe ridurre i costi associati alle modifiche successive, migliorando così l'efficienza del processo di manutenzione.

7.  **Studi sui costi di manutenzione:**
    -   Studi ben noti sui costi di manutenzione nei sistemi implementati forniscono evidenze empiriche che la maggior parte delle anomalie potrebbe essere individuata attraverso revisioni sistematiche degli artefatti del progetto.
        -   In particolare, il testing strutturato e accurato è un buon modo per garantire che le anomalie non si propaghino ai sistemi implementati.
8.  **Dati interessanti sulla necessità di testing strutturato:**
    -   Alcuni dati interessanti per quantificare la necessità di testing strutturato includono:
        -   1 anomalia su 10 trovate durante il testing si propaga al sistema implementato.
        -   Il costo di risolvere un'anomalia aumenta di un fattore 10 se il sistema è già implementato.
        -   Qualsiasi ritardo nel trovare un'anomalia influenza gravemente il costo di risolvere l'anomalia.

> **Esempio:** Se durante il testing di un'applicazione si scoprono 10 anomalie, è probabile che solo una di esse si verifichi nel sistema implementato. Risolvere un problema dopo che il sistema è già in produzione è significativamente più costoso. Inoltre, qualsiasi ritardo nel rilevare un problema durante il testing potrebbe comportare costi aggiuntivi significativi nel correggerlo successivamente. Pertanto, l'approccio di test accurato può aiutare a ridurre i costi associati alle anomalie post-implementazione.

> Gli "artefatti del progetto" si riferiscono a tutti i documenti, codici sorgenti, diagrammi, specifiche e altri oggetti prodotti durante il ciclo di vita di sviluppo del software. Questi elementi costituiscono il materiale di lavoro e la documentazione generata durante le fasi di progettazione, sviluppo, testing e manutenzione di un progetto software.

[_Torna all'indice_](#indice)

---

## Testing
Alcune considerazioni fondamentali sul testing del software:
1.  **Sufficiente copertura dei casi di test:**
    -   Se il comportamento di una parte di un sistema viene testato in un numero sufficientemente elevato di casi, è possibile considerare accettabile il comportamento di tale parte anche nei (auspicabilmente pochi) casi rimanenti.
    -   L'obiettivo è coprire una vasta gamma di scenari possibili per garantire che il software funzioni correttamente in varie condizioni.
2.  **Limitazioni del testing:**
    -   Sebbene il testing sia un mezzo efficace per individuare anomalie, non può dimostrare che una parte di un sistema è corretta al 100%.
    -   Il testing può fornire un elevato grado di fiducia, ma non è esaustivo nel dimostrare la correttezza assoluta di un sistema.
3.  **Divisione del testing:**
    -   **Testing in the small (o Unit Testing):** Si concentra sul test delle singole parti (unità) del software. L'obiettivo è assicurarsi che ciascuna unità funzioni correttamente.
    -   **Testing in the large:** Coinvolge il test dell'intero sistema. L'obiettivo è garantire che tutte le parti del sistema funzionino correttamente quando sono integrate.

![[42.jpeg]]

### Unit testing
L'unit testing è un processo di test di unità di codice in isolamento. L'obiettivo dell'unit testing è garantire che ogni unità di codice funzioni come previsto.

L'unit testing viene solitamente eseguito dagli sviluppatori software. Gli sviluppatori possono utilizzare diversi strumenti e tecniche per eseguire l'unit testing.

### Module testing
Il module testing è un processo di test di moduli di codice. Un modulo di codice è un gruppo di unità di codice che lavorano insieme per eseguire una funzione specifica.

L'obiettivo del module testing è garantire che ogni modulo di codice funzioni come previsto.

Il module testing viene solitamente eseguito dagli sviluppatori software. Gli sviluppatori possono utilizzare diversi strumenti e tecniche per eseguire il module testing.

### Integration testing
L'integration testing è un processo di test dell'interazione tra moduli di codice. L'obiettivo dell'integration testing è garantire che i moduli di codice interagiscano tra loro come previsto.

L'integration testing viene solitamente eseguito dai testatori professionisti. I testatori possono utilizzare diversi strumenti e tecniche per eseguire l'integration testing.

### System testing
Il system testing è un processo di test dell'intero sistema software. L'obiettivo del system testing è garantire che il sistema software funzioni come previsto in tutte le sue funzionalità.

Il system testing viene solitamente eseguito dai testatori professionisti. I testatori possono utilizzare diversi strumenti e tecniche per eseguire il system testing.

### Acceptance testing
L'acceptance testing è un processo di test del sistema software da parte degli utenti finali. L'obiettivo dell'acceptance testing è garantire che il sistema software soddisfi i requisiti degli utenti finali.

L'acceptance testing viene solitamente eseguito dagli utenti finali. Gli utenti finali possono utilizzare diversi strumenti e tecniche per eseguire l'acceptance testing.

> I tipi di test possono essere eseguiti da diverse persone. L'unit testing e il module testing sono spesso eseguiti dagli sviluppatori software (component testing). L'integration testing, il system testing e l'acceptance testing sono spesso eseguiti da testatori professionisti (user testing).

[_Torna all'indice_](#indice)

---

## Testing in the small & in the slrge
1.  **Testing in the large:**
    -   Tratta il sistema come una scatola nera.
    -   Il testing è mirato a verificare che il comportamento del sistema sia conforme alle aspettative.
    -   Il set di casi di test è selezionato utilizzando gli artefatti prodotti per specificare i requisiti del sistema.
    -   Questo approccio si concentra sulla funzionalità del sistema nel suo complesso, senza esaminare internamente il codice sorgente.
2.  **Testing in the small:**
    -   Tratta il sistema come una scatola bianca.
    -   Esamina una parte sufficientemente piccola del sistema ispezionando il codice consegnato.
    -   Il set di casi di test è selezionato utilizzando il codice considerato.
    -   Tipicamente, si suddivide in:
        -   **Testing delle istruzioni (Statement Testing):** Verifica che ogni istruzione nel codice venga eseguita almeno una volta.
        -   **Testing dei rami (Branch Testing):** Garantisce che ogni ramo nel flusso di controllo del programma venga attraversato.
        -   **Testing di branch e condizione (Branch and Condition Testing):** Assicura che ogni condizione e ogni ramo vengano valutati in tutte le possibili combinazioni.

[_Torna all'indice_](#indice)

### Statement testing
Il concetto di **Statement Testing**, anche chiamato **Coverage Testing**, è basato sull'idea che nessuna parte del codice può essere considerata testata se non è stata eseguita. In altre parole, per rilevare eventuali anomalie, è necessario eseguire almeno una volta le parti del codice che le producono.

Principali punti chiave:
1.  **Coverage testing:**
    -   Il termine "coverage" si riferisce al grado di copertura del codice sorgente da parte dei test.
    -   L'obiettivo è eseguire abbastanza test da garantire che tutte le istruzioni del codice siano state eseguite almeno una volta.
2.  **Esecuzione del codice:**
    -   L'esecuzione del codice è fondamentale per identificare e correggere anomalie.
    -   Solo le parti del codice che sono effettivamente eseguite possono rivelare anomalie durante il testing.
3.  **Set di test:**
    -   Un insieme di casi di test $T$ può essere utilizzato per eseguire il **Statement Testing** di un codice $C$.
    -   Dopo l'esecuzione di tutti i casi di test in $T$, ogni istruzione in $C$ dovrebbe essere stata eseguita almeno una volta.

> Il **Statement Testing** si concentra sull'esecuzione di ogni singola istruzione nel codice sorgente per garantire che tutte siano state testate. Ciò contribuisce a identificare eventuali parti di codice che potrebbero non essere state eseguite durante il testing, indicando la necessità di ulteriori casi di test per migliorare la copertura.

[_Torna all'indice_](#indice)

### Branch testing
Il concetto di **Branch Testing**, spesso chiamato **Path Coverage Testing**, si concentra sull'analisi di tutti i percorsi di esecuzione del codice, puntando a coprire tutti i rami decisionali.

Principali punti chiave:
1.  **Path coverage testing:**
    -   Il termine "path coverage" si riferisce al fatto che l'obiettivo è coprire tutti i percorsi di esecuzione possibili nel codice.
    -   Si tratta di un'approfondita analisi dei percorsi di esecuzione all'interno del codice.
2.  **Rami decisionali:**
    -   I rami decisionali si verificano quando il flusso del programma deve prendere una decisione, ad esempio in una struttura di controllo come un'istruzione `if` o un ciclo `for`.
    -   L'obiettivo del Branch Testing è garantire che tutti i rami decisionali siano stati attraversati almeno una volta.
3.  **Set di test:**
    -   Un insieme di casi di test $T$ può essere utilizzato per eseguire il **Branch Testing** di un codice $C$.
    -   Dopo l'esecuzione di tutti i casi di test in $T$, tutti i percorsi di esecuzione in $C$ dovrebbero essere stati attraversati almeno una volta.
4.  **Anomalie complesse:**
    -   Poiché alcune anomalie possono derivare da percorsi di esecuzione intricati, il Branch Testing è utile per identificare tali situazioni.

> Il **Branch Testing** è progettato per coprire tutti i rami decisionali del codice, contribuendo a rilevare anomalie associate a percorsi di esecuzione complessi e garantendo una copertura più completa del flusso del programma.

[_Torna all'indice_](#indice)

### Branch and Condition Testing
Il **Branch and Condition Testing**, spesso chiamato **Condition Coverage Testing**, è mirato all'analisi delle condizioni che generano diversi percorsi di esecuzione nel codice considerato.

Punti chiave:
1.  **Condition Coverage Testing:**
    -   Questo tipo di testing si concentra sull'analisi delle condizioni all'interno del codice.
    -   Il termine "condition coverage" indica che l'obiettivo è coprire tutte le condizioni nel codice durante l'esecuzione dei test.
2.  **Percorsi di Esecuzione Intricati:**
    -   Gli errori possono spesso derivare da percorsi di esecuzione complessi causati da diverse condizioni interrelate.
    -   Il Branch and condition testing è progettato per affrontare questa complessità, garantendo che tutte le condizioni siano state considerate.
3.  **Set di Test:**
    -   Un insieme di casi di test $T$ può essere utilizzato per eseguire il Branch and Condition Testing di un codice $C$.
    -   Dopo l'esecuzione di tutti i casi di test in $T$, tutte le condizioni in $C$ dovrebbero essere state considerate, tenendo conto di tutte le cause per i loro valori di verità.
4.  **Anomalie Derivate da Condizioni Interrelate:**
    -   Le anomalie possono essere causate da condizioni che interagiscono tra loro in modi intricati. Questo tipo di testing è progettato per identificare tali situazioni.

> Il **Branch and Condition Testing** mira ad affrontare la complessità delle condizioni interrelate nel codice, contribuendo a una copertura più completa delle possibilità di esecuzione e a una maggiore robustezza del software.

[_Torna all'indice_](#indice)

---

## JUnit
**JUnit** e' uno strumento comune utilizzato per supportare il testing strutturato e accurato del codice Java.

1.  **JUnit Overview:**    
    -   **JUnit** è uno strumento comune nel contesto del **testing di codice Java**.
    -   È progettato per fornire un supporto efficace per la scrittura e l'esecuzione di test in ambiente Java.
2.  **Lavoro su Test e Test Suites:**
    -   **JUnit** lavora con due concetti principali: **Test (casi di test)** e **Test Suites**.
    -   I **Test** rappresentano unità individuali di test, mentre le **Test Suites** sono insiemi di test.
3.  **Supporto in Eclipse:**
    -   **Eclipse** fornisce un supporto diretto per **JUnit**.
    -   Questo è utile per promuovere lo sviluppo guidato dai test (Test-Driven Development, TDD) in cui i test sono scritti prima del codice di implementazione.

```java
import static org.junit.Assert.*;
import org.junit.Test;

public class Tests {
	@Test
	public void getNameNotNull() {
		Person tester = new Person();
		assertNotNull(tester.getName());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void setNameNotNull() {
		Person tester = new Person();
		tester.setName(null);
	}
}
```

**Esempio di Test con JUnit:**
- Viene fornito un esempio di test JUnit nel codice Java riportato.
- La classe `Tests` contiene due metodi annotati con `@Test`, che sono i casi di test effettivi:
	- `getNameNotNull`: Verifica che il metodo `getName` di un oggetto `Person` restituisca un valore non nullo.
	- `setNameNotNull`: Verifica che l'assegnazione di un valore nullo al metodo `setName` di un oggetto `Person` generi un'eccezione di tipo `IllegalArgumentException`.

**Utilizzo di Assert:**
- Il metodo `assertEquals` viene utilizzato per confrontare valori.
- La classe `org.junit.Assert` fornisce varie asserzioni utili per verificare le condizioni nei test.

[_Torna all'indice_](#indice)