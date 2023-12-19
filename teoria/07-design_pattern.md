# Indice
```table-of-contents
```
---

## Design Pattern
I design patterns (pattern di progettazione) nel contesto del software sono soluzioni generali e riutilizzabili per problemi comuni che si verificano frequentemente nel design del software.

1.  **Definizione:**
    
    -   Un design pattern non è un design completo trasformabile direttamente in codice sorgente.
    -   È una descrizione o un modello su come risolvere un problema, utilizzabile in diverse situazioni.
2.  **Ruolo:**
    
    -   I design patterns rappresentano le migliori pratiche formalizzate che il progettista può utilizzare per risolvere problemi comuni durante la progettazione di un sistema.
3.  **Natura delle Soluzioni:**
    
    -   I pattern di progettazione forniscono soluzioni generiche e adattabili, piuttosto che progettazioni finite direttamente implementabili.
4.  **Principale Caratteristica:**
    
    -   Sono best practice formalizzate, e il loro utilizzo consente di affrontare in modo efficiente problemi comuni senza dover riprogettare da zero ogni volta.
5.  **Orientamento agli Oggetti:**
    
    -   I design patterns orientati agli oggetti mostrano tipicamente le relazioni e le interazioni tra classi e oggetti, senza specificare le classi o gli oggetti finali che saranno coinvolti nell'applicazione.

Gli sviluppatori possono utilizzare i design patterns come strumenti concettuali per risolvere problemi ricorrenti durante la progettazione e lo sviluppo del software, facilitando la creazione di soluzioni robuste e manutenibili.

---

### GoF
I design patterns hanno guadagnato popolarità nell'ingegneria del software dopo la pubblicazione del libro "Design Patterns: Elements of Reusable Object-Oriented Software" nel 1994, scritto dal cosiddetto Gang of Four (GoF), composto da E. Gamma, R. Helm, R. Johnson, J. Vlissides. Ecco una dettagliata spiegazione:

1.  **Origine e Popolarità:**
    
    -   I design patterns sono diventati popolari nel campo dell'ingegneria del software con la pubblicazione del libro del GoF nel 1994.
    -   Il libro, intitolato "Design Patterns: Elements of Reusable Object-Oriented Software", è stato scritto dai quattro autori noti come Gang of Four.
2.  **Categorie dei Design Patterns:**
    
    -   Il libro GoF discute vari design patterns suddividendoli nelle seguenti classi:
        -   <u>**Creational Patterns</u> (Pattern Creazionali):** Intenti a creare oggetti, evitando che il programmatore istanzi direttamente gli oggetti.
        -   <u>**Structural Patterns</u> (Pattern Strutturali):** Utilizzano l'ereditarietà per comporre interfacce e definiscono modi per comporre oggetti per ottenere nuove funzionalità.
        -   <u>**Behavioral Patterns</u> (Pattern Comportamentali):** Sono specificamente concentrati sui protocolli di comunicazione tra gli oggetti.

Questi design patterns forniscono un approccio strutturato per affrontare tipi specifici di problemi di progettazione, offrendo soluzioni che sono state validate e riconosciute come buone pratiche dalla comunità di sviluppatori.

