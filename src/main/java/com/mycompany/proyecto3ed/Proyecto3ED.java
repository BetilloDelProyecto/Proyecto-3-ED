package com.mycompany.proyecto3ed;

import Ciudad.*;
import Graphing.*;

import java.io.IOException;


public class Proyecto3ED {

    public static void main(String[] args) throws IOException {
        
        Mundo mundo = new Mundo();
        mundo.cargarMundo("C:\\Users\\X\\Desktop\\euler.json");
        //mundo.imprimir();
        System.out.println(mundo.grafo.isConnected());
        Grafos.generateImage();
        mundo.grafo.esCicloEuleriano();
    }
    
    
        
}
