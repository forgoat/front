package com.company.inter;

public class Seq extends Stmt {
    Stmt stmt1; Stmt stmt2;
    public Seq(Stmt st1,Stmt st2){
        stmt1=st1;
        stmt2=st2;
    }
    public void gen(int b,int a){
        if (stmt1==Stmt.Null)
            stmt2.gen(b,a);
        else if(stmt2==Stmt.Null)
            stmt1.gen(b, a);
        else{
            int label=newlabel();
            stmt1.gen(b,label);
            emitLabel(label);
            stmt2.gen(label,a);
        }
    }
}
