# cifraYdescifraAlgo

## caesar cipher

> Due to the increasing popularity of Wikipedia, some editions, including the English version, 
> have introduced editing restrictions in some cases. For instance, on the English Wikipedia and some other 
> language editions, only registered users may create a new article.[77] On the English Wikipedia, among others, some 
> particularly controversial, sensitive or vandalism-prone pages have been protected to some degree.
```sh
$ java -cp target/classes e1.CifradoDesplazamiento texto.txt cifrar 4
```
> HYIXSXLIMRGVIEWMRKTSTYPEVMXCSJAMOMTIHMEWSQIIHMXMSRWMRGPYHMRKXLIIRKPMWLZIVW
> MSRLEZIMRXVSHYGIHIHMXMRKVIWXVMGXMSRWMRWSQIGEWIWJSVMRWXERGISRXLIIRKPMWLAMOMTIHMEERHWSQISXLIVPER
> KYEKIIHMXMSRWSRPCVIKMWXIVIHY>WIVWQECGVIEXIERIAEVXMGPI;;SRXLIIRKPMWLAMOMTIHMEEQSRKSXLIVWWSQITEVXMGYP
> EVPCGSRXVSZIVWMEPWIRWMXMZISVZERHEPMWQTVSRITEKIWLEZIFIIRTVSXIGXIHXSWSQIHIKVII
```sh
$ java -cp target/classes e1.CifradoDesplazamiento texto.c descifrar 4
```
> DUETOTHEINCREASINGPOPULARITYOFWIKIPEDIASOMEEDITIONSINCLUDINGTHEENGLISHVERSIONHAVEINTRODUCEDEDITIN
> GRESTRICTIONSINSOMECASESFORINSTANCEONTHEENGLISHWIKIPEDIAANDSOMEOTHERLANGUAGEEDITIONSONLYREGISTEREDU
> SERSMAYCREATEANEWARTICLEQQONTHEENGLISHWIKIPEDIAAMONGOTHERSSOMEPARTICULARLYCONTROVERSIALSENSITIVEORVAND
> ALISMPRONEPAGESHAVEBEENPROTECTEDTOSOMEDEGREE

## frequency analysis against monoalphabetic substitution cipher
```sh
$ java -cp target/classes e3.FrecuenciasEnDesplazamiento texto.txt
```

*** Texto cifrado ***

TQSPS LOLYJ ESTYR NZYQT OPYET LWEZD LJSPH CZEPT ETYNT ASPCE SLETD MJDZN SLYRT YRESP
ZCOPC ZQESP WPEEP CDZQE SPLWA SLMPE ESLEY ZELHZ CONZF WOMPX LOPZF ETQLY JZYPH TDSPD
EZOPN TASPC ESPDP LYORP ELEES PTCXP LYTYR SPXFD EDFMD ETEFE PESPQ ZFCES WPEEP CZQES
PLWAS LMPEY LXPWJ OQZCL LYODZ HTESE SPZES PCD


First Header | Second Header | K (valor más cercano a 0.065) |
------------ | ------------- | ------------- |
j: 0 --> 0.05002701612903227   | j: 13 --> 0.03226298387096774 | K: 11
j: 1 --> 0.040211774193548384   | j: 14 --> 0.03487032258064516 |
j: 2 --> 0.03181399193548387   | j: 15 --> 0.0419471370967742 |
j: 3 --> 0.030798266129032262   | j: 16 --> 0.034843709677419364 |
j: 4 --> 0.042681330645161296   | j: 17 --> 0.03595762096774194 |
j: 5 --> 0.034969959677419366   | j: 18 --> 0.03830314516129031 |
j: 6 --> 0.031140645161290326   | j: 19 --> 0.03178818548387097 |
j: 7 --> 0.04289431451612904   | j: 20 --> 0.031174112903225807 |
j: 8 --> 0.034323991935483876   | j: 21 --> 0.03373520161290322 |
j: 9 --> 0.028207620967741934   | j: 22 --> 0.046016612903225805 |
j: 10 --> 0.03876169354838709   | j: 23 --> 0.04113568548387097 |
j: 11 --> 0.06913221774193551   | j: 24 --> 0.04193572580645162 |
j: 12 --> 0.04189899193548387   | j: 25 --> 0.03915774193548387 |


*** desencriptado *** 

IFHEHADANYTHINGCONFIDENTIALTOSAYHEWROTEITINCIPHERTHATISBYSOCHANGINGTHE
ORDEROFTHELETTERSOFTHEALPHABETTHATNOTAWORDCOULDBEMADEOUTIFANYONEWISHES
TODECIPHERTHESEANDGETATTHEIRMEANINGHEMUSTSUBSTITUTETHEFOURTHLETTEROFTH
EALPHABETNAMELYDFORAANDSOWITHTHEOTHERS

## polyalphabetic cipher decryp with the index of coincidence
```sh
java -cp target/classes e4.FrecuenciasEnVigenere texto.txt 
```

*** Mensaje cifrado ***

KVQMRKJSZGRJNOEIAKKFGCGWUWRHRUFIXDAGKOBPEGRQTTBZLFXAFHVODWVLYHTEYWKHQRSSJHQNRVKCFHRLYCZGVFJWPEGZVSZTEWEQTMRFKCRTUWTOYPSWRFUNTVRBSEELYSSAHDUWECUSIUQDGZVGBENJRGTEUSUPQEAAEGFRHUKSPBLUYOZCRAKGFUPCWOETVFKVQTBOVFMNQXFFFWBVRMEWNKECFSVYYHQDOQFIDTEGFDEOALYSFHVJURMYVLNOESVYYHQDOQRGALQAVFFAXWERAWASERPEYAMSDEQLFQUCRJFVQRRSUWFTUJFISHNFUHTEAJVQUTRVZHMTNHRFMDRGWHTEGJFCBSOJZBSIAYKVQGEWRHQSGJVXAIPAEUFONDC

*** Indices de coincidencia para cada periodo 't' ***

First Header | Second Header | 
------------ | ------------- | 
t: 1 => Ic = 0.04311102367368352 | t: 6 => Ic = 0.0699179292929293 |
t: 2 => Ic = 0.04917811692480323 | t: 7 => Ic = 0.040603312031883464 |
t: 3 => Ic = 0.05180886139522745 | t: 8 => Ic = 0.05168304023737154 |
t: 4 => Ic = 0.04986653166421207 | t: 9 => Ic = 0.054973880555275904 |
t: 5 => Ic = 0.04208196866424715 |


longitud de la clave: 6

*** Usando el analasis de frencuencias por 'columna' ***

clave: ROMANS

*** Mensaje descifrado ***

THEMESSENGERWASINSTRUCTEDIFHECOULDNOTAPPROACHTOHURLASPEARWITHTHELETTERFASTENEDTOTHETHONGINSIDETHEENTRENCHMENTOFTHECAMPFEARINGDANGERTHEGAULDISCHARGEDTHESPEARASHEHADBEENINSTRUCTEDBYCHANCEITSTUCKFASTINTHETOWERANDFORTWODAYSWASNOTSIGHTEDBYOURTROOPSONTHETHIRDDAYITWASSIGHTEDBYASOLDIERTAKENDOWNANDDELIVEREDTOCICEROHEREADITTHROUGHANDTHENRECITEDITATAPARADEOFTHETROOPSBRINGINGTHEGREATESTREJOICINGTOALL
