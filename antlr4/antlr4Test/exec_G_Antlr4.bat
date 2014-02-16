@REM Miguel Angel Gallardo Lemus_21-03-2013-2100
@REM script para  probar  que  funcionen las  clases  gneradas en antlr4  y compiladas  en linux
@REM para ejecutar este  archivo:
@Rem 1.- /bin/bash grun.bash [parametros]
@Rem 2.- ./grun.bash [parametros]
@Rem nota:mgl:21-03-2013: para  separar elementos en el "-cp", enlinux  en con ":", en win es con";"
@Rem nota: para a ceptar paramentros en el escrit es con "$1 $2 ... o  $*  "
@Rem exec java -cp ./:/home/mikelemus/antlr4_lib/antlr-4.0-complete.jar org.antlr.v4.runtime.misc.TestRig "$@";
@Rem uso: ./Comando.sh [ruta  de las  fuentes] [archivo a compilar]
java -cp ./;c:\antlr4_lib\;antlr-4.0-complete.jar;%1 %2

~
~
