package com.company;

import java.util.List;

public class Usuario {

    String cpf;
    String nome;
    Double totalCompra;
    List<Produto> produtos;

    public  Double retornaTotalCompra(){
        Double x = 0.0;
        Double desconto = 0.10;
        Double acrescimo = 0.01;
        for(Produto p : this.produtos){
            x = x + p.precoTotal;
        }
        if(x > 100){
            Double d = x * desconto;
            x = x - d;
        }else{
            Double d = x * acrescimo;
            x = x + d;
        }
        return x;
    }
}
