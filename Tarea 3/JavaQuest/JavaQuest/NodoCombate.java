package JavaQuest;

public class NodoCombate extends Nodo{
    private Personaje Enemigo;

    public Personaje getEnemigo() {
        return Enemigo;
    }

    public void setEnemigo(Personaje enemigo) {
        Enemigo = enemigo;
    }

    /**
    *   Constructor de clase que incializa el Nodo con un enemigo aleatorio.
    */
    public NodoCombate(){
        setEnemigo(new Personaje());
    }

    /**
    *   Funcion que inicia un combate entre el jugador y el enemigo de este nodo.
    *
    *   @param PJ: Parametro tipo Jugador. Entrega el Jugador creado por el usuario.
    *
    */
    public void interactuar(Jugador PJ){
        System.out.println("Comienza el combate contra el"+Enemigo.getNombre());
        PJ.combate(Enemigo);
    };


}
