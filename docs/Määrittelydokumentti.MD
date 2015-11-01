## Aihe

Ongelmana on löytää lyhin polku verkosta (2D-ruudukosta) kahden solmun välillä. Polunetsinnän ohella myös
vertaillaan konkreettisesti verkkoalgoritmien tehokkuutta toisiinsa nähden.

## Algoritmit ja tietorakenteet

Ohjelma käyttää lyhimmän polun etsintään tunnettua A* algoritmia ja sen optimointikeinoja (Jump Point Search). 
A* vaatii tehokkaan prioriteettijonon toteutuksen (minimikeko).

## Ohjelman käyttö ja syötteet

Graafisen käyttöliittymän kautta voi lisätä ja poistaa läpäisemättömiä solmuja ruudukosta, muuttaa alku- ja 
maalisolmun paikkaa sekä vaihtaa algoritmin käyttävää heuristiikkaa.

## Aika- ja tilavaativuudet

Tavoitellut aika- ja tilavaativuudet vastaavat tunnettuja A* ja A* + JPS vaativuuksia. 

Aikavaativuudet:
* A* = O((|E|+|V|)log|V|)
* A* + JPS = O((|E|+|V|)log|V|)
Tilavaativuudet:
* A* = O(|E|)
* A* + JPS = O(|E|)

## Lähteet

[A* Wikipedia](https://en.wikipedia.org/wiki/A*_search_algorithm)
[Jump Point Search Wikipedia] (https://en.wikipedia.org/wiki/Jump_point_search)
[Jump Point Search](https://harablog.wordpress.com/2011/09/07/jump-point-search/)
[How to Speed Up A* Pathfinding With the Jump Point Search Algorithm](http://gamedevelopment.tutsplus.com/tutorials/how-to-speed-up-a-pathfinding-with-the-jump-point-search-algorithm--gamedev-5818)
[Rectangular Symmetry Reduction](https://harablog.wordpress.com/2011/09/01/rectangular-symmetry-reduction/)