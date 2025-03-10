package ARTentrevista;

import java.util.Random;


// Buenos días. Esta es mi solución al problema propuesto. He realizado 3 posibles soluciones que ofrecen sus pros y contras. 
// Desconozco como de compleja es la generación de números aleatorios. Por ello trataré de minimizar el uso de esta función y consideraré 
// mejor aquella función que lo haga.



public class Entrevista {
     //Primero empezamos creando una funcion que simule un generador aleatorio de numeros entre 1 y 5 enteros. 
	//El ordenador no puede crear números verdaderamente aleatorios pero hacemos la suposición
   
    public static int generarNumero1a5() {
        Random rand = new Random();
        return rand.nextInt(5) + 1; // nextInt(5) genera un número entre 0 y 4, sumamos 1 para que sea entre 1 y 5
    }
    
    //Este primer método se basa en generar un lista del 1 al 7, que se reorganiza utilizando la función aleatoria. Luego haciendo uso 
    // de nuevo de la función aleatoria se selecciona un número de la lista. El resultado es aleatorio y mantiene la uniformidad ya que 
    // con la reorganización vemos que los numeros saldrán 1/7 de las veces. 
    // la lista en un principio es {1,2,3,4,5,6,7} si por ejemplo el primer resultado aleatorio da 3 pasará a ser{3,4,5,6,7,1,2}
    // y se selecciona aleatoriamente un número entre los primeros 5. 
    // Esta solución no requiere mucha computación y hace del generador de números aleatorios tan solo 2 veces. 

    public static int generarNumero1a7PrimerMetodo() {
        // Creamos una lista con los números del 1 al 7
        int[] lista = {1, 2, 3, 4, 5, 6, 7};

        // Reorganizamos la lista basándonos en el resultado de generarNumero1a5
        int desplazamiento = generarNumero1a5() - 1; // Convertimos el número (1-5) a un índice (0-4)
        int[] listaReorganizada = new int[7];
        for (int i = 0; i < 7; i++) {
            listaReorganizada[i] = lista[(desplazamiento + i) % 7];
        }

        // Seleccionamos un elemento de la lista reorganizada usando generarNumero1a5
        int indice = generarNumero1a5() - 1; // Convertimos el número (1-5) a un índice (0-4)
        return listaReorganizada[indice];
    }
    
    
    //El siguiente método es parecido al anterior. Generamos una lista como antes, solo que ahora seleccionamos un número de la nueva lista
    // con la función de java random. El resultado es aleatorio ya que el orden de la lista lo es. El resultado solo utiliza la función
    // de generación de números aleatorios 1 vez
    
    public static int generarNumero1a7SegundoMetodo() {
        // Creamos una lista con los números del 1 al 7
        int[] lista = {1, 2, 3, 4, 5, 6, 7};

        // Reorganizamos la lista basándonos en el resultado de generarNumero1a5
        int desplazamiento = generarNumero1a5() - 1; // Convertimos el número (1-5) a un índice (0-4)
        int[] listaReorganizada = new int[7];
        for (int i = 0; i < 7; i++) {
            listaReorganizada[i] = lista[(desplazamiento + i) % 7];
        }

        // Seleccionamos un número aleatorio de la lista reorganizada usando Random
        Random rand = new Random();
        int indice = rand.nextInt(7); // nextInt(7) genera un número entre 0 y 6
        return listaReorganizada[indice];
    }


    
    
    
    // Esta solución se basa en el método de VON Neumann y es fácil de encontrar en internet. No la he hecho yo, pero me imagino
    // que valdrá como solución. Se basa en generar dos numeros aleatorios x e y. Hacemos (X-1)*5 + y. 
    // Este resultado da como máximo 25. Como no es divisible por 7 tenemos en cuenta solo números menores a 21.
    // De este modo obtenemos 7 números aleatorios y uniformes. Utiliza la función de generación de aleatorios dos veces. 
    
    public static int generarNumero1a7TercerMetodo() {
        int num;
        do {
            // Generamos un número entre 1 y 25 (5 * 5) y luego lo escalamos a 1-7
            num = 5 * (generarNumero1a5() - 1) + generarNumero1a5();
        } while (num > 21); // Descartamos los números mayores a 21 para mantener la uniformidad

        return (num % 7) + 1; // Escalamos el resultado a 1-7
    }
    
    //De aquí en adelante no tiene que ver con lo exigido necesariamente. He diseñado estos metodos para comprobar que efectivamente 
   // los métodos generan cada uno de los numeros 1/7 de las veces. Las funciones estan comentadas. 
    
    public static void comprobarUniformidad() {
        // Número de experimentos
        int numExperimentos = 100;

        // Contadores para cada método
        int[] contadorPrimerMetodo = new int[7];
        int[] contadorSegundoMetodo = new int[7];
        int[] contadorTercerMetodo = new int[7];

        // Realizamos los experimentos
        for (int i = 0; i < numExperimentos; i++) {
            // Contamos las apariciones para el primer método
            int num1 = generarNumero1a7PrimerMetodo();
            contadorPrimerMetodo[num1 - 1]++;

            // Contamos las apariciones para el segundo método
            int num2 = generarNumero1a7SegundoMetodo();
            contadorSegundoMetodo[num2 - 1]++;

            // Contamos las apariciones para el tercer método
            int num3 = generarNumero1a7TercerMetodo();
            contadorTercerMetodo[num3 - 1]++;
        }

        // Mostramos los resultados
        System.out.println("Frecuencias para el Primer Método:");
        mostrarFrecuencias(contadorPrimerMetodo, numExperimentos);

        System.out.println("Frecuencias para el Segundo Método:");
        mostrarFrecuencias(contadorSegundoMetodo, numExperimentos);

        System.out.println("Frecuencias para el Tercer Método:");
        mostrarFrecuencias(contadorTercerMetodo, numExperimentos);
    }

    // Función auxiliar para mostrar las frecuencias
    public static void mostrarFrecuencias(int[] contador, int numExperimentos) {
        for (int i = 0; i < 7; i++) {
            double frecuencia = (double) contador[i] / numExperimentos * 100;
            System.out.printf("Número %d: %.2f%%\n", i + 1, frecuencia);
        }
        System.out.println();
    }
    
    
    
    
    
 // Método main (punto de entrada del programa)
    public static void main(String[] args) {
        // Prueba de la función generarNumero1a5
        System.out.println("Número aleatorio entre 1 y 5: " + generarNumero1a5());

        System.out.println("Número aleatorio entre 1 y 7 (Primer Método): " + generarNumero1a7PrimerMetodo());
        System.out.println("Número aleatorio entre 1 y 7 (Segundo Método): " + generarNumero1a7SegundoMetodo());
        System.out.println("Número aleatorio entre 1 y 7 (Tercer Método): " + generarNumero1a7TercerMetodo());
        comprobarUniformidad();
    }
}
