package JavaQuest;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Jugador extends Personaje{
    
    private List<String> items_aplicados;
    private Scanner myObjScanner = new Scanner(System.in);

    public List<String> getItems_aplicados() {
        return items_aplicados;
    }

    /**
    *   Funcion que añade a una lista de items los que hayan sidos aplicados al jugador.
    *
    *   @param aplicar: Nombre del item a añadir a la lista de items.
    *
    */
    public void setItems_aplicados(String aplicar) {
        this.items_aplicados.add(aplicar);
    }

    /**
    *   Constructor del Jugador en el cual se establecen todas sus estadisticas base.
    */
    public Jugador(){
        System.out.println("Ingresa nickname");
        String nickname = myObjScanner.nextLine();
        System.out.println("\n");
        setNombre(nickname);
        setDinero(500);
        setDanio(5);
        setDefensa(1);
        setHp_total(20);
        setHp_actual(20);
        items_aplicados = new ArrayList<String>();
        
    };
    /**
    *   Funcion que imprime por pantalla las estadísticas actuales del jugador.
    */
    public void verEstados(){
        System.out.println("Nombre: "+ this.getNombre());
        System.out.println("Dinero: "+ this.getDinero());
        System.out.println("HP_actual: " + this.getHp_actual());
        System.out.println("HP_total: "+ this.getHp_total());
        System.out.println("Daño: " + this.getDanio());
        System.out.println("Defensa: " + this.getDefensa() );
        System.out.println("\n");
    };
    
    /**
    *   Funcion que imprime por pantalla los items aplicados en el jugador.
    */
    public void verItems(){
        System.out.println("ItemsAplicados : "+this.getItems_aplicados());
    };
};