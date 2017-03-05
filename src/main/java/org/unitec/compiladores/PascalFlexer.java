/* The following code was generated by JFlex 1.6.1 */

package org.unitec.compiladores;

import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>C:/Users/Cesar Bonilla/Documents/GitHub/unitec.compilidores.mini-pascal/src/main/jflex/mini-pascal.jflex</tt>
 */
public class PascalFlexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int COMMENT = 2;
  public static final int COMILLA_SIMPLE = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\46\1\50\1\55\1\56\1\47\22\0\1\46\6\0\1\42"+
    "\1\7\1\11\1\14\1\45\1\10\1\45\1\34\1\14\12\54\1\33"+
    "\1\23\1\43\1\24\1\44\2\0\1\5\1\37\1\35\1\31\1\22"+
    "\1\20\1\4\1\36\1\52\2\51\1\27\1\6\1\30\1\3\1\1"+
    "\1\51\1\2\1\53\1\21\1\41\1\32\1\25\1\51\1\15\1\51"+
    "\1\16\1\0\1\17\1\0\1\51\1\0\1\5\1\37\1\35\1\31"+
    "\1\22\1\20\1\4\1\36\1\52\2\51\1\27\1\6\1\30\1\3"+
    "\1\1\1\51\1\2\1\53\1\21\1\41\1\32\1\25\1\51\1\15"+
    "\1\51\1\12\1\0\1\13\7\0\1\55\252\0\2\26\115\0\1\40"+
    "\u1ea8\0\1\55\1\55\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\6\2\1\3\1\4\1\5\1\6\1\7"+
    "\1\10\1\11\1\12\3\2\1\13\1\14\1\2\1\1"+
    "\3\2\1\15\1\16\2\2\1\1\1\17\1\20\1\21"+
    "\1\22\2\23\2\2\1\24\1\25\1\26\1\27\3\30"+
    "\1\31\1\32\10\30\1\0\1\30\1\0\2\30\1\33"+
    "\3\30\2\0\1\34\1\35\1\36\5\30\1\37\1\22"+
    "\3\30\1\40\1\0\1\30\1\0\1\41\1\42\3\30"+
    "\1\0\3\30\1\43\1\30\1\0\1\44\1\45\1\0"+
    "\1\30\1\0\1\46\1\30\1\0\1\30\1\0\3\30"+
    "\1\47\1\44\2\50\1\0\1\30\2\51\1\0\3\30"+
    "\1\0\1\30\1\0\1\30\1\52\1\30\1\52\1\53"+
    "\2\54\1\55\1\56\1\55";

  private static int [] zzUnpackAction() {
    int [] result = new int[136];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\215\0\274\0\353\0\u011a\0\u0149"+
    "\0\u0178\0\u01a7\0\215\0\215\0\215\0\215\0\215\0\215"+
    "\0\215\0\215\0\u01d6\0\u0205\0\u0234\0\215\0\215\0\u0263"+
    "\0\u0292\0\u02c1\0\u02f0\0\u031f\0\u034e\0\215\0\u037d\0\u03ac"+
    "\0\u03db\0\u040a\0\u0439\0\u0468\0\215\0\215\0\u0497\0\u04c6"+
    "\0\u04f5\0\u0524\0\215\0\215\0\215\0\u0149\0\u0553\0\u0582"+
    "\0\u0149\0\u0149\0\u05b1\0\u05e0\0\u060f\0\u063e\0\u066d\0\u069c"+
    "\0\u06cb\0\u06fa\0\u0729\0\u0758\0\u0787\0\u07b6\0\u07e5\0\215"+
    "\0\u0814\0\u0843\0\u0872\0\u08a1\0\u040a\0\215\0\215\0\215"+
    "\0\u08d0\0\u08ff\0\u092e\0\u095d\0\u098c\0\u0149\0\u0149\0\u09bb"+
    "\0\u09ea\0\u0a19\0\u0149\0\u0a48\0\u0a77\0\u0aa6\0\u0149\0\u0149"+
    "\0\u0ad5\0\u0b04\0\u0b33\0\u0b62\0\u0b91\0\u0bc0\0\u0bef\0\u0149"+
    "\0\u0c1e\0\u0c4d\0\u0149\0\u0149\0\u0c7c\0\u0cab\0\u0cda\0\u0149"+
    "\0\u0d09\0\u0d38\0\u0d67\0\u0d96\0\u0dc5\0\u0df4\0\u0e23\0\u0149"+
    "\0\215\0\u0e52\0\u0e81\0\u0eb0\0\u0edf\0\215\0\u0149\0\u0f0e"+
    "\0\u0f3d\0\u0f6c\0\u0f9b\0\u0fca\0\u0ff9\0\u1028\0\u1057\0\215"+
    "\0\u1086\0\u0149\0\u0149\0\215\0\u0149\0\215\0\u0149\0\u0149";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[136];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\10\1\21\1\22"+
    "\1\23\1\24\1\25\1\26\1\27\1\30\1\31\1\10"+
    "\1\32\1\33\1\34\1\35\1\36\1\37\1\10\1\40"+
    "\1\41\1\10\1\42\1\43\1\44\1\45\1\46\1\47"+
    "\1\46\1\10\1\50\1\51\1\52\1\0\14\46\1\53"+
    "\33\46\2\0\4\46\2\0\42\54\1\55\4\54\2\0"+
    "\4\54\62\0\1\56\1\57\4\56\6\0\1\56\2\0"+
    "\3\56\2\0\1\56\1\0\4\56\2\0\3\56\1\0"+
    "\1\56\7\0\4\56\3\0\6\56\6\0\1\56\2\0"+
    "\2\56\1\60\2\0\1\56\1\0\4\56\2\0\3\56"+
    "\1\0\1\56\7\0\4\56\3\0\1\56\1\61\4\56"+
    "\6\0\1\56\2\0\1\62\2\56\2\0\1\56\1\0"+
    "\4\56\2\0\3\56\1\0\1\56\7\0\4\56\3\0"+
    "\6\56\6\0\1\56\2\0\3\56\2\0\1\56\1\0"+
    "\4\56\2\0\3\56\1\0\1\56\7\0\4\56\3\0"+
    "\1\56\1\63\4\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\1\56\1\64\2\56\2\0\3\56\1\0"+
    "\1\56\7\0\4\56\3\0\2\56\1\65\3\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\0\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\3\0\4\56\1\66"+
    "\1\56\6\0\1\56\2\0\3\56\2\0\1\56\1\0"+
    "\4\56\2\0\3\56\1\0\1\56\7\0\4\56\3\0"+
    "\1\56\1\67\4\56\6\0\1\70\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\3\0\6\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\1\56\1\71\2\56\2\0\3\56\1\0"+
    "\1\56\7\0\4\56\3\0\1\56\1\72\4\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\0\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\32\0\1\73\27\0"+
    "\2\56\1\74\3\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\3\0\6\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\75\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\1\56\1\76\2\56\3\0\4\56\1\77\1\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\0\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\26\0\1\100\33\0"+
    "\6\56\6\0\1\56\2\0\3\56\2\0\1\56\1\0"+
    "\4\56\2\0\1\56\1\101\1\56\1\0\1\56\7\0"+
    "\4\56\3\0\2\56\1\102\3\56\6\0\1\56\2\0"+
    "\2\56\1\103\2\0\1\56\1\0\4\56\2\0\3\56"+
    "\1\0\1\56\7\0\4\56\23\0\1\104\35\0\42\105"+
    "\1\106\14\105\24\0\1\107\56\0\1\110\102\0\1\46"+
    "\7\0\6\56\6\0\1\56\2\0\3\56\2\0\1\56"+
    "\1\0\1\56\1\111\2\56\2\0\3\56\1\0\1\56"+
    "\7\0\4\56\3\0\6\56\6\0\1\56\2\0\1\56"+
    "\1\112\1\56\2\0\1\56\1\0\4\56\2\0\3\56"+
    "\1\0\1\56\7\0\4\56\56\0\1\52\3\0\2\56"+
    "\1\113\3\56\6\0\1\56\2\0\3\56\2\0\1\56"+
    "\1\0\4\56\2\0\3\56\1\0\1\56\7\0\4\56"+
    "\3\0\4\56\1\114\1\56\6\0\1\56\2\0\3\56"+
    "\2\0\1\56\1\0\4\56\2\0\3\56\1\0\1\56"+
    "\7\0\4\56\3\0\1\56\1\115\4\56\6\0\1\56"+
    "\2\0\3\56\2\0\1\56\1\0\4\56\2\0\3\56"+
    "\1\0\1\56\7\0\4\56\3\0\6\56\6\0\1\56"+
    "\2\0\3\56\2\0\1\56\1\0\2\56\1\116\1\56"+
    "\2\0\3\56\1\0\1\56\7\0\4\56\3\0\6\56"+
    "\6\0\1\56\2\0\3\56\2\0\1\56\1\0\2\56"+
    "\1\117\1\56\2\0\3\56\1\0\1\56\7\0\4\56"+
    "\3\0\6\56\6\0\1\56\2\0\3\56\2\0\1\56"+
    "\1\0\1\120\3\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\3\0\6\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\121\7\0"+
    "\4\56\3\0\1\122\5\56\6\0\1\56\2\0\3\56"+
    "\2\0\1\56\1\0\4\56\2\0\3\56\1\0\1\56"+
    "\7\0\4\56\3\0\6\56\6\0\1\56\2\0\3\56"+
    "\2\0\1\56\1\0\2\56\1\123\1\56\2\0\3\56"+
    "\1\0\1\56\7\0\4\56\3\0\6\56\6\0\1\56"+
    "\2\0\3\56\2\0\1\56\1\124\4\56\2\0\3\56"+
    "\1\0\1\56\7\0\1\56\1\125\2\56\23\0\1\126"+
    "\36\0\6\56\6\0\1\56\2\0\1\56\1\127\1\56"+
    "\2\0\1\56\1\0\4\56\2\0\3\56\1\0\1\56"+
    "\7\0\4\56\34\0\1\45\25\0\6\56\6\0\1\56"+
    "\2\0\3\56\2\0\1\56\1\0\3\56\1\117\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\3\0\1\56\1\130"+
    "\4\56\6\0\1\56\2\0\3\56\2\0\1\56\1\0"+
    "\4\56\2\0\3\56\1\0\1\56\7\0\4\56\3\0"+
    "\4\56\1\131\1\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\3\0\2\56\1\132\3\56\6\0\1\56\2\0"+
    "\3\56\2\0\1\56\1\0\4\56\2\0\3\56\1\0"+
    "\1\56\7\0\4\56\3\0\3\56\1\133\2\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\0\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\4\0\1\134\55\0"+
    "\6\56\6\0\1\56\2\0\1\56\1\135\1\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\3\0\1\56\1\136\4\56\6\0\1\56\2\0"+
    "\3\56\2\0\1\56\1\0\4\56\2\0\3\56\1\0"+
    "\1\56\7\0\4\56\3\0\3\56\1\137\2\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\0\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\3\0\6\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\0\2\56\1\140"+
    "\1\56\2\0\3\56\1\0\1\56\7\0\4\56\3\0"+
    "\4\56\1\141\1\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\3\0\6\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\142\1\56\7\0"+
    "\2\56\1\121\1\56\3\0\6\56\6\0\1\56\2\0"+
    "\2\56\1\143\2\0\1\56\1\0\4\56\2\0\3\56"+
    "\1\0\1\56\7\0\4\56\3\0\6\56\6\0\1\56"+
    "\2\0\2\56\1\144\2\0\1\56\1\0\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\23\0\1\145\36\0"+
    "\6\56\6\0\1\56\2\0\1\56\1\146\1\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\24\0\1\147\35\0\1\56\1\150\4\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\0\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\3\0\6\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\0\1\151\3\56"+
    "\2\0\3\56\1\0\1\56\7\0\4\56\3\0\6\56"+
    "\6\0\1\56\2\0\3\56\2\0\1\56\1\152\4\56"+
    "\2\0\3\56\1\0\1\56\7\0\1\56\1\153\2\56"+
    "\30\0\1\154\23\0\1\154\5\0\6\56\6\0\1\56"+
    "\2\0\2\56\1\155\2\0\1\56\1\0\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\3\0\6\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\154\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\1\56\1\156\2\56\3\0"+
    "\1\56\1\157\4\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\3\0\6\56\6\0\1\160\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\24\0\1\161\56\0\1\162\35\0\6\56\6\0"+
    "\1\56\2\0\2\56\1\163\2\0\1\56\1\0\4\56"+
    "\2\0\3\56\1\0\1\56\7\0\4\56\6\0\1\164"+
    "\53\0\6\56\6\0\1\56\2\0\2\56\1\165\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\32\0\1\166\27\0\6\56\6\0\1\56\2\0"+
    "\3\56\2\0\1\56\1\0\1\56\1\167\2\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\32\0\1\170\27\0"+
    "\3\56\1\171\2\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\3\0\6\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\1\56\1\172\2\56\2\0\3\56\1\0"+
    "\1\56\7\0\4\56\3\0\4\56\1\173\1\56\6\0"+
    "\1\56\2\0\3\56\2\0\1\56\1\0\4\56\2\0"+
    "\3\56\1\0\1\56\7\0\4\56\31\0\1\174\30\0"+
    "\6\56\6\0\1\56\2\0\3\56\2\0\1\56\1\0"+
    "\1\175\3\56\2\0\3\56\1\0\1\56\7\0\4\56"+
    "\24\0\1\176\35\0\4\56\1\177\1\56\6\0\1\56"+
    "\2\0\3\56\2\0\1\56\1\0\4\56\2\0\3\56"+
    "\1\0\1\56\7\0\4\56\6\0\1\200\53\0\6\56"+
    "\6\0\1\56\2\0\2\56\1\201\2\0\1\56\1\0"+
    "\4\56\2\0\3\56\1\0\1\56\7\0\4\56\3\0"+
    "\3\56\1\202\2\56\6\0\1\56\2\0\3\56\2\0"+
    "\1\56\1\0\4\56\2\0\3\56\1\0\1\56\7\0"+
    "\4\56\3\0\5\56\1\203\6\0\1\56\2\0\3\56"+
    "\2\0\1\56\1\0\4\56\2\0\3\56\1\0\1\56"+
    "\7\0\4\56\32\0\1\204\27\0\6\56\6\0\1\56"+
    "\2\0\3\56\2\0\1\56\1\0\1\56\1\205\2\56"+
    "\2\0\3\56\1\0\1\56\7\0\4\56\4\0\1\206"+
    "\55\0\6\56\6\0\1\56\2\0\3\56\2\0\1\56"+
    "\1\0\1\56\1\207\2\56\2\0\3\56\1\0\1\56"+
    "\7\0\4\56\3\0\1\56\1\210\4\56\6\0\1\56"+
    "\2\0\3\56\2\0\1\56\1\0\4\56\2\0\3\56"+
    "\1\0\1\56\7\0\4\56\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[4277];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\6\1\10\11\3\1\2\11\6\1\1\11"+
    "\6\1\2\11\4\1\3\11\15\1\1\0\1\1\1\0"+
    "\2\1\1\11\3\1\2\0\3\11\13\1\1\0\1\1"+
    "\1\0\5\1\1\0\5\1\1\0\2\1\1\0\1\1"+
    "\1\0\2\1\1\0\1\1\1\0\4\1\1\11\2\1"+
    "\1\0\1\1\1\11\1\1\1\0\3\1\1\0\1\1"+
    "\1\0\1\1\1\11\3\1\1\11\1\1\1\11\2\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[136];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    
      StringBuffer string = new StringBuffer();

      private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
      }
      private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
      }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public PascalFlexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 220) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { throw new Error("Illegal character <"+yytext()+">");
            }
          case 47: break;
          case 2: 
            { return new Symbol(sym.LiteralCaracter);
            }
          case 48: break;
          case 3: 
            { return new Symbol(sym.ParentesisAbrir);
            }
          case 49: break;
          case 4: 
            { return new Symbol(sym.Coma);
            }
          case 50: break;
          case 5: 
            { return new Symbol(sym.ParentesisCerrar);
            }
          case 51: break;
          case 6: 
            { yybegin(COMMENT);
            }
          case 52: break;
          case 7: 
            { return new Symbol(sym.LlaveCerrar);
            }
          case 53: break;
          case 8: 
            { return new Symbol(sym.OperadorMultiplicacion);
            }
          case 54: break;
          case 9: 
            { return new Symbol(sym.BracketAbrir);
            }
          case 55: break;
          case 10: 
            { return new Symbol(sym.BracketCerrar);
            }
          case 56: break;
          case 11: 
            { return new Symbol(sym.PuntoComa);
            }
          case 57: break;
          case 12: 
            { return new Symbol(sym.OperadorIgual);
            }
          case 58: break;
          case 13: 
            { return new Symbol(sym.DosPuntos);
            }
          case 59: break;
          case 14: 
            { return new Symbol(sym.Punto);
            }
          case 60: break;
          case 15: 
            { yybegin(COMILLA_SIMPLE);
            }
          case 61: break;
          case 16: 
            { return new Symbol(sym.OperadorMenor);
            }
          case 62: break;
          case 17: 
            { return new Symbol(sym.OperadorMayor);
            }
          case 63: break;
          case 18: 
            { return new Symbol(sym.OperadorSuma);
            }
          case 64: break;
          case 19: 
            { 
            }
          case 65: break;
          case 20: 
            { return new Symbol(sym.LiteralEntero);
            }
          case 66: break;
          case 21: 
            { yybegin(YYINITIAL);
            }
          case 67: break;
          case 22: 
            { string.append(yytext());
            }
          case 68: break;
          case 23: 
            { yybegin(YYINITIAL);return new Symbol(sym.LiteralString,string.toString());
            }
          case 69: break;
          case 24: 
            { return new Symbol(sym.Identificador, yytext());
            }
          case 70: break;
          case 25: 
            { return new Symbol(sym.OperadorOr);
            }
          case 71: break;
          case 26: 
            { return new Symbol(sym.Of);
            }
          case 72: break;
          case 27: 
            { return new Symbol(sym.DosPuntosIgual);
            }
          case 73: break;
          case 28: 
            { return new Symbol(sym.LiteralString);
            }
          case 74: break;
          case 29: 
            { return new Symbol(sym.OperadorMenorIgual);
            }
          case 75: break;
          case 30: 
            { return new Symbol(sym.OperadorMayorIgual);
            }
          case 76: break;
          case 31: 
            { return new Symbol(sym.OperadorAnd);
            }
          case 77: break;
          case 32: 
            { return new Symbol(sym.End);
            }
          case 78: break;
          case 33: 
            { return new Symbol(sym.OperadorNot);
            }
          case 79: break;
          case 34: 
            { return new Symbol(sym.Var);
            }
          case 80: break;
          case 35: 
            { return new Symbol(sym.Read);
            }
          case 81: break;
          case 36: 
            { return new Symbol(sym.LiteralBoolean);
            }
          case 82: break;
          case 37: 
            { return new Symbol(sym.Tipo);
            }
          case 83: break;
          case 38: 
            { return new Symbol(sym.TipoChar);
            }
          case 84: break;
          case 39: 
            { return new Symbol(sym.Array);
            }
          case 85: break;
          case 40: 
            { return new Symbol(sym.Write);
            }
          case 86: break;
          case 41: 
            { return new Symbol(sym.Begin);
            }
          case 87: break;
          case 42: 
            { return new Symbol(sym.TipoString);
            }
          case 88: break;
          case 43: 
            { return new Symbol(sym.Programa);
            }
          case 89: break;
          case 44: 
            { return new Symbol(sym.WriteLn);
            }
          case 90: break;
          case 45: 
            { return new Symbol(sym.TipoInteger);
            }
          case 91: break;
          case 46: 
            { return new Symbol(sym.TipoBoolean);
            }
          case 92: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
