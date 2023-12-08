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

L'Abstract Factory è uno dei principali pattern creazionali il cui scopo è quello di fornire un'interfaccia per creare famiglie di oggetti interconnessi fra loro, in modo che non ci sia necessità di specificare i nomi delle classi concrete all'interno del proprio codice. In questo modo si facilita la creazione di un sistema indipendente dall'implementazione degli oggetti concreti, infatti, l'utilizzatore (Client) conosce solo l'interfaccia per creare le famiglie di prodotti ma non la sua implementazione concreta.

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

#### Esempio
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
### Singleton

---

## Structural Patterns
### Adapter
### Bridge
### Composite
### Decorator
### Proxy

---

## Behavioral Patterns
### Command
### Interpreter
### Iterator
### Observer
### Visitator