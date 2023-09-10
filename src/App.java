import java.math.BigInteger;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        BigInteger numPrevio;
        BigInteger numActual;
        
        Procesador procesador = new Procesador();

        Scanner input = new Scanner(System.in);
        int nSemilla;

        System.out.print("Longitud semilla: ");
        nSemilla = Integer.parseInt( input.nextLine());
        System.out.print("Primer numero");
        numPrevio = new BigInteger(input.nextLine());
        System.out.print("Segundo numero");
        numActual = new BigInteger(input.nextLine());

        try {

            procesador.obtenerNumerosAleatorios(numPrevio, numActual, nSemilla);
            
        } catch (Exception e) {
            input.close();
            System.out.println(e);
        }
        
    }
}

class Procesador{

    String numerosGenerados = "";
    String numerosYGenerados = "";

    public void obtenerNumerosAleatorios( BigInteger numPrev, BigInteger numActual, int nSemilla){
        BigInteger mult, aux;
        int rep;

        System.out.println("X0:" + numPrev + "\nX1:" + numActual);
        
        for ( rep = 0; rep < 8; rep++) {
            mult = multiplicarNums(numPrev, numActual);

            aux = obtenerCentro(mult, nSemilla);
            numPrev = numActual;
            numActual = aux;

        }

        mostrarNumeros();
    }


    private BigInteger multiplicarNums( BigInteger num1, BigInteger num2){
        return num1.multiply(num2);
    }

    private BigInteger obtenerCentro(BigInteger num, int nSemilla){
        BigInteger centro;
        String numInString = num.toString();
        
        centro = new BigInteger( nSemilla % 2 == 0
                ? procesarSemillaPar(numInString, nSemilla)
                : procesarSemillaInPar(numInString, nSemilla));

        
        return centro;
    }

    private String procesarSemillaPar(String num, int nSemilla){
        Boolean seEncontroCentro = false;
        String centro = num;

        while (!seEncontroCentro) {
            if( num.length() % 2 == 0){
                centro = econtrarCentro(num, nSemilla);
                seEncontroCentro = true;
            }
            else{
                num = concatenarCeros(num);
            }
        }

        return centro;
    }

    private String procesarSemillaInPar( String num, int nSemilla ){
        
        Boolean seEncontroCentro = false;
        String centro = num;

        while (!seEncontroCentro) {
            if( num.length() % 2 != 0){
                centro = econtrarCentro(num, nSemilla);
                seEncontroCentro = true;
            }
            else{
                num = concatenarCeros(num);
            }
        }

        return centro;
    }

    private String econtrarCentro(String num, int nSemilla){

        boolean seTerminoProceso = false;
        String auxCentro = num;


        int posIni = 0, posFinal = num.length();

        while (!seTerminoProceso && posIni < posFinal) {
            
            if( auxCentro.length() == nSemilla ) {
                seTerminoProceso = true;
            }
            else{
                auxCentro = num.substring(posIni, posFinal);
                posIni++;
                posFinal--;
            }
        }

        numerosYGenerados += num + ",";
        numerosGenerados += auxCentro + ",";

        return auxCentro;
    }

    private String concatenarCeros( String num ){
        return "0" + num;
    }

    private void mostrarNumeros( ){
        
        cortarCadenasNumeros();
        String[] numerosY = numerosYGenerados.split(",");
        String[] numerosRandom = numerosGenerados.split(",");
        
        
        int index, index2;

        System.out.println("Numeros generados");
        System.out.println("-----------------------------------------------");

        for ( index = 0, index2 = 2; index < numerosRandom.length; index++, index2++) {
            System.out.println("Y"+index+":"+numerosY[index] + "           " + "X"+index2+":" + numerosRandom[index] +"           "+"R"+(index+1)+":."+numerosRandom[index]);
        }
    }

    private void cortarCadenasNumeros(){
        numerosGenerados.substring(0, numerosGenerados.length() - 1);
        numerosYGenerados.substring(0, numerosYGenerados.length() - 1);
    }
}