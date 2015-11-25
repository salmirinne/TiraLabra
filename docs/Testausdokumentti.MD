## Testausdokumentti

Testausta on suoritettu kahdella tavalla: JUnit yksikkötestien avulla sekä empiirisellä testaamisella
graafisen käyttöliittymän kautta. Graafinen käyttöliittymä on auttanut huomattavasti räikeimpien
ongelmien ratkaisussa, kuten esimerkiksi poikkeaako "lyhin" polku täysin selkeästi ulos silmämääräisesti
lyhimmältä polulta. Lyhimmän polun JUnit testeissä on käytetty vertailukohtana silmämääräisesti laskettua
lyhintä polkua ruudukossa.