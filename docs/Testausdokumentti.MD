## Testausdokumentti

### J-Unit testit

Yksikkötestejä löytyy minimikeolle ja jokaiselle verkkoalgoritmille eri heuristiikoilla. Jump Point Searchin
testaus rajoittuu lähinnä sen ja A*:n tulosten peräkkäisiin vertailuihin satunnaisissa ruudukoissa. Yksikkötestejä
olisi voinut kirjoittaa huomattavan määrän JPS:n jump ja getNeighbours -metodeille, mutta käytin tässä vain
graafisen käyttöliittymän antamia tuloksia. Testit voidaan suorittaa mavenilla esimerkiksi komennolla mvn test.

### Empiirinen testaus

Graafinen käyttöliittymä on auttanut huomattavasti räikeimpien ongelmien ratkaisussa, kuten esimerkiksi
poikkeaako "lyhin" polku täysin selkeästi ulos silmämääräisesti lyhimmältä polulta. Käyttöliittymästä on ollut
apua myös algoritmien toteutuksessa siten, että on voitu tarkastella mitkä solmut ovat olleet tarkastelussa, 
mitä solmuja on mahdollisesti tarkasteltu minkäkin jälkeen ja käyttäytyvätkö algoritmit ylipäätään halutulla tavalla.
Toteutusdokumentti sisältää oletus- ja testiruudukon tuloksia hakujen eri parametreilla.