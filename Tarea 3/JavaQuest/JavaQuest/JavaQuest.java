package JavaQuest;


import java.util.InputMismatchException;
import java.util.Scanner;

public class JavaQuest {
    public static void main(String[] args) {

        //INICIAMOS JUGADOR Y MAPA
        Jugador Player = new Jugador();
        NodoInicial inicio = new NodoInicial();
        inicio.interactuar(Player);
        Mapa map = new Mapa(inicio,inicio);

        //DATOS MENU
        Scanner input = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        
        //MENU QUE PUEDE VER EL JUGADOR EN CUALQUIER MOMENTO
        while (!salir) {
 
            System.out.println("1. Ver mapa");
            System.out.println("2. Ver estado");
            System.out.println("3. Ver items");
            System.out.println("4. Avanzar");
            System.out.println("5. Cerrar Juego (PERDERAS TODO EL PROGRESO DE ESTA PARTIDA)");
 
            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = input.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out.println("\n");
                        map.verMapa();
                        break;
                    case 2:
                        System.out.println("\n");
                        Player.verEstados();
                        break;
                    case 3:
                        System.out.println("\n");
                        Player.verItems();
                        break;
                    case 4:
                        System.out.println("\n");
                        System.out.println("EJECUTAR FUNCION AVANZAR");
                        break;
                    case 5:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                input.next();
            }
        }

        input.close();
    }
}


  