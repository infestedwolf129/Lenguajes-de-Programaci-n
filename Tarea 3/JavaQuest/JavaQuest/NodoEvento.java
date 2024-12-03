package JavaQuest;

import java.util.InputMismatchException;
import java.util.Scanner;

public class NodoEvento extends Nodo{
    private String descripcion;
    private String alternativa1;
    private String alternativa2;
    private Item resultado1;
    private Item resultado2;

    /**
    *   Constructor que inicia un Evento en el que se hallan dos items aplicables al jugador.
    */
    public NodoEvento(){
        this.descripcion = "Has encontrado un cofre mistorioso, contiene 2 compartimientos pero solo hay 1 llave.\n¿Que compartimiento deseas abrir?";
        this.alternativa1 = "Abrir compartimiento de la izquierda";
        this.alternativa2 = "Abrir compartimiento de la derecha";
        this.resultado1 = new Item();
        this.resultado2 = new Item();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getAlternativa1() {
        return alternativa1;
    }

    public void setAlternativa1(String alternativa1) {
        this.alternativa1 = alternativa1;
    }

    public String getAlternativa2() {
        return alternativa2;
    }

    public void setAlternativa2(String alternativa2) {
        this.alternativa2 = alternativa2;
    }

    public Item getResultado1() {
        return resultado1;
    }

    public void setResultado1(Item resultado1) {
        this.resultado1 = resultado1;
    }

    public Item getResultado2() {
        return resultado2;
    }

    public void setResultado2(Item resultado2) {
        this.resultado2 = resultado2;
    }

    /**
    *   Funcion que despliega un menu por consola en el que usuario decide que pasara con los items encontrados.
    *
    *   @param PJ: Parametro tipo Jugador. Entrega el Jugador creado por el usuario.
    *
    */
    public void interactuar(Jugador PJ){
        System.out.println(descripcion);
        Scanner input = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
 
        while (!salir) {
 
            System.out.println("1."+alternativa1);
            System.out.println("2."+alternativa2);
 
            try {
 
                System.out.println("¿Que deseas hacer?");
                opcion = input.nextInt();
 
                switch (opcion) {
                    case 1:
                        System.out.println("Has decidido "+alternativa1);
                        resultado1.aplicar(PJ);
                        salir = true;
                    case 2:
                        System.out.println("Has decidido "+alternativa2);
                        resultado2.aplicar(PJ);
                        salir=true;
                    default:
                        System.out.println("Solo números entre 1 y 2");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                input.next();
            }
        }
    
        input.close();
    }
}
