## Toteutusdokumentti

## Ohjelman yleisrakenne

Ohjelma sis‰lt‰‰ graafisen k‰yttˆliittym‰n, josta voi valita lyhimm‰n polun etsint‰‰n erilaisia algoritmeja ja
niiden k‰ytt‰mi‰ heuristiikkafunktioita.

## Saavutetut aika- ja tilavaativuudet

Lis‰t‰‰n myˆhemmin

## Puutteet ja parannusehdotukset

Siirtymisen v‰li-ilmansuuntiin sallivassa ruudukossa Manhattanin heuristiikka ei aina palauta lyhint‰ polkua,
koska se saattaa yliarvioida lyhimm‰n et‰isyyden maaliin joissakin tapauksissa. Esimerkiksi matka (1,1) -> (0,0)
on Manhattanin arvion mukaan |1-0|+|1-0|=2, mutta ohjelman toteutuksen mukaan sqrt(2).

## Suorituskyky- ja O-analyysivertailu

Suluissa kymmenen haun ajallinen keskiarvo, openlistin koko ja k‰ytetty heuristiikka

Oletusruudukko

A* (25ms, 9739, Dijkstra)
A* (25ms, 6130, Euclidean)
JPS (1ms, 13, Euclidean)

Satunnainen 

A* (15ms, 4790, Dijkstra)
A* (2ms, 464, Euclidean)
JPS (1ms, 136, Euclidean)

## L‰hteet

[M‰‰rittelydokumentti](https://github.com/salmirinne/TiraLabra/blob/master/docs/M%C3%A4%C3%A4rittelydokumentti.MD#l%C3%A4hteet)
