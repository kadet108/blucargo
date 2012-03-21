CREATE VIEW commentandoffer AS
SELECT t1.*, t2.username, t3.contact, t3.owner FROM comment AS t1
LEFT JOIN endedoffer AS t2 
on t2.offerid = t1.cargoofferid 
LEFT JOIN cargooffer AS t3 
ON t2.offerid = t3.id;


-- WHERE t2.username!=t1.author;

