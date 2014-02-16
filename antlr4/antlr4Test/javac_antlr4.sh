#/bin/bash
#Miguel Angel Gallardo Lemus_21-03-2013-2100
#script para  probar  que  funcionen las  clases  gneradas en antlr4  y compiladas  en linux 
#para ejecutar este  archivo:
#1.- /bin/bash grun.bash [parametros]
#2.- ./grun.bash [parametros]
#Parametros: [ruta  de las  fuentes] [archivo a compilar] 
#nota:mgl:21-03-2013: para  separar elementos en el "-cp", enlinux  en con ":", en win es con";"
#nota: para a ceptar paramentros en el escrit es con "$1 $2 ... o  $*  "
#uso: ./javac_antlr4.sh [ruta  de las  fuentes] [archivo a compilar] 
if [ "$1" == "." ]
then
echo "compilacion ene l directorio actual"
echo "compilando el directorio $1 y los  archivos  $2"
exec javac -cp ./:/home/mikelemus/antlr4_lib/antlr-4.0-complete.jar $2;      
exit 0
fi
echo "compilacion ene l directorio remoto"
echo ""$1""/""$2""
echo "compilando el directorio $1 y los  archivos  $2"
exec javac -cp ./:/home/mikelemus/antlr4_lib/antlr-4.0-complete.jar:$1 ""$1""/""$2"";

