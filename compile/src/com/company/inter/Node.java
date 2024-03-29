package com.company.inter;

import com.company.lexer.Lexer;

public class Node {
    int lexline=0;
    Node(){
        lexline= Lexer.line;
    }
    void error(String s){
        throw new Error("near line "+lexline+": "+s);
    }
    static int labels=0;
    public int newlabel(){
        return ++labels;
    }
    public void emitLabel(int i){
        System.out.println("L"+i+":");
    }
    public void emit(String s){
        System.out.println("\t"+s);
    }
}
