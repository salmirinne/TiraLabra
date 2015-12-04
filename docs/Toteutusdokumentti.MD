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

Suluissa kymmenen haun ajallinen keskiarvo, openlistin koko ja käytetty heuristiikka

### Oletusruudukko
| Algoritmi | Heuristiikka | Aika (ms) | Iterations | Polun pituus |
|-----------|--------------|-----------|------------|--------------|
| A*        | Dijkstra     | 25        | 9739       | 149.19       |
| A*        | Euclidean    | 25        | 6130       | 149.19       |
| JPS       | Euclidean    | 1         | 13         | 149.19       |

 
### Satunnainen
| Algoritmi | Heuristiikka | Aika (ms) | Iterations | Polun pituus |
|-----------|--------------|-----------|------------|--------------|
| A*        | Dijkstra     | 18        | 5062       | 82.66        |
| A*        | Euclidean    | 2         | 765        | 82.66        |
| JPS       | Euclidean    | 1         | 93         | 82.66        |

## Lähteet

[Määrittelydokumentti](https://github.com/salmirinne/TiraLabra/blob/master/docs/M%C3%A4%C3%A4rittelydokumentti.MD#l%C3%A4hteet)
