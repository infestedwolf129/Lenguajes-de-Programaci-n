package JavaQuest;

public class NodoInicial extends Nodo{
    /**
    *   Constructor nodo inicial.
    */
    public NodoInicial(){
        setId(0);
        setSiguientes_nodos(getSiguientes_nodos());
    }

    /**
    *   Funcion que muestra por pantalla una pequeña introduccion del juego y sus instrucciones.
    */
    public void interactuar(Jugador PJ){
        System.out.println("\tBienvenido a JavaQuest "+PJ.getNombre()+"\n");
        System.out.println("Hoy te enfrenteras a una ruta peligrosa hasta llegar a la cima de todo,este juego consta de un camino de tamaño N el cual se genera aleatoriamente donde deberas de enfretarte a 4 tipos de enemigos y un jefe final.\nDurante el camino puedes activar un evento de combate, o toparte con una tienda en la que recuperar tus fuerzas por un conveniente precio o escuchar alguna historia de un enemigo antes de luchar.\nDisfruta de este epico viaje.");
        System.out.println("INSTRUCCIONES:\n");
        System.out.println("Debes escojer en cada evento que haras ingresando un numero del 1 al 4 en cosola.\nEventualmente se vera textual que hace cada opción.\nAdemás durante todo el juego tienes acceso a un menu con el que puedes Ver el mapa, ver tus estadisticas, ver tus items o avanzar por el mapa.");

    };
}
