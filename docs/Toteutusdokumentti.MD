## Toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelma sisältää graafisen käyttöliittymän, josta voi valita lyhimmän polun etsintään erilaisia algoritmeja ja
niiden käyttämiä heuristiikkafunktioita.

## Saavutetut aika- ja tilavaativuudet

Lisätään myöhemmin

## Puutteet ja parannusehdotukset

Siirtymisen väli-ilmansuuntiin sallivassa ruudukossa Manhattanin heuristiikka ei aina palauta lyhintä polkua,
koska se saattaa yliarvioida lyhimmän etäisyyden maaliin joissakin tapauksissa. Esimerkiksi matka (1,1) -> (0,0)
on Manhattanin arvion mukaan |1-0|+|1-0|=2, mutta ohjelman toteutuksen mukaan sqrt(2).

## Suorituskyky- ja O-analyysivertailu

Aika (ms) laskettu kymmenen perättäisen haun keskiarvona

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

 
### Satunnainen
| Algoritmi | Heuristiikka | Aika (ms) | Iterations | Polun pituus |
|-----------|--------------|-----------|------------|--------------|
| A*        | Dijkstra     | 18        | 5062       | 82.66        |
| A*        | Euclidean    | 2         | 765        | 82.66        |
| JPS       | Euclidean    | 1         | 93         | 82.66        |

## Lähteet

[Määrittelydokumentti](https://github.com/salmirinne/TiraLabra/blob/master/docs/M%C3%A4%C3%A4rittelydokumentti.MD#l%C3%A4hteet)