[_Torna all'indice_](#indice)

---

## Creational Patterns
I Creational Patterns, o Pattern Creazionali, sono una categoria di design patterns che si concentrano sulla creazione degli oggetti:

1.  **Abstract Factory (Factory Astratta):**
    
    -   Raggruppa le fabbriche di oggetti che hanno un tema comune.
    -   Fornisce un'interfaccia per creare famiglie di oggetti o sistemi di oggetti correlati senza specificare le classi concrete degli oggetti.
2.  **Builder (Costruttore):**
    
    -   Costruisce oggetti complessi separando la costruzione dalla rappresentazione.
    -   Consente di creare un oggetto passo dopo passo, consentendo configurazioni flessibili.
3.  **Factory Method (Metodo di Fabbrica):**
    
    -   Crea oggetti senza specificare la classe esatta da creare.
    -   Definisce un'interfaccia per la creazione di un oggetto, ma lascia alle sottoclassi la scelta della classe da istanziare.
4.  **Prototype (Prototipo):**
    
    -   Crea oggetti clonando un oggetto esistente (prototipo).
    -   Permette di creare nuovi oggetti duplicando quelli esistenti, evitando la necessità di creare nuove classi.
5.  **Singleton:**
    
    -   Limita la creazione di oggetti per una classe a una sola istanza.
    -   Assicura che una classe abbia una sola istanza globale e fornisce un punto di accesso globale a tale istanza.

Questi Creational Patterns forniscono approcci diversi alla creazione di oggetti, consentendo una maggiore flessibilità, riusabilità del codice e gestione efficiente delle istanze degli oggetti.

[_Torna all'indice_](#indice)

---

### Abstract Factory

> Conosciuto anche come Kit.

![[47.png]]

L'Abstract Factory è uno dei principali pattern <u>creazionali</u> il cui scopo è quello di fornire un'interfaccia per creare famiglie di oggetti interconnessi fra loro, in modo che non ci sia necessità di specificare i nomi delle classi concrete all'interno del proprio codice. In questo modo si facilita la creazione di un sistema indipendente dall'implementazione degli oggetti concreti, infatti, l'utilizzatore (Client) conosce solo l'interfaccia per creare le famiglie di prodotti ma non la sua implementazione concreta.

L'Abstract Factory è costituito da 5 elementi:
1. **AbstractFactory**: interfaccia che definisce i metodi mediante i quali sarà possibile ottenere gli AbstractProduct.

2. **ConcreteFactory**: nel sistema possono essere create _n_ ConcreteFactory, ciascuna delle quali dovrà implementare l'interfaccia AbstractFactory e quindi implementare i metodi mediante i quali sarà possibile ottenere i ConcreteProduct. Per garantire che nel sistema esiste un'unica istanza di ciascuna ConcreteFactory, è buona norma definire ciascuna di esse come Singleton.

3. **AbstractProduct**: interfaccia che definisce la struttura base dei prodotti che la factory può instanziare.

4. **ConcreteProduct**: nel sistema possono essere creati _n_ ConcreteProduct ciascuno dei quali dovrà implementare l'interfaccia AbstractProduct.

5. **Client**: classe che utilizza l'AbstractFactory per generare i prodotti concreti all'interno del sistema.

Naturalmente il Pattern offre dei vantaggi ma anche degli svantaggi.

I vantaggi principali sono i seguenti:
-   Il pattern permette di isolare i punti di creazione degli oggetti di una classe. La Factory incapsula tutti i meccanismi di creazione. Le classi concrete si trovano specificate soltanto all'interno della factory, il resto si affida alla definizione delle interfacce. Il client può ottenere l'istanza di un prodotto concreto esclusivamente mediante l'interfaccia AbstractFactory.
-   Il client può cambiare la famiglia di prodotti utilizzata semplicemente cambiando la linea di codice che riguarda la creazione della factory.
-   Promuove la consistenza tra i prodotti che sono organizzati in famiglie. I prodotti di una famiglia sono coordinati per lavorare insieme.

Lo svantaggio principlamente è uno: aggiungere un nuovo prodotto richiede la modifica dell'interfaccia AbstractFactory ma la modifica si ripercuote a cascata nelle factory concrete e in tutte le sottoclassi, rendendo laboriosa l'operazione.

Il pattern AbstractFactory può essere utilizzato in un gran numero di situazioni reali. 
Per cercare di acquisire una certa dimestichezza con questo pattern e capirne meglio il funzionamento illustriamo un esempio di utilizzo in un contesto reale. Il nostro esempio simula la renderizzazione di una figura geometrica. Per semplicità implementiamo un'unica ConcreteFactory e soltanto due prodotti che non fanno altro che stampare una stringa a video.

#### Esempio - Abstract Factory
Analizziamo ora in dettaglio le singole interfacce/classi necessarie per implementare il pattern. Partiamo da **FiguraFactory** che rappresenta la nostra AbstractFactory. Definisce i metodi che ciascuna ConcreteFactory deve implementare: `createRettangolo()` e `createCerchio()`. Entrambi i metodi restituiscono un'istanza della classe Figura.

**FiguraFactory:**
```java
public abstract class FiguraFactory {  
	 public abstract Figura createRettangolo();  
	 public abstract Figura createCerchio();  
}
```

**MiaFiguraFactory** rappresenta la nostra unica ConcreteFactory. Tale classe deve estendere la classe astratta FiguraFactory e quindi, implementare i due metodi definiti: `createRettangolo()` e `createCerchio()` che restituiscono rispettivamente, un'istanza della classe MioRettangolo e un'istanza della classe MioCerchio.

**MiaFiguraFactory:**
```java
public class MiaFiguraFactory extends FiguraFactory {  
	public Figura createCerchio() {  
		return new MioCerchio();  
	}  
	public Figura createRettangolo() {  
		return new MioRettangolo();  
	}  
}
```

**Figura** rappresenta il nostro AbstractProduct che definisce la struttura base di un generico prodotto della famiglia. Per semplicità definiamo esclusivamente il metodo `disegna()`.

**Classe Figura:**
```java
public abstract class Figura {  
	 public abstract void disegna();  
}
```

MioCerchio e MioRettangolo sono i nostri ConcreteProduct che estendono la classe astratta Figura. Per semplicità i due metodi stampano soltanto una stringa a video.

**ConcreteProduct: MioCerchio e MioRettangolo**
```java
public class MioCerchio extends Figura {  
	public void disegna() {  
		System.out.println("Io sono il cerchio");  
	}  
}

public class MioRettangolo extends Figura {  
	public void disegna() {  
		System.out.println("Io sono il rettangolo");  
	}  
}
```

Test rappresenta il nostro Client, cioè la classe che utilizza l'AbstractFactory per ottenere un'istanza dei nostri prodotti concreti. È possibile vedere come il Client non deve sapere nulla delle classi concrete che deve utilizzare.

**Client: Classe Test**
```java
public class Test {  
	public static void main(String[] args) {  
		FiguraFactory factory = new MiaFiguraFactory();  
		Figura c = factory.createCerchio();  
		Figura r = factory.createRettangolo();  
		c.disegna();  
		r.disegna();  
	}  
}
```

[_Torna all'indice_](#indice)

---

### Builder
Il Build Pattern e' un pattern <u>creazionale</u> che in molte situazioni può rappresentare una valida alternativa alla costruzione di oggetti mediante costruttori.

La necessità di introdurre meccanismi alternativi a quelli forniti da Java per la creazione di oggetti nasce dal fatto che talvolta le strutture sono molto complesse e non sempre è banale impostare un costruttore ben formato. Pensiamo ai casi in cui il numero di attributi sia molto alto oppure ai casi in cui ci sono attributi che possono anche non essere valorizzati. La probabilità di fare un errore scrivendo il costruttore a mano è molto alta.

L'obiettivo finale è quello di separare la creazione dell'oggetto dalla sua rappresentazione. In tale maniera l'algoritmo per la creazione dell'oggetto è indipendente dalle varie parti che costituiscono l’oggetto e da come vengono assemblate.

La creazione delle istanze e la loro gestione vengono quindi separate in modo da rendere il programma più semplice.

Un aspetto molto interessante è che questi meccanismi permettono di creare un oggetto passo passo, verificandone l'idoneità ad ogni passaggio (pensiamo a quando vogliamo costruire un oggetto con dati provenienti dai risultati di un parser) e soprattutto ci permette di nascondere la logica di controllo che sarebbe magari stata presente nell'eventuale costruttore.

> Il Builder Pattern è usato per creare istanze di oggetti molto complessi con costruttori telescopici nella maniera più semplice.

![[58.png]]

Analizziamo in dettaglio i vari componenti:

-   **Product:** definisce il tipo di oggetto complesso che sarà generato dal **Builder Pattern**.
-   **Builder:** questa **classe astratta** va a definire i vari passaggi per creare correttamente gli oggetti. Ogni metodo è generalmente astratto e le implementazioni sono fornite dalle sottoclassi concrete. Il metodo `getProduct()` è utilizzato per restituire il prodotto finale. Talvolta il Builder viene sostituito da un'interfaccia.
-   **ConcreteBuilder:** possono esserci diverse sottoclassi concrete `ConcreteBuilder`. Queste sottoclassi forniscono i meccanismi per la creazione di oggetti complessi.
-   **Director:** la classe Director controlla l'algoritmo per la creazione dei vari oggetti. Quando viene istanziata, il suo costruttore viene invocato. Contiene un parametro che indica quale `ConcreteBuilder` utilizzare per la creazione degli oggetti. Durante il processo di creazione, i vari metodi del `ConcreteBuilder` vengono richiamati e alla fine delle operazioni, il metodo `getProduct()` viene utilizzato per ottenere il prodotto finale.

#### Esempio - Builder
> Esempio presente in Effective Java di Joshua Bloch.

```java
public class Animal {
	private final String id;
	private String name;
	private String pedigreeName;
	private String owner;
	private String race;
	private String residence;
	private Boolean isVaccinated;
	private Boolean isChampion;
	private List sons;
	private Sex sex;
	private Double weight;
	private Double height;
	
	public Animal(String name, String pedigreeName, String id, String owner, String race, String residence, Boolean isVaccinated, Boolean isChampion, List sons, Sex sex, Double weight, Double height) {
		this.name = name;
		this.pedigreeName = pedigreeName;
		this.id = id;
		this.owner = owner;
		this.race = race;
		this.residence = residence;
		this.isVaccinated = isVaccinated;
		this.isChampion = isChampion;
		this.sons = sons;
		this.sex = sex;
		this.weight = weight;
		this.height = height;
	}
	
	public enum Sex {
		MALE,
		FEMALE
	}
} // ! Animal
```

Applichiamo ora il pattern:

```java
public final class AnimalBuilder {
	private String id;
	private String name;
	private String pedigreeName;
	private String owner;
	private String race;
	private String residence;
	private Boolean isVaccinated;
	private Boolean isChampion;
	private List<String> sons;
	private Animal.Sex sex;
	private Double weight;
	private Double height;
	
	private AnimalBuilder(String id) {
		this.id = id;
	}
	
	public static AnimalBuilder newBuilder(String id) {
		return new AnimalBuilder(id);
	}
	
	public AnimalBuilder name(String name) {
		this.name = name;
		return this;
	}
	
	public AnimalBuilder pedigreeName(String pedigreeName) {
		this.pedigreeName = pedigreeName;
		return this;
	}
	
	public AnimalBuilder owner(String owner) {
		this.owner = owner;
		return this;
	}
	
	public AnimalBuilder race(String race) {
		this.race = race;
		return this;
	}
	
	public AnimalBuilder residence(String residence) {
		this.residence = residence;
		return this;
	}
	
	public AnimalBuilder isVaccinated(Boolean isVaccinated) {
		this.isVaccinated = isVaccinated;
		return this;
	}
	
	public AnimalBuilder isChampion(Boolean isChampion) {
		this.isChampion = isChampion;
		return this;
	}
	
	public AnimalBuilder sons(List<String> sons) {
		this.sons = sons;
		return this;
	}
	
	public AnimalBuilder sex(Animal.Sex sex) {
		this.sex = sex;
		return this;
	}
	
	public AnimalBuilder weight(Double weight) {
		this.weight = weight;
		return this;
	}
	
	public AnimalBuilder height(Double height) {
		this.height = height;
		return this;
	}
	
	public Animal build() {
		return new Animal(name, pedigreeName, id, owner, race, residence, isVaccinated, isChampion, sons, sex, weight, height);
	}
} // ! AnimalBuilder
```

Un oggetto potrà ora essere istanziato come:

```java
Animal pluto = AnimalBuilder.newBuilder("0000001")
	.name("0000001")
	.pedigreeName("PlutoSecondo")
	.owner("Marco Rossi")
	.race("labrador")
	.residence("Via x")
	.isVaccinated(true)
	.isChampion(false)
	.sons(null)
	.sex(Animal.Sex.MALE)
	.weight(40.5)
	.height(30.0)
	.build();
```

> Premettiamo che in questo particolare caso, la classe astratta Builder non è strettamente indispensabile. Può essere aggiunta senza modificare radicalmente la struttura presentata qui sotto.

Troviamo diversi vantaggi nell'utilizzo di questo pattern creazionale, infatti possiamo creare oggetti cloni, o comunque molto simili, minimizzando il codice da scrivere. Il metodo utilizzato è simile al seguente, facendo riferimento al builder istanziato sopra:

```java
Animal animal3A = animalBuilder.build();
Animal animal3AClone = animalBuilder.build();
Animal animal3B = animalBuilder.sex(Animal.Sex.FEMALE).build();
```

Qui si creano due oggetti uguali e un oggetto simile ai due precedenti, ma con sesso opposto. Un vantaggio molto importante è quello di concentrare la validazione della classe in un unico metodo e di ottenere quindi oggetti pressochè immutabili.

Va precisato che la versione presentata è leggermente diversa da quella presentata nel modello originale. L'unico svantaggio dell'utilizzo del pattern è il fatto che vada necessariamente definita una classe builder per ogni oggetto, aumentando nettamente il tempo di sviluppo.

[_Torna all'indice_](#indice)

---

### Factory Method

> Conosciuto anche come Virtual Constructor.

Si tratta di un pattern <u>creazionale</u> basato su classi e viene utilizzato per creare degli oggetti senza conoscerne i dettagli ma delegando un Creator che, in base alle informazioni ricevute, saprà quale oggetto restituire. Questo pattern consente di separare il Client dal Framework  permettendo di modificare i dettagli implementativi senza dovere modificare il Client.

Questo pattern è composto dai seguenti partecipanti:
- Creator: dichiara la `Factory` che avrà il compito di ritornare l’oggetto appropriato.
- ConcreteCreator: effettua l’overwrite del metodo della `Factory` al fine di ritornare l’implementazione dell’oggetto.
- Product: definisce l’interfaccia dell’oggetto che deve essere creato dalla `Factory`.
- ConcreteProduct: implementa l’oggetto in base ai metodi definiti dall’interfaccia `Product`.

![[59.png]]

Tale pattern presenta i seguenti vantaggi/svantaggi:

1.  Rappresenta un gancio alle sottoclassi: tramite il `Creator` è possibile scegliere quale classe concreta utilizzare e decidere di cambiarla senza avere nessun impatto verso il `Client`.
2. Consente di collegare gerarchie di classi in modo parallelo: i `ConcreteCreator` possono collegarsi con i `ConcreteProduct` e generare un collegamento parallelo tra gerarchie diverse.

#### Esempio - Factory Method
Come esempio  pensiamo al caso in cui ci rechiamo in un centro commerciale  per acquistare un paio di scarpe sportive, in particolare da ginnastica, quindi chiediamo al commesso di turno che ci rimanda al commesso specializzato nel settore di nostro interesse che ci consegnerà le scarpe di ginnastica che cercavamo.

Vediamo come si presenta il pattern in UML in base all’esempio:

![[60.png]]

Vediamo come si presenta la classe Cliente:

```java
public class Cliente {
    public static void main(String[] args) {
        Commesso commesso = new Commesso();
        
        Scarpe scarpe = commesso.getScarpe("ginnastica");
        
        System.out.println(scarpe.getClass());
    }
} // ! Cliente
```

Vediamo la definizione del prodotto nella sua definizione e nelle sue implementazioni che nel nostro caso sono vuote per semplicità:

```java
public interface Scarpe { }
 
public class ScarpeGinnastica implements Scarpe { }
 
public class ScarpeTennis implements Scarpe { }
```

Di seguito abbiamo l’implementazioni della Factory:

```java
public class Commesso {
    public Scarpe getScarpe(String tipo) {
        
        Scarpe scarpe = null;
        
        if(tipo.equals("ginnastica"))
            scarpe = CommessoGinnastica.getScarpe();
        else if(tipo.equals("tennis"))
            scarpe = CommessoTennis.getScarpe();
        
        return scarpe;
    }
} // ! Commesso
 
public class CommessoGinnastica extends Commesso {
    public static Scarpe getScarpe(){
        return new ScarpeGinnastica();
    }
} // ! CommessoGinnastica

public class CommessoTennis extends Commesso {
    public static Scarpe getScarpe(){
        return new ScarpeTennis();
    }
} // ! CommessoTennis
```

[_Torna all'indice_](#indice)

---

### Prototype
Si tratta di un pattern <u>creazionale</u> basato su oggetti e viene utilizzato per creare un nuovo oggetto clonando un oggetto già esistente detto prototipo. Questo pattern risulta utile affinchè il Client possa creare nuovi oggetti senza conoscerne i dettagli implementativi ma avvalendosi della clonazione. 

>La creazione del clone avviene a RunTime e non a CompileTime, pertanto il clone viene creato in sede di esecuzione.

Durante la creazione del clone dell’oggetto occorre prestare molta attenzione alla creazione degli oggetti annidati. Una classe può contenere al suo interno dei riferimenti ad altre classi, pertanto la clonazione dell’oggetto principale deve effettuare la clonazione anche di tutti gli altri oggetti al suo interno. 

La clonazione dell’intero albero degli oggetti genera un clone detto ***deep-clone*** in quanto copia tutti gli oggetti presenti. Se la clonazione si limita solo all’oggetto principale “contenitore” allora nel clone verranno mantenuti gli stessi riferimenti agli oggetti secondari: in questo caso si parla di ***shallow-clone***.

![[61.png]]

Tale pattern presenta i seguenti vantaggi/svantaggi:
1. <u>Aggiungere / rimuovere prodotti a RunTime:</u> è possibile decidere a Runtime se aggiungere nuovi oggetti.
2. <u>Specificare nuovi oggetti cambiando il loro valore:</u> invece di creare nuove classi per definire nuovi comportamenti, possiamo cambiare il valore di un oggetto per definire un nuovo comportamento. In questo modo vengono ridotti i numeri delle classi.

#### Esempio - Prototype
Come esempio pensiamo al caso in cui vogliamo creare dei template di tabelle di Hash da poter essere facilmente clonate all’occorrenza. In Java una tabella di hash può essere realizzata utilizzando per esempio le classi HashMap, IdentityHashMap, LinkedHashMap ognuna con caratteristiche diverse. Realizziamo una classe astratta Hash e specializziamo le classi di hash di nostro interesse.

Vediamo come si presenta in UML in base all’esempio:

![[62.png]]

La classe astratta Hash implementa l’interfaccia Clonable per dichiarare la propria volontà di clonazione. 

La classe astratta Hash, ereditando di default dalla classe Object, eredita il metodo `protected native Object clone() throws CloneNotSupportedException;` che implementa di default la clonazione. Nel nostro caso richiameremo semplicemente il metodo nativo `super.clone()`:

```java
public abstract class Hash implements Cloneable {
 
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
 
    public abstract void addItem(Object key, Object value);
 
    public abstract int getSize();
 
}
```

Le classi seguenti MyLinkedHashMap, MyHashMap e MyIdentityHashMap ereditano dalla classe Hash ed effettuano l’overriding del metodo `clone()` *SENZA* deep-copy *MA* shadow-copy ossia non duplicano gli oggetti contenuti in esse.

```java
public class MyLinkedHashMap extends Hash {
    private LinkedHashMap hash = new LinkedHashMap();
    
    @Override
    public Object clone() throws CloneNotSupportedException{
        return (MyLinkedHashMap) super.clone();
    }
    
    @Override
    public void addItem(Object key, Object value) {
        hash.put(key, value);
    }
    
    @Override
    public int getSize() {
        return hash.size();
    }
}
```

> Le classi MyHashMap e MyIdentityHashMap non vengono implementate nell'esempio perche' sono praticamente uguali a MyLinkedHashMap.

La classe Client ha il compito di invocare il template di interesse e richiedere la clonazione.
Nel nostro esempio viene creato un template della classe MyLinkedHashMap per poi essere popolato con una chiave ed a questo punto viene clonato l’oggetto. Abbiamo fatto una shallow-copy, ossia una clonazione superficiale.

L’oggetto MyLinkedHashMap è stato duplicato e ce ne accorgiamo dal fatto che l’hashcode è diverso.
L’oggetto annidato LinkedHashMap non è stato clonato e ce ne accorgiamo dal fatto che se proviamo ad aggiungere una nuova chiave al template questa viene “ritrovata” anche nell’oggetto clonato.

```java
public class Client {

    public static void main(String[] args) throws CloneNotSupportedException {
     
        Hash hash = new MyLinkedHashMap();
        hash.addItem("key1", "value1");
        
        System.out.println("Prototype");
        System.out.println("ClassName: " + hash.getClass().getCanonicalName());
        System.out.println("ClassHashCode:" + hash.hashCode());
        
        Hash hashCloned = (MyLinkedHashMap) hash.clone();
        System.out.println("Clone:");
        System.out.println("ClassName: " + hashCloned.getClass().getCanonicalName());
        System.out.println("ClassHashCode:" + hashCloned.hashCode());
        
        System.out.println("Prototype Hashtable size: " + hash.getSize());
        System.out.println("Cloned Hashtable size: " + hashCloned.getSize());
        
        System.out.println("Adding new key");
        hash.addItem("key2", "value2");
        
        System.out.println("Prototype Hashtable size: " + hash.getSize());
        System.out.println("Cloned Hashtable size: " + hashCloned.getSize());
    
    } // ! main()
} // ! Client
```

Output:
```bash
$JAVA_HOME/bin/java patterns.prototype.Client
Prototype
ClassName: patterns.prototype.A
ClassHashCode:16130931
Clone:
ClassName: patterns.prototype.A
ClassHashCode:26315233
Prototype Hashtable size: 1
Cloned Hashtable size: 1
Adding new key
Prototype Hashtable size: 2
Cloned Hashtable size: 2
```

Guardando l’Object Diagram abbiamo questa situazione di condivisione dell’oggetto hash:

![[63.png]]

Per evitare questo problema occorre prevedere la deep-copy definiendo nell’overriding del metodo `clone()` la clonazione anche dell’oggetto annidato. Pertanto in tutte le classi che ereditano direttamente dalla classe astratta Hash modifichiamo il metodo `clone()` in questo modo:

```java
@Override
public Object clone() throws CloneNotSupportedException{
    MyLinkedHashMap myLinkedHashMap = (MyLinkedHashMap) super.clone();
    myLinkedHashMap.hash = (LinkedHashMap) myLinkedHashMap.hash.clone();
    return myLinkedHashMap;
}
```

In questo modo quando eseguiamo la classe Client vediamo che l’inserimento di una nuova chiave nella nostra tabella di hash di template non determina alcuna modifica nella tabella di hash clonata.

Output:
```bash
$JAVA_HOME/bin/java patterns.prototype.Client
Prototype
ClassName: patterns.prototype.MyLinkedHashMap
ClassHashCode:16130931
Clone:
ClassName: patterns.prototype.MyLinkedHashMap
ClassHashCode:23660326
Prototype Hashtable size: 1
Cloned Hashtable size: 1
Adding new key
Prototype Hashtable size: 2
Cloned Hashtable size: 1
```

Guardando l’Object Diagram abbiamo questa situazione di <u>NON</u> condivisione dell’oggetto hash:

![[64.png]]

A seconda della complessità dell’oggetto template ed a seconda dell’alberatura annidata degli oggetti presenti nella classe template, occorrerà ricordarsi di clonare tutti gli oggetti presenti nel template.

[_Torna all'indice_](#indice)

---

### Singleton

![[57.jpg]]

Il singleton è un pattern <u>creazionale</u> che viene utilizzato per mantenere una singola istanza di una classe e fornire un accesso globale a questa. 
L'utilizzo del pattern si presenta quando:
- Deve esistere esattamente una singola istanza di una classe, e deve essere accessibile dal cliente da un punto d'accesso ben preciso.
- Quando soltanto l'istanza della classe deve essere estesa mediante una sottoclasse, che il cliente deve essere in grado di utilizzare senza modificare il codice.

Il singleton è composto da un solo elemento:
- Singleton, che è responsabile della creazione dell'oggetto e definisce un'operazione `Instance` che permette al cliente di accedere all'istanza univoca della classe.

> Il cliente accede all'istanza univoca mediante l'operazione `Instance`.

I vantaggi principali sono:
- Accesso controllato ad una singola istanza, perché questo questo pattern si occupa di encapsulare l'istanza, avendo il controllo completo su di essa e gestendo come/quando i vari clienti possono accederci.
- Riduzione del namespace, Il pattern è un miglioramento rispetto alle variabili globali, racchiudendo le variabili all'interno dell'istanza.

#### Esempio - Singleton

```java
public class Singleton {
	private static Singleton instance_ = null;

	public static Singleton Instace() {
		if(instance_ == null)
			instance_ = new Singleton();
		return instance_;
	}

	private Singleton() {}
}
```

[_Torna all'indice_](#indice)

---

## Structural Patterns
  
Gli structural design pattern sono un insieme di pattern che si concentrano sulla composizione di classi e oggetti per formare strutture più complesse. Questi pattern sono progettati per gestire la composizione di classi e oggetti in modo flessibile, consentendo la creazione di sistemi più estensibili e riutilizzabili. Ecco una breve panoramica di alcuni degli structural design pattern principali:

1.  **Adapter Pattern (Pattern Adattatore):**
    
    -   **Scopo:** Converte l'interfaccia di una classe in un'altra interfaccia che un cliente si aspetta.
    -   **Utilizzo comune:** Quando si desidera utilizzare una classe esistente che non ha l'interfaccia desiderata.
2.  **Bridge Pattern (Pattern Ponte):**
    
    -   **Scopo:** Separa un'astrazione dalla sua implementazione, consentendo loro di evolvere indipendentemente.
    -   **Utilizzo comune:** Quando si desidera evitare una connessione fissa tra un'astrazione e la sua implementazione.
3.  **Composite Pattern (Pattern Composito):**
    
    -   **Scopo:** Consente di trattare oggetti singoli e composizioni di oggetti in modo uniforme.
    -   **Utilizzo comune:** Per creare strutture ad albero e rappresentare parti-tutto.
4.  **Decorator Pattern (Pattern Decoratore):**
    
    -   **Scopo:** Aggiunge responsabilità a un oggetto dinamicamente.
    -   **Utilizzo comune:** Per estendere le funzionalità di un oggetto in modo flessibile e senza modificare il suo codice.
7.  **Proxy Pattern (Pattern Proxy):**
    
    -   **Scopo:** Fornisce un surrogato o un segnaposto per controllare l'accesso a un oggetto.
    -   **Utilizzo comune:** Per implementare il controllo dell'accesso, il caricamento pigro o la registrazione.

[_Torna all'indice_](#indice)

---

### Adapter
> Conosciuto anche come Wrapper.

Si tratta di un pattern <u>strutturale basato su classi o su oggetti</u> in quanto è possibile ottenere entrambe le rappresentazioni. Viene utilizzato quando si intende utilizzare un componente software ma occorre adattare la sua interfaccia per motivi di integrazione con l’applicazione esistente.

Questo comporta la definizione di una nuova interfaccia che deve essere compatibile con quella esistente in modo tale da consentire la comunicazione con l’interfaccia da “adattare”. In tale contesto possono essere anche effettuate delle trasformazioni di dati per cui l’Adapter si occuperà di interfacciarsi con il nuovo sistema e fornisce anche le regole di mapping dei dati.
Come abbiamo accennato, tale pattern può essere basato sia su classi che su oggetti pertanto l’instanza della classe da adattare può derivare da ereditarietà oppure da associazione.


Questo pattern è composto dai seguenti partecipanti:
- Client: colui che effettua l’invocazione all’operazione di interesse.
- Target: definisce l’interfaccia specifica del dominio applicativo utilizzata dal Client.
- Adaptee: definisce l’interfaccia di un diverso dominio applicativo da dover adattare per l’invocazione da parte del Client.
- Adapter: definisce l’interfaccia compatibile con il Target che maschera l’invocazione dell’Adaptee.

Abbiamo visto precedentemente che il pattern può essere basato su Classi o su Oggetti, in base a questo possiamo schematizzare in UML la relazione esistente tra l’adattatore e l’adattato (Adapter e Adaptee):
1. Sotto forma di ereditarietà come nel caso seguente:
	![[65.png]]
2. Sotto forma di associazione come nel caso seguente:
	![[66.png]]

Tale pattern presenta i seguenti vantaggi/svantaggi:
1. <u>Class Adapter</u>: prevede un rapporto di ereditarietà tra Adapter e Adaptee, in cui Adapter specializza Adaptee, pertanto non è possibile creare un Adapter che specializzi più Adaptee. Se esiste una gerarchia di Adaptee occorre creare una gereachia di Adapter.
2. <u>Object Adapter</u>: prevede un rapporto di associazione tra Adapter e Adaptee, in cui Adapter instanzia Adaptee, pertanto è possible avere un Adapter associato con più Adaptee.

#### Esempio - Adapter
Come esempio pensiamo al caso in cui dobbiamo gestire l’elenco dei dipendenti di una società. I loro dati vengono memorizzati in un java bean dal nome Impiegati che contiene tutte le informazioni personali (nel nostro caso per semplicità indichiamo solo il cognome).

Per effetto di una fusione societaria con una società straniera, il numero dei dipendenti aumenta ed occorre integrare il loro java bean dal nome Employer con quello esistente dal nome Impiegati. Semanticamente è uguale ma sintatticamente è diverso, pertanto creiamo la classe AdattatoreEmployer per adattare la classe Employer.

Sappiamo che possiamo utilizzare sia l’Object Adapter che il Class Adapter: la differenza principale dipende dal fatto che nel secondo caso è richiesta l’ereditarietà multipla e in java non è possibile, o meglio, non è possibile ereditare più classi ma è possibile implementare più interfacce. Questo significa che qualora disponiamo di una interfaccia Target ed un’interfaccia Adaptee possiamo utilizzare anche il Class Adapter.

##### Object Adapter
Per cominciare iniziamo ad implementare Object Adapter per eseguire l’esempio precedente.
Vediamo come si presenta il pattern in UML in base all’esempio nel caso di Object Adapter:

![[67.png]]

A questo punto passiamo a creare la classe Impiegato e la classe Employer:

```java
public class Impiegato {
    private String cognome = null;
    
    public String getCognome() {
        return cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
} // ! Impiegato
```

```java
public class Employer {
    private String lastName = null;
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
} // ! Employer
```

Creiamo la classe Adapter AdattatoreEmployer che eredita la classe Impiegato ed è associata con la classe Employer:

```java
public class AdattatoreEmployer extends Impiegato {
    Employer employer = null;
 
    public AdattatoreEmployer(Employer employer) {
        this.employer = employer;
    }
 
    @Override
    public String getCognome() {
        return employer.getLastName();
    }
 
    @Override
    public void setCognome(String cognome) {
        employer.setLastName(cognome);
    }
} // ! AdattatoreEmployer
```

L’invocazione avviene ad opera della classe Client che passa per l’Adapter per invocare la classe Employer utilizzando gli stessi metodi utilizzati per invocare la classe Impiegato:

```java
public class Client {
    public static void main(String[] args) {
        Impiegato impiegato = new Impiegato();
        impiegato.setCognome("Rossi");
        System.out.println("Impiegato: " + impiegato.getCognome());
        
        AdattatoreEmployer adattatoreEmployer = 
							        new AdattatoreEmployer(new Employer());
        
        adattatoreEmployer.setCognome("Verdi");
        System.out.println("AdattatoreEmployer: " + adattatoreEmployer.getCognome());
    } // ! main()
} // ! Client
```

L’output del Client è mostrato di seguito:
```java
$JAVA_HOME/bin/java patterns.adapter.object.Client
Impiegato: Rossi
AdattatoreEmployer: Verdi
```

##### Class Adapter
Sicuramente più complesso è il caso del Class Adapter.
Vediamo di seguito il Class Diagram che ci mostra che l’Adapter eredita sia da Target che da Adaptee. Nel caso precedente Target ed Adaptee erano due classi, pertanto non era possibile l’ereditarietà multipla in Java, quindi abbiamo previsto due interfacce:
1. Una del Target dal nome InterfacciaImpiegato.
2. Una di Adaptee dal nome InterfacciaEmployer.

![[68.png]]

Le due interfacce definiscono i metodi presenti nelle due organizzazioni:

```java
public interface InterfaceImpiegato {
    public String getCognome();
    public void setCognome(String cognome);
}
```
 
```java
public interface InterfaceEmployer {
    public String getLastName();
    public void setLastName(String lastName);
}
```

Le classi Impiegato ed Employer ereditano dalle due interfacce nel modo seguente:

```java
public class Impiegato implements InterfaceImpiegato {
    private String cognome = null;
    
    @Override
    public String getCognome() {
        return cognome;
    }
    @Override
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
}
```

```java
public class Employer implements InterfaceEmployer {
    private String lastName = null;
    
    @Override
    public String getLastName() {
        return lastName;
    }
    
    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
```

L’Adapter dal nome AdattatoreEmployer può ereditare dalle due interfacce ed espone i metodi previsti da Target ed Adaptee.

```java
public class AdattatoreEmployer extends Employer 
								implements InterfaceEmployer, InterfaceImpiegato {
								
    @Override
    public String getCognome() {
        return getLastName();
    }
    
    @Override
    public void setCognome(String cognome) {
        setLastName(cognome);
    }
}
```

L’invocazione da parte del Client consente di invocare Employer passando per la classe Adapter AdattatoreEmployer, come nel caso precedente Object Adapter.

```java
public class Client {
    public static void main(String[] args) {
        Impiegato impiegato = new Impiegato();
        impiegato.setCognome("Rossi");
        System.out.println("Impiegato: " + impiegato.getCognome());
        
        AdattatoreEmployer adattatoreEmployer = new AdattatoreEmployer();
        adattatoreEmployer.setCognome("Verdi");
        System.out.println("AdattatoreEmployer: " + adattatoreEmployer.getCognome());
    } // ! main()
} // ! Client
```

L’output della classe Client è il seguente:
```java
$JAVA_HOME/bin/java patterns.adapter.classes.Client
Impiegato: Rossi
AdattatoreEmployer: Verdi
```

[_Torna all'indice_](#indice)

---

### Bridge
Si tratta di un pattern <u>strutturale basato su oggetti</u> che viene utilizzato per disaccoppiare dei componeti software. In questo modo è possibile effettuare uno switch a Run-Time, garantire il disaccoppiamento, nascondere l’implementazione, estendere la specializzazione delle classi.

Per esempio: si vuole cambiare l’interfaccia grafica della nostra applicazione da Motif a XWindow preservando la funzionalità di tutti i componenti grafici: in poche parole si vuole cambiare il LookAndFeel di tutti i tasti ma fare in modo che continuino a fare sempre la stessa cosa.

1. La prima idea, <u>ma errata</u>, sarebbe quella di creare 2 classi per ogni tasto, esempio ButtonXWindow e ButtonMotif; RadioXWindow e RadioMotif e via dicendo.  In questo modo ci sarebbe un proliferare di classi da gestire. Ovviamente se viene introdotto un nuovo LookAndFeel occorrerà inserire tutte le classi di gestione dei tasti nuovi.
	In UML sarebbe così rappresentabile:
	
	![[52.png]]
 Occorre separare la funzionalità e l’estetica, come?

2. Un’altra idea, <u>più corretta</u>, è quella di creare 2 gerachie: una per la funzionalità ed una per l’estetica. 
	La funzionalità è composta dal tasto: Button, Radio.  L’estetica è data dal LookAndFell: XWindow, Motif.
	Per disaccoppiare le gerachie definiamo 2 interfacce: la funzionalità nell’interfaccia Tasti e l’estetica nell’interfaccia LookAndFeel, successivamente possiamo implementare le 2 gerachie creando le classi concrete.
	In UML sarebbe così rappresentabile:
	
	![[53.png]]

**Partecipanti e Struttura**
Questo pattern è composto dai seguenti partecipanti:

1.  **Client**: colui che effettua l’invocazione all’operazione di interesse.
2.  **Abstraction**: definisce l’interfaccia del dominio applicativo utilizzata dal Client.
3.  **RefinedAbstraction**: definisce l’implementazione dell’interfaccia utilizzata.
4.  **Implementor**: definisce l’interfaccia da usare come Bridge e riferibile agli oggetti concreti da utilizzare.
5.  **ConcreteImplementor**: implementa l’interfaccia Implementor usata come Bridge per il transito degli oggetti.

Possiamo schematizzare in UML:

![[54.png]]

**Conseguenze**
Tale pattern presenta i seguenti vantaggi/svantaggi:

1. <u>Disaccoppia l’interfaccia dall’implementazione</u>: disaccoppiando Abstraction e Implementor è possibile gestire i cambiamenti delle classi concrete senza cablare nel codice dei riferiementi diretti.
2. <u>Migliora l’estendibilità</u>: è possibile estendere la gerarchia di Abstraction e Implementor senza problemi.
3. <u>Nasconde l’implementazione al client</u>: il Client non si deve porre il problema di conoscere l’implementazione delle classi concrete.

#### Esempio - Bridge
Facciamo un altro esempio: pensiamo al caso in cui ci rechiamo in un ristorante-pizzeria e facciamo un’ordinazione. Il cameriere addetto alla pizzeria prenderà la nostra ordinazione indipendentemente dal tipo di pizza che scegliamo.

Rappresentiamo questa situazione in questo Class Diagram UML:

![[55.png]]

L’interfaccia Cameriere definisce il metodo ordinazione che prende come parametro il Pasto: nel nostro caso sceglieremo una pizza.

```java
public interface Cameriere {
    Pasto ordinazione(Pasto pasto);
}
```

La classe CamerierePizzeria implementa l’interfaccia Cameriere e ritorna
il tipo di pasto che abbiamo scelto.

```java
public class CamerierePizzeria implements Cameriere {
    public Pasto ordinazione(Pasto pasto) {
        return pasto;
    }
}
```

L’interfaccia Pasto definisce il tipo di piatto, pertanto qualunque pietanza ipotizzabile in un ristorante-pizzeria:

```java
public interface Pasto {
    Pasto getPiatto();
}
```

La classe PizzaCapricciosa implementa come viene fatta la pizza Capricciosa.

```java
public class PizzaCapricciosa implements Pasto {
    public Pasto getPiatto() {
        return this;
    }
}
```

Mentre la classe PizzaMargherita implementa come viene fatta la pizza Margherita.

```java
public class PizzaMargherita implements Pasto {
    public Pasto getPiatto() {
        return this;
    }
}
```

Siamo arrivati alla classe Cliente che effettua l’ordinazione. Il nostro cliente ordina una pizza Margherita al cameriere addetto alle pizze.

```java
public class Cliente {
    public static void main(String[] args) {
        Cameriere cameriere = new CamerierePizzeria();
        Pasto ordinazione = cameriere.ordinazione(new PizzaMargherita());
        System.out.println(ordinazione);
    }
}
```

L’ordine della pizza Margherita è stato eseguito.
Possiamo aggiungere qualunque tipo di pizza implementando l’interfaccia Pasto disaccoppiandola con la classe CamerierePizzeria. Nascondiamo l’implementazione della pizza al cameriere che non è tenuto a sapere come viene fatta.

**Estensione**
Visto e considerato che abbiamo parlato di un ristorante-pizzeria, immaginiamo di avere un angolo ristorante servito da un cameriere diverso da quello della pizzeria. Ovviamente abbiamo anche un menù ristorante che presenta altri piatti oltre alle pizze.
Il cameriere addetto al ristorante implementa il metodo ordinazione dall’interfaccia Cameriere e si chiamerà CameriereRistorante. I pasti del ristorante implementano l’interfaccia Pasto.

Rappresentiamo questa situazione in questo Class Diagram UML:

![[56.png]]

Vediamo come si presenta la classe CameriereRistorante che si occupa di servire i clienti del ristorante.

```java
public class CameriereRistorante implements Cameriere {
    public Pasto ordinazione(Pasto pasto) {
        return pasto;
    }
}
```

Queste invece sono le classi che si occupano di implementare l’interfaccia Pasto per gestire altre pietanze: PastaFagioli e PastaPomodoro .

```java
public class PastaFagioli implements Pasto {
    public Pasto getPiatto() {
        return this;
    }
}
```

```java
public class PastaPomodoro implements Pasto {
    public Pasto getPiatto() {
        return this;
    }
}
```

Eseguiamo la classe Cliente che effettua 2 ordinazioni: PizzaMargherita ed un piatto di PastaPomodoro.

```java
public class Cliente {
    public static void main(String[] args) {
        Cameriere[] cameriere  = new Cameriere[2];
 
        cameriere[0] = new CamerierePizzeria();
        Pasto pasto = cameriere[0].ordinazione(new PizzaMargherita());
        System.out.println(pasto);
 
        cameriere[1] = new CameriereRistorante();
        pasto = cameriere[1].ordinazione(new PastaPomodoro());
        System.out.println(pasto);
    }
}
```

[_Torna all'indice_](#indice)

---

### Composite
### Decorator
### Proxy

---

## Behavioral Patterns
### Command
### Interpreter
### Iterator
### Observer

---

### Visitator
Si tratta di un pattern <u>comportamentale basato su oggetti</u> e viene utilizzato per eseguire delle operazioni sugli elementi di una struttura. L’utilizzo di questo pattern consente di definire le operazioni di un elemento senza doverlo modificare.

**Ma com’è possibile?**
Solitamente ogni classe definisce le proprie proprietà e le proprie operazioni nel rispetto del principio della singola responsabilità (SRP) ed usando il concetto di ereditarietà può condividere le operazioni alle classi figlie.  

**Ma cosa succede se ci accorgiamo a posteriori che dobbiamo introdurre una nuova operazione?**
- Se le operazioni sono state definite a *livello di classe*, l’introduzione di un nuovo metodo comporterà la modifica della classe interessata, violando il principio open-closed (OCP).
- Se le operazioni sono state definite a *livello di interfaccia*, l’introduzione di un nuovo metodo comporterà la modifica di tutte le classi figlie.

<u>Ovviamente se questa situazione si presenta frequentemente, la manutenzione del codice non sarà agevole.</u>

**Per evitare questo problema** sarà possibile seguire un’altra strada, ossia disaccoppiare gli oggetti che definiscono lo stato dagli oggetti che definiscono il comportamento ed in questo modo sarà più semplice inserire nuovi metodi.  
Il pattern Visitor ci consente di implementare questa separazione tra stato e comportamento e realizzare il legame tra questi oggetti tramite la definizione di 2 metodi presenti nelle due strutture.

1.  Nella _prima struttura_, che definisce lo stato, è presente il metodo `accept()` che invoca il metodo `visit()`.
2.  Nella _seconda struttura_, che definisce il comportamento, è presente il metodo `visit()`.

In questo modo sarà possibile aggiungere nuove operazioni semplicemente definendo nuove classi nella seconda struttura che si occuperà poi di elaborare lo stato della prima.

Vediamo la rappresentanzione UML usando il Class Diagram:

![[48.png]]

Pertanto, parlando in termini del pattern Visitor:  
In base alla competenza:
1.  la _prima struttura_ definisce gli _Element_ che detengono lo <u>stato</u>.
2.  la _seconda struttura_ definisce i _Visitor_ che detengono i <u>comportamenti</u>.

In base all’ordine di invocazione:
1.  Il Client invoca il metodo `accept()` presente nell’_Element_ passandogli in ingresso un oggetto _Visitor_.
2.  L’_Element_ invoca il metodo `visit()` del Visitor passandogli se stesso (oggetto `this`) come parametro.
3.  Il _Visitor_, disponendo della referenza all’_Element_ (tramite l’oggetto `this`) accede alle proprietà dell’Element ed eseguire le operazioni.

Vediamo la rappresentanzione UML usando il Sequence Diagram:

![[49.png]]

Questo pattern utilizza la tecnica del [Double Dispatch](https://dellabate.wordpress.com/2012/11/28/simple-double-e-multi-dispatch/) al fine di consentire questo scambio di messaggi tra l’Element ed il Visitor, pertanto risulta un po’ complesso considerando che utilizza polimorfismo, overriding ed overloading.

**Partecipanti e Struttura**  
Questo pattern è composto dai seguenti partecipanti:
-   _Element_: definisce il metodo `accept()` che prende un Visitor come argomento.
-   _ConcreteElement_: implementa un oggetto Element che prende un Visitor come argomento.
-   _ObjectStructure_: contiene una collezione di Element che può essere visitata dagli oggetti Visitor.
-   _Visitor_: dichiara un metodo `visit()` per ogni Element; il nome del metodo ed il parametro identificano la classe Element che ha effettuato l’invocazione.
-   _ConcreteVisitor_: implementa il metodo `visit()` e definisce l’algoritmo da applicare per l’Element passato come parametro.

Vediamo come si presenta il Pattern Visitor utilizzando il Class Diagram in UML:

![[50.png]]

**Conseguenze**  
Tale pattern presenta i seguenti vantaggi/svantaggi:

-  <u>Facilità nell’aggiungere nuovi Visitor</u>: definendo un nuovo Visitor sarà possibile aggiungere una nuova operazione ad un Element.

-  <u>Difficoltà nell’aggiungere nuovi Element</u>: definire un nuovo Element comporterà la modifica dell’interfaccia Visitor e di tutte le implementazioni.

-  <u>Separazione tra stato ed algoritmi</u>: gli algoritmi di elaborazioni sono nascosti nelle classi Visitor e non vengono esposti nelle classi Element.

-  <u>Iterazione su struttura eterogenea</u>: la classe Visitor è in grado di accedere a tipi diversi, senza la necessità che tra di essi ci sia un vincolo di parentela. In poche parole, il metodo `visit()` può definire come parametro un tipo $X$ oppure un tipo $Y$ senza che tra di essi ci sia alcuna relazione di parentela, diretta o indiretta.

-  <u>Accumulazione dello stato</u>: un Visitor può accumulare delle informazioni di stato a seguito dell’attraversamento degli Element.

-  <u>Violazione dell’incapsulamento</u>: i Visitor devono poter accedere allo stato degli Element e questo può comportare la violazione dell’incapsulamento.

#### Esempio - Visitor
Calcoliamo l'area e il perimetro di un rettangolo utilizzando il design pattern Visitor.

![[51.png]]

Definiamo l'interfaccia Visitor:
```java
public interface Visitor {
	public void visitRettangoloArea(ElementRettangolo element);
	public void visitRettangoloPerimetro(ElementRettangolo elemento);
}
```

Implementiamo la classe VisitorRettangoloArea. Ovviamente il metodo relativo al calcolo del perimetro non dovrà essere implementato:

```java
public class VisitorRettangoloArea implements Visitor {
    @Override
    public void visitRettangoloArea(ElementRettangolo element) {
        int area = element.getAltezza() * element.getLarghezza();
        System.out.println("L'area del rettangolo e': "+ area);
    }
    @Override
    public void visitRettangoloPerimetro(ElementRettangolo element) {
        throw new UnsupportedOperationException("Not supported.");
    }
}
```

Adesso definiamo l’altro Visitor, VisitorRettangoloPerimetro, che si occupa di calcolare il perimentro del rettangolo:

```java
public class VisitorRettangoloPerimetro implements Visitor {
    @Override
    public void visitRettangoloArea(ElementRettangolo element) {
        throw new UnsupportedOperationException("Not supported.");
    }
    @Override
    public void visitRettangoloPerimetro(ElementRettangolo element) {
        int per = element.getAltezza() + elemento.getLarghezza();
        per = per * 2;
	    System.out.println("Il perimetro del rettangolo e': " + per);
    }
}
```

Adesso nella classe Element, ElementRettangolo, definiamo un comportamento diverso a seconda dell’oggetto passato come paramentro al metodo `accept()`:
```java
public class ElementRettangolo {
    private int altezza;
    private int larghezza;
 
    public int getAltezza() {
        return this.altezza;
    }
 
    public void setAltezza(int altezza) {
        this.altezza = altezza;
    }
 
    public int getLarghezza() {
        return this.larghezza;
    }
 
    public void setLarghezza(int larghezza) {
        this.larghezza = larghezza;
    }
 
    public void accept(Visitor visitor) {
        if (visitor instanceof VisitorRettangoloArea)
            visitor.visitRettangoloArea(this);
        else if (visitor instanceof VisitorRettangoloPerimetro)
            visitor.visitRettangoloPerimetro(this);
    }
}
```

Infine nella classe Client possiamo invocare il nostro Client che si occupa di creare il rettangolo e successivamente di invocare le operazioni relative al calcolo dell’aria e del perimetro in base al tipo di Visitor che viene passato: 

```java
public class Client {
    public void test() {
        ElementRettangolo element = new ElementRettangolo();
        
        element.setAltezza(10);
        element.setLarghezza(20);
        
        element.accept( new VisitorRettangoloPerimetro() ); // 60
        element.accept( new VisitorRettangoloArea() ); // 200
    }
}
```

[_Torna all'indice_](#indice)