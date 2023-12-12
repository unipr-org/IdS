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
### Factory Method
### Prototype

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

	protected Singleton() {}
}
```

>  Per controllare la creazione di in singleton, mantenendo la possibilità di creare delle sottoclassi, è necessario mettere il costruttore `protected`.



[_Torna all'indice_](#indice)

---

## Structural Patterns
### Adapter

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

**Implementazione**
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