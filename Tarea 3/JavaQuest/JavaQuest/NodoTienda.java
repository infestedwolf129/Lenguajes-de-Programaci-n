package JavaQuest;

import java.util.ArrayList;
import java.util.List;

public class NodoTienda extends Nodo{
    
    private List<Item> inventario;

    public List<Item> getInventario() {
        return inventario;
    }

    public void setInventario(List<Item> inventario) {
        this.inventario = inventario;
    }

    /**
    *   Constructor del nodo tienda que inicializa con una cantidad aleatoria de items.
    */

    public NodoTienda(){
       inventario = new ArrayList<Item>();
       int cantItems[] = {5,6,7,8};
       for(Integer i = 0; i < cantItems[(int)Math.random()*5];i++){
           Item Pocion = new Item();
           inventario.add(Pocion);
       }
    }
    
    public void comprar(Integer dinero){
    }

    /**
    *   Funcion que le muestra al Jugador el inventario de la tienda y su dinero actual. Permitiendole comprar cantidad X de items disponibles.
    *
    *   @param PJ: Parametro tipo Jugador. Entrega el Jugador creado por el usuario.
    */
    public void interactuar(Jugador PJ){
        System.out.println("Esto es lo que tenemos en venta actualmente:\n"+getInventario());
        System.out.println("Dinero actual: "+PJ.getDinero());
        System.out.println("Compraste: " );

    };
}
