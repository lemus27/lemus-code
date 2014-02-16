v1=ARGV[0]
print "analizando la gramatica #{v1}"  
exec("java -jar antlr-4.0-complete.jar #{v1}")
