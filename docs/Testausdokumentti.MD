## Testausdokumentti

### J-Unit testit

Yksikk�testej� l�ytyy minimikeolle ja jokaiselle verkkoalgoritmille eri heuristiikoilla. Jump Point Searchin
testaus rajoittuu l�hinn� sen ja A*:n tulosten per�kk�isiin vertailuihin satunnaisissa ruudukoissa. Yksikk�testej�
olisi voinut kirjoittaa huomattavan m��r�n JPS:n jump ja getNeighbours -metodeille, mutta k�ytin t�ss� vain
graafisen k�ytt�liittym�n antamia tuloksia. Testit voidaan suorittaa mavenilla esimerkiksi komennolla mvn test.

### Empiirinen testaus

Graafinen k�ytt�liittym� on auttanut huomattavasti r�ikeimpien ongelmien ratkaisussa, kuten esimerkiksi
poikkeaako "lyhin" polku t�ysin selke�sti ulos silm�m��r�isesti lyhimm�lt� polulta. K�ytt�liittym�st� on ollut
apua my�s algoritmien toteutuksessa siten, ett� on voitu tarkastella mitk� solmut ovat olleet tarkastelussa, 
mit� solmuja on mahdollisesti tarkasteltu mink�kin j�lkeen ja k�ytt�ytyv�tk� algoritmit ylip��t��n halutulla tavalla.
Toteutusdokumentti sis�lt�� oletus- ja testiruudukon tuloksia hakujen eri parametreilla.