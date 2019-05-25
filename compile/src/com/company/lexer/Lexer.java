package com.company.lexer;
import java.io.IOException;
import java.util.Hashtable;
import com.company.symbols.*;

public class Lexer {
    public static int line=1;
    char peer=' ';
    Hashtable words=new Hashtable();
    void reserve(Word w){
        words.put(w.lexeme,w);
    }
    public Lexer(){
        reserve(new Word("if",Tag.IF));
        reserve(new Word("else",Tag.ELSE));
        reserve(new Word("while",Tag.WHILE));
        reserve(new Word("do",Tag.DO));
        reserve(new Word("break",Tag.BREAK));
        reserve(Word.True); reserve(Word.False);
        reserve(Type.Int);  reserve(Type.Char);
        reserve(Type.Bool); reserve(Type.Float);
    }
    void readch() throws IOException{
        peer=(char)System.in.read();
    }
    boolean readch(char c)throws IOException{
        readch();
        if (peer!=c){
            return false;
        }
        peer=' ';
        return true;
    }
    public Token scan()throws IOException{
        for (;;readch()){
            if (peer==' '||peer=='\t'){
                continue;
            }
            else if (peer=='\n'){
                line=line+1;
            }
            else break;
        }
        switch (peer){
            case '&':
                if (readch('&')) return Word.and;
                else return new Token('&');
            case '|':
                if (readch('|')) return Word.or;
                else return new Token('|');
            case  '=':
                if (readch('=')) return Word.eq;
                else return new Token('=');
            case '!':
                if (readch('=')) return Word.ne;
                else return new Token('=');
            case '<':
                if (readch('=')) return Word.le;
                else return new Token('<');
            case '>':
                if (readch('=')) return Word.ge;
                else return new Token('>');
        }
        if (Character.isDigit(peer)){
            int v=0;
            do{
                v=10*v+Character.digit(peer,10);
                readch();
            }while (Character.isDigit(peer));
            if (peer!='.'){
                return new Num(v);
            }
            float x=v; float d=10;
            for (;;){
                readch();
                if (!Character.isDigit(peer)) break;
                x=x+Character.digit(peer,10)/d;d=d*10;
            }
            return new Real(x);
        }
        if (Character.isLetter(peer)){
            StringBuffer b=new StringBuffer();
            do{
                b.append(peer);
                readch();
            }while (Character.isLetter(peer));
            String s=b.toString();
            Word w=(Word)words.get(s);
            if (w!=null){
                return w;
            }
            w=new Word(s,Tag.ID);
            words.put(s,w);
            return w;
        }
        Token tok=new Token(peer);
        peer=' ';
        return tok;
    }
}
