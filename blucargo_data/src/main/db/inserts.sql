-- MySQL dump 10.13  Distrib 5.1.46, for Win64 (unknown)
--
-- Host: localhost    Database: cargo
-- ------------------------------------------------------
-- Server version	5.1.46-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `acceptedoffer`
--

LOCK TABLES `acceptedoffer` WRITE;
/*!40000 ALTER TABLE `acceptedoffer` DISABLE KEYS */;
INSERT INTO `acceptedoffer` VALUES (401,301,'a'),(402,302,'a'),(403,303,'a'),(404,304,'a');
/*!40000 ALTER TABLE `acceptedoffer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `carbody`
--

LOCK TABLES `carbody` WRITE;
/*!40000 ALTER TABLE `carbody` DISABLE KEYS */;
INSERT INTO `carbody` VALUES (251,'Plandeka'),(252,'Izoterma'),(253,'Platforma'),(254,'Wywrotka'),(255,'Jumbo'),(256,'Ch?‚odnia'),(257,'Firanka'),(258,'Cysterna'),(259,'Silos'),(260,'Meblow??z'),(261,'Kontener'),(262,'Zestaw'),(263,'Bus'),(264,'Laweta'),(265,'Mega'),(266,'Coilmulde'),(267,'Inne');
/*!40000 ALTER TABLE `carbody` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cargooffer`
--

LOCK TABLES `cargooffer` WRITE;
/*!40000 ALTER TABLE `cargooffer` DISABLE KEYS */;
INSERT INTO `cargooffer` VALUES (301,'ADR','ADR',NULL,NULL,'Body','100m','100m','Tak','100USD','Tak','Opole','Opole',NULL,'Mir T cos tam cos tam','pl','pl','2011-07-29','opis','dimension of palettes','elevator','hds','2011-07-29','tak',100,'2011-07-29','sdfdsf','b','sdfsd','345','467',NULL,'sdsdf','tak','2011-07-29',0,'2011-07-29',NULL,'\0','100 L','100 T'),(302,'ADR11','ADR11',NULL,NULL,'Planteka','100m111','100m111','Tak111','100USD111','Tak1111','Kiev','Lvov',NULL,'Mir T cos tam cos tam111','ua','ua','2011-07-29','opis1111','dimension of palettes111','elevator111','hds111','2011-07-29','tak111',100,'2011-07-29','sdfdsf111','b','sdfsd111','345111','467111',NULL,'sdsdf111','tak111','2011-07-29',0,'2011-07-29',NULL,'\0','100 L111','100 T111'),(303,'ADR2222','ADR2222',NULL,NULL,'IZO','100m222','100m222','Tak222','100USD222','Tak222','London','birmingham',NULL,'Mir T cos tam cos tam222','uk','uk','2011-07-29','opis2222','dimension of palettes222','elevator2222','hds222','2011-07-29','tak222',100,'2011-07-29','sdfdsf222','b','sdfsd2222','345222','467222',NULL,'sdsdf222','tak222','2011-07-29',1,'2011-07-29',NULL,'\0','100 L222','100 T222'),(304,'ADR333','ADR333',NULL,NULL,'ASDAD','100m333','100m333','Tak333','100USD333','Tak333','Warszawa','Rzesz???w',NULL,'Mir T cos tam cos tam333','pl','pl','2011-07-29','opis333','dimension of palettes3333','elevator333','hds333','2011-07-29','tak333',100,'2011-07-29','sdfdsf333','b','sdfsd333','345333','4673333',NULL,'sdsdf333','tak333','2011-07-29',1,'2011-07-29',NULL,'\0','100 L333','100 T3333'),(305,'dsddsf','dsddsf',NULL,NULL,'sdfaaaa','100m44','100m44','Tak444','100USD444','Tak444','Bakar','Barban',NULL,'Mir T cos tam cos tam444','kr','kr','2011-07-29','opis4444','dimension of palettes4444','elevator444','hds444','2011-07-29','tak44',100,'2011-07-29','sdfdsf44','b','sdfsd444','345444','467444',NULL,'sdsdf','tak444','2011-07-29',1,'2011-07-29',NULL,'\0','100 L444','100 T444'),(306,'dsddsf','dsddsf',NULL,NULL,'plandeka','100m44','100m44','Tak444','100USD444','Tak444','Opole','Krak???w',NULL,'Mir T cos tam cos tam444','pl','pl','2011-07-29','opis4444','dimension of palettes4444','elevator444','hds444','2011-07-29','tak44',100,'2011-07-29','sdfdsf44','b','sdfsd444','345444','467444',NULL,'sdsdf','tak444','2011-07-29',1,'2011-07-29',NULL,'\0','100 L444','k1'),(307,'dsddsf','dsddsf',NULL,NULL,'izoterma','100m44','100m44','Tak444','100USD444','Tak444','Opole','moskva',NULL,'Mir T cos tam cos tam444','pl','ru','2011-07-29','opis4444','dimension of palettes4444','elevator444','hds444','2011-07-29','tak44',100,'2011-07-29','sdfdsf44','b','sdfsd444','345444','467444',NULL,'sdsdf','tak444','2011-07-29',1,'2011-07-29',NULL,'\0','100 L444','k2');
/*!40000 ALTER TABLE `cargooffer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `commentandoffer`
--

LOCK TABLES `commentandoffer` WRITE;
/*!40000 ALTER TABLE `commentandoffer` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentandoffer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,'AF','AFG','004','Afganistan','AFGANISTAN'),(2,'AL','ALB','008','Albania','ALBANIA'),(3,'DZ','DZA','012','Algieria','ALGIERIA'),(4,'AS','ASM','016','Samoa Ameryka?±skie','SAMOA AMERYKA?‘SKIE'),(5,'AD','AND','020','Andora','ANDORA'),(6,'AO','AGO','024','Angola','ANGOLA'),(7,'AI','AIA','660','Anguilla','ANGUILLA'),(8,'AQ',NULL,NULL,'Antarktyka','ANTARKTYKA'),(9,'AG','ATG','028','Antigua i Barbuda','ANTIGUA I BARBUDA'),(10,'AR','ARG','032','Argentyna','ARGENTYNA'),(11,'AM','ARM','051','Armenia','ARMENIA'),(12,'AW','ABW','533','Aruba','ARUBA'),(13,'AU','AUS','036','Australia','AUSTRALIA'),(14,'AT','AUT','040','Austria','AUSTRIA'),(15,'AZ','AZE','031','AzerbejdÂ?an','AZERBEJDÂ?AN'),(16,'BS','BHS','044','Bahamy','BAHAMY'),(17,'BH','BHR','048','Bahrain','BAHRAIN'),(18,'BD','BGD','050','Bangladesz','BANGLADESZ'),(19,'BB','BRB','052','Barbados','BARBADOS'),(20,'BY','BLR','112','BiaÂ?oru?š','BIA?‚ORU?š'),(21,'BE','BEL','056','Belgia','BELGIA'),(22,'BZ','BLZ','084','Belize','BELIZE'),(23,'BJ','BEN','204','Benin','BENIN'),(24,'BM','BMU','060','Bermudy','BERMUDY'),(25,'BT','BTN','064','Bhutan','BHUTAN'),(26,'BO','BOL','068','Boliwia','BOLIWIA'),(27,'BA','BIH','070','Bo?šnia i Hercegowina','BO?šNIA I HERCEGOWINA'),(28,'BW','BWA','072','Botswana','BOTSWANA'),(29,'BV',NULL,NULL,'Wyspa Bouveta','WYSPA BOUVETA'),(30,'BR','BRA','076','Brazylia','BRAZYLIA'),(31,'IO',NULL,NULL,'Brytyjskie Terytorium Oceanu Indyjskiego','BRYTYJSKIE TERYTORIUM OCEANU INDYJSKIEGO'),(32,'BN','BRN','096','Brunei','BRUNEI'),(33,'BG','BGR','100','BuÂ?garia','BU?‚GARIA'),(34,'BF','BFA','854','Burkina Faso','BURKINA FASO'),(35,'BI','BDI','108','Burundi','BURUNDI'),(36,'KH','KHM','116','KambodÂ?a','KAMBODÂ?A'),(37,'CM','CMR','120','Kamerun','KAMERUN'),(38,'CA','CAN','124','Kanada','KANADA'),(39,'CV','CPV','132','Republika Zielonego PrzylÂ?dka','REPUBLIKA ZIELONEGO PRZYLÂ?DKA'),(40,'KY','CYM','136','Kajmany','KAJMANY'),(41,'CF','CAF','140','Republika ?šrodkowoafryka?±ska','REPUBLIKA ?šRODKOWOAFRYKA?‘SKA'),(42,'TD','TCD','148','Czad','CZAD'),(43,'CL','CHL','152','Chile','CHILE'),(44,'CN','CHN','156','Chiny','CHINY'),(45,'CX',NULL,NULL,'Wyspa BoÂ?ego Narodzenia','WYSPA BOÂ?EGO NARODZENIA'),(46,'CC',NULL,NULL,'Wyspy Cocos','WYSPY COCOS'),(47,'CO','COL','170','Kolumbia','KOLUMBIA'),(48,'KM','COM','174','Komory','KOMORY'),(49,'CG','COG','178','Kongo','KONGO'),(50,'CD','COD','180','Demokratyczna Republika Konga','DEMOKRATYCZNA REPUBLIKA KONGA'),(51,'CK','COK','184','Wyspy Cooka','WYSPY COOKA'),(52,'CR','CRI','188','Kostaryka','KOSTARYKA'),(53,'CI','CIV','384','WybrzeÂ?e Ko?šci SÂ?oniowej','WYBRZEÂ?E KO?šCI S?‚ONIOWEJ'),(54,'HR','HRV','191','Chorwacja','CHORWACJA'),(55,'CU','CUB','192','Kuba','KUBA'),(56,'CY','CYP','196','Cypr','Cypr'),(57,'CZ','CZE','203','Czechy','CZECHY'),(58,'DK','DNK','208','Dania','DANIA'),(59,'DJ','DJI','262','DÂ?ibutu','DÂ?IBUTI'),(60,'DM','DMA','212','Dominika','DOMINIKA'),(61,'DO','DOM','214','Dominikana','DOMINIKANA'),(62,'EC','ECU','218','Ekwador','EKWADOR'),(63,'EG','EGY','818','Egipt','EGIPT'),(64,'SV','SLV','222','Salwador','SALWADOR'),(65,'GQ','GNQ','226','Gwinea R??wnikowa','GWINEA R?“WNIKOWA'),(66,'ER','ERI','232','Erytrea','ERyTREA'),(67,'EE','EST','233','Estonia','ESTONIA'),(68,'ET','ETH','231','Etiopia','ETIOPIA'),(69,'FK','FLK','238','Falklandy Malwiny','FALKLANDY MALWINY'),(70,'FO','FRO','234','Wyspy Faraona','WYSPY FARAONA'),(71,'FJ','FJI','242','FidÂ?i','FIDÂ?I'),(72,'FI','FIN','246','Finlandia (ale nie do picia))','FINLANDIA'),(73,'FR','FRA','250','Francja','FRANCJA'),(74,'GF','GUF','254','Gujana Francuska','GRUJANA FRANCUSKA'),(75,'PF','PYF','258','Polinezja Francuska','POLINEZJA FRANCUSKA'),(76,'TF',NULL,NULL,'Francuskie Terytoria PoÂ?udniowe','FRANCUSKIE TERYTORIA PO?‚UDNIOWE'),(77,'GA','GAB','266','Gabon','GABON'),(78,'GM','GMB','270','Gambia','GAMBIA'),(79,'GE','GEO','268','Georgia PoÂ?udniowa','GEORGIA PO?‚UDNIOWA'),(80,'DE','DEU','276','Niemcy','NIEMCY'),(81,'GH','GHA','288','Ghana','GHANA'),(82,'GI','GIB','292','Gibraltar','GIBRALTAR'),(83,'GR','GRC','300','Grecja','GRECJA'),(84,'GL','GRL','304','Grenlandia','GRENLANDIA'),(85,'GD','GRD','308','Grenada','GRENADA'),(86,'GP','GLP','312','Gwadelupa','GWADELUPA'),(87,'GU','GUM','316','Guam','GUAM'),(88,'GT','GTM','320','Gwatemala','GWATEMALA'),(89,'GN','GIN','324','Gwinea','GWINEA'),(90,'GW','GNB','624','Gwinea Bissau','GWINEA-BISSAU'),(91,'GY','GUY','328','Gujana','GUJANA'),(92,'HT','HTI','332','Haiti','HAITI'),(93,'HM',NULL,NULL,'Wyspy Heard i McDonalda','WYSPY HEARD I MCDONALDA'),(94,'VA','VAT','336','Watykan','WATYKAN'),(95,'HN','HND','340','Honduras','HONDURAS'),(96,'HK','HKG','344','Hong Kong','HONG KONG'),(97,'HU','HUN','348','WÄ™gry','WÄ™GRY'),(98,'IS','ISL','352','Islandia','ISLANDIA'),(99,'IN','IND','356','Indie','INDIE'),(100,'ID','IDN','360','Indonezja','INDONEZJA'),(101,'IR','IRN','364','Iran','IRAN'),(102,'IQ','IRQ','368','Irak','IRAK'),(103,'IE','IRL','372','Irlandia','IRLANDIA'),(104,'IL','ISR','376','Izrael','IZRAEL'),(105,'IT','ITA','380','WÂ?ochy','W?‚OCHY'),(106,'JM','JAM','388','Jamaika','JAMAIKA'),(107,'JP','JPN','392','Japonia','JAPONIA'),(108,'JO','JOR','400','Jordania','JORDANIA'),(109,'KZ','KAZ','398','Kazachstan','KAZACHSTAN'),(110,'KE','KEN','404','Kenia','KENIA'),(111,'KI','KIR','296','Kiribati','KIRIBATI'),(112,'KP','PRK','408','Korea P??Â?nocna','KOREA P?“?‚NOCNA'),(113,'KR','KOR','410','Korea PoÂ?udniowa','KOREA PO?‚UDNIOWA'),(114,'KW','KWT','414','Kuwejt','KUWEJT'),(115,'KG','KGZ','417','Kirgistan','KIRGISTAN'),(116,'LA','LAO','418','Laos','LAOS'),(117,'LV','LVA','428','???otwa','???OTWA'),(118,'LB','LBN','422','Liban','LIBAN'),(119,'LS','LSO','426','Lesotho','LESOTHO'),(120,'LR','LBR','430','Liberia','LIBERIA'),(121,'LY','LBY','434','Libia','LIBIA'),(122,'LI','LIE','438','Liechtenstein','LIECHTENSTEIN'),(123,'LT','LTU','440','Litwa','LITWA'),(124,'LU','LUX','442','Luksemburg','LUKSEMBURG'),(125,'MO','MAC','446','Makau','MAKAU'),(126,'MK','MKD','807','Macedonia','MACEDONIA'),(127,'MG','MDG','450','Madagaskar','MADAGASkAR'),(128,'MW','MWI','454','Malawi','MALAWI'),(129,'MY','MYS','458','Malezja','MALEZJA'),(130,'MV','MDV','462','Malediwy','MALEDIWY'),(131,'ML','MLI','466','Mali','MALI'),(132,'MT','MLT','470','Malta','MALTA'),(133,'MH','MHL','584','Wyspy Marshalla','WYSPY MARSHALLA'),(134,'MQ','MTQ','474','Martynika','MARTYNIKA'),(135,'MR','MRT','478','Mauretania','MAURETANIA'),(136,'MU','MUS','480','Mauritius','MAURITIUS'),(137,'YT',NULL,NULL,'Majotta','MAJOTTA'),(138,'MX','MEX','484','Meksyk','MEKSYK'),(139,'FM','FSM','583','Mikronezja','MIKRONEZJA'),(140,'MD','MDA','498','MoÂ?dawia','MO?‚DAWIA'),(141,'MC','MCO','492','Monako','MONAKO'),(142,'MN','MNG','496','Mongolia','MONGOLIA'),(143,'MS','MSR','500','Montserrat','MONTSERRAT'),(144,'MA','MAR','504','Maroko','MAROKO'),(145,'MZ','MOZ','508','Mozambik','MOZAMBIK'),(146,'MM','MMR','104','Birma','BIRMA'),(147,'NA','NAM','516','Namibia','NAMIBIA'),(148,'NR','NRU','520','Nauru','NAURU'),(149,'NP','NPL','524','Nepal','NEPAL'),(150,'NL','NLD','528','Holandia','HOLANDIA'),(151,'AN','ANT','530','Antyle Holenderskie','ANTYLE HOLENDERSKIE'),(152,'NC','NCL','540','Nowa Kaledonia','NOWA KALEDONIA'),(153,'NZ','NZL','554','Nowa Zelandia','NOWA ZELANDIA'),(154,'NI','NIC','558','Nikaragua','NIKARAGUA'),(155,'NE','NER','562','Niger','NIGER'),(156,'NG','NGA','566','Nigeria','NIGERIA'),(157,'NU','NIU','570','Niue','NIUE'),(158,'NF','NFK','574','Norfolk','NORFOLK'),(159,'MP','MNP','580','Mariany P??Â?nocne','MARIANY P?“?‚NOCNE'),(160,'NO','NOR','578','Norwegia','NORWEGIA'),(161,'OM','OMN','512','Oman','OMAN'),(162,'PK','PAK','586','Pakistan','PAKISTAN'),(163,'PW','PLW','585','Palau','PALAU'),(164,'PS',NULL,NULL,'Palestyna','PALESTYNA'),(165,'PA','PAN','591','Panama','PANAMA'),(166,'PG','PNG','598','Papua-Nowa Gwinea','PAPUA-NOWA GWINEA'),(167,'PY','PRY','600','Paragwaj','PARAGWAJ'),(168,'PE','PER','604','Peru','PERU'),(169,'PH','PHL','608','Filipiny','FILIPINY'),(170,'PN','PCN','612','Pitcairn','PITCAIRN'),(171,'PL','POL','616','Polska','POLSKA'),(172,'PT','PRT','620','Portugalia','PORTUGALIA'),(173,'PR','PRI','630','Portoryko','PORTORYKO'),(174,'QA','QAT','634','Katar','KATAR'),(175,'RE','REU','638','Reunion','REUNION'),(176,'RO','ROM','642','Rumunia','RUMUNIA'),(177,'RU','RUS','643','Rosja','ROSJA'),(178,'RW','RWA','646','Rwanda','RWANDA'),(179,'SH','SHN','654','?šwiÄ™ta Helena i ZaleÂ?ne','?šWIÄ™TA HELENA'),(180,'KN','KNA','659','Saint Kitts i Nevis','SAINT KITTS I NEVIS'),(181,'LC','LCA','662','Saint Lucia','SAINT LUCIA'),(182,'PM','SPM','666','Saint Pierre i Miquelon','SAINT PIERRE I MIQUELON'),(183,'VC','VCT','670','Saint Vincent i Grenadyny','SAINT VINCENT I GRENADYNY'),(184,'WS','WSM','882','Samoa','SAMOA'),(185,'SM','SMR','674','San Marino','SAN MARINO'),(186,'ST','STP','678','Wyspy ?šwiÄ™tego Tomasza i KsiÂ?Â?Ä™ca','WYSPY ?šWIÄ™TEGO TOMASZA I KSIÂ?Â?Ä™CA'),(187,'SA','SAU','682','Arabia Saudyjska','ARABIA SAUDYJSKA'),(188,'SN','SEN','686','Senegal','SENEGAL'),(189,'ME','MNE','499','Czarnog??ra','CZARNOG?“RA'),(190,'RS','SRB','688','Serbia','SERBIA'),(191,'SC','SYC','690','Seszele','SESZELE'),(192,'SL','SLE','694','Sierra Leone','SIERRA LEONE'),(193,'SG','SGP','702','Singapure','SINGAPURE'),(194,'SK','SVK','703','SÂ?owacja','S?‚OWACJA'),(195,'SI','SVN','705','SÂ?owenia','S?‚OWENIA'),(196,'SB','SLB','090','Wyspy Salomona','WYSPY SALOMONA'),(197,'SO','SOM','706','Somalia','SOMALIA'),(198,'ZA','ZAF','710','Republika PoÂ?udniowej Afryki','REPUBLIKA PO?‚UDNIOWEJ AFRYKI'),(199,'GS',NULL,NULL,'Georgia PoÂ?udniowa i Sandwich PoÂ?udniowy','GEORGIA PO?‚UDNIOWA I SANDWICH PO?‚UDNIOWY'),(200,'ES','ESP','724','Hiszpania','HISZPANIA'),(201,'LK','LKA','144','Sri Lanka','SRI LANKA'),(202,'SD','SDN','736','Sudan','SUDAN'),(203,'SR','SUR','740','Surinam','SURINAM'),(204,'SJ','SJM','744','Svalbard i Jan Mayen','SVALBARD I JAN MAYEN'),(205,'SZ','SWZ','748','Suazi','SUAZI'),(206,'SE','SWE','752','Szwecja','SZWECJA'),(207,'CH','CHE','756','Szwajcaria','SZWAJCARIA'),(208,'SY','SYR','760','Syria','SYRIA'),(209,'TW','TWN','158','Tajwan','TAJWAN'),(210,'TJ','TJK','762','TadÂ?ykistan','TADÂ?YKISTAN'),(211,'TZ','TZA','834','Tanzania','TANZANIA'),(212,'TH','THA','764','Tajlandia','TAJLANDIA'),(213,'TL',NULL,NULL,'Timor Wschodni','TIMOR WSCHODNI'),(214,'TG','TGO','768','Togo','TOGO'),(215,'TK','TKL','772','Tokelau','TOKELAU'),(216,'TO','TON','776','Tonga','TONGA'),(217,'TT','TTO','780','Trinidad i Tobago','TRINIDAD I TOBAGO'),(218,'TN','TUN','788','Tunezja','TUNEZJA'),(219,'TR','TUR','792','Turcja','TURCJA'),(220,'TM','TKM','795','Turkmenistan','TURKMENISTAN'),(221,'TC','TCA','796','Wyspy Turks i Caicos','WYSPY TURKS I CAICOS'),(222,'TV','TUV','798','Tuvalu','TUVALU'),(223,'UG','UGA','800','Uganda','UGANDA'),(224,'UA','UKR','804','Ukraina','UKRAINA'),(225,'AE','ARE','784','Zjednoczone Emiraty Arabskie','ZJEDNOCZONE EMIRATY ARABSKIE'),(226,'GB','GBR','826','Wielka Brytania','WIELKA BRYTANIA'),(227,'US','USA','840','Stany Zjednoczone','STANY ZJEDNOCZONE'),(228,'UM',NULL,NULL,'Dalekie Wyspy Mniejsze Stan??w Zjednoczonych','DALEKIE WYSPY MNIEJSZE STAN?“W ZJEDNOCZONYCH'),(229,'UY','URY','858','Urugwaj','URUGWAJ'),(230,'UZ','UZB','860','Uzbekistan','UZBEKISTAN'),(231,'VU','VUT','548','Vanuatu','VANUATU'),(232,'VE','VEN','862','Wenezuela','WENEZUELA'),(233,'VN','VNM','704','Wietnam','WIETNAM'),(234,'VG','VGB','092','Brytyjskie Wyspy Dziewicze','BRYTYJSKIE WYSPY DZIEWICZE'),(235,'VI','VIR','850','Wyspy Dziewicze Stan??w Zjednoczonych','WYSPY DZIEWICZE STAN?“W ZJEDNOCZONYCH'),(236,'WF','WLF','876','Wallis i Futuna','WALLIS I FUTUNA'),(237,'EH','ESH','732','Sahara Zachodnia','SAHARA ZACHODNIA'),(238,'YE','YEM','887','Jemen','JEMEN'),(239,'ZM','ZMB','894','Zambia','ZAMBIA'),(240,'ZW','ZWE','716','Zimbabwe','ZIMBABWE');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `endedoffer`
--

LOCK TABLES `endedoffer` WRITE;
/*!40000 ALTER TABLE `endedoffer` DISABLE KEYS */;
INSERT INTO `endedoffer` VALUES (451,301,'a',1),(452,302,'a',1),(453,303,'a',1),(454,304,'a',1);
/*!40000 ALTER TABLE `endedoffer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `favouriteoffer`
--

LOCK TABLES `favouriteoffer` WRITE;
/*!40000 ALTER TABLE `favouriteoffer` DISABLE KEYS */;
INSERT INTO `favouriteoffer` VALUES (351,301,'a'),(352,302,'a');
/*!40000 ALTER TABLE `favouriteoffer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `offeracceptance`
--

LOCK TABLES `offeracceptance` WRITE;
/*!40000 ALTER TABLE `offeracceptance` DISABLE KEYS */;
INSERT INTO `offeracceptance` VALUES (501,'b','a',1),(502,'b','a',2),(503,'a','b',3);
/*!40000 ALTER TABLE `offeracceptance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `openjpa_sequence_table`
--

LOCK TABLES `openjpa_sequence_table` WRITE;
/*!40000 ALTER TABLE `openjpa_sequence_table` DISABLE KEYS */;
INSERT INTO `openjpa_sequence_table` VALUES (0,651);
/*!40000 ALTER TABLE `openjpa_sequence_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `registrationdata`
--

LOCK TABLES `registrationdata` WRITE;
/*!40000 ALTER TABLE `registrationdata` DISABLE KEYS */;
INSERT INTO `registrationdata` VALUES (601,'Haslo1','1234567890123456789012345678901234567890123456789012345678901234','Radek','aaaaaaaaaabbbbbbbbbbccccccccccccddddddddddeeeeeeeeeeefffffffffffggggggggggghhhhhhhhhhhhhiiiiiiiiiiijjjjjjjjj'),(602,'Haslo2','1234567890123456789012345678901234567890123456789012345678901235','Lukasz','aaaaaaaaaabbbbbbbbbbccccccccccccddddddddddeeeeeeeeeeefffffffffffggggggggggghhhhhhhhhhhhhiiiiiiiiiiijjjjjjjjj'),(603,'Haslo3','1234567890123456789012345678901234567890123456789012345678901236','Krzysiek','aaaaaaaaaabbbbbbbbbbccccccccccccddddddddddeeeeeeeeeeefffffffffffggggggggggghhhhhhhhhhhhhiiiiiiiiiiijjjjjjjjj');
/*!40000 ALTER TABLE `registrationdata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `searchcriteria`
--

LOCK TABLES `searchcriteria` WRITE;
/*!40000 ALTER TABLE `searchcriteria` DISABLE KEYS */;
INSERT INTO `searchcriteria` VALUES (551,'testCriteria','a',NULL);
/*!40000 ALTER TABLE `searchcriteria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-07-29  2:14:56
