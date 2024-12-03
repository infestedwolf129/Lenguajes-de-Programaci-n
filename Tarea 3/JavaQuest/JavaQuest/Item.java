package JavaQuest;

import java.util.concurrent.ThreadLocalRandom;

public class Item{
    private String item;
    private Integer precio;
    private Integer recuperar_hp;
    private Integer aumentar_hp;
    private Integer aumentar_danio;
    private Integer aumentar_defensa;
    private String[] items = {"Pocion de salud","Pocion de defensa","Pocion de daño","Pocion de aumento"};

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getRecuperar_hp() {
        return recuperar_hp;
    }

    public void setRecuperar_hp(Integer recuperar_hp) {
        this.recuperar_hp = recuperar_hp;
    }

    public Integer getAumentar_hp() {
        return aumentar_hp;
    }

    public void setAumentar_hp(Integer aumentar_hp) {
        this.aumentar_hp = aumentar_hp;
    }

    public Integer getAumentar_danio() {
        return aumentar_danio;
    }

    public void setAumentar_danio(Integer aumentar_danio) {
        this.aumentar_danio = aumentar_danio;
    }

    public Integer getAumentar_defensa() {
        return aumentar_defensa;
    }
    
    public void setAumentar_defensa(Integer aumentar_defensa) {
        this.aumentar_defensa = aumentar_defensa;
    }


    /**
    *   Funcion que elige un nombre aleatorio de una lista de Strings.
    *   
    *   @return item: Retorna un string con el nombre aleatorio generado para el item a crear.
    */
    public String itemRandom(){
        String item;
        item = items[(int)ThreadLocalRandom.current().nextInt(0, 4)];
        return item;
    }

    /**
    *   Constructor que inicia un item con nombre aleatorio y dependiendo de este nombre se asignan estadisticas al item.
    *
    */
    public Item(){
        this.item = itemRandom();
        System.out.println(item);

        if (item == "Pocion de salud"){
            setPrecio(500);
            setRecuperar_hp(5);
        }

        if (item == "Pocion de defensa"){
            setPrecio(1000);
            setAumentar_defensa(2);
        }

        if (item == "Pocion de daño"){
            setPrecio(1000);
            setAumentar_danio(3);
        }

        if (item == "Pocion de aumento"){
            setPrecio(2000);
            setRecuperar_hp(3);
        }
    }

    /**
    *   Funcion que es llamada por un Item y aplica al Jugador un aumento de estadisticas o recuperacion de estas informandole al usuario todos los cambios.
    *
    *   @param PJ: Parametro tipo Jugador que entrega a la funcion el Jugador creado por el usuario.
    *
    */
    public void aplicar(Jugador PJ){
        //INFORME AL USUARIO SOBRE ITEM QUE UTILIZO
        String nombreItem = this.getItem();
        System.out.println("Has usado: "+nombreItem+"\n");

        
        if (nombreItem== "Pocion de salud"){
            //CAMBIO EN LAS ESTADISTICAS
            if(PJ.getHp_actual()  != PJ.getHp_total()){
                Integer salud = (int)PJ.getHp_actual()+this.getRecuperar_hp();
                if(salud > PJ.getHp_total()){
                    PJ.setHp_actual(PJ.getHp_total());
                }
                else{
                    PJ.setHp_actual(salud);
                }

            //INFORME AL USUARIO DE ESTADISTICAS CAMBIADAS
                System.out.println(PJ.getNombre()+" ha recuperado " + this.getRecuperar_hp()+" puntos de hp"); 
                PJ.setItems_aplicados("Pocion de salud");
            }
            else{
                //INFORME AL USUARIO DE ESTADISTICA AL MAXIMO
                System.out.println("Tu hp esta al máximo");
            }
            System.out.println("HP ACTUAL DE "+PJ.getNombre()+":"+PJ.getHp_actual()+"/"+PJ.getHp_total());
            
        }
        
        else if(nombreItem == "Pocion de defensa"){
            //CAMBIO EN LAS ESTADISTICAS
            Integer defensa =(int)PJ.getDefensa()+ this.getAumentar_defensa();
            PJ.setDefensa(defensa);

            //INFORME AL USUARIO DE ESTADISTICAS CAMBIADAS
            System.out.println(PJ.getNombre()+" ha aumentado su defensa en "+ this.getAumentar_defensa()+" puntos.");
            System.out.println("DEFENSA ACTUALIZADA DE "+ PJ.getNombre() + ":" + PJ.getDefensa());
            PJ.setItems_aplicados("Pocion de defensa");
        }
        
        else if(nombreItem == "Pocion de daño"){
            //CAMBIO EN LAS ESTADISTICAS
            Integer danio =(int)PJ.getDanio()+ this.getAumentar_danio();
            PJ.setDanio(danio);

            //INFORME AL USUARIO DE ESTADISTICAS CAMBIADAS
            System.out.println(PJ.getNombre()+" ha aumentado su daño en "+ this.getAumentar_danio()+" puntos.");
            System.out.println("DAÑO ACTUALIZADO DE "+ PJ.getNombre() + ":" + PJ.getDanio());
            PJ.setItems_aplicados("Pocion de daño");
        }
        
        else if(nombreItem == "Pocion de aumento"){
            //CAMBIO EN LAS ESTADISTICAS
            Integer aumento = (int)PJ.getHp_total()+this.getAumentar_hp();
            PJ.setHp_total(aumento);
            
            //INFORME AL USUARIO DE ESTADISTICAS CAMBIADAS
            System.out.println(PJ.getNombre()+" ha aumentado su hp total en "+ this.getAumentar_hp()+" puntos.");
            System.out.println("HP TOTAL ACTUALIZADO DE "+ PJ.getNombre() + ":" + PJ.getHp_total());
            PJ.setItems_aplicados("Pocion de aumento");

        }
    }
}
