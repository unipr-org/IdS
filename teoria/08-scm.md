# Indice
```table-of-contents
```
---

## SCM
Il Software Configuration Management (SCM) è una disciplina nel campo dell'ingegneria del software che si occupa di gestire e controllare l'evoluzione di un prodotto software durante il suo ciclo di vita. L'obiettivo principale dello SCM è garantire che il software sviluppato risponda in modo efficace ai requisiti, sia correttamente testato e manutenibile nel tempo. 
> The goal is to maximize [programmer] productivity by minimizing [co-ordination] mistakes.

Di seguito, esploreremo i concetti chiave dello SCM:

1.  **Gestione delle Configurazioni:**
    
    -   **Baseline:** Una baseline è uno stato specifico di un sistema software identificato e conservato in modo da poter essere recuperato in futuro. Può rappresentare una versione stabile e verificata del software.
    -   **Controllo delle Modifiche:** Le modifiche al software vengono gestite in modo controllato. Ogni modifica deve essere documentata, valutata e implementata seguendo un processo ben definito.
2.  **Controllo di Versione:**
    
    -   **Repository:** Tutti gli artefatti software (codice sorgente, documentazione, configurazioni, ecc.) sono mantenuti in un repository centrale. I repository consentono il versionamento del software.
    -   **Branching e Merging:** Consentono ai team di lavoro di sviluppare in parallelo senza interferire l'uno con l'altro. Le modifiche effettuate in rami separati possono poi essere integrate (merge) nel ramo principale.
3.  **Gestione delle Modifiche:**
    
    -   **Change Request:** Ogni modifica proposta deve essere documentata attraverso una Change Request. Questa richiesta include informazioni sulla modifica proposta, la sua motivazione e l'impatto previsto.
    -   **Revisione e Approvazione:** Le modifiche sono soggette a revisione da parte del team e richiedono l'approvazione prima di essere implementate.
4.  **Build e Rilascio:**
    
    -   **Build Automation:** L'automazione del processo di creazione del software (build) assicura che ogni versione del software sia riproducibile e costruita da una configurazione nota.
    -   **Deployment:** La gestione delle configurazioni include anche la pianificazione e il controllo del rilascio del software, garantendo che le versioni approvate siano distribuite in modo appropriato.
5.  **Tracciabilità e Audit:**
    
    -   **Tracciabilità:** Fornisce la capacità di seguire le relazioni tra gli artefatti software, come la relazione tra requisiti, codice sorgente e test.
    -   **Auditabilità:** La possibilità di ispezionare e verificare le modifiche apportate al software e ai suoi componenti.
6.  **Ambiente di Lavoro Collaborativo:**
    
    -   **Collaborazione e Comunicazione:** Uno degli aspetti chiave dello SCM è facilitare la collaborazione e la comunicazione tra i membri del team di sviluppo. Strumenti come sistemi di tracciamento dei bug, chat, e-mail integrati possono supportare questa dimensione dello SCM.

