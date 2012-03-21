SPARK_WWW=/opt/www/spark


cd blucargo_spark/target/build/lib

#DELETE DUPLICATED LICENSE.TXT from base.jar
zip -d base.jar META-INF/LICENSE.txt

jarsigner -storepass Kadet108Kadet# activation.jar kadet108
jarsigner -storepass Kadet108Kadet# asterisk-im-client.jar kadet108
jarsigner -storepass Kadet108Kadet# base.jar kadet108
jarsigner -storepass Kadet108Kadet# dom4j.jar kadet108
jarsigner -storepass Kadet108Kadet# fmj.jar kadet108
jarsigner -storepass Kadet108Kadet# i4jruntime.jar kadet108
jarsigner -storepass Kadet108Kadet# jcalendar-1.3.3.jar kadet108
jarsigner -storepass Kadet108Kadet# smack.jar kadet108
jarsigner -storepass Kadet108Kadet# smackx.jar kadet108
jarsigner -storepass Kadet108Kadet# smackx-debug.jar kadet108
jarsigner -storepass Kadet108Kadet# spark.jar kadet108
jarsigner -storepass Kadet108Kadet# startup.jar kadet108
jarsigner -storepass Kadet108Kadet# swingx-beaninfo-1.6.2.jar kadet108
jarsigner -storepass Kadet108Kadet# swingx-core-1.6.2.jar kadet108
jarsigner -storepass Kadet108Kadet# synthetica.jar kadet108
jarsigner -storepass Kadet108Kadet# syntheticaBlueMoon.jar kadet108
jarsigner -storepass Kadet108Kadet# systeminfo.jar kadet108
jarsigner -storepass Kadet108Kadet# lti-civil.jar kadet108
jarsigner -storepass Kadet108Kadet# xpp.jar kadet108
jarsigner -storepass Kadet108Kadet# xstream.jar kadet108
jarsigner -storepass Kadet108Kadet# xpp3-1.1.3.4.C.jar kadet108
jarsigner -storepass Kadet108Kadet# blucargo_data-1.0-SNAPSHOT.jar kadet108
jarsigner -storepass Kadet108Kadet# openjpa-all-2.0.0.jar kadet108
jarsigner -storepass Kadet108Kadet# log4j.jar kadet108

cd windows
jarsigner -storepass Kadet108Kadet# jdic.jar kadet108


cd ..

if [ -d $SPARK_WWW/lib ]
then 
  echo $SPARK_WWW/lib folder exists
else
  mkdir $SPARK_WWW/lib
fi 

cp -R * $SPARK_WWW/lib

cd ../../../
cp cargo.jnlp $SPARK_WWW/
