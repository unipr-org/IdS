# Ingegneria del Software
```toc
```
---

## Linguaggi logici
Un linguaggio formale e' descitto da una sintassi (insieme delle formule ben formate) e da una semantica (interpretazione delle formule).

Un linguaggio logico e' un linguaggio formale con l'aggiunta di:
- Assiomi, che consideriamo sempre veri.
- Regole di inferenza, che cercano di ottenere nuove verita' dagli assiomi.

I linguaggi logici possno essere usati per dimostrare i teoremi.

### Categorie
I linguaggi logici si dividono in due categorie:
- Linguaggi logici classici
	- Logica proposizionale
	- Logica di predicati
- Linguaggi logici modali
	- Logiche epistemiche
	- Logiche temporali

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

## Logica proposizionale (composizionale)
Il linguaggio e' basato su simboli proposizionali, i quali sono atomici e possono essere veri o falsi. 
Esempio: $p$ significa che "Alice ama Bob".

Le proposizioni sono ottenute combinando i simboli proposizionali con i simboli atomici (connettivi) $\vee, \wedge, \neg, \to, \equiv$.
Un letterale e' un simbolo proposizionale o la sua negazione.

### Sintassi
E' l'insieme di tutte le formule ben formate.
Tutti i simboli proposizionali sono proposizioni (top e bottom inclusi).
Se A e B sono proposizioni, allora anche allora i risultati ottenute dalle combinazioni con i simboli atomici sono anch'essi delle proposizioni.
Tutto il resto non sono proposizioni.

### Semantica
La semantica della logica proposizionale e' intesa come la ragione sulla verita' o sulla falsita' delle proposizioni. <u>Ai simboli proposizionali deve essere assegnato un valore di verità per garantire che il valore di verità di una proposizione complessa possa essere calcolato.</u>

Un'interpretazione e' una funzione che assegna un valore di verita' per ogni simbolo in P. Si dice *totale* se calcola ogni valore del dominio.
Esempio: $\;I : P \to B, \;\;B = \{ \;F, \;T \;\}$

Data un'interpretazione $I$ su $P$, l'interpretazione $G_I$ di un'arbitraria proposizione in $Prop[\;p\;]$ puo' essere calcolata come segue: 

