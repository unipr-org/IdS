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

Data un'interpretazione $I$ su $P$, l'interpretazione $G_I$ di un'arbitraria proposizione in Prop[p] puo' essere calcolata come segue: 

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
Una proposizione $A$ e' una contraddizionhe (proposizione insoddisfabile) se e soltanto se nessuna interpretazione e' modello per $A$. Esempio: $\neg \;( \;p\to ( \;p \vee q \;))$

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
slide 44