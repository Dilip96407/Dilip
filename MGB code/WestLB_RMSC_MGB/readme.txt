Infos zum MGB Build Process 
===========================

Zertifikat für das Applet erstellen
  keytool -genkey -alias mgb -storepass mgbmgb
  => Datei C:\WINNT\Profiles\wsy5258a\.keypass wird erzeugt, 
  Einträge in build.properties anpassen:
  keystore.alias = mgb
  keystore.password = mgbmgb
  keystore.storetype = jks
  keystore.location = c:/winnt/profiles/wsy4148/.keystore
  