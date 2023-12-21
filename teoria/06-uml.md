# Indice
```table-of-contents
```
---

## UML
**UML (Unified Modeling Language)** è un linguaggio di modellazione standardizzato utilizzato per visualizzare, specificare, costruire e documentare i sistemi software. Gli UML sono importanti in quanto forniscono un mezzo comune e comprensibile a tutti gli stakeholder (sviluppatori, analisti, clienti, ecc.) per comunicare e comprendere il design e la struttura di un sistema.

---

### Class Diagram
Spiegazione consigliata: https://www.visual-paradigm.com/guide/uml-unified-modeling-language/uml-class-diagram-tutorial/.

![[43.png]]

**Class Diagram (Diagramma delle Classi)** in UML è uno dei diagrammi più fondamentali e ampiamente utilizzati. Rappresenta la struttura statica del sistema mostrando le classi, i loro attributi, i metodi e le relazioni tra di esse.

1. **Classe**
	-   **Notazione:** È rappresentata come un rettangolo suddiviso in tre sezioni orizzontali.
	-   **Sezioni:**
	    -   **Sezione superiore:** Nome della classe.
	    -   **Sezione centrale:** Attributi della classe con i rispettivi tipi di dati.
	    -   **Sezione inferiore:** Metodi della classe con le loro firme.
2. **Attributo**
	-   **Notazione:** Nome dell'attributo seguito da due punti e dal tipo di dato.
	-   **Esempio:** `nome: String`
3. **Metodo**
	-   **Notazione:** Nome del metodo seguito da parentesi tonde contenenti i parametri di input e il tipo di ritorno.
	-   **Esempio:** `calcolaPrezzo(prodotto: Prodotto): double`
4. **Associazione**
	-   **Definizione:** Rappresenta una relazione tra due classi.
	-   **Notazione:** Linea che collega due classi, con eventuali moltiplicità (numeri) sulle estremità per indicare la cardinalità della relazione.
	-   **Esempio:** `1 -----------> 0..*`
5. **Multiplicità**
	-   **Definizione:** Indica il numero di istanze della classe associata.
	-   **Notazione:** Numeri o asterischi sulle estremità dell'associazione.
	-   **Esempio:** `0..*` indica "zero o più".
6. **Generalizzazione (Ereditarietà)**
	-   **Definizione:** Indica una relazione "è-un" tra classi.
	-   **Notazione:** Linea con una freccia dalla sottoclasse alla superclasse.
	-   **Esempio:** `Studente` (sottoclasse) eredita da `Persona` (superclasse).
7. **Interfaccia**
	-   **Definizione:** Rappresenta un contratto, una collezione di firme di metodi che le classi implementano.
	-   **Notazione:** Rettangolo con il nome preceduto dalla parola chiave "interface".
	-   **Esempio:** `<<interface>> Pagamento`
8. **Classe Astratta**
	-   **Definizione:** Una classe che non può essere istanziata, ma può essere usata come superclasse.
	-   **Notazione:** Nome in corsivo o con una linea tratteggiata.
	-   **Esempio:** `Shape` (classe astratta) con sottoclassi `Cerchio` e `Quadrato`.
9. **Dependency**
	-   **Definizione:** Indica che una classe dipende da un'altra, ma senza una relazione forte.
	-   **Notazione:** Linea tratteggiata con una freccia dalla classe dipendente alla dipendenza.
	-   **Esempio:** `ClasseA` ----> `ClasseB`
10. **Composizione e Aggregazione**
	-   **Definizione:** Indicano relazioni di "parte-di" tra classi.
	-   **Notazione:** Rombo pieno (composizione) o rombo vuoto (aggregazione) sulla linea di connessione.
	-   **Esempio:** `Scuola` (composizione) ha `Aule` (parti).
11. **Note e Commenti**
	-   **Definizione:** Commenti o note per chiarire o fornire dettagli aggiuntivi.
	-   **Notazione:** Nota attaccata a una classe o a un'altra forma con una linea tratteggiata.
	-   **Esempio:** `// Questa classe rappresenta uno studente.`
12. **Pacchetto**
	-   **Definizione:** Raggruppamento di classi correlati.
	-   **Notazione:** Rettangolo con il nome del pacchetto.
	-   **Esempio:** `it.mioProgetto.modello`

