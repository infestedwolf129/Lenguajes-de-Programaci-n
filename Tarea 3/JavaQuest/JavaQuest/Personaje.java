package JavaQuest;

public class Personaje{
    private String nombre;
    private Integer dinero;
    private Integer hp_actual;
    private Integer hp_total;
    private Integer danio;
    private Integer defensa;
    private String[] Nombres = {"Esbirro","Golem","Duende","Mago"};
    private Integer[] Dineros = {500,1000,2000,4000};
    private Integer[] HP = {3,7,11,15};
    private Integer[] Danio = {2,4,6};
    private Integer[] Defensa = {1,2,3};

    /**
    *   Funcion que retorna un string correspondiente a un nombre aleatorio.
    *
    *   @return nombre: Retorna un parametro tipo String con un nombre aleatorio.
    */
    public String nombreRandom(){
        String nombre;
        nombre = Nombres[(int)(Math.random()*(int)Nombres.length)];
        return nombre;
    }

    /**
    *   Funcion que retorna un Integer correspondiente a un valor aleatorio.
    *
    *   @return dinero: Retorna un parametro tipo Integer con un valor aleatorio.
    */
    public Integer dineroRandom(){
        Integer dinero;
        dinero = Dineros[(int)(Math.random()*(int)Dineros.length)];
        return dinero;
    }

    /**
    *   Funcion que retorna un Integer correspondiente a un valor aleatorio.
    *
    *   @return hp: Retorna un parametro tipo Integer con un valor aleatorio.
    */    
    public Integer hpRandom(){
        Integer hp;
        hp = HP[(int)(Math.random()*(int)HP.length)];
        return hp;
    }

    /**
    *   Funcion que retorna un Integer correspondiente a un valor aleatorio.
    *
    *   @return danio: Retorna un parametro tipo Integer con un valor aleatorio.
    */
    public Integer danioRandom(){
        Integer danio;
        danio = Danio[(int)(Math.random()*(int)Danio.length)];
        return danio;
    }

    /**
    *   Funcion que retorna un Integer correspondiente a un valor aleatorio.
    *
    *   @return dinero: Retorna un parametro tipo Integer con un valor aleatorio.
    */
    public Integer defensaRandom(){
        Integer defensa;
        defensa = Defensa[(int)(Math.random()*(int)Defensa.length)];
        return defensa;
    }

    /**
    *   Funcion que retorna un Integer correspondiente a un valor aleatorio.
    *
    *   @param danio: Integer que corresponde al daño actual del personaje.
    *
    *   @return danio: Retorna un parametro tipo Integer con un valor aleatorio.
    */
    public Integer danioMenorDefensa(Integer danio){
        danio = (int)Math.random()*4;
        return danio;
    }

    /**
    *   Funcion que retorna un String correspondiente a un ataque aleatorio.
    *
    *   @param enemigo: Parametro tipo Personaje que corresponde al enemigo generado.
    *
    *   @return ataqueRandom: Parametro tipo String con un ataque aleatorio dependiendo del nombre del Personaje.
    */
    public String ataquesRandomEnemy(Personaje enemigo){
        String ataqueRandom;
        if(enemigo.getNombre() == "Esbirro"){
            String[] ataquesRandom = {"Escupo de Fuego","Rocio de acido", "Arañazo Penetrante"};
            ataqueRandom = ataquesRandom[(int)(Math.random()*(int)ataquesRandom.length)];    
        }
        else if(enemigo.getNombre() == "Golem"){
            String[] ataquesRandom = {"Puño roca","Terremoto", "Grito retumbante"};
            ataqueRandom = ataquesRandom[(int)(Math.random()*(int)ataquesRandom.length)]; 
        }
        else if(enemigo.getNombre() == "Duende"){
            String[] ataquesRandom = {"Apuñalada","Ataque de Lanza","Bomba Incendiaria"};
            ataqueRandom = ataquesRandom[(int)(Math.random()*(int)ataquesRandom.length)]; 
        }
        else{
            String[] ataquesRandom = {"Sello de oscuridad","Mano divina", "Espectrum Patronus"};
            ataqueRandom = ataquesRandom[(int)(Math.random()*(int)ataquesRandom.length)]; 
        }
        
        return ataqueRandom;
    }