[_Torna all'indice_](#indice)

---

## Problemi
Esaminiamo nel dettaglio i problemi relativi al Software Configuration Management (SCM):

1.  **Identification (Identificazione):**
    
    -   **Problema:** Identificare correttamente i singoli componenti e le configurazioni può essere una sfida.
    -   **Esempi di Problemi:**
        -   "Questo ha funzionato ieri, cosa è successo oggi?"
        -   "Abbiamo l'ultima versione?"
        -   "Ho già risolto questo problema. Perché è ancora presente?"
2.  **Change Tracking (Tracciamento delle Modifiche):**
    
    -   **Problema:** Tracciare accuratamente quali modifiche sono state apportate a quali moduli e da chi, quando e per quale motivo.
    -   **Esempi di Problemi:**
        -   "Questo problema è stato risolto?"
        -   "Chi è responsabile di questa modifica?"
        -   "Questa modifica sembra ovvia, è stata già testata in precedenza?"
3.  **Software Production (Produzione del Software):**
    
    -   **Problema:** La costruzione di un programma coinvolge processi come pre-elaborazione, compilazione, collegamento, ecc.
    -   **Esempi di Problemi:**
        -   "Ho appena corretto questo, qualcosa non è stato compilato?"
        -   "Come è stato prodotto questo binario?"
        -   "Abbiamo seguito tutti i passaggi necessari nell'ordine corretto?"
4.  **Concurrent Updating (Aggiornamenti Concorrenti):**
    
    -   **Problema:** Il sistema dovrebbe offrire possibilità per modifiche concorrenti ai componenti.
    -   **Esempi di Problemi:**
        -   "Perché le mie modifiche sono scomparse?"
        -   "Come inserisco queste modifiche nella mia versione?"
        -   "Le nostre modifiche sono in conflitto tra loro?"

Questi problemi sottolineano le sfide fondamentali affrontate nel campo dello SCM. L'identificazione accurata, il tracciamento delle modifiche, la produzione del software senza errori e la gestione degli aggiornamenti concorrenti sono essenziali per garantire la stabilità e la coerenza del software durante il suo ciclo di vita. Soluzioni e pratiche efficaci nello SCM mirano a mitigare questi problemi, fornendo processi, strumenti e procedure chiare per affrontare queste sfide.

[_Torna all'indice_](#indice)

---

### Shared data
Il problema dei dati condivisi nel contesto dello sviluppo del software si riferisce alla gestione e all'accesso concorrente ai file e alle risorse comuni da parte di più sviluppatori all'interno di un team. Questa problematica può portare a conflitti, sovrascritture indesiderate e altri errori quando più persone lavorano contemporaneamente sugli stessi file o progetti.

**Sfide legate ai Dati Condivisi:**
1.  **Conflitti di Versione:**
    
    -   Se più sviluppatori modificano lo stesso file contemporaneamente, potrebbe verificarsi un conflitto quando si cerca di combinare le versioni, specialmente se le modifiche riguardano le stesse linee di codice.
2.  **Sovrascritture Accidentali:**
    
    -   Due sviluppatori potrebbero sovrascrivere involontariamente le modifiche apportate dall'altro, perdendo lavoro e introducendo inconsistenze.
3.  **Difficoltà nel Tracciamento delle Modifiche:**
    
    -   Senza un sistema di gestione delle versioni adeguato, può essere difficile tracciare chi ha apportato quali modifiche e quando, complicando la risoluzione dei problemi e la gestione delle versioni.

**Soluzione: L'utilizzo di Workspaces Indipendenti:**

La soluzione principale per affrontare il problema dei dati condivisi è l'adozione di un modello di sviluppo basato su workspaces indipendenti per ogni sviluppatore. Questo significa che ogni membro del team lavora in un ambiente isolato, che include una copia locale del repository o del progetto.

1.  **Vantaggi dei Workspaces Indipendenti:**
    
    -   **Isolamento:** Ogni sviluppatore lavora nel proprio spazio senza interferire con il lavoro degli altri.
    -   **Riduzione dei Conflitti:** La probabilità di conflitti di versione e sovrascritture indesiderate è significativamente ridotta.
    -   **Tracciamento più Semplice:** È più facile tracciare le modifiche e risolvere i problemi quando ogni sviluppatore gestisce il proprio ambiente.
2.  **Utilizzo di Sistemi di Gestione delle Versioni (VCS):**
    
    -   L'adozione di un VCS, come Git o SVN, facilita la gestione delle modifiche, consentendo agli sviluppatori di lavorare in branch separati e di integrare successivamente le modifiche in modo controllato.
3.  **Politiche di Lavoro Collaborativo:**
    
    -   Definire politiche e procedure chiare per la collaborazione, come la frequenza degli aggiornamenti dal repository condiviso e le modalità di integrazione delle modifiche, contribuisce a mantenere un flusso di lavoro collaborativo ordinato.

L'utilizzo di workspaces indipendenti combinato con pratiche di sviluppo basate su VCS è una strategia efficace per affrontare il problema dei dati condivisi, consentendo a più sviluppatori di contribuire al progetto senza compromettere l'integrità del codice sorgente e delle risorse condivise.

![[86.png]]

[_Torna all'indice_](#indice)

---

### Double maintenance
Il problema della Doppia Manutenzione si riferisce alla necessità di apportare modifiche o correzioni su più versioni di un software quando viene rilasciata una nuova versione o variante. Questo problema si manifesta soprattutto in ambienti in cui sono supportate versioni multiple di un'applicazione o in situazioni in cui il software è personalizzato per clienti diversi.

**Sfide della Doppia Manutenzione:**

1.  **Sviluppo e Manutenzione di Versioni Diverse:**
    
    -   Quando è necessario mantenere versioni diverse di un'applicazione, ad esempio una versione per un cliente specifico e una generica per il pubblico, le modifiche apportate alla versione generica potrebbero richiedere sforzi duplicati per adattarle alla versione personalizzata.
2.  **Rischio di Errori e Incongruenze:**
    
    -   La gestione manuale delle modifiche su più versioni aumenta il rischio di errori, omissioni e incongruenze tra le varie implementazioni.

**Soluzione: Repository "Copy-Write":**

La soluzione principale per affrontare il problema della Doppia Manutenzione è l'implementazione di una strategia di gestione delle versioni nota come "Copy-Write" o "Branch by Abstraction". Questo approccio coinvolge l'utilizzo di una sola repository principale da cui derivano le diverse varianti, evitando di mantenere copie separate per ogni versione.

1.  **Caratteristiche della Repository "Copy-Write":**
    
    -   **Branching Logico:** Piuttosto che creare una copia fisica del codice per ogni versione, si crea un "branch" logico all'interno della repository principale. Questo branch rappresenta l'evoluzione comune di tutte le varianti.
        
    -   **Introduzione di Abstract Layer:** Viene introdotto un livello astratto (Abstract Layer) che rappresenta le differenze specifiche di ciascuna variante. Questo strato astratto consente di separare le modifiche generiche da quelle specifiche delle varianti.
        
2.  **Vantaggi della Repository "Copy-Write":**
    
    -   **Condivisione di Codice Comune:** Le modifiche apportate alla versione generica sono automaticamente condivise con tutte le varianti tramite il branch logico comune.
        
    -   **Riduzione dell'Overhead:** Elimina la necessità di mantenere copie separate del codice per ogni variante, riducendo l'overhead di gestione e manutenzione.
        
    -   **Facilità di Manutenzione:** Le correzioni di bug, gli aggiornamenti e le nuove funzionalità sono applicate in modo coerente su tutte le varianti, semplificando la manutenzione.
        
    -   **Minimizzazione dei Rischi:** Riduce il rischio di errori e incongruenze dovuti alla gestione manuale delle diverse versioni.
        

Implementare una repository "Copy-Write" richiede una pianificazione e una progettazione oculata per definire il livello astratto e garantire una gestione delle versioni efficace. Tuttavia, questa strategia è una soluzione potente per ridurre la complessità e i rischi associati alla gestione di versioni multiple di un'applicazione software.

![[87.png]]

[_Torna all'indice_](#indice)

---

### Simultaneous update
Il problema degli Aggiornamenti Simultanei si verifica quando due o più sviluppatori lavorano contemporaneamente su parti diverse di un sistema software e successivamente cercano di combinare i loro cambiamenti. Questa situazione può portare a conflitti e sovrascritture non desiderate, creando una serie di sfide durante la gestione delle modifiche concorrenti.

**Sfide degli Aggiornamenti Simultanei:**

1.  **Conflitti di Modifica:**
    -   Due o più sviluppatori apportano modifiche alla stessa sezione di codice o al medesimo file contemporaneamente. Quando si tenta di unire o integrare questi cambiamenti, possono verificarsi conflitti che richiedono una risoluzione manuale.
2.  **Perdita di Modifiche:**
    -   Se più sviluppatori modificano lo stesso file o componente e sovrascrivono le modifiche l'uno dell'altro senza consapevolezza, si può verificare la perdita di alcune modifiche.
3.  **Difficoltà nella Fusione:**
    -   La fusione di rami di sviluppo paralleli può diventare complicata a causa delle sovrapposizioni di modifiche. È possibile che siano necessarie attività manuali intensive per risolvere i conflitti.

**Soluzione: Utilizzo del Versioning (Version Control System - VCS):**

La principale soluzione per gestire gli aggiornamenti simultanei è l'implementazione di un sistema di controllo versione (VCS). Un VCS consente di tenere traccia delle modifiche apportate al codice sorgente nel tempo, fornendo un meccanismo per gestire, combinare e monitorare le modifiche effettuate da più sviluppatori contemporaneamente.

1.  **Caratteristiche del Versioning System:**
    
    -   **Storia delle Modifiche:** Registra la storia di tutte le modifiche apportate al codice, consentendo di visualizzare, annullare o combinare modifiche precedenti.
        
    -   **Branching e Merging:** Permette agli sviluppatori di lavorare su rami (branch) separati e di successivamente unire (merge) le loro modifiche. Ciò facilita la gestione di sviluppi paralleli.
        
    -   **Risoluzione dei Conflitti:** Fornisce strumenti per risolvere i conflitti generati durante la fusione di modifiche concorrenti.
        
2.  **Vantaggi dell'Utilizzo del Versioning System:**
    
    -   **Controllo su Modifiche:** Consente agli sviluppatori di lavorare in modo indipendente e tenere traccia delle modifiche.
        
    -   **Collaborazione Efficiente:** Facilita la collaborazione tra sviluppatori consentendo loro di integrare e condividere le modifiche senza perdere il lavoro degli altri.
        
    -   **Gestione delle Versioni:** Offre un sistema strutturato per gestire diverse versioni del software in modo ordinato.
        
    -   **Reversibilità delle Modifiche:** Permette di tornare a versioni precedenti del software in caso di necessità.
        

L'utilizzo di un sistema di controllo versione, come Git, SVN o Mercurial, è considerato una best practice nell'ambito dello sviluppo del software. Questi strumenti forniscono un'infrastruttura robusta per gestire gli aggiornamenti simultanei, riducendo i rischi di conflitti e semplificando la gestione del codice sorgente in un ambiente collaborativo.

![[88.png]]

[_Torna all'indice_](#indice)

---

## Model
  
Il "Model di Babich" si riferisce a un modello proposto da Boris Babich per affrontare problematiche specifiche relative allo sviluppo del software, in particolare quelle legate al problema degli "Aggiornamenti Simultanei". Il modello di Babich si basa su alcuni principi chiave per mitigare le sfide associate all'aggiornamento simultaneo del software.

**Principio Fondamentale**
L'elemento centrale del modello Babich è il principio che i componenti del sistema dovrebbero essere immutabili. In altre parole, una volta creato un componente, non dovrebbe essere modificato, ma piuttosto sostituito con una nuova versione.

![[89.png]]

**Problemi Affrontati dal Modello di Babich:**

1.  **Shared Data (Dati Condivisi):**
    -   Affronta il problema dei dati condivisi, dove più parti del sistema accedono e modificano gli stessi dati contemporaneamente.
2.  **Double Maintenance (Doppia Manutenzione):**
    -   Risolve il problema della doppia manutenzione, che si verifica quando è necessario modificare la stessa porzione di codice in più parti del sistema.
3.  **Simultaneous Update (Aggiornamenti Simultanei):**
    -   Affronta il problema degli aggiornamenti simultanei, ovvero quando più sviluppatori apportano modifiche a componenti diversi allo stesso tempo.

**Come Funziona:**

1.  **Componenti Immobili:**
    -   I componenti del sistema, una volta creati, diventano immutabili. Se è necessario apportare modifiche, viene creata una nuova versione del componente senza alterare l'esistente.
2.  **Evita Condivisione Diretta di Dati:**
    -   Evita situazioni in cui più parti del sistema condividono direttamente lo stesso spazio di memoria dei dati. Invece, le informazioni sono passate attraverso interfacce ben definite.
3.  **Versioning per Componenti:**
    -   Quando è necessario apportare modifiche, viene creata una nuova versione del componente, consentendo di mantenere le versioni esistenti intatte.
4.  **Prevenzione della Doppia Manutenzione:**
    -   Riduce la necessità di effettuare modifiche simili in più parti del sistema, poiché i componenti immutabili vengono sostituiti con nuove versioni.

**Vantaggi del Modello di Babich:**

-   **Riduzione dei Conflitti:** La natura immutabile dei componenti riduce i conflitti dovuti agli aggiornamenti simultanei.
    
-   **Miglior Controllo delle Modifiche:** Le modifiche vengono gestite attraverso la creazione di nuove versioni, garantendo un migliore controllo sulle modifiche apportate al sistema.
    
-   **Minimizzazione della Doppia Manutenzione:** Riduce la necessità di effettuare modifiche simili in più parti del codice, contribuendo a evitare la doppia manutenzione.
    
-   **Miglior Gestione del Versioning:** L'approccio versioning facilita la gestione delle diverse versioni del software.
    

Il Modello di Babich si basa su concetti chiave di immutabilità e versioning, offrendo una strategia per affrontare problemi specifici legati agli aggiornamenti simultanei e alla condivisione di dati nel contesto dello sviluppo del software.

![[90.png]]

[_Torna all'indice_](#indice)

---

## Parallel work
La gestione del lavoro parallelo nel contesto del Software Configuration Management (SCM) coinvolge due strategie principali: la strategia pessimistica (locking) e la strategia ottimistica (copy-merge).

### Pessimistic (Locking)

**Descrizione:**

-   La strategia pessimistica si basa sull'idea di "bloccare" un componente quando un utente inizia a modificarlo.
-   Quando un utente intende apportare modifiche a un componente, richiede un "lock" su quel componente.
-   Il lock impedisce ad altri utenti di apportare modifiche al componente bloccato fino a quando il detentore del lock non rilascia il blocco.

**Pro e Contro:**

-   **Pro:** Evita conflitti diretti, poiché solo una persona alla volta può modificare un componente specifico.
-   **Contro:** Potenziali ritardi e congestioni se molte persone richiedono lock su componenti molto utilizzati. Inoltre, un utente potrebbe trattenere un lock per un periodo prolungato, causando attese per altri sviluppatori.

![[91.png]]

### Ottimistica (Copy-Merge)

**Descrizione:**

-   La strategia ottimistica prevede che più utenti possano apportare modifiche contemporaneamente a componenti diversi.
-   Le modifiche vengono apportate in modo indipendente senza alcun blocco preventivo.
-   Quando è necessario integrare le modifiche, il sistema tenta di "unire" o "mergiare" automaticamente le modifiche provenienti da diversi utenti.

**Pro e Contro:**

-   **Pro:** Favorisce il lavoro parallelo, poiché gli utenti possono modificare indipendentemente i componenti.
-   **Contro:** Può causare conflitti che devono essere risolti durante il merge. La corretta risoluzione dei conflitti richiede una gestione accurata del versioning e un processo di merge ben progettato.

**Scelta tra Pessimistic e Optimistic**
1.  **Pessimistic (Locking):**
    -   **Scelta:** Utile quando i componenti sono complessi e richiedono modifiche estese, riducendo così la probabilità di conflitti.
    -   **Considerazioni:** Può rallentare il processo se molti utenti richiedono lock su componenti frequentemente utilizzati.
2.  **Ottimistica (Copy-Merge):**
    -   **Scelta:** Adatto quando ci sono molte modifiche indipendenti che possono essere integrate successivamente.
    -   **Considerazioni:** Richiede una gestione accurata dei conflitti durante il merge e una comunicazione efficace tra gli sviluppatori.

La scelta tra le due strategie dipende dalla natura del progetto, dalla frequenza delle modifiche e dalla complessità dei componenti. In alcuni casi, potrebbe essere utile adottare un approccio misto basato sulla natura specifica dei componenti e delle attività di sviluppo.

[_Torna all'indice_](#indice)

---

## Copy/Merge Work Model
Il modello di lavoro Copy/Merge è un approccio utilizzato nel contesto del controllo di versione e del Software Configuration Management (SCM). Esso coinvolge il concetto di "branching" e "merging".

1.  **Branching:**
    
    -   Quando un membro del team deve apportare modifiche significative o sperimentali a una parte del codice, crea un "branch" separato.
    -   Un branch è essenzialmente una copia indipendente dell'intero repository o di una parte significativa di esso.
    -   Il branch consente di lavorare indipendentemente sulle modifiche senza influenzare direttamente il codice principale (trunk).
2.  **Lavoro Indipendente:**
    
    -   Gli sviluppatori lavorano indipendentemente sul proprio branch, apportando le modifiche necessarie per implementare nuove funzionalità o risolvere problemi specifici.
3.  **Merging:**
    
    -   Dopo aver completato il lavoro nel proprio branch, gli sviluppatori devono "mergiare" le modifiche nel codice principale (trunk) o in altri branch, se necessario.
    -   Il merge è il processo di integrazione delle modifiche apportate in un branch con le modifiche apportate in un altro branch o nel codice principale.

![[92.png]]

**Vantaggi del Copy/Merge Model:**

-   **Isolamento delle Modifiche:** I branch consentono agli sviluppatori di lavorare in isolamento, riducendo il rischio di conflitti diretti con il codice principale.
-   **Sperimentazione:** I branch possono essere utilizzati per sperimentare nuove funzionalità o approcci senza influenzare il codice principale.
-   **Gestione delle Release:** È possibile mantenere branch separati per gestire le diverse versioni di un'applicazione o per preparare una release stabile.

[_Torna all'indice_](#indice)

---

### Importanza del Branching

1.  **Sviluppo Isolato:**
    
    -   Consentire a diversi membri del team di lavorare su funzionalità o correzioni di bug senza interferire l'uno con l'altro.
2.  **Sperimentazione:**
    
    -   Offrire uno spazio sicuro per sperimentare nuove idee o approcci senza influire sullo sviluppo principale.
3.  **Rilasci Stabili:**
    
    -   Consentire la creazione di branch dedicati per rilasci stabili, garantendo che eventuali correzioni di bug siano integrate solo quando sono pronte.
4.  **Concorrenza:**
    
    -   Supportare lo sviluppo parallelo consentendo a diversi team o sviluppatori di lavorare su funzionalità o aree di codice diverse.
5.  **Gestione di Feature Set:**
    
    -   Agevolare lo sviluppo di nuove funzionalità senza influire sulle funzionalità esistenti finché non sono pronte per l'integrazione.
6.  **Collaborazione Distribuita:**
    
    -   Facilitare la collaborazione tra team distribuiti, in quanto ciascun team può lavorare su un branch separato e successivamente integrare le modifiche.

![[93.png]]

In sintesi, il branching nel Copy/Merge Work Model è fondamentale per gestire il lavoro in modo isolato, sperimentare, mantenere rilasci stabili e facilitare lo sviluppo parallelo. La capacità di integrare le modifiche in modo controllato tramite il merging contribuisce a mantenere la coerenza del codice e a gestire efficacemente lo sviluppo del software.

[_Torna all'indice_](#indice)

---

### 3-Way merging
Il 3-way merging è un processo utilizzato nei sistemi di controllo versione per integrare le modifiche apportate in due branch o versioni divergenti di un file o di un insieme di file. Questo approccio coinvolge tre "versioni" o "snapshot" del codice sorgente:

1.  **Versione "Base" (Base Version):**
    -   Questa è la versione comune del file o dei file su cui sono stati creati i branch separati. Può anche essere chiamata "ancestrale" o "originale".
2.  **Versione "Source" (Source Version):**
    -   Questa è la versione che si trova su un branch. Rappresenta le modifiche apportate su quel branch specifico.
3.  **Versione "Target" (Target Version):**
    -   Questa è la versione presente in un altro branch. Rappresenta le modifiche apportate su un branch diverso.

**Processo di 3-Way Merging:**

1.  **Identificazione delle Differenze:**
    -   Il sistema di controllo versione identifica le differenze tra la versione base, la versione di origine e la versione di destinazione.
2.  **Applicazione delle Modifiche:**
    -   Il sistema applica le modifiche dalla versione di origine alla versione base e, contemporaneamente, applica le modifiche dalla versione di destinazione alla versione base.
3.  **Risoluzione dei Conflitti:**
    -   Se ci sono conflitti durante l'applicazione delle modifiche, il sistema richiede un intervento umano per risolvere manualmente tali conflitti.
4.  **Creazione della Nuova Versione:**
    -   Una volta risolti i conflitti, il sistema crea una nuova versione del file che include tutte le modifiche provenienti dai due branch.

**Vantaggi del 3-Way Merging:**

-   **Riconoscimento Automatico delle Modifiche Comuni:**
    
    -   Il processo è progettato per riconoscere automaticamente le modifiche comuni apportate in entrambi i branch, riducendo la necessità di intervento umano quando le modifiche sono compatibili.
-   **Gestione dei Conflitti:**
    
    -   Fornisce un meccanismo strutturato per gestire i conflitti, che possono verificarsi quando le modifiche apportate in due branch interessano la stessa porzione di codice.
-   **Efficienza nel Merge:**
    
    -   Riduce il rischio di sovrascrivere le modifiche apportate in uno dei branch durante il merge.

Il 3-way merging è particolarmente utile quando si lavora con sviluppo parallelo su branch separati. Consente una maggiore automazione nel processo di integrazione, garantendo al contempo che le modifiche confliggenti siano attentamente gestite per preservare l'integrità del codice sorgente.

![[94.png]]

[_Torna all'indice_](#indice)

---

## Split-Combine Work Model

Lo _split-combine_ è un modello di lavoro parallelo utilizzato nel contesto del Software Configuration Management (SCM). Questo modello è particolarmente utile in situazioni in cui si lavora su rami separati di uno stesso progetto e si desidera riunire successivamente le modifiche apportate in modo efficiente. Vediamo una spiegazione dettagliata del processo _split-combine_:

1.  **Split (Divisione):**
    
    -   Inizia con una versione di base (main branch) del progetto. Quando un team inizia a lavorare su un nuovo aspetto o una nuova funzionalità, si crea un nuovo ramo (_branch_) separato dal ramo principale. Questo nuovo ramo è ora un ambiente isolato in cui il team può apportare modifiche senza influenzare il lavoro sul ramo principale.
2.  **Development on Separate Branches (Sviluppo su Rami Separati):**
    
    -   I diversi team o sviluppatori lavorano contemporaneamente su rami separati. Ogni ramo può includere modifiche, aggiunte o correzioni specifiche.
3.  **Combine (Unione):**
    
    -   Quando le modifiche sono state completate e testate con successo sui rami separati, è il momento di riunire (combining) le modifiche nel ramo principale o in un altro ramo di integrazione. Questo processo è noto come _combine_.
4.  **Conflict Resolution (Risoluzione dei Conflitti):**
    
    -   Durante il processo di combinazione (_combine_), potrebbero verificarsi conflitti. I conflitti possono sorgere quando più rami hanno apportato modifiche alla stessa parte del codice. È necessario risolvere manualmente questi conflitti, decidendo quali modifiche includere nella versione finale.
5.  **Testing and Integration (Test e Integrazione):**
    
    -   Dopo la risoluzione dei conflitti, il codice risultante deve essere testato per garantire che funzioni correttamente. Successivamente, la versione combinata può essere integrata nel flusso principale del progetto.
6.  **Documentation (Documentazione):**
    
    -   È essenziale documentare accuratamente le modifiche apportate in modo che altri membri del team possano comprendere il lavoro svolto e le motivazioni dietro le decisioni di progettazione.

**Vantaggi del Modello Split-Combine:**

-   **Parallel Development:** Permette lo sviluppo parallelo su rami separati.
-   **Isolamento delle Modifiche:** Ogni ramo è isolato, consentendo ai team di lavorare indipendentemente.
-   **Controllo dei Conflitti:** Il processo di combinazione richiede la risoluzione dei conflitti in modo controllato.
-   **Gestione delle Versioni:** Fornisce un modo organizzato per gestire le diverse versioni del codice.

Questo modello è utile in scenari di sviluppo collaborativo, in cui diversi team o sviluppatori lavorano contemporaneamente su parti diverse di un progetto.

![[95.png]]

[_Torna all'indice_](#indice)

---

## CM
La gestione della configurazione (Configuration management) è un processo di ingegneria dei sistemi per stabilire e mantenere la coerenza delle prestazioni, degli attributi funzionali e fisici di un prodotto con i suoi requisiti, la progettazione e le informazioni operative per tutta la sua vita
Il CM, se applicato durante il ciclo di vita di un sistema, fornisce visibilità e controllo delle sue prestazioni, attributi funzionali e fisici. Il CM verifica che un sistema funzioni come previsto e sia identificato e documentato in modo sufficientemente dettagliato per supportare il ciclo di vita previsto. Il processo CM facilita la gestione ordinata delle informazioni di sistema e delle modifiche del sistema.

**Gestione della configurazione tradizionale**
- Identificazione: la selezione e la gestione degli artefatti importanti per la creazione del prodotto.
- Controllo delle modifiche: il controllo delle modifiche alla configurazione di un prodotto e ai suoi artefatti.
- Status accounting: registrazione e reporting dell'implementazione delle modifiche a un prodotto e ai suoi artefatti.
- Audit: la convalida della configurazione di un prodotto per la conformità alla sua definizione.

### Identificazione
L'identificazione nella gestione della configurazione è il processo di selezionare, designare e descrivere gli elementi di configurazione. Gli elementi di configurazione possono essere singoli artefatti, configurazioni più complesse o prodotti costruiti. La gestione della configurazione richiede una corretta identificazione per garantire la tracciabilità e la gestione ordinata delle informazioni del sistema e delle modifiche del sistema nel tempo. Questo processo coinvolge nomi univoci, metadati e archiviazione strutturata per consentire una gestione efficace degli elementi di configurazione. La tracciabilità, sia orizzontale che verticale, è fondamentale per comprendere le relazioni e le dipendenze tra gli elementi di configurazione.

### Status accounting
Lo status accounting nella gestione della configurazione è il processo di registrazione e segnalazione delle informazioni necessarie per gestire efficacemente un progetto. Questo processo prevede la risposta a domande specifiche, che possono variare in base alle esigenze delle persone e alle diverse situazioni. L'interrogazione sulla CMDB può essere standardizzata o ad hoc. La registrazione delle informazioni richiede talvolta aggiornamenti allo schema della CMDB e deve garantire che i dati siano acquisiti correttamente e al momento opportuno. L'obiettivo è rendere le informazioni disponibili e utili a tutti i membri del team per mantenere un costante stato di aggiornamento sul progetto.

### Controllo delle modifiche
Il controllo delle modifiche nella gestione della configurazione è il processo che gestisce le proposte di modifica, dalla valutazione alla loro attuazione. Le richieste di modifica (CR) possono riguardare vari elementi, come report di problemi, deroge, requisiti, ecc. Ogni CR deve contenere tutte le informazioni necessarie sulla modifica proposta. Il processo di modifica comprende la fase di filtraggio, l'analisi d'impatto e la presentazione alla Change Control Board (CCB) per l'approvazione o il rifiuto. L'analisi d'impatto valuta il tempo, le risorse, i costi e le conseguenze delle modifiche proposte. La CCB, presieduta da un presidente con poteri decisivi, si riunisce regolarmente per prendere decisioni su CR presentati. I risultati possono essere approvati, respinti, rinviati o inoltrati.

### Audit
L'audit nella gestione della configurazione è un'indagine indipendente su un elemento di configurazione modificato per verificare la sua conformità a specifiche, standard, accordi contrattuali e altri criteri. L'obiettivo principale è assicurare che il prodotto (CI - Configuration Item) corrisponda alla descrizione nelle specifiche e nella documentazione, verificando anche che il lavoro sia stato eseguito correttamente, rispettando gli standard e le linee guida di sviluppo. Gli audit possono essere effettuati in vari momenti e con diversi gradi di formalità, fungendo da "cancelli di qualità" prima che un CI venga accettato nella baseline.

[_Torna all'indice_](#indice)

---

## Business value
**Come il SCM si traduce in valore per l'azienda?**

-   Sviluppo più rapido
-   Migliore qualità
-   Maggiore affidabilità

**Sette requisiti critici:**

1.  **Sicurezza:** Garantire che le informazioni e i dati siano protetti contro accessi non autorizzati o modifiche indesiderate.
2.  **Stabilità:** Assicurare che il sistema sia coerente e funzionante senza interruzioni indesiderate o errori critici.
3.  **Controllo:** Mantenere un controllo completo sulle modifiche, garantendo che solo le modifiche autorizzate siano implementate.
4.  **Auditabilità:** Fornire una traccia chiara e completa di tutte le attività di gestione della configurazione per scopi di audit e conformità.
5.  **Riproducibilità:** Essere in grado di riprodurre specifiche configurazioni o versioni di un sistema in modo coerente e affidabile.
6.  **Tracciabilità:** Avere la capacità di tracciare l'origine, le modifiche e l'impatto di ciascun componente della configurazione.
7.  **Scalabilità:** Garantire che il sistema sia in grado di gestire crescenti dimensioni e complessità, adattandosi alle esigenze in continua evoluzione del progetto o dell'organizzazione.

Una corretta implementazione del Software Configuration Management (SCM) porta a benefici tangibili per l'azienda, come sviluppo più rapido, migliore qualità e maggiore affidabilità. I requisiti critici indicano gli aspetti fondamentali che devono essere affrontati per ottenere tali benefici. La sicurezza, la stabilità e il controllo sono elementi chiave per garantire un ambiente sicuro e gestibile. L'auditabilità, la riproducibilità e la tracciabilità contribuiscono a una gestione più trasparente e controllata delle configurazioni. Infine, la scalabilità è cruciale per adattarsi alle esigenze mutevoli del progetto o dell'organizzazione nel tempo.

[_Torna all'indice_](#indice)