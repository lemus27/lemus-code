#!/usr/bin/env groovy
//A string can be executed in the standard java way:
println "compilando la ruta ${args[0]}"
def command = """javac -cp ./:/home/mikelemus/antlr4_lib/antlr-4.0-complete.jar:${args[0]} ${args[1]}"""// Create the String
def proc = command.execute()                 // Call *execute* on the string
proc.waitFor()                               // Wait for the command to finish

// Obtain status and output
println "return code: ${ proc.exitValue()}"
println "stderr: ${proc.err.text}"
println "stdout: ${proc.in.text}" // *out* from the external program is *in* for groovy



