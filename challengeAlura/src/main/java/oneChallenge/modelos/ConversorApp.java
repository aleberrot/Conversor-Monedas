package modelos;

import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class ConversorApp{

  public static void doConversion(){

    String apiKey = System.getenv("EXCHANGE_API_KEY");
    ApiClient api = new ApiClient(apiKey);
    api.setCurrency("USD");
    //Exchange exchange = api.request();
    //System.out.println(exchange);
    Scanner scanner = new Scanner(System.in);
    Exchange exchangeDolar = api.request();
    Map<String, Double> conversionRatesDolar = exchangeDolar.getConversionRates();

    int userOption = 0;

    while (userOption != 9){
      mostrarMenu();
      userOption = scanner.nextInt();
      clearScreen();
      scanner.nextLine();
      switch (userOption) {
        case 1: // Dólar ==>> Peso Argentino (USD -> ARS)
          double valorDolarPesoArgentino = conversionRatesDolar.get("ARS");
          
          System.out.println("Ingrese la cantidad de USD a convertir en ARS: ");
          int cantidadDolar = scanner.nextInt();

          double cantidadConvertida = convertir(cantidadDolar, valorDolarPesoArgentino);
          System.out.println("""
          Valor actual del ARS (%.4f)
          
          %d USD ==>> %.2f ARS
          """.formatted(valorDolarPesoArgentino, cantidadDolar, cantidadConvertida));
          break;

        case 2: // Peso Argentino ==>> Dólar (ARS -> USD)
          System.out.println("Ingrese la cantidad de ARS a convertir en USD: ");
          int cantidadPesoArgentino = scanner.nextInt();

          // La tasa USD->ARS es X, por lo tanto, la tasa ARS->USD es 1/X
          double valorPesoArgentinoDolar = cantidadPesoArgentino / conversionRatesDolar.get("ARS");

          System.out.println("""
          Valor actual del USD (1.0000)
          
          %d ARS ==>> %.2f USD
          """.formatted(cantidadPesoArgentino, valorPesoArgentinoDolar));
          break;
        
        // ------------------------------------------------------------------
        // CASOS ADAPTADOS (USD -> OTRA MONEDA)
        // ------------------------------------------------------------------
        case 3: // Dólar ==>> Real Brasileño (USD -> BRL)
          double valorDolarRealBrasilero = conversionRatesDolar.get("BRL");

          System.out.println("Ingrese la cantidad de USD a convertir en BRL: ");
          int cantidadDolarBRL = scanner.nextInt();

          double convertidoBRL = convertir(cantidadDolarBRL, valorDolarRealBrasilero);
          System.out.println("""
          Valor actual del BRL (%.4f)
          
          %d USD ==>> %.2f BRL
          """.formatted(valorDolarRealBrasilero, cantidadDolarBRL, convertidoBRL));
          break;

        case 5: // Dólar ==>> Peso Colombiano (USD -> COP)
          double valorDolarPesoColombiano = conversionRatesDolar.get("COP");

          System.out.println("Ingrese la cantidad de USD a convertir en COP: ");
          int cantidadDolarCOP = scanner.nextInt();

          double convertidoCOP = convertir(cantidadDolarCOP, valorDolarPesoColombiano);
          System.out.println("""
          Valor actual del COP (%.4f)
          
          %d USD ==>> %.2f COP
          """.formatted(valorDolarPesoColombiano, cantidadDolarCOP, convertidoCOP));
          break;

        case 7: // Dólar ==>> Peso Chileno (USD -> CLP)
          double valorDolarPesoChileno = conversionRatesDolar.get("CLP");

          System.out.println("Ingrese la cantidad de USD a convertir en CLP: ");
          int cantidadDolarCLP = scanner.nextInt();

          double convertidoCLP = convertir(cantidadDolarCLP, valorDolarPesoChileno);
          System.out.println("""
          Valor actual del CLP (%.4f)
          
          %d USD ==>> %.2f CLP
          """.formatted(valorDolarPesoChileno, cantidadDolarCLP, convertidoCLP));
          break;

        // ------------------------------------------------------------------
        // CASOS ADAPTADOS (OTRA MONEDA -> USD)
        // ------------------------------------------------------------------
        case 4: // Real Brasileño ==>> Dólar (BRL -> USD)
          System.out.println("Ingrese la cantidad de BRL a convertir en USD: ");
          int cantidadBRL = scanner.nextInt();

          double valorBRLDolar = cantidadBRL / conversionRatesDolar.get("BRL");

          System.out.println("""
          Valor actual del USD (1.0000)
          
          %d BRL ==>> %.2f USD
          """.formatted(cantidadBRL, valorBRLDolar));
          break;
          
        case 6: // Peso Colombiano ==>> Dólar (COP -> USD)
          System.out.println("Ingrese la cantidad de COP a convertir en USD: ");
          int cantidadCOP = scanner.nextInt();

          double valorCOPDolar = cantidadCOP / conversionRatesDolar.get("COP");

          System.out.println("""
          Valor actual del USD (1.0000)
          
          %d COP ==>> %.2f USD
          """.formatted(cantidadCOP, valorCOPDolar));
          break;

        case 8: // Peso Chileno ==>> Dolar (CLP -> USD)
          System.out.println("Ingrese la cantidad de CLP a convertir en USD: ");
          int cantidadCLP = scanner.nextInt();

          double valorCLPDolar = cantidadCLP / conversionRatesDolar.get("CLP");

          System.out.println("""
          Valor actual del USD (1.0000)
          
          %d CLP ==>> %.2f USD
          """.formatted(cantidadCLP, valorCLPDolar));
          break;
          
        case 9:
          System.out.println("Gracias por usar.\nSaliendo del programa.....");
          break;

        default:
          System.out.println("Opción Inválida...");
          break;
      }
    }


     

  }
  public static void mostrarMenu(){
    System.out.println("""
    *****************************************************
    Sea Bienvenido/a al Conversor de Monedas :)

    1) Dólar ==>> Peso Argentino
    2) Peso Argentino ==>> Dólar
    3) Dólar ==>> Real Brasileño
    4) Real Brasileño ==>> Dólar
    5) Dólar ==>> Peso Colombiano
    6) Peso Colombiano ==>> Dólar
    7) Dólar ==>> Peso Chileno
    8) Peso Chileno ==>> Dolar
    9) Salir
    Elija una opción válida:
    *****************************************************
      """);
  }

  public static double convertir(int cantidadConvertir, double precio){
    return cantidadConvertir * precio;
  }

  public static void clearScreen() {
    // Corregir no funciona
    try {

        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } else {
            Runtime.getRuntime().exec("clear");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
  }
}