    /**
    *   Constructor que inicia un enemigo con estadisticas randomizadas.
    */
    public Personaje(){
        nombre = nombreRandom();
        dinero = dineroRandom();
        hp_total = hpRandom();
        hp_actual = hp_total;
        danio = danioRandom();
        defensa = defensaRandom();
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getDinero() {
        return dinero;
    }
    public void setDinero(Integer dinero) {
        this.dinero = dinero;
    }
    public Integer getHp_actual() {
        return hp_actual;
    }
    public void setHp_actual(Integer hp_actual) {
        this.hp_actual = hp_actual;
    }
    public Integer getHp_total() {
        return hp_total;
    }
    public void setHp_total(Integer hp_total) {
        this.hp_total = hp_total;
    }
    public Integer getDanio() {
        return danio;
    }
    public void setDanio(Integer danio) {
        this.danio = danio;
    }
    public Integer getDefensa() {
        return defensa;
    }
    public void setDefensa(Integer defensa) {
        this.defensa = defensa;
    }


    /**
    *   Funcion que efectua un combate entre Jugador y un parametro Personaje. Afectando las estadisticas de vida de ambos.
    *
    *   @param enemigo: Parametro tipo Personaje. Enemigo contra el que se efectua el combate.
    */
    public void combate(Personaje enemigo){
        //"Moneda" que decide quien empieza el combate.
        int starter = (int)(Math.random()*2);

        //Ajuste de daño del enemigo en base a la defensa del Jugador.
        int danioEnemy;
        if (this.defensa>=enemigo.getDanio()){
            danioEnemy=danioMenorDefensa(enemigo.getDanio());
        }
        else{
            danioEnemy = enemigo.getDanio() - this.defensa;
        }
        
        //Ajuste de daño del Jugador en base a la defensa del Enemigo.
        int danioYo;
        if (enemigo.getDefensa()>=this.getDanio()){
            danioYo=enemigo.getDefensa() - danioMenorDefensa(this.getDanio());
        }
        else{
            danioYo = this.getDanio() - enemigo.defensa;
        }
        
        //Informamos la vida del enemigo al inicio del combate.
        System.out.println("HP inicial "+enemigo.getNombre() + ": " + enemigo.getHp_actual()+ "/" + enemigo.getHp_total());

        //CASO 1: ATACA JUGADOR PRIMERO
        if (starter == 0){
            
            System.out.println(this.nombre +" ataca primero");
            while(this.hp_actual > 0 && enemigo.getHp_actual()>0){
                System.out.println("\n");
                System.out.println(this.nombre+" ataca");
                enemigo.setHp_actual(enemigo.getHp_actual() - danioYo);
                System.out.println("HP "+enemigo.getNombre()+" tras ataque: " + enemigo.getHp_actual()+ "/" + enemigo.getHp_total());
                
                if (enemigo.getHp_actual()>0){
                    System.out.println("\n");
                    System.out.println(enemigo.getNombre()+" ataca");
                    this.setHp_actual(this.hp_actual - danioEnemy);
                    System.out.println("HP de "+ this.nombre +" tras recibir "+ataquesRandomEnemy(enemigo)+": " + this.getHp_actual()+ "/" + this.getHp_total());      
                    if(this.getHp_actual() <= 0){
                        System.out.println("Moriste\n\nFIN DEL JUEGO");
                        this.setHp_actual(0);
                        System.exit(0);  
                    }
                }
                else{
                    enemigo.setHp_actual(0);
                    System.out.println("Felicidades, ganaste\n");
                    System.out.println("Conseguiste un botin de "+enemigo.getDinero()+" monedas de oro\n");
                    this.setDinero(this.getDinero()+enemigo.getDinero());
                }          
            }
            
        }

        //CASO 2: ATACA JUGADOR SEGUNDO
        else{
            System.out.println(this.nombre+" ataca segundo");
            while(enemigo.getHp_actual() > 0 && this.hp_actual>0){
                System.out.println("\n");
                System.out.println(enemigo.getNombre()+" ataca");
                this.setHp_actual(this.getHp_actual() - danioEnemy );

                System.out.println("HP de "+this.nombre+" tras recibir "+ataquesRandomEnemy(enemigo)+": " + this.getHp_actual()+ "/" + this.getHp_total());
                if (this.getHp_actual()>0){
                    System.out.println("\n");
                    System.out.println(this.nombre+" ataca");
                    enemigo.setHp_actual(enemigo.hp_actual - danioYo);
                    System.out.println("HP "+ enemigo.getNombre() +" tras ataque:" + enemigo.getHp_actual()+ "/" + enemigo.getHp_total());
                    if(enemigo.getHp_actual() <= 0){
                        enemigo.setHp_actual(0);
                        System.out.println("Felicidades, ganaste\n");
                        System.out.println("Conseguiste un botin de "+enemigo.getDinero()+" monedas de oro\n");
                        this.setDinero(this.getDinero()+enemigo.getDinero());
                        
                    };  
                }
                else{
                    this.setHp_actual(0);
                    System.out.println("Moriste\n\nFIN DEL JUEGO");
                    System.exit(0);                    
                }

            }       
        }
    }
    
}
