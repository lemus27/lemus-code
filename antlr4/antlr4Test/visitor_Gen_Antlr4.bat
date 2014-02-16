@REM Miguel Angel Gallardo Lemus_21-03-2013-2100
@REM #script para  probar  que  funcionen las  clases  gneradas en antlr4  y compiladas  en linux
@REM #para ejecutar este  archivo: #1.- /bin/bash grun.bash [parametros]
@REM #2.- ./grun.bash [parametros]
@REM #Parametros:  se debe  indicar el archivo  g4  dela  gramatica a utilizar
@REM #nota:mgl:21-03-2013: para  separar elementos en el "-cp", enlinux  en con ":", en win es con";"
@REM  #nota: para a ceptar paramentros en el escrit es con "$1 $2 ... o  $*  "
@REM ivar="antlr-4.0-complete.jar" #nota:  con las  variables  no funciona
@REM #notas:  la cadena "$@"  continen todos los parametros  pasados al script@REM
@REM #nota: "exec": llama al comando y termina el script si esperar a que el cmando llamdo  termine

@REM #uso: ./antlr4.sh [ubicacion] [archivo]
@REM java -jar antlr-4.0-complete.jar -no-listener -visitor  %1/%2;
java -cp .;C:\antlr4_lib\antlr-4.0-complete.jar;%CLASSPATH%;%1 org.antlr.v4.Tool -no-listener -visitor %1\%2
~
~
~
~
~
~
~
~
~
~
~
~
~
