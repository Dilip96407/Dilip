Infos zum MGB Build Process 
===========================

Zertifikat f�r das Applet erstellen
  keytool -genkey -alias mgb -storepass mgbmgb
  => Datei C:\WINNT\Profiles\wsy5258a\.keypass wird erzeugt, 
  Eintr�ge in build.properties anpassen:
  keystore.alias = mgb
  keystore.password = mgbmgb
  keystore.storetype = jks
  keystore.location = c:/winnt/profiles/wsy4148/.keystore
  