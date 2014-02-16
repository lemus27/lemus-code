package main
public class archivo{
private static archi = new File ("ejemplo.txt");



public static void main (String [] args)
{
	
// archi.eachLine( { println it });
//def m = archi.text.findAll('\\n').size()+1;
	
int m=0;;
archi.eachLine({m++})//una  forma de  contyar las  lineas

println "numero de lineas: "+ (m);

String [] str = new String [m]
String [][] datos = new String [m][5]
String delimiter =","
int i=0;//NOTAÇ: por eso  no salia   la variable i debe esta feura del  each line, ya  que  el each line  es  un  ciclo de  0 al  numerod e lienas del archivo
archi.eachLine({
	str[i]= it
	datos[i]=str[i].split(delimiter);
    println "guardado dato"+i+": "+ datos[i]
i++

	})
	
println "imprimiendo  todo los  datos"
	println datos[0]
	println datos[1]
	println datos[2]
	println datos[3]
	println datos[4]




}

}