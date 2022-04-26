
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import clases.Filosofo;
import clases.Palillo;

public class App {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = null; // Instanciasmos un objeto de la clase ExecutorService que usaremos despues
        Filosofo[] filosofos = null;  // Comenzamos con un arreglo de filosofos que rellenaremos despues
        // Declaramos dos constantes con el numero de filosofos y pallillos
        final int numeroFilosofos = 5;
        final int numeroPalillos = 5;  
        
        try{
            // Asignamos el numero de filosofos y el numero de palillos
            filosofos = new Filosofo[numeroFilosofos];
            Palillo[] palillos = new Palillo[numeroPalillos]; 
            
            for(int i = 0; i<numeroPalillos; i++){
                palillos[i] = new Palillo(i); // Creamos los palillos con ayuda de la clase palillo
            }
            executorService = Executors.newFixedThreadPool(numeroFilosofos); // Creamos una pool de con 5 Threads

            for(int i = 0; i < numeroFilosofos; i++){
                filosofos[i] = new Filosofo(i, palillos[i], palillos[(i+1)%numeroPalillos]); // Creamos los filosofos instanciando de la clase filosofo
                System.out.println("Izquierdo: " + palillos[i] + " Derecho: " + palillos[(i+1)%numeroPalillos]); // Palillo izquierdo, palillo derecho es el siguiente modulo 5
                executorService.execute(filosofos[i]); // Ejecutamos cada uno de los filosofos creados
            }

            // Simulamos un tiempo de ejecucion
            Thread.sleep(5000);

            /* Una vez que se ha terminado el tiempo de ejecucion, asignamos el valor de estaLleno = true
            simulando que los filosofos han comido. */ 
            for(Filosofo pFilosofo : filosofos){
                pFilosofo.setLleno(true);
            }

        }finally{
            // Apagamos el executorService para que no pueda seguir escuchando nuevas tareas
            executorService.shutdown();

            /* isTerminated() nunca arroja un true a menos de que se haya detenido el executorService
            antes de que se haya invocado este, solamente indica si todas las tareas se han terminado
            hasta este punto, si no se han terminado solamente hacemos una espera de 1s */
            while(!executorService.isTerminated()){
                Thread.sleep(1000);
            }

            // Imprimimos las veces que cada filosofo comio
            for(Filosofo pFilosofo: filosofos){
                System.out.println(pFilosofo + " comio: " + pFilosofo.getContador() + " veces. ");
            }
        }
    }
}
