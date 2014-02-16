#/bin/bash
#Miguel Angel Gallardo Lemus_21-03-2013-2100
#script para  probar  que  funcionen las  clases  gneradas en antlr4  y compiladas  en linux 
#para ejecutar este  archivo:
#1.- /bin/bash grun.bash [parametros]
#2.- ./grun.bash [parametros]
#Parametros:  se debe  indicar el archivo  g4  dela  gramatica a utilizar
#nota:mgl:21-03-2013: para  separar elementos en el "-cp", enlinux  en con ":", en win es con";"
#nota: para a ceptar paramentros en el escrit es con "$1 $2 ... o  $*  "
#var="antlr-4.0-complete.jar" #nota:  con las  variables  no funciona
#notas:  la cadena "$@"  continen todos los parametros  pasados al script
#nota: "exec": llama al comando y termina el script si esperar a que el cmando llamdo  termine
#uso: ./antlr4.sh [ubicacion] [archivo]
exec java -jar antlr-4.0-complete.jar ""$1""/""$2"";
