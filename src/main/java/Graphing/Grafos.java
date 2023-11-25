package Graphing;

import Ciudad.Ciudad;
import com.mxgraph.layout.*;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxStylesheet;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.cycle.HierholzerEulerianCycle;
import org.jgrapht.alg.interfaces.EulerianCycleAlgorithm;
import org.jgrapht.alg.interfaces.SpanningTreeAlgorithm;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.spanning.PrimMinimumSpanningTree;
import org.jgrapht.ext.JGraphXAdapter;
import org.jgrapht.graph.*;
import org.jgrapht.alg.interfaces.EulerianCycleAlgorithm;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Ciudad.Conexion;


public class Grafos {
    public  static Graph<Ciudad, DefaultWeightedEdge> g = new SimpleWeightedGraph<Ciudad, DefaultWeightedEdge>(DefaultWeightedEdge.class);

    private static Graph<Ciudad, DefaultWeightedEdge> grafoPorBienes;

    public static boolean GraphDirected = false;
    ArrayList<Ciudad> ciudades ;

    public Grafos(ArrayList<Ciudad> ciudades) throws IOException {
        this.ciudades = ciudades;
        //Se anhaden los vertices
        for(Ciudad ciudad: ciudades){
            g.addVertex(ciudad);
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
    }

    //Metodo para llenar el grafo por bienes
    private void llenarGrafoPorBienes(){
        grafoPorBienes = new SimpleWeightedGraph<Ciudad, DefaultWeightedEdge>(DefaultWeightedEdge.class); //Se crea el grafo
        //Se anhaden los vertices del grafo original
        for(Ciudad vertex: g.vertexSet()){
            grafoPorBienes.addVertex(vertex);
        }
        //Se anhaden las aristas del grafo original con el peso de los bienes
        for(DefaultWeightedEdge edge: g.edgeSet()){
            Ciudad sourceVertex = g.getEdgeSource(edge);  //Se obtiene el vertice de origen
            Ciudad targetVertex = g.getEdgeTarget(edge); //Se obtiene el vertice de destino
            int peso = bienes(g.getEdgeTarget(edge), g.getEdgeSource(edge).getConexiones()); //Se obtiene el peso de los bienes
            DefaultWeightedEdge edgeTmp = grafoPorBienes.addEdge(sourceVertex, targetVertex); //Se crea la arista
            if(edgeTmp != null){
                grafoPorBienes.setEdgeWeight(edgeTmp, peso); //Se le asigna el peso a la arista
            }
        }
    }

    private int bienes(Ciudad ciudad, ArrayList<Conexion> conexion){
        for(Conexion conex: conexion){
            if(conex.getNomCiudad().equals(ciudad.getNomCiudad())){
                return conex.getBienes();
            }
        }
        return 0;
    }



    public static void generateImage() throws IOException {
        JGraphXAdapter<Ciudad, DefaultWeightedEdge> graphAdapter = new JGraphXAdapter<>(g){
        @Override
        public String convertValueToString(Object cell) {
            if (getModel().isEdge(cell)) {
                return "";  // return empty string for edges
            }
            return super.convertValueToString(cell);
        }
        };
        if(!GraphDirected){
            // Get the stylesheet of the graph
            mxStylesheet stylesheet = graphAdapter.getStylesheet();
            // Create a new style for the edges
            Map<String, Object> style = new HashMap<>();
            style.put(mxConstants.STYLE_ENDARROW, mxConstants.NONE);
            // Add the style to the stylesheet under the name "edgeStyle"
            stylesheet.putCellStyle("edgeStyle", style);
            // Apply the style to each edge
            for (DefaultWeightedEdge edge : g.edgeSet()) {
                graphAdapter.setCellStyle("edgeStyle", new Object[]{graphAdapter.getEdgeToCellMap().get(edge)});
            }
        }
        mxGraphComponent component = new mxGraphComponent(graphAdapter);
        component.setConnectable(false);
        mxCircleLayout layout = new mxCircleLayout(graphAdapter);
        layout.execute(graphAdapter.getDefaultParent());

        JFrame frame = new JFrame();
        frame.getContentPane().add(component);
        frame.setSize(1200, 1200);
        frame.setVisible(true);
    }


    //Metodo para verificar si el grafo es conexo
public boolean isConnected(){
    ConnectivityInspector<Ciudad, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(g);
    return inspector.isConnected();
}

    public void findShortestPath(Ciudad start, Ciudad end) {
        DijkstraShortestPath<Ciudad, DefaultWeightedEdge> dijkstra2 = new DijkstraShortestPath<>(g);
        System.out.println("Shortest path from " + start + " to " + end + ":");
        System.out.println(dijkstra2.getPath(start, end));
    }

    //ALGORITMO NUMERO 1
    public Ciudad ciudadMasConectada(){
        ArrayList<Ciudad> ciudades2 = new ArrayList<>();
        for(Ciudad c : g.vertexSet())
            ciudades2.add(c);
        for (int i = 0; i < ciudades2.size(); i++)
            for (int j = 0; j < ciudades2.size()-1; j++) {
                Ciudad ciudad1 = ciudades2.get(j);
                Ciudad ciudad2 = ciudades2.get(j+1);
                if(ciudad1.getConexiones().size() < ciudad2.getConexiones().size()){
                    ciudades2.set(j, ciudad2);
                    ciudades2.set(j+1, ciudad1);
                }

            }
        return ciudades2.get(0);
    }


    public void particionDelGrafo() throws IOException{
        while(isConnected()){
            g.removeVertex(ciudadMasConectada());
        }
        generateImage();
    }


    //Metodo para bloquear la expansion minima
    //ALGORITMO NUMERO 2
    public void bloquearExpansionMinima(){
            if(isConnected()){
                System.out.println("Grafo conectado");
                llenarGrafoPorBienes();
                System.out.println("Grafo por bienes lleno");
                PrimMinimumSpanningTree<Ciudad, DefaultWeightedEdge>  expansionMinima = new PrimMinimumSpanningTree<>(grafoPorBienes); // Se crea la expansion mínima
                SpanningTreeAlgorithm.SpanningTree<DefaultWeightedEdge> arbolExpansionMinima = expansionMinima.getSpanningTree(); //Se obtiene la expansion minima
                for(DefaultWeightedEdge edge: arbolExpansionMinima.getEdges()){
                    System.out.println(edge);
                    Ciudad sourceVertex = grafoPorBienes.getEdgeSource(edge);
                    Ciudad targetVertex = grafoPorBienes.getEdgeTarget(edge);
                    DefaultWeightedEdge edgeTmp = g.getEdge(sourceVertex, targetVertex);
                    if(edgeTmp != null){
                        g.removeEdge(edgeTmp);
                    }
                }
            }
            System.out.println("Expansion minima creada");
            }


    //Metodo para convertir el grafo no dirigido en dirigido
    //ALGORITMO NUMERO 3
    public void convertirGrafoNoDirigidoEnDirigido() {
        if (isConnected()) {
            Graph<Ciudad, DefaultWeightedEdge> grafoDirigidoTmp = new SimpleDirectedWeightedGraph<Ciudad, DefaultWeightedEdge>(DefaultWeightedEdge.class);
            for (Ciudad vertex : g.vertexSet()) {
                grafoDirigidoTmp.addVertex(vertex);
            }
            for (DefaultWeightedEdge edge : g.edgeSet()) {
                Ciudad sourceVertex = g.getEdgeSource(edge);
                Ciudad targetVertex = g.getEdgeTarget(edge);
                DefaultWeightedEdge edgeTmp = grafoDirigidoTmp.addEdge(sourceVertex, targetVertex);
                if (edgeTmp != null) {
                    grafoDirigidoTmp.setEdgeWeight(edgeTmp, g.getEdgeWeight(edge));
                }
            }
            System.out.println("Before setting: " + GraphDirected);
            GraphDirected = true;
            System.out.println("After setting: " + GraphDirected);
            g = grafoDirigidoTmp;
        } else {
            System.out.println("No se puede convertir el grafo en dirigido porque no es conexo");
            //FALTA TIRAR EL ERROR SI NO ES CONEXO
        }
    }

    //Metodo para encontrar el camino mas eficiente desde la ciudad mas poderosa
    //ALGORITMO NUMERO 4
    public void caminoMasPoderoso(){
        Ciudad ciudadMasPoderosa = null;
        boolean first = true;
        for(Ciudad vertex: g.vertexSet()){
            if(first){
                ciudadMasPoderosa = vertex;
                first = false;
            }
            else{
                int ponderadoTmp = vertex.getSoldados() + vertex.getMisilies();
                int ponderadoCiudadPoderosaTmp = ciudadMasPoderosa.getSoldados() + ciudadMasPoderosa.getMisilies();
                if(ponderadoTmp > ponderadoCiudadPoderosaTmp){
                    ciudadMasPoderosa = vertex;
                }
            }
        }
        convertirGrafoNoDirigidoEnDirigido();
        System.out.println("Ciudad mas poderosa: " + ciudadMasPoderosa);
        caminoMasEficiente(ciudadMasPoderosa);
        System.out.println("Camino mas eficiente desde la ciudad mas poderosa: ");
    }

    public void caminoMasEficiente(Ciudad target){
        DijkstraShortestPath<Ciudad, DefaultWeightedEdge> dijkstra = new DijkstraShortestPath<>(g);
        for (Ciudad vertex : g.vertexSet()) {
            if (!vertex.equals(target)) {
                System.out.println("Most efficient path from " + vertex + " to " + target + ":");
                var camino  = dijkstra.getPath(vertex, target);
                System.out.println(camino);
                if(camino != null){
                    for(DefaultWeightedEdge edge:  camino.getEdgeList()){
                        System.out.print("Peso: " + g.getEdgeWeight(edge) + " | ");
                        g.removeEdge(edge);
                    }
                    System.out.println();
                }
            }
        }
    }

    //Metodo para verificar si el grafo es un ciclo euleriano
    //ALGORITMO NUMERO 5
    public void esCicloEuleriano() {
        // Utilizar el algoritmo HierholzerEulerianCycle para verificar si el grafo es un ciclo euleriano
        HierholzerEulerianCycle<Ciudad, DefaultWeightedEdge> eulerianCycle = new HierholzerEulerianCycle<>();
        if(eulerianCycle.isEulerian(g)){
            System.out.println("El grafo es un ciclo euleriano");
            GraphPath<Ciudad,DefaultWeightedEdge> cycle = eulerianCycle.getEulerianCycle(g);
            if(cycle != null){
                System.out.println(cycle);
                System.out.println("Fulminado");
                for(DefaultWeightedEdge edge: cycle.getEdgeList()){
                    g.removeEdge(edge);
                }
        }
        }
        else{
            System.out.println("El grafo no es un ciclo euleriano");
        }
    }





}



