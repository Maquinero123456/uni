
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

import java_cup.runtime.*;
import java.util.ArrayList;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\035\000\002\003\004\000\002\002\004\000\002\003" +
    "\003\000\002\002\004\000\002\002\003\000\002\011\002" +
    "\000\002\006\007\000\002\012\002\000\002\006\007\000" +
    "\002\013\002\000\002\006\007\000\002\014\002\000\002" +
    "\006\007\000\002\006\005\000\002\006\005\000\002\006" +
    "\005\000\002\006\005\000\002\006\005\000\002\006\003" +
    "\000\002\006\005\000\002\006\003\000\002\005\005\000" +
    "\002\005\005\000\002\007\005\000\002\007\003\000\002" +
    "\007\007\000\002\007\005\000\002\010\005\000\002\010" +
    "\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\074\000\024\004\011\006\020\010\012\013\013\021" +
    "\006\022\004\023\007\024\016\025\005\001\002\000\004" +
    "\006\073\001\002\000\020\007\uffef\013\uffef\014\071\015" +
    "\uffef\016\uffef\017\uffef\020\uffef\001\002\000\004\006\065" +
    "\001\002\000\004\006\061\001\002\000\014\013\060\015" +
    "\022\016\023\017\025\020\024\001\002\000\006\004\042" +
    "\026\041\001\002\000\006\004\042\026\041\001\002\000" +
    "\026\002\ufffd\004\ufffd\006\ufffd\010\ufffd\013\ufffd\021\ufffd" +
    "\022\ufffd\023\ufffd\024\ufffd\025\ufffd\001\002\000\026\002" +
    "\037\004\011\006\020\010\012\013\013\021\006\022\004" +
    "\023\007\024\016\025\005\001\002\000\016\007\uffed\013" +
    "\uffed\015\uffed\016\uffed\017\uffed\020\uffed\001\002\000\004" +
    "\006\033\001\002\000\026\002\uffff\004\uffff\006\uffff\010" +
    "\uffff\013\uffff\021\uffff\022\uffff\023\uffff\024\uffff\025\uffff" +
    "\001\002\000\022\004\011\006\020\010\012\021\006\022" +
    "\004\023\007\024\016\025\005\001\002\000\014\007\026" +
    "\015\022\016\023\017\025\020\024\001\002\000\022\004" +
    "\011\006\020\010\012\021\006\022\004\023\007\024\016" +
    "\025\005\001\002\000\022\004\011\006\020\010\012\021" +
    "\006\022\004\023\007\024\016\025\005\001\002\000\022" +
    "\004\011\006\020\010\012\021\006\022\004\023\007\024" +
    "\016\025\005\001\002\000\022\004\011\006\020\010\012" +
    "\021\006\022\004\023\007\024\016\025\005\001\002\000" +
    "\016\007\uffee\013\uffee\015\uffee\016\uffee\017\uffee\020\uffee" +
    "\001\002\000\016\007\ufff2\013\ufff2\015\ufff2\016\ufff2\017" +
    "\ufff2\020\ufff2\001\002\000\016\007\ufff1\013\ufff1\015\ufff1" +
    "\016\ufff1\017\ufff1\020\ufff1\001\002\000\016\007\ufff3\013" +
    "\ufff3\015\ufff3\016\ufff3\017\025\020\024\001\002\000\016" +
    "\007\ufff4\013\ufff4\015\ufff4\016\ufff4\017\025\020\024\001" +
    "\002\000\022\004\011\006\020\010\012\021\006\022\004" +
    "\023\007\024\016\025\005\001\002\000\014\007\ufffc\015" +
    "\022\016\023\017\025\020\024\001\002\000\004\007\036" +
    "\001\002\000\016\007\ufffb\013\ufffb\015\ufffb\016\ufffb\017" +
    "\ufffb\020\ufffb\001\002\000\004\002\000\001\002\000\026" +
    "\002\001\004\001\006\001\010\001\013\001\021\001\022" +
    "\001\023\001\024\001\025\001\001\002\000\012\005\uffe5" +
    "\011\uffe5\012\054\013\uffe5\001\002\000\004\026\041\001" +
    "\002\000\004\011\047\001\002\000\010\005\uffe9\011\uffe9" +
    "\013\045\001\002\000\006\004\042\026\041\001\002\000" +
    "\006\005\uffea\011\uffea\001\002\000\016\007\uffec\013\uffec" +
    "\015\uffec\016\uffec\017\uffec\020\uffec\001\002\000\004\005" +
    "\051\001\002\000\010\005\uffe7\011\uffe7\012\052\001\002" +
    "\000\006\004\042\026\041\001\002\000\006\005\uffe8\011" +
    "\uffe8\001\002\000\004\026\041\001\002\000\010\005\uffe6" +
    "\011\uffe6\013\uffe6\001\002\000\004\005\057\001\002\000" +
    "\016\007\uffeb\013\uffeb\015\uffeb\016\uffeb\017\uffeb\020\uffeb" +
    "\001\002\000\026\002\ufffe\004\ufffe\006\ufffe\010\ufffe\013" +
    "\ufffe\021\ufffe\022\ufffe\023\ufffe\024\ufffe\025\ufffe\001\002" +
    "\000\022\004\011\006\020\010\012\021\006\022\004\023" +
    "\007\024\016\025\005\001\002\000\014\007\ufff6\015\022" +
    "\016\023\017\025\020\024\001\002\000\004\007\064\001" +
    "\002\000\016\007\ufff5\013\ufff5\015\ufff5\016\ufff5\017\ufff5" +
    "\020\ufff5\001\002\000\022\004\011\006\020\010\012\021" +
    "\006\022\004\023\007\024\016\025\005\001\002\000\014" +
    "\007\ufffa\015\022\016\023\017\025\020\024\001\002\000" +
    "\004\007\070\001\002\000\016\007\ufff9\013\ufff9\015\ufff9" +
    "\016\ufff9\017\ufff9\020\ufff9\001\002\000\022\004\011\006" +
    "\020\010\012\021\006\022\004\023\007\024\016\025\005" +
    "\001\002\000\016\007\ufff0\013\ufff0\015\022\016\023\017" +
    "\025\020\024\001\002\000\022\004\011\006\020\010\012" +
    "\021\006\022\004\023\007\024\016\025\005\001\002\000" +
    "\014\007\ufff8\015\022\016\023\017\025\020\024\001\002" +
    "\000\004\007\076\001\002\000\016\007\ufff7\013\ufff7\015" +
    "\ufff7\016\ufff7\017\ufff7\020\ufff7\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\074\000\012\002\016\003\013\005\014\006\007\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\006\007\055\010" +
    "\043\001\001\000\006\007\042\010\043\001\001\000\002" +
    "\001\001\000\010\002\037\005\014\006\007\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\006" +
    "\005\014\006\020\001\001\000\002\001\001\000\006\005" +
    "\014\006\031\001\001\000\006\005\014\006\030\001\001" +
    "\000\006\005\014\006\027\001\001\000\006\005\014\006" +
    "\026\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\006\005" +
    "\014\006\033\001\001\000\004\011\034\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\004\010\047\001\001\000\002" +
    "\001\001\000\002\001\001\000\006\007\045\010\043\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\007\052\010\043\001\001\000" +
    "\002\001\001\000\004\010\054\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\006\005\014\006\061\001\001\000\004\014\062\001\001" +
    "\000\002\001\001\000\002\001\001\000\006\005\014\006" +
    "\065\001\001\000\004\012\066\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\005\014\006\071\001\001\000" +
    "\002\001\001\000\006\005\014\006\073\001\001\000\004" +
    "\013\074\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$parser$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$parser$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 1;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$parser$actions {
  private final parser parser;

  /** Constructor */
  CUP$parser$actions(parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$parser$do_action_part00000000(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$parser$result;

      /* select the action based on the action number */
      switch (CUP$parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // lineaExp ::= lineaExp linea 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("lineaExp",1, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= lineaExp EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT = start_val;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$parser$parser.done_parsing();
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // lineaExp ::= linea 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("lineaExp",1, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // linea ::= exp PYC 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("linea",0, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // linea ::= PYC 
            {
              Object RESULT =null;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("linea",0, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // NT$0 ::= 
            {
              double[][] RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
Matrices.print(v);
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NT$0",7, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // exp ::= PRINT AP exp NT$0 CP 
            {
              double[][] RESULT =null;
              // propagate RESULT from NT$0
                RESULT = (double[][]) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // NT$1 ::= 
            {
              double[][] RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
if(Matrices.filas(v)==Matrices.columnas(v)){
                              RESULT = Matrices.inversa(v);
                           }else{
                              System.out.println(Matrices.ERROR_INVERSA);
                              System.exit(-1);
                           }
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NT$1",8, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // exp ::= INVERSA AP exp NT$1 CP 
            {
              double[][] RESULT =null;
              // propagate RESULT from NT$1
                RESULT = (double[][]) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // NT$2 ::= 
            {
              double[][] RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
RESULT = Matrices.transpuesta(v);
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NT$2",9, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // exp ::= TRANSPUESTA AP exp NT$2 CP 
            {
              double[][] RESULT =null;
              // propagate RESULT from NT$2
                RESULT = (double[][]) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // NT$3 ::= 
            {
              double[][] RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
if(Matrices.filas(v)==Matrices.columnas(v)){
                              RESULT = Matrices.adjunta(v);
                           }else{
                              System.out.println(Matrices.ERROR_ADJUNTA);
                              System.exit(-1);
                           }
              CUP$parser$result = parser.getSymbolFactory().newSymbol("NT$3",10, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // exp ::= ADJUNTA AP exp NT$3 CP 
            {
              double[][] RESULT =null;
              // propagate RESULT from NT$3
                RESULT = (double[][]) ((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // exp ::= exp MAS exp 
            {
              double[][] RESULT =null;
		int v1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int v1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		double[][] v1 = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int v2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int v2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v2 = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		if((Matrices.columnas(v1)==Matrices.columnas(v2))&&(Matrices.filas(v1)==Matrices.filas(v2))){
                              RESULT = Matrices.suma(v1,v2);
                            }else{
                              System.out.println(Matrices.ERROR_SUMA);
                              System.exit(-1);
                            }
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // exp ::= exp MENOS exp 
            {
              double[][] RESULT =null;
		int v1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int v1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		double[][] v1 = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int v2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int v2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v2 = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // exp ::= exp POR exp 
            {
              double[][] RESULT =null;
		int v1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int v1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		double[][] v1 = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int v2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int v2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v2 = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		if(Matrices.columnas(v1)==Matrices.filas(v2)){
                              RESULT = Matrices.producto(v1,v2);
                            }else{
                              System.out.println(Matrices.ERROR_PROD);
                              System.exit(-1);
                            };
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // exp ::= exp DIV exp 
            {
              double[][] RESULT =null;
		int v1left = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int v1right = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		double[][] v1 = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int v2left = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int v2right = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v2 = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;

              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // exp ::= IDENT ASIG exp 
            {
              double[][] RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		TablaSimbolos.insertar(a, v);
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // exp ::= IDENT 
            {
              double[][] RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		double[][] aux = TablaSimbolos.buscar(a);
                  if(aux==null){
                        System.out.println(TablaSimbolos.ERROR_NOEXISTE);
                        System.exit(-1);
                  }
                  RESULT = aux;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // exp ::= AP exp CP 
            {
              double[][] RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		double[][] a = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		RESULT=a;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // exp ::= matriz 
            {
              double[][] RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		double[][] v = (double[][])((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		RESULT = v;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("exp",4, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // matriz ::= AC procesarMatriz CC 
            {
              double[][] RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		ArrayList<ArrayList<Double>> v = (ArrayList<ArrayList<Double>>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		try{
                                          double[][] a = Matrices.toArray(v);
                                          RESULT=a;
                                    }catch (IndexOutOfBoundsException e){
                                          System.out.println(Matrices.ERROR_FILAS);
                                          System.exit(-1);
                                    }
                                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("matriz",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // matriz ::= ALL procesarMatriz CLL 
            {
              double[][] RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		ArrayList<ArrayList<Double>> v = (ArrayList<ArrayList<Double>>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		try{
                                          double[][] a = Matrices.toArray(v);
                                          RESULT=a;
                                    }catch (IndexOutOfBoundsException e){
                                          System.out.println(Matrices.ERROR_FILAS);
                                          System.exit(-1);
                                    }
                                    
              CUP$parser$result = parser.getSymbolFactory().newSymbol("matriz",3, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // procesarMatriz ::= fila PYC procesarMatriz 
            {
              ArrayList<ArrayList<Double>> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		ArrayList<Double> v = (ArrayList<Double>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int mleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int mright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		ArrayList<ArrayList<Double>> m = (ArrayList<ArrayList<Double>>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		ArrayList<ArrayList<Double>> aux = new ArrayList<>(); aux.add(v);aux.addAll(m); RESULT = aux;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("procesarMatriz",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // procesarMatriz ::= fila 
            {
              ArrayList<ArrayList<Double>> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		ArrayList<Double> v = (ArrayList<Double>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		ArrayList<ArrayList<Double>> aux = new ArrayList<>(); aux.add(v); RESULT = aux;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("procesarMatriz",5, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // procesarMatriz ::= ALL fila CLL COMA procesarMatriz 
            {
              ArrayList<ArrayList<Double>> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-3)).right;
		ArrayList<Double> v = (ArrayList<Double>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-3)).value;
		int mleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int mright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		ArrayList<ArrayList<Double>> m = (ArrayList<ArrayList<Double>>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		ArrayList<ArrayList<Double>> aux = new ArrayList<>(); aux.add(v);aux.addAll(m); RESULT = aux;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("procesarMatriz",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-4)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 26: // procesarMatriz ::= ALL fila CLL 
            {
              ArrayList<ArrayList<Double>> RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-1)).right;
		ArrayList<Double> v = (ArrayList<Double>)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-1)).value;
		ArrayList<ArrayList<Double>> aux = new ArrayList<>(); aux.add(v); RESULT = aux;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("procesarMatriz",5, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 27: // fila ::= NUMERO COMA fila 
            {
              ArrayList<Double> RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)).right;
		Double a = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.elementAt(CUP$parser$top-2)).value;
		int vleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		ArrayList<Double> v = (ArrayList<Double>)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		ArrayList<Double> aux = new ArrayList<>(); aux.add(a); aux.addAll(v); RESULT=aux;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("fila",6, ((java_cup.runtime.Symbol)CUP$parser$stack.elementAt(CUP$parser$top-2)), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 28: // fila ::= NUMERO 
            {
              ArrayList<Double> RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$parser$stack.peek()).right;
		Double a = (Double)((java_cup.runtime.Symbol) CUP$parser$stack.peek()).value;
		ArrayList<Double> aux = new ArrayList<>(); aux.add(a); RESULT = aux;
              CUP$parser$result = parser.getSymbolFactory().newSymbol("fila",6, ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$parser$stack.peek()), RESULT);
            }
          return CUP$parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$parser$do_action(
    int                        CUP$parser$act_num,
    java_cup.runtime.lr_parser CUP$parser$parser,
    java.util.Stack            CUP$parser$stack,
    int                        CUP$parser$top)
    throws java.lang.Exception
    {
              return CUP$parser$do_action_part00000000(
                               CUP$parser$act_num,
                               CUP$parser$parser,
                               CUP$parser$stack,
                               CUP$parser$top);
    }
}

}
