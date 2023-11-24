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
        Grafos.g.removeVertex(mundo.getMundo().get(3));
        Grafos.g.removeVertex(mundo.getMundo().get(4));
        Grafos.g.removeVertex(mundo.getMundo().get(0));
        Grafos.g.removeEdge(mundo.getMundo().get(5), mundo.getMundo().get(1));
        mundo.grafo.findShortestPath(mundo.getMundo().get(5), mundo.getMundo().get(1));
        Grafos.generateImage();
        System.out.println(mundo.grafo.isConnected());
    }
    
    
        
}