[_Torna all'indice_](#indice)

---

### Sequence Diagram
**Sequence Diagram (Diagramma di Sequenza)** in UML è un diagramma di comportamento che rappresenta l'interazione tra oggetti o parti di un sistema nel tempo. Descrive come gli oggetti collaborano tra di loro per completare un'attività o un processo.

![[44.png]]

1.  **Partecipanti (Attori)**
    -   **Definizione:** Oggetti o entità che partecipano all'interazione.
    -   **Notazione:** Rappresentati da box verticali in cima al diagramma, con il nome dell'attore.
2.  **Oggetti**
    -   **Definizione:** Istanze di classi o entità coinvolte nell'interazione.
    -   **Notazione:** Box verticali sotto l'attore, con il nome dell'oggetto e la sua classe tra parentesi.
3.  **Messaggi**  
    -   **Definizione:** Le comunicazioni tra gli oggetti o attori durante l'interazione.
    -   **Notazione:** Linee di freccia tra gli oggetti, con etichette che indicano il tipo di messaggio e i parametri associati.
4.  **Vita dell'Oggetto (Lifeline)**
    -   **Definizione:** Rappresenta la vita di un oggetto durante l'interazione.
    -   **Notazione:** Linea verticale (lifeline) sotto l'oggetto, con una freccia in cima che indica il suo ciclo di vita.
5.  **Attivazione (Activation Bar)**
    -   **Definizione:** Indica il periodo di tempo in cui un oggetto è attivo e sta eseguendo un'operazione.
    -   **Notazione:** Barra orizzontale sulla vita dell'oggetto, posizionata sotto i messaggi che coinvolgono l'oggetto.
6.  **Messaggi Asincroni e Sincroni**
    -   **Definizione:**
        -   **Messaggio Asincrono:** Non blocca il mittente; il mittente può continuare a eseguire altre attività senza attendere una risposta.
        -   **Messaggio Sincrono:** Blocca il mittente fino a quando il destinatario non ha elaborato il messaggio.
    -   **Notazione**
        -   Messaggio Asincrono: Freccia tratteggiata.
        -   Messaggio Sincrono: Freccia solida.
7.  **Creazione ed Eliminazione di Oggetti**
    -   **Definizione:**
        -   **Creazione:** Indica quando un nuovo oggetto viene creato durante l'interazione.
        -   **Eliminazione (Destroy):** Indica quando un oggetto viene eliminato.
    -   **Notazione:**
        -   Creazione: Punto con una "x" al centro sulla vita dell'oggetto.
        -   Eliminazione: Barra orizzontale con una "x" sulle vita dell'oggetto.

[_Torna all'indice_](#indice)

---

### State Diagram

![[45.png]]

Il Diagramma di Stato in UML è una rappresentazione visuale che modella il comportamento di un oggetto o di un sistema in termini dei vari stati attraverso cui può transire durante la sua esecuzione. Questo diagramma è particolarmente utile per modellare entità che passano attraverso stati distinti in risposta agli stimoli esterni o ai cambiamenti interni.

1.  **Stati (States)**
    -   **Definizione:** I diversi stati che un oggetto o un sistema può assumere.
    -   **Notazione:** Rappresentati da riquadri rotondi, etichettati con il nome dello stato.
2.  **Transizioni (Transitions)**
    -   **Definizione:** Le condizioni o gli eventi che causano il passaggio da uno stato all'altro.
    -   **Notazione:** Frecce che collegano gli stati, annotate con la condizione o l'evento che attiva la transizione.
3.  **Eventi (Events)**
    -   **Definizione:** Stimoli esterni o cambiamenti interni che innescano una transizione.
    -   **Notazione:** Etichette lungo le frecce di transizione, indicando l'evento scatenante.
4.  **Azioni (Actions)**
    -   **Definizione:** Le attività o operazioni eseguite quando si verifica una transizione.
    -   **Notazione:** Annotazioni sotto le frecce di transizione, indicando l'azione associata.
5.  **Stati Finali (Final States)**
    -   **Definizione:** Indicano la conclusione o la terminazione di un processo.
    -   **Notazione:** Riquadri rotondi con un cerchio nero al loro interno.
6.  **Stati Iniziali (Initial States)**
    -   **Definizione:** Lo stato iniziale da cui inizia l'esecuzione.
    -   **Notazione:** Una freccia entrante in uno stato rotondo da un punto iniziale.
7.  **Regole di Guarigione (Guard Conditions)**
    -   **Definizione:** Condizioni booleane che determinano se una transizione può verificarsi.
    -   **Notazione:** Specificate accanto alle frecce di transizione, evidenziando le condizioni di passaggio.

[_Torna all'indice_](#indice)

---

### Activity Diagram

![[46.png]]

Il Diagramma delle Attività in UML è uno strumento visuale che modella il flusso di lavoro o le attività all'interno di un sistema. È ampiamente utilizzato per rappresentare processi, procedure o algoritmi, mostrando come diverse attività si collegano e si svolgono nel contesto del sistema.

1.  **Attività (Activity)**
    -   **Definizione:** Rappresenta un'unità di lavoro o un'azione eseguita all'interno del sistema.
    -   **Notazione:** Rappresentato da un rettangolo con il nome dell'attività al suo interno.
2.  **Flusso di Controllo (Control Flow)**
    -   **Definizione:** Indica l'ordine sequenziale delle attività all'interno del diagramma.
    -   **Notazione:** Linee frecce che collegano le attività, mostrando la sequenza di esecuzione.
3.  **Decisioni (Decision Nodes)**
    -   **Definizione:** Rappresentano punti di decisione nel flusso di controllo, dove il percorso può divergere in base a una condizione.
    -   **Notazione:** Rombi con una condizione scritta al loro interno. Le frecce indicano i diversi percorsi possibili.
4.  **Fork e Join Nodes**
    -   **Definizione:** Indicano punti nel diagramma in cui più flussi di controllo si separano (fork) o si riuniscono (join).
    -   **Notazione:** Una barra orizzontale indica un fork, mentre una barra verticale indica un join.
5.  **Nodi di Unione (Merge Nodes)**
    -   **Definizione:** Specificano punti in cui flussi di controllo separati convergono e proseguono insieme.
    -   **Notazione:** Rappresentati come cerchi.
6.  **Swimlanes**
    -   **Definizione:** Divisione dello spazio del diagramma in "corsie" verticali o orizzontali, ciascuna assegnata a un'entità specifica (es. ruolo, attore o sistema).
    -   **Notazione:** Rappresentate da linee orizzontali o verticali attraverso il diagramma.
7.  **Partizioni (Partitions)**
    -   **Definizione:** Suddivisione di attività correlate all'interno di una swimlane specifica.
    -   **Notazione:** Linee tratteggiate che dividono la swimlane in sezioni.
8.  **Flusso di Oggetto (Object Flow)**
    -   **Definizione:** Indica il passaggio di oggetti tra diverse attività.
    -   **Notazione:** Linea tratteggiata con una freccia che indica il flusso degli oggetti.

[_Torna all'indice_](#indice)