package clases;

public class Filosofo implements Runnable{
    private int id; // Identificador del filosofo
    private Palillo palilloIzquierdo; // Palillo izquierdo
    private Palillo palilloDerecho; // Palillo derecho
    private int contadorComida; // Cuantas veces ha comido el filosofo
    private boolean estaLleno = false; // Cuando el filosofo ya haya comido


    // Inicializamos los valores
    public Filosofo(int id, Palillo palilloIzquierdo, Palillo palilloDerecho){
        this.id = id; 
        this.palilloIzquierdo = palilloIzquierdo; 
        this.palilloDerecho = palilloDerecho; 
    }


    @Override
    public void run(){
        try {
            // Mientras estaLleno sea = false
            while(!estaLleno){
                pensar(); // Comienza pensando 
                if(palilloIzquierdo.tomar(this, Estado.IZQUIERDA)){ // Trata de tomar el palillo de la izquierda
                    if(palilloDerecho.tomar(this, Estado.DERECHA)){ // Trata de tomar el palillo de la derecha
                        comer(); // Comienza a comer 
                        palilloDerecho.dejar(this, Estado.DERECHA); // Baja el palillo de la derecha
                    }
                    palilloIzquierdo.dejar(this, Estado.IZQUIERDA); // Baja el palillo de la izquierda
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    // Estas funciones permitiran que el filosofo realice las acciones correspondientes (comer y pensar)
    
    private void pensar() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " esta pensando. ");
        Thread.sleep(((int)(Math.random() * 100)));
    }

    private void comer() throws InterruptedException{
        System.out.println(Thread.currentThread().getName() + " esta comiendo. ");
        contadorComida++;
        Thread.sleep(((int)(Math.random() * 100)));
    }



    // Asignamos el valor que sera contenido en estaLleno
    public void setLleno(boolean estaLleno){
        this.estaLleno = estaLleno;
    }

    // Cuantas veces ha comido el filosofo 
    public int getContador(){
        return this.contadorComida; 
    }

    // Identificamos el filosofo
    @Override
    public String toString(){
        return "Filosofo " + id;
    }

}
