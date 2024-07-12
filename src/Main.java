

import Service.ConsumoAPI;
import Service.ConvierteDatos;

import java.io.IOException;

import java.util.Scanner;

public class Main {
    static final String apiKey= "971f0df7f49e0be605c02c0b";
    private static Scanner sc= new Scanner(System.in);
    private static ConsumoAPI consumoAPI= new ConsumoAPI();
    private static ConvierteDatos conversor= new ConvierteDatos();
    private static final String URL_Base= "https://v6.exchangerate-api.com/v6/";

    public static void main(String[] args) throws IOException, InterruptedException {

        menu();


    }

    private static void menu() throws IOException, InterruptedException {
        var option= -1;
        while (option!=7){
            var menu= """
                    ****************************************
                    Sea bienvenido/a al Conversor de Moneda
                    
                    1) Dolar Americano -> Peso Argentino
                    2) Peso Argentino -> Dolar Americano
                    3) Dolar Americano -> Real Brasilero
                    4) Real Brasilero -> Dolar Americano
                    5) Dolar Americano -> Peso Colombiano
                    6) Peso Colombiano -> Dolar Americano
                    7) Salir
                    Elija una opcion válida
                    ****************************************
                    """;
            System.out.println(menu);
            option= sc.nextInt();

            switch (option){
                case 1: convertirDolarAPesoArgentino();
                break;
                case 2: convertirPesoArgentinoADolar();
                    break;
                case 3: convertirDolarARealBrasilero();
                    break;
                case 4: convertirRealBrasileroADolar();
                    break;
                case 5: convertirDolarAPesoColombiano();
                    break;
                case 6: convertirPesoColombianoADolar();
                    break;
                case 7:
                    System.out.println("Cerrando la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion Inválida");

            }
        }

    }

    private static void convertirPesoColombianoADolar() throws IOException, InterruptedException {
        Moneda moneda= getDatos("COP", "USD");
        System.out.println("El cambio actual esta a "+moneda.getConversion_rate()+" de " +moneda.getTarget_code()+" por Peso Colombiano");
        System.out.println("El valor ingresado en pesos, corresponde al valor final de "+moneda.getConversion_result()+" "+moneda.getTarget_code());

    }

    private static void convertirDolarAPesoColombiano() throws IOException, InterruptedException {
        Moneda moneda= getDatos("USD", "COP");
        System.out.println("El cambio actual esta a "+moneda.getConversion_rate()+moneda.getTarget_code()+" por dolar");
        System.out.println("El valor ingresado en dolares, corresponde al valor final de "+moneda.getConversion_result() +" "+moneda.getTarget_code());


    }

    private static void convertirRealBrasileroADolar() throws IOException, InterruptedException {
        Moneda moneda= getDatos("BRL", "USD");
        System.out.println("El cambio actual esta a "+moneda.getConversion_rate()+" de " +moneda.getTarget_code()+" por Reales Brasileros");
        System.out.println("El valor ingresado en reales, corresponde al valor final de "+moneda.getConversion_result()+" "+moneda.getTarget_code());

    }

    private static void convertirDolarARealBrasilero() throws IOException, InterruptedException {
        Moneda moneda= getDatos("USD", "BRL");
        System.out.println("El cambio actual esta a "+moneda.getConversion_rate()+moneda.getTarget_code()+" por dolar");
        System.out.println("El valor ingresado en dolares, corresponde al valor final de "+moneda.getConversion_result()+" "+moneda.getTarget_code());
    }

    private static void convertirPesoArgentinoADolar() throws IOException, InterruptedException {
        Moneda moneda= getDatos("ARS", "USD");
        System.out.println("El cambio actual esta a "+moneda.getConversion_rate()+" de " +moneda.getTarget_code()+" por Peso Argentino");
        System.out.println("El valor ingresado en pesos, corresponde al valor final de "+moneda.getConversion_result()+" "+moneda.getTarget_code());
    }

    private static void convertirDolarAPesoArgentino() throws IOException, InterruptedException {
        Moneda moneda= getDatos("USD", "ARS");
        System.out.println("El cambio actual esta a "+moneda.getConversion_rate()+moneda.getTarget_code()+" por dolar");
        System.out.println("El valor ingresado en dolares, corresponde al valor final de "+moneda.getConversion_result()+" "+moneda.getTarget_code());

    }


    private static Moneda getDatos(String base, String destino) throws IOException, InterruptedException {
        System.out.println("Ingrese la cantidad de dinero que quiera convertir");
        var mount= sc.nextDouble();
        var json= consumoAPI.ObtenerDatos(URL_Base+apiKey+"/pair/"+base+"/"+destino+"/"+mount);
        var datos= conversor.obtenerDatos(json, Moneda.class);
        return datos;
    }
}