## Aihe

Ongelmana on l�yt�� lyhin polku verkosta (2D-ruudukosta) kahden solmun v�lill�. Polunetsinn�n ohella my�s
vertaillaan konkreettisesti verkkoalgoritmien tehokkuutta toisiinsa n�hden.

## Algoritmit ja tietorakenteet

Ohjelma k�ytt�� lyhimm�n polun etsint��n tunnettua A* algoritmia ja sen optimointikeinoja (Jump Point Search). 
A* vaatii tehokkaan prioriteettijonon toteutuksen (minimikeko).

## Ohjelman k�ytt� ja sy�tteet

Graafisen k�ytt�liittym�n kautta voi lis�t� ja poistaa l�p�isem�tt�mi� solmuja ruudukosta, muuttaa alku- ja 
maalisolmun paikkaa sek� vaihtaa algoritmin k�ytt�v�� heuristiikkaa.

## Aika- ja tilavaativuudet

Tavoitellut aika- ja tilavaativuudet vastaavat tunnettuja A* ja A* + JPS vaativuuksia. 

Aikavaativuudet:
* A* = O((|E|+|V|)log|V|)
* A* + JPS = O((|E|+|V|)log|V|)

Tilavaativuudet:
* A* = O(|E|)
* A* + JPS = O(|E|)

## L�hteet

[A* Wikipedia](https://en.wikipedia.org/wiki/A*_search_algorithm)

[Jump Point Search Wikipedia] (https://en.wikipedia.org/wiki/Jump_point_search)

[Jump Point Search](https://harablog.wordpress.com/2011/09/07/jump-point-search/)

[How to Speed Up A* Pathfinding With the Jump Point Search Algorithm](http://gamedevelopment.tutsplus.com/tutorials/how-to-speed-up-a-pathfinding-with-the-jump-point-search-algorithm--gamedev-5818)

[Rectangular Symmetry Reduction](https://harablog.wordpress.com/2011/09/01/rectangular-symmetry-reduction/)