![[IdS/teoria/images/1.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

### Modelli e soddisfacibilità
Data una interpretazione $I$ e una proposizione $A$
- $I$ soddisfa $A \;(\models)$ se e soltanto se $G_I(A)=T$
- $I$ non soddisfa $A \;(\nvDash)$ se e soltanto se $G_I(A)=F$

Una interpretazione $I$ e' un modello per una proposizione $A$ se e soltanto se $I \models A$.
Una proposizione $A$ e' soffisfabile se e soltanto se esiste una interpretazione $I$ tale che $I \models A$.

Data una interpretazione $I$ e una proposizione $A$, le seguenti regole possono essere usate per verificare se $I \models A$ : 

![[IdS/teoria/images/2.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

### Tautologie
> Una tautologia, in logica, è un'affermazione vera per definizione, quindi fondamentalmente priva di valore informativo.

Una proposizione $A$ e' una tautologia se e soltanto se $I \models A$ per ogni possibile interpretazione $I$. Tutte le interpretazioni sono modelli per $A$.

Per tutte le proposizioni $A$ e $B$, le seguenti proposizioni sono tautologie: 

![[IdS/teoria/images/3.png]]

---

### Contraddizioni
Una proposizione $A$ e' una contraddizione (proposizione insoddisfabile) se e soltanto se nessuna interpretazione e' modello per $A$. Esempio: $\neg \;( \;p\to ( \;p \vee q \;))$

Una proposizione $A$ e'
- una tautologia se e soltanto se $\neg A$ e' una contraddizione
- una contraddizione se e soltanto se $\neg A$ e' una tautologia

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

### Equivalenze logiche
Le proposizioni $A$ e $B$ sono equivalenze logiche ($A \iff B$) se e soltanto se $\models (A \equiv B)$. Per ogni interpretazione $I$, $I \models A$ se e soltanto se $I \models B$.

![[IdS/teoria/images/4.png]]
![[IdS/teoria/images/5.png]]

#### Conseguenze logiche
Un'interpretazione $I$ e' un modello per un insieme di proposizioni $S$ se e soltano se e' un modello per ogni proposizione in $S$.
Un insieme di proposizioni che non ha modelli si dice insoddisfacibile.
La proposizione $A$ e' una conseguenza logica di un insieme di proposizioni $S \;(S \models A)$ se e soltanto se tutti i modelli $I$ per $S$ sono anche modelli per $A$.

> Nota bene: $S \models A$ non implica che tutti i modelli di $A$ sono anche modelli di $S$.

![[6.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

### Trableaux proposizionale
Un tableaux proposizionale (semantico) e' un  albero in cui i nodi sono etichettati con un insieme di proposizioni che usano le seguenti regole finche' esse siano applicabili: 

![[7.png]]

> Nota bene: un tableau proposizionale e' sempre finito.
> ![[8.png]]

I tableau proposizionali sono usati per verificare la satisfiability.
- Un percorso dalla radiche di un tableau completo verso una foglia e' detto chiuso se la foglia e' contadditoria, altrimenti e' detto aperto.
- Un tableau e' detto chiuso se tutti i suoi percorsi sono chiusi.
- L'insieme di proposizioni che identifica la radice di un completo tableau è 0.  unsatisfiable se e solo se il tableau è chiuso
- L'etichetta della foglia di un percorso aperto identifica un insieme di modelli dell'insieme di proposizioni che etichetta la radice

Un insieme di proposizioni $S$ e' unsatisfiable se e soltanto se tutte le foglie del tableau sono marcate come contradditorie.

Dato un insieme di proposizioni $S$ e una proposizione $A$, per verificare che $S \models A$, e' sufficiente considerare $S' = S \cup \{\neg A\}$ e provare, costruendo un tableau, che $S'$ sia unsatisfiable.

![[9.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

#### Forma negata
Una proposizione e' nella forma negata se:
- Solo congiunzioni, disgiunzioni e negazioni sono usate nella proposizione
- Le negazioni si verificano solo nei letterali (ricordiamo che un letterale e' un simbolo proposizionale o la sua negazione)

Qualsiasi proposizione puo' essere trasformata in una equivalente proposizione in forma negata usando le [equivalenze logiche](#Equivalenze%20logiche).

#### Forme congiuntive e disgiuntive
Una proposizione e':
- In forma congiuntiva normale (CNF) se e' in forma negata ed e' strutturata come una congiunzione di disgiunzioni di letterali (ci sono solo AND).
- In forma disgiuntiva normale (DNF) se e' in forma negata ed e' strutturata come una disgiunzione di congiunzioni di letterali (ci sono solo OR).

Qualsiasi proposizione puo' essere trasformata in una equivalente proposizione in forma congiuntiva o disgiuntiva usando le [equivalenze logiche](#Equivalenze%20logiche).

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

## Logiche temporali
Nelle logiche classiche, le proposizioni sono interpretate staticamente e sono interpretate con lo scopo di essere in un unico mondo statico.
Le interpretazioni assegnano staticamente i valori alle proposizioni e non possono cambiare nel tempo.

Nelle logiche temporali, invece, l'interpretazione avviene nell'ambito di un insieme di mondi. 

> Trasformiamo il tempo in una variabile discreta.

L'insieme dei mondi che caratterizzano le logiche temporali corrispondono ai momenti nel tempo.
Il particolare modello di tempo per una data logica temporale è catturato da una relazione di accessibilità (temporale) tra i mondi.

Le logiche temporali estendono la logica proposizionale con operatori temporali (modali) per navigare nei mondi utilizzando la relazione di accessibilità della logica scelta.

Esistono due modelli per esprimere il tempo: 

![[10.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

### Logica temporale lineare (LTL)
La logica temporale lineare ha una relazione di accessibilità che descrive un modello lineare e discreto del tempo isomorfo ai numeri naturali.

![[11.png]]

Dato un software che ci assicura che:
- G(requested $\to$ F received)
- G(received $\to$ X processed)
- G(processed $\to$ FG done)
Se abbiamo fatto bene queste 3 cose, allora la seguente affermazione sara' sempre falsa: G requested $\wedge$ G $\neg$ done.

[_Torna all'indice_](#Ingegneria%20del%20Software)

---
 
### Sintassi e Semantica
La sintassi della LTL e' una semplice estensione della sintassi delle logiche proposizionali. I connettivi binari sono associativi a sinistra. 
La precedenza dei connettivi e operatori segue il seguente ordine: 
$$\neg, \;G, \;F, \;X, \;U, \;\wedge, \;\vee, \;\to, \;\equiv$$

La semantica delle proposizioni LTL e' basata su:
- Ogni momento del tempo e' rappresentato da un numero naturale.
- Per ogni momento del tempo, un mondo e' rappresentato da un insieme di rappresentazioni LTL che sono vere.

La funzione di interpretazione
$$I : P \;\times \; ℕ \to B$$
dove $B = \{ F, T\}$, mappa ogni simbolo proposizionale a un valore di B per ogni momento nel tempo.
> Nel caso in cui ℕ sia 0, indichiamo il momento "adesso".
> O e' vera o e' falsa, non esiste il "forse".

Data una interpretazione $I$ su $P$, l'interpretazione $$G_I : LTL[\;P\;] \;\times \; ℕ \to B$$ di un'arbitraria proposizione LTL in $LTL[\;P\;]$ puo' essere calcolata come segue: 
![[12.png]]
![[13.png]]

#### Semantica degli operatori temporali
![[14.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

### Soddisfacibilita' e Tautologie
Data una interpretazione $M$, un momento nel tempo $i \in ℕ$, e una proposizione LTL $A$:
- $<M,i> \;\models\; A$ ($M$ soddisfa $A$ in $i$) se e solo se $G_M(A,i)=T$
- $<M,i> \;\nvDash\; A$ ($M$ non soddisfa $A$ in $i$) se e solo se $G_M(A,i)=F$

Un'interpretazione $M$ e' un modello per una proposizione $A$ se e solo se esiste qualche $i \in ℕ$ tale che $<M,i> \;\models\; A$ .

Una proposizione LTL $A$ e':
- Soddisfabile se e solo se esiste un modello per A
- Una tautologia ($\models A$) se e solo se per ogni interpretazione $M$ e ogni momento nel tempo $i \in ℕ$, $<M,i> \;\models\; A$.

---

### Modelli
Data una interpretazione $M$ definita su un insieme di simboli proposizionali $P$, le seguenti regole possono essere usate per vericare se $<M,i> \;\models\; A$  ($M$ soddisfa $A$ in $i$): 
![[15.png]]![[16.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

### Equivalenze logiche
Due proposizioni LTL $A$ e $B$ sono equivalenti logiche ($A \Leftrightarrow B$) se e solo se $\models(A \equiv B).$
> Per ogni interpretazione $M$ e in ogni momento del tempo $i \in ℕ$, $<M,i> \;\models\; A$ se e solo se $<M,i> \;\models\; B$.

Altre equivalenze logiche: ![[17.png]] ![[18.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

### Tableaux LTL
I tableaux LTL sono grafi diretti (non alberi) usati per verificare la soddisfacibilita' di un insieme di proposizioni LTL. 
Un tableaux LTL e' un grafo etichettato, il quale non contiene nodi con la stessa etichetta. Un nuovo nodo non viene aggiunto al grafo se la sua etichetta appare in un altro nodo.
> $\{A,B\} = \{A,B,B,A,B,B\}$ stessi insiemi.

#### Forma negata LTL
Come prima cosa dobbiamo trasformare le proposizioni LTL in forma negata usando le seguenti equivalenze logiche: ![[19.png]]

#### Forme congiuntive e disgiuntive LTL
Come per il tableaux proposizionale, anche qui valgono le regole delle forme congiuntive e disgiuntive: ![[20.png]]

#### Regole temporali (temporal rules)
Una volta che ho portato le proposizioni LTL in forma negata, devo applicare dalla radice le regole temporali per trasformare gli operatori temporali in "next" ($X$): ![[21.png]]

#### Regola del loop (loop rule)
La loop rule e' l'ultima regola che deve essere applicata se nessun'altra regola precedente e' applicabile: 
![[22.png]]

> Nota bene: il controllo del loop e' sufficiente per assicurare la terminazione della costruzione del tableaux.

#### Esempio 
![[23.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

### LTL Soddisfacibilita' 
Una eventualita' $E$ e' una proposizione LTL strutturata come F$E$ o AU$E$.
> "Alla fine di una questione succedera' qualcosa".

Una eventualita' F$E$ o AU$E$ e' soddisfatta in un nodo $n$ se esiste un percorso che parte da $n$ la cui etichetta contiene $E$.

Dato un tableau completo, un nodo puo' essere cancellato se:
- Il nodo e' contradditorio.
- L'etichetta di un nodo contiene una eventualita' che non e' soddisfatta nel nodo.
- Tutti i figli di un nodo sono marcati come cancellati.

Un tableau completo e' detto chiuso se e solo se la sua radice puo' essere cancellata.
Un insieme di proposizioni LTL che etichetta la radice di un tableau completo e' unsatisfiable se e solo se il tableau e' chiuso.

> Dato un tableau competo, se non e' chiuso e' aperto.

I percorsi che partono dalla radice in un tableau aperto forniscono informazioni sui modelli dell'insieme di proposizioni LTL che etichettano la radice del tableau.

#### Esempi
![[24.png]]![[25.png]]

[_Torna all'indice_](#Ingegneria%20del%20Software)

---

## Sistemi reattivi concorrenti asincroni
slide 69