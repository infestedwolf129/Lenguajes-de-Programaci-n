package JavaQuest;

import java.util.List;
import java.util.LinkedList;

public abstract class  Nodo {
    private Integer id;
    private List<Nodo> siguientes_nodos;


    public List<Nodo> getSiguientes_nodos() {
        return siguientes_nodos;
    }
    public void setSiguientes_nodos(List<Nodo> siguientes_nodos) {
        this.siguientes_nodos = siguientes_nodos;
    }

    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    /**
    *   Constructor de un Nodo asignando su contenido en vacio para su correcta utilizacion.
    */
    
    public Nodo(){
        setId(null);
        siguientes_nodos = new LinkedList<Nodo>();
    }


    /**
    *   Funcion abstracta que es definida en las extenciones de Nodo.
    */
    public abstract void interactuar(Jugador PJ);

    /**
    *   Funcion que agrega un Nodo a la lista de nodos siguientes.
    *
    *   @param nodo_actual: Paramtro tipo Nodo el cual es agregado a la lista de nodos siguientes.
    *
    */
    public void agregarNodo(Nodo nodo_actual){
        List<Nodo> siguientes_nodos = getSiguientes_nodos();
        siguientes_nodos.add(nodo_actual);
        setSiguientes_nodos(siguientes_nodos);
    }
}
