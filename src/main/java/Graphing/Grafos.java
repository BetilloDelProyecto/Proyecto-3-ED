package Graphing;

import Ciudad.Ciudad;
import com.mxgraph.layout.*;
import com.mxgraph.model.mxCell;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxCellRenderer;
import com.mxgraph.util.mxConstants;
import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraManyToManyShortestPaths;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Ciudad.Conexion;


public class Grafos {
    public  static Graph<Ciudad, DefaultWeightedEdge> g = new SimpleDirectedWeightedGraph<Ciudad, DefaultWeightedEdge>(DefaultWeightedEdge.class);

    ArrayList<Ciudad> ciudades = new ArrayList<>();

    public Grafos(ArrayList<Ciudad> ciudades) throws IOException {
        this.ciudades = ciudades;
        //Se anhaden los vertices
        for(Ciudad ciudad: ciudades){
            addVertex(ciudad);
        }
        //Se anhaden las aristas
        for(Ciudad ciudad: ciudades){
            for(Conexion conexion: ciudad.getConexiones()){
                for(Ciudad ciudad2: ciudades) {
                    if (ciudad2.getNomCiudad().equals(conexion.getNomCiudad())) {
                        int peso = (conexion.getBienes() + conexion.getFuerzaMilitar() + conexion.getDistancia()) /3;
                        g.addEdge(ciudad, ciudad2);
                        g.setEdgeWeight(ciudad, ciudad2, peso);
                    }
                }
            }
        }
        generateImage();
    }
    private static void addVertex(Ciudad ciudad){
        g.addVertex(ciudad);
    }

    public static void generateImage() throws IOException {
        JGraphXAdapter<Ciudad, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter<>(g);
        mxGraphComponent component = new mxGraphComponent(graphAdapter);
        component.setConnectable(false);
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.setX0((double) (component.getGraphControl().getWidth() * 50));
        layout.setY0((double) (component.getGraphControl().getHeight() * 50));
        layout.setRadius(component.getGraphControl().getWidth() * 50);
        layout.execute(graphAdapter.getDefaultParent());

        JFrame frame = new JFrame();
        frame.getContentPane().add(component);
        frame.setSize(1200, 1200);
        frame.setVisible(true);
    }

public boolean isConnected(){
    ConnectivityInspector<Ciudad, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(g);
    return inspector.isConnected();
}
    public java.util.List<Ciudad> findCutVertices() {
        java.util.List<Ciudad> cutVertices = new ArrayList<>();
        List<Ciudad> vertices = new ArrayList<>(g.vertexSet());

        for (Ciudad vertex : vertices) {
            g.removeVertex(vertex);
            ConnectivityInspector<Ciudad, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(g);
            if (!inspector.isConnected()) {
                cutVertices.add(vertex);
            }
            g.addVertex(vertex);
        }
        return cutVertices;
    }

    public void findShortestPath(Ciudad start, Ciudad end) {
        DijkstraShortestPath<Ciudad, DefaultWeightedEdge> dijkstra2 = new DijkstraShortestPath<>(g);
        System.out.println("Shortest path from " + start + " to " + end + ":");
        System.out.println(dijkstra2.getPath(start, end));
    }

    public void bloquarExpansionMinima(){

    }





}



