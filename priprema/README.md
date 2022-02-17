# Atos Praksa - Hrvoje Škrbina

## Praktični zadatci

1. [x] <span style="color: rgba(242, 38, 19, 0.8);">Instaliraj program Eclipse. Na [stranici](http://www.eclipse.org/downloads/) nalazi se popis različitih alata iz Eclipse porodice. Za pripremu prakse bit će dovoljan Eclipse Standard. Odaberi verziju operacijskog sustava koji koristiš i preuzmi alat. Eclipse se ne instalira, nego jednostavno „raspakira“ na željenu lokaciju.</span>
-----

2. [x] <span style="color: rgba(242, 38, 19, 0.8);">Kreiraj Java projekt i nazovi ga PripremaPrakse_\<ImePrezime\>, pri čemu \<ImePrezime\> trebate zamijeniti svojim imenom i prezimenom (npr. PripremaPrakse_IvanHorvat)</span>
-----

3. [x] <span style="color: rgba(242, 38, 19, 0.8);">Kreiraj tzv. "Hello World" aplikaciju tj. aplikaciju koja će nakon pokretanja na konzolu ispisati riječi "Hello World, ovo je prva java aplikacija.".</span>
-----

4. [x] <span style="color: rgba(242, 38, 19, 0.8);">Kreiraj program koji ima definirano polje pet različitih cjelobrojnih vrijednosti. Program treba provjeriti je li svaka od pet vrijednosti paran ili neparan broj i da li je višekratnik od jednog od idučih brojeva: 3, 5 ili 11, te ispisati poruku na konzolu u obliku „XY je paran broj“ ili „XY je neparan broj“ te „XY je višekratnik od 3 “ (ili 5 ili 11, zamijeniti broj u tekstu).</span>
-----

5. [x] <span style="color: rgba(242, 38, 19, 0.8);">Kreiraj program koji ima definiran početak i kraj intervala te jedan brojač. Neka početak intervala bude manji od 10, a kraj intervala veći od 100. Program treba provjeriti jesu li početak i kraj intervala ispravno definirani te ispisati poruku o pogrešci ukoliko nisu. Ukoliko jesu program treba za svaki broj u zadanom intervalu napraviti sljedeće:
    - ukoliko je broj manji ili jednak 18, brojač treba povećati za 4
    - ukoliko je broj veći od 18, brojač treba umanjiti za 1
    - ukoliko je broj djeljiv s 20, treba prekinuti procesuiranje tog broja i prijeći na sljedeći
    - ukoliko je broj jednak ili veći od 75, treba prekinuti procesuiranje intervala te ispisati vrijednost brojača</span>
-----

6. [x] <span style="color: rgba(242, 38, 19, 0.8);">Kreiraj program koji će koristeći for petlje automatizirati ispis tablice množenja u ovom obliku, te umjesto "Ime" treba ispisati ime uneseno s konzole i pri tome pripaziti da zadnje slovo imena bude poravnato s desnim rubom tablice.</span>

```
-------------------------------
: : :  TABLICA  MNOZENJA  : : :
-------------------------------
 * |  1  2  3  4  5  6  7  8  9
-------------------------------
 1 |  1  2  3  4  5  6  7  8  9
 2 |  2  4  6  8 10 12 14 16 18
 3 |  3  6  9 12 15 18 21 24 27
 4 |  4  8 12 16 20 24 28 32 36
 5 |  5 10 15 20 25 30 35 40 45
 6 |  6 12 18 24 30 36 42 48 54
 7 |  7 14 21 28 35 42 49 56 63
 8 |  8 16 24 32 40 48 56 64 72
 9 |  9 18 27 36 45 54 63 72 81
-------------------------------
:  :  :  :  :  :  :  :  :by Ime
-------------------------------
```
-----

7. [x] <span style="color: rgba(3, 138, 255, 1);">Kreiraj program koji će ispisati koliko ima brojeva djeljivih sa 6 u intervalu između neka dva prirodna broja unesena s konzole.</span>
-----

8. [x] <span style="color: rgba(3, 138, 255, 1);">Kreiraj program koji će s konzole učitati redni broj mjeseca u nekoj prijestupnoj godini. Napraviti provjeru je li uneseni redni broj mjeseca ispravan (ne postoji npr. 15. mjesec), a zatim ispisati kalendar tog mjeseca u obliku. Pretpostavlja se kako prvi u mjesecu pada na ponedjeljak. Također, treba ispisati onoliko dana koliko ih taj mjesec po klasičnom kalendaru doista ima.</span>

```
P  U  S  Č  P  S  N
 1  2  3  4  5  6  7
 8  9 10 11 12 13 14
15 16 17 18 19 20 21
22 23 24 25 26 27 28
29 30 31
```
-----

9. [x] <span style="color: rgba(46, 204, 113, 1);">Luka je vaš dobar prijatelj. Nedavno vam je spomenuo kako bi volio imati grafičko rješenje prikaza njegovog troška struje njegove „krušne“ peći kroz godinu (on je naime pizza majstor i ima svoj restoran). Račun mu stalno varira, ali nikada ne prelazi 4500kn. Prvo ste mu rekli da još ne znate raditi grafički prikaz u Javi, ali vam je bilo žao ne ponuditi mu nikakvo rješenje pa ste na papiru nacrtali ovakav graf i rekli da znanje za napraviti ovakav prikaz imate. Također ste mu pojasnili kako će morati unijeti plaću za svaki pojedini mjesec, a onda će program iscrtati graf. Upozorili ste ga kako će vrijednosti plaće biti aproksimirane. Primjerice mjesečna plaća koja iznosi od 1501kn pa sve do 2500kn, na grafu će biti obilježena iksićem uz vrijednost 2000kn. On se naposljetku složio s vašim prijedlogom i od srca zahvalio. Stoga kreirajte program kojim ćete pomoći Luki.
</span>

```
5000kn - |
         |                              x
4000kn - |       x
         |
3000kn - |                      x  x
         |
2000kn - |          x
         |
1000kn - |    x        x                    x
         |
   0kn - | x                 x
          -- -- -- -- -- -- -- -- -- -- -- --
           1  2  3  4  5  6  7  8  9 10 11 12
```
-----

10. [x] <span style="color: rgba(46, 204, 113, 1);">Kreiraj java program koji će pretražiti sve datoteke s ekstenzijama "csv" i "txt" unutar jednog direktorija (foldera) kako bi provjerio nalazi li se u njima definirana tekstualna fraza. Nazive datoteka u kojima se fraza nalazi, ispisat će na konzolu. Putanju direktorija za pretraživanje kao i tekstualnu frazu treba unijeti s konzole. Fraza usput može biti regular expression.
    - Popis klasa koje bi mogle biti korisne:
        - java.io.File (za manipuliranje datotekama / direktorijima)
        - java.io.BufferedReader (za čitanje sadržaja datoteke)
        - java.lang.String (za manipulaciju sa tekstom)
-----

11. [x] <span style="color: rgba(46, 204, 113, 1);">Kreiraj program koji će omogućiti interakciju s korisnikom na jednom od tri ponuđena jezika. Interakcija se treba sastojati od nekoliko međusobno izmijenjenih poruka. Prijevodi na pojedini jezik trebaju biti dostupni unutar java properties datoteka za svaki jezik odvojeno.
-----

12. [x] <span style="color: rgba(46, 204, 113, 1);">Napraviti program koji će omogućiti korisniku ispis statistike broja pojedinih riječi unutar datoteke koju odabere. Riječi trebaju biti poredane po abecedi i treba ignorirati velika/mala slova. Ispod se nalazi primjer ispisa:
U datoteci XX nalaze se sljedece rijeci:
```
------------------------
Rijec (broj ponavljanja)
------------------------
auto (17)
auta (2)
boje (19)
cipele (3)
je (39)
su (110)
trava (1)
zeleno (2)
------------------------
```
-----

13. [x] <span style="color: rgba(46, 204, 113, 1);">Kreiraj program koji se sastoji od jedne klase i sučelja koje klasa nasljeđuje. Sučelje treba definirati dvije različite metode po vlastitom izboru. 
-----

14. [x] <span style="color: rgba(46, 204, 113, 1);">Kreiraj program kojim će korisnik izračunati površinu ispod neke krivulje opisane funkcijom y=f(x)=A*funk(x)+B od neke početne do krajnje točke (T1,T2). Izračun izvesti numeričkom metodom. Ulazni parametri su A, B, T1 i T2 te funkcija koja može biti sinus, kosinus, tangens ili kotangens. Pri rješavanju zadatka obavezno koristiti više klasa i(li) sučelja te nasljeđivanje.
-----

15. [] <span style="color: rgba(46, 204, 113, 1);">Za jednu tvrtku potrebno je napraviti demo aplikaciju u kojoj će moći raditi administraciju zaposlenika te njihbovih radnih zadataka. Potrebno je omogućiti administraciju na nekoliko razina tj. potrebno je definirati korisnička prava korisnika aplikacije po grupama: "admin", "superuser" i "user" tako da se omoguće sljedeće akcije:
    <ul style="color: rgba(46, 204, 113, 1);">
    <li>kreiranje zadataka i zaposlenika (admin, superuser)</li>
    <li>izlistanje zadataka i zaposlenika (admin, superuser, user)</li>
    <li>izmjena zadataka i zaposlenika (admin)</li>
    <li>brisanje zadataka i zaposlenika (admin)</li>
    </ul>
    <span style="color: rgba(46, 204, 113, 1);">naposljetku aplikacija mora omogućiti kreiranje izvještaja:</span>
    <ul style="color: rgba(46, 204, 113, 1);">
    <li>po radnom mjestu tj. koliko radnika radi na pojedinom radnom mjestu</li>
    <li>utrošeno vrijeme po osobi</li>
    <li>najduže otvoren zadatak</li>
    </ul>

    <span style="color: rgba(46, 204, 113, 1);">Izvještaje mogu kreirati samo korisnici s admin ili superuser pravima.
Sučelje za komunikaciju s korisnicima neka bude konzola, a podaci se trebaju spremati u tekstualne datoteke ili bazu podataka po izboru.
Ovo su im najbitniji podatci:</span>
```
Zaposlenici
  ime
  prezime
  radno mjesto
  oib

Zadaci
  naziv
  opis
  tip (bug, task)
  trenutni status (otvoren, zatvoren, u tijeku)
  kompleksnost (broj)
  potrošeno vrijeme (sati)
  početni datum i vrijeme
  završni datum i vrijeme
  (napomena, početak i završetak ne znače nužno da je potrošeno vrijeme njihova razlika)
```
## Teorijski zadaci
----
<!-- TODO: Solve and paste tasks -->