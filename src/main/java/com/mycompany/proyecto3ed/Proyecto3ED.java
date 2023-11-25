package com.mycompany.proyecto3ed;

import Ciudad.Mundo;
import Graphing.Grafos;

import java.io.IOException;


public class Proyecto3ED {

    public static void main(String[] args) throws IOException {
        
        Mundo mundo = new Mundo();
        mundo.cargarMundo();
        mundo.imprimir();
        System.out.println(mundo.grafo.isConnected());
        Grafos.generateImage();
        mundo.grafo.esCicloEuleriano();
        Grafos.generateImage();
    }
    
    
        
}
