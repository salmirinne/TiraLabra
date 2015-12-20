## Toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelma sisältää graafisen käyttöliittymän, josta voi valita lyhimmän polun etsintään erilaisia algoritmeja ja
niiden käyttämiä heuristiikkafunktioita.

## Saavutetut aika- ja tilavaativuudet

### Aikavaativuus

A* = O((|E|+|V|)log|V|)  
JPS = O((|E|+|V|)log|V|)

### Tilavaativuus

A* = O(|E|)  
JPS = O(|E|)

## Puutteet ja parannusehdotukset

Siirtymisen väli-ilmansuuntiin sallivassa ruudukossa Manhattanin heuristiikka ei aina palauta lyhintä polkua,
koska se saattaa yliarvioida lyhimmän etäisyyden maaliin joissakin tapauksissa. Esimerkiksi matka (1,1) -> (0,0)
on Manhattanin arvion mukaan |1-0|+|1-0|=2, mutta ohjelman toteutuksen mukaan sqrt(2).

## Suorituskyky- ja O-analyysivertailu

Aika (ms) laskettu kymmenen perättäisen haun keskiarvona ja iterations tarkoittaa tarkasteltujen solmujen määrää

### Oletusruudukko
| Algoritmi | Heuristiikka | Diagonal | Aika (ms) | Iterations | Polun pituus |
|-----------|--------------|----------|-----------|------------|--------------|
| A*        | Dijkstra     | Kyllä    | 25        | 9739       | 149.19       |
| A*        | Euclidean    | Kyllä    | 25        | 6130       | 149.19       |
| A*        | Chebyshev    | Kyllä    | 26        | 6398       | 149.19       |
| JPS       | Euclidean    | Kyllä    | 1         | 13         | 149.19       |
| A*        | Dijkstra     | Ei       | 21        | 9734       | 182.0        |
| A*        | Manhattan    | Ei       | 35        | 7245       | 182.0        |
| A*        | Euclidean    | Ei       | 21        | 8005       | 182.0        |
| A*        | Chebyshev    | Ei       | 22        | 8057       | 182.0        |

 
### [Testilabyrintti] (https://github.com/salmirinne/TiraLabra/tree/master/docs/img/testaus_doc.png)
| Algoritmi | Heuristiikka | Diagonal | Aika (ms) | Iterations | Polun pituus |
|-----------|--------------|----------|-----------|------------|--------------|
| A*        | Dijkstra     | Kyllä    | 8         | 6407       | 137.29       |
| A*        | Euclidean    | Kyllä    | 3         | 1825       | 137.29       |
| A*        | Chebyshev    | Kyllä    | 2         | 1988       | 137.29       |
| JPS       | Euclidean    | Kyllä    | 1         | 119        | 137.29       |
| A*        | Dijkstra     | Ei       | 21        | 6455       | 166.0        |
| A*        | Manhattan    | Ei       | 2         | 1826       | 166.0        |
| A*        | Euclidean    | Ei       | 2         | 2218       | 166.0        |
| A*        | Chebyshev    | Ei       | 2         | 2486       | 166.0        |

## Lähteet

[Määrittelydokumentti](https://github.com/salmirinne/TiraLabra/blob/master/docs/M%C3%A4%C3%A4rittelydokumentti.MD#l%C3%A4hteet)
