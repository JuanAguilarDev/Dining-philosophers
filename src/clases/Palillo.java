package clases;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Palillo {
    private int id;  // id del palillo
    private Lock lock; // Esta es una herramienta que nos ayudara a controlar el acceso a recursoscompartidos

    // Inicializamos los valores
    public Palillo(int id){
        this.id = id; 
        this.lock = new ReentrantLock(); 
    }

    // Funcion para tomar el palillo
    public boolean tomar(Filosofo filosofo, Estado estado) throws InterruptedException{
        if(lock.tryLock(10, TimeUnit.MILLISECONDS)){ // Tratamos de obtener el lock del palillo indicado
            // Tomamamos acceso al palillo y regresamos true como bandera
            System.out.println(filosofo + " ha tomado el palillo de su " + estado.toString() + " #" + this);
            return true;
        }
        // De lo contrario, regresamos false como bandera
        return false; 
    }

    // Funcion para dejar el palillo
    public void dejar(Filosofo filosofo, Estado estado){
        lock.unlock(); // Liberamos el recurso, en este caso el palillo
        System.out.println(filosofo + " ha bajado el palillo de su " + estado.toString() + " #" + this);
    }

    // Hacemos un override de la funcion toString para recibir el estado del palillo junto con el id
    @Override
    public String toString() {
        return " " + id; 
    }
}
