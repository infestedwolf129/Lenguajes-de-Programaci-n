package JavaQuest;

public class NodoJefe extends Nodo{
    
    private Personaje Jefe;

    /**
    *   Constructor que inicia con estadisticas base el Jefe del juego.
    */

    public NodoJefe(){
        Jefe.setNombre("Señor Oscuro");
        Jefe.setDanio(20);
        Jefe.setDefensa(5);
        Jefe.setDinero(100000);
        Jefe.setHp_total(30);
        Jefe.setHp_actual(30);

    };

    /**
    *   Funcion que inicia un combate entre el Jefe del juego y el jugador.
    *
    */
    public void interactuar(Jugador PJ){
        System.out.println("Un ente se ve aparecer a lo lejos\n\"Has llegado bastante lejos, pero eso acaba ahora\"\nJefe final: "+Jefe.getNombre());
        PJ.combate(Jefe);
        if (Jefe.getHp_actual() <= 0){
            System.out.println("Felicidades, derrotaste a "+Jefe.getNombre());
        }
        else if (PJ.getHp_actual() <= 0){
            System.out.println(Jefe.getNombre()+" te ha arrasado con su poder.");
        }
    };
}
