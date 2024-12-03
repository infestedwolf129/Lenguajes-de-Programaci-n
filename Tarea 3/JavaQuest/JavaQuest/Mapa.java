package JavaQuest;

import java.util.SortedSet;

public class Mapa{
    private Integer profundidad;
    private NodoInicial nodo_inicial;
    private Nodo nodo_actual;

    public Mapa(Nodo nodo_actual, NodoInicial nodo_inicial) {
        Integer profundidad_random[] = {6,9,12};
        this.profundidad = profundidad_random[(int)Math.random()*3];
        this.nodo_inicial = nodo_inicial;
        this.nodo_actual = nodo_actual;
    }

    public Integer getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public NodoInicial getNodo_inicial() {
        return nodo_inicial;
    }

    public void setNodo_inicial(NodoInicial nodo_inicial) {
        this.nodo_inicial = nodo_inicial;
    }

    public Nodo getNodo_actual() {
        return nodo_actual;
    }

    public void setNodo_actual(Nodo nodo_actual) {
        this.nodo_actual = nodo_actual;
    }

    public void verMapa(){
        SortedSet<Edge> edges = GraphGenerator.Generar(5);
        for (Edge e : edges) {
            System.out.printf("(%d) -> (%d)\n", e.x, e.y);
        }
    };
    
    public void avanzar(){};

}