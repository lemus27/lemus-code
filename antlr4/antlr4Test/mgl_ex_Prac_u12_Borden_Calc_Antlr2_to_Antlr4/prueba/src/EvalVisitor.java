import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends MicroCalcBaseVisitor<Integer> {
Map<String, Integer> memory = new HashMap<String, Integer>();
/***************solod e prueba  no deberia  ir se gun l lectura*******************************/
 /*@Override
public Integer visitExpresion(MicroCalcParser.ExpresionContext ctx) 
{ 
return Integer.valueOf(visit());
} 
/********************************************************/
EvalVisitor()
{
 System.out.println("Objeto  Eval visitor Creado");
}
 @Override
    public Integer visitOPSUMA(MicroCalcParser.OPSUMAContext ctx) {
	System.out.println("metodo OPSUMa Empezando");
		int left = visit(ctx.expResta(0));  
        int right = visit(ctx.expResta(1)); 
		System.out.println("suma:left: " +  left);
		System.out.println("suma:right: " +  right);
        return left+right;	
    }

  @Override
    public Integer visitOPRESTA(MicroCalcParser.OPRESTAContext ctx) {
		int left = visit(ctx.expProducto(0));  
        int right = visit(ctx.expProducto(1)); 
		System.out.println("resta:left: " +  left);
		System.out.println("resta:right: " +  right);
        return left-right;	
    }

  @Override
    public Integer visitOPPRODDUCTO(MicroCalcParser.OPPRODDUCTOContext ctx) {
		int left = visit(ctx.expCociente(0));  
        int right = visit(ctx.expCociente(1)); 
		System.out.println("producto:left: " +  left);
		System.out.println("producto:right: " +  right);
        return left*right;	
    }

  @Override
    public Integer visitOPCOCIENTE(MicroCalcParser.OPCOCIENTEContext ctx) {
		int left = visit(ctx.expBase(0));  
        int right = visit(ctx.expBase(0)); 
		System.out.println("cociente:left: " +  left);
		System.out.println("cociente:right: " +  right);
        return left/right;	
    }
 /** UNNUMERO */
    @Override
    public Integer visitUNNUMERO(MicroCalcParser.UNNUMEROContext ctx) {//MicroCalcParser.UNNUMEROContext ctx):. "UNNUMEROContext" es el contexto de la regla  etiquetada  con UNNUMERO
        System.out.println("ctx.NUMERO().getText(): " +  ctx.NUMERO().getText());
		return Integer.valueOf(ctx.NUMERO().getText()); //ctx.Numero:.  "NUMERO" es el terminal de la gramatica  que  representa los digitos
    }
	 /** PARENT_AB! expresion PARENT_CE! */
    @Override
    public Integer visitPARENTESIS(MicroCalcParser.PARENTESISContext ctx) {//MicroCalcParser.PARENTESISContext ctx):. "PARENTESISContext" es el contexto de la regla  etiquetada  con PARENTESIS
      System.out.println("ctx.expresion(): " +  ctx.expresion());
		return visit(ctx.expresion()); // ctx.expresion:. "expresion"  es el nodo  que va entre parentesis
    }
}