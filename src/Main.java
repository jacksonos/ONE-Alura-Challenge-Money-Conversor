import com.google.gson.Gson;
import com.google.gson.JsonObject;
import config.ApiActions;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final Map<Integer, String[]> historialConversion = new HashMap<>();
    private static int key = 1;
    static ApiActions api = new ApiActions();

    public static void mostrarHistorial(Map<Integer, String[]> prm) {
        if (prm == null || prm.isEmpty()) {
            System.out.println("El historial esta vacio.");
            return;
        }
        System.out.println("HISTORIAL 📒");
        for (Map.Entry<Integer, String[]> entry : prm.entrySet()) {
            String[] valores = entry.getValue();
            System.out.printf("%d: %s | F.H. Consulta: %s%n", entry.getKey(), valores[0], valores[1]);
        }
    }

    public static String obtenerFechaYHoraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        return ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static void convertir(String monedaBase, String monedaDestino, double monto) {
        try {
            String apiKey = api.getApiKey();
            String urlStr = BASE_URL + apiKey + "/latest/" + monedaBase;
            String[] registroParaHistorial = new String[2];
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(urlStr))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

                if (jsonObject.get("result").getAsString().equals("success")) {
                    JsonObject tasas = jsonObject.getAsJsonObject("conversion_rates");
                    double tasaCambio = tasas.get(monedaDestino).getAsDouble();
                    double resultado = monto * tasaCambio;
                    String resultadoFormateado = String.format("%12.2f %-3s = %12.2f %-3s", monto, monedaBase, resultado, monedaDestino);
                    System.out.printf("Resultado: %s%n", resultadoFormateado);
                    registroParaHistorial[0] = resultadoFormateado;
                    registroParaHistorial[1] = obtenerFechaYHoraActual();
                    historialConversion.put(key, registroParaHistorial);
                    key++;
                } else {
                    System.out.println("Error en la conversión. Verifica tu API KEY o los parámetros.");
                }
            } else {
                System.out.println("Error HTTP: Código " + response.statusCode());
            }
        } catch (Exception e) {
            System.out.println("Error al conectar con la API: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        System.out.println("BIENVENIDO AL CONVERSOR DE MONEDAS 🪙");

        do {
            System.out.println("\nMENU PRINCIPAL");
            System.out.println("""
                    1) DÓLAR ==> NUEVO SOL
                    2) NUEVO SOL ==> USD
                    3) DÓLAR ==> REAL BRASILEÑO
                    4) REAL BRASILEÑO a DÓLAR
                    5) DÓLAR ==> PESO ARGENTINO
                    6) PESO ARGENTINO ==> DÓLAR
                    7) VER HISTORIAL DE CONVERSIONES
                    8) SALIR
                           \s""");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Ingrese el monto a convertir: ");
                double monto = scanner.nextDouble();

                switch (opcion) {
                    case 1 -> convertir("USD", "PEN", monto);
                    case 2 -> convertir("PEN", "USD", monto);
                    case 3 -> convertir("USD", "BRL", monto);
                    case 4 -> convertir("BRL", "USD", monto);
                    case 5 -> convertir("USD", "ARS", monto);
                    case 6 -> convertir("ARS", "USD", monto);
                }
            } else if (opcion == 7) {
                mostrarHistorial(historialConversion);
            }
        } while (opcion != 8);

        System.out.println("Gracias por usar el conversor de moneda.");
    }
}