package com.company.inter;

import com.company.lexer.Token;
import com.company.symbols.Type;

public class Op extends Expr {
    public Op(Token tok, Type p){
        super(tok,p);
    }
    public Expr reduce(){
        Expr x=gen();
        Temp t=new Temp(type);
        emit(t.toString()+ " = "+x.toString());
        return t;
    }
}
