
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package org.unitec.compiladores;

import java_cup.runtime.*;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Parser extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Parser() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Parser(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Parser(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\032\000\002\005\007\000\002\002\004\000\002\006" +
    "\006\000\002\010\002\000\002\010\003\000\002\010\005" +
    "\000\002\011\003\000\002\011\003\000\002\015\003\000" +
    "\002\015\003\000\002\016\003\000\002\016\003\000\002" +
    "\016\003\000\002\016\003\000\002\020\003\000\002\020" +
    "\003\000\002\020\003\000\002\017\010\000\002\013\006" +
    "\000\002\021\003\000\002\021\005\000\002\022\003\000" +
    "\002\037\003\000\002\040\003\000\002\041\003\000\002" +
    "\042\003" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\051\000\004\070\004\001\002\000\004\061\007\001" +
    "\002\000\004\002\006\001\002\000\004\002\000\001\002" +
    "\000\004\074\010\001\002\000\006\012\016\042\ufffe\001" +
    "\002\000\006\042\ufffb\074\ufffb\001\002\000\004\042\051" +
    "\001\002\000\006\042\ufffd\074\047\001\002\000\006\042" +
    "\ufffa\074\ufffa\001\002\000\004\073\046\001\002\000\004" +
    "\061\021\001\002\000\006\055\044\057\uffee\001\002\000" +
    "\004\057\022\001\002\000\006\055\uffec\057\uffec\001\002" +
    "\000\014\007\027\013\025\014\032\015\031\016\026\001" +
    "\002\000\006\042\ufff9\074\ufff9\001\002\000\006\042\ufff8" +
    "\074\ufff8\001\002\000\006\042\ufff5\074\ufff5\001\002\000" +
    "\006\042\ufff6\074\ufff6\001\002\000\004\051\033\001\002" +
    "\000\006\042\uffef\074\uffef\001\002\000\006\042\ufff4\074" +
    "\ufff4\001\002\000\006\042\ufff7\074\ufff7\001\002\000\004" +
    "\077\035\001\002\000\004\052\036\001\002\000\004\052" +
    "\uffeb\001\002\000\004\010\037\001\002\000\010\013\040" +
    "\014\043\015\042\001\002\000\006\042\ufff2\074\ufff2\001" +
    "\002\000\006\042\ufff0\074\ufff0\001\002\000\006\042\ufff1" +
    "\074\ufff1\001\002\000\006\042\ufff3\074\ufff3\001\002\000" +
    "\004\061\021\001\002\000\004\057\uffed\001\002\000\004" +
    "\002\001\001\002\000\006\012\016\042\ufffe\001\002\000" +
    "\004\042\ufffc\001\002\000\002\001\002\000\004\043\053" +
    "\001\002\000\004\073\uffff\001\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\051\000\004\005\004\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\014" +
    "\006\014\010\011\011\012\013\010\014\013\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\006\021\017\022\016\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\010\015\027\016\022\017\023\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\004\037\033\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\004\020\040\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\006\021\044\022\016\001\001\000" +
    "\002\001\001\000\002\001\001\000\012\010\047\011\012" +
    "\013\010\014\013\001\001\000\002\001\001\000\004\024" +
    "\051\001\001\000\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Parser$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Parser$actions(this);
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
    return action_obj.CUP$Parser$do_action(act_num, parser, stack, top);
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
class CUP$Parser$actions {
  private final Parser parser;

  /** Constructor */
  CUP$Parser$actions(Parser parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action_part00000000(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Parser$result;

      /* select the action based on the action number */
      switch (CUP$Parser$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // program ::= Program Identificador PuntoComa block Punto 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object b = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("program",3, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-4)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // $START ::= program EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		RESULT = start_val;
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Parser$parser.done_parsing();
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // block ::= decls Begin stmts End 
            {
              Object RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int dright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		Object d = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-1)).right;
		Object s = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-1)).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("block",4, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // decls ::= 
            {
              Object RESULT =null;
		 RESULT = null; 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("decls",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // decls ::= decl 
            {
              Object RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int dright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object d = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("decls",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // decls ::= decl PuntoComa decls 
            {
              Object RESULT =null;
		int dleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int dright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object d = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int dsleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int dsright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object ds = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("decls",6, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // decl ::= vardecl 
            {
              Object RESULT =null;
		int vdleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int vdright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object vd = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("decl",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // decl ::= procdecl 
            {
              Object RESULT =null;
		int pdleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int pdright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object pd = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("decl",7, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // type ::= identtype 
            {
              Object RESULT =null;
		int itleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int itright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object it = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("type",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // type ::= arraytype 
            {
              Object RESULT =null;
		int atsleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int atsright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object ats = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("type",11, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // identtype ::= TipoChar 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("identtype",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // identtype ::= TipoString 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("identtype",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // identtype ::= TipoBoolean 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("identtype",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // identtype ::= TipoInteger 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("identtype",12, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // arrayidenttype ::= TipoChar 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("arrayidenttype",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // arrayidenttype ::= TipoBoolean 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("arrayidenttype",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // arrayidenttype ::= TipoInteger 
            {
              Object RESULT =null;

              CUP$Parser$result = parser.getSymbolFactory().newSymbol("arrayidenttype",14, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // arraytype ::= Array BracketAbrir integer BracketCerrar Of arrayidenttype 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-3)).value;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("arraytype",13, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-5)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // vardecl ::= Var vars DosPuntos type 
            {
              Object RESULT =null;
		int vsleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int vsright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object vs = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int tleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int tright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object t = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("vardecl",9, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-3)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // vars ::= var 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("vars",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // vars ::= var Coma vars 
            {
              Object RESULT =null;
		int vleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).left;
		int vright = ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)).right;
		Object v = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.elementAt(CUP$Parser$top-2)).value;
		int vsleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int vsright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object vs = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("vars",15, ((java_cup.runtime.Symbol)CUP$Parser$stack.elementAt(CUP$Parser$top-2)), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // var ::= Identificador 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		Object i = (Object)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		 
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("var",16, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // integer ::= LiteralEntero 
            {
              Object RESULT =null;
		int ileft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int iright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		java.lang.Number i = (java.lang.Number)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("integer",29, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // character ::= LiteralCaracter 
            {
              Object RESULT =null;
		int cleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int cright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		java.lang.Character c = (java.lang.Character)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("character",30, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 24: // boolean ::= LiteralBoolean 
            {
              Object RESULT =null;
		int bleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		java.lang.Boolean b = (java.lang.Boolean)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("boolean",31, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 25: // string ::= LiteralString 
            {
              Object RESULT =null;
		int sleft = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).left;
		int sright = ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()).right;
		java.lang.String s = (java.lang.String)((java_cup.runtime.Symbol) CUP$Parser$stack.peek()).value;
		  
              CUP$Parser$result = parser.getSymbolFactory().newSymbol("string",32, ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), ((java_cup.runtime.Symbol)CUP$Parser$stack.peek()), RESULT);
            }
          return CUP$Parser$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Parser$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Parser$do_action(
    int                        CUP$Parser$act_num,
    java_cup.runtime.lr_parser CUP$Parser$parser,
    java.util.Stack            CUP$Parser$stack,
    int                        CUP$Parser$top)
    throws java.lang.Exception
    {
              return CUP$Parser$do_action_part00000000(
                               CUP$Parser$act_num,
                               CUP$Parser$parser,
                               CUP$Parser$stack,
                               CUP$Parser$top);
    }
}

}
