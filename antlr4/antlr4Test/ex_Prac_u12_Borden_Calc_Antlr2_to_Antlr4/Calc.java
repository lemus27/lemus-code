import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

public class Calc {
    
    public static void main(String[] args) throws Exception{
			try{
					BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	                String entrada = null;
					float result = 0;
					boolean bSalir = false;
	
						do{
							System.out.print("\n? ");
						    entrada = in.readLine();
								
								if(entrada.startsWith("salir")){bSalir=true;}
												
									else{
									
										 ANTLRInputStream input = new ANTLRInputStream(entrada); 
										 MicroCalcLexer lexer = new MicroCalcLexer(input); 
										 CommonTokenStream tokens = new CommonTokenStream(lexer); 
										 MicroCalcParser parser = new MicroCalcParser(tokens); 
						                 ParseTree tree = parser.expresion();
										 EvalVisitor eval = new EvalVisitor();
										 
										
							             System.out.println("Resultado: " +  eval.visit(tree));
										 }
										 

						} while(!bSalir);
			}catch (RecognitionException e) { System.out.println(e+ " Algo Paso");}
			
    }	
}
