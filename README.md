# 💰 Conversor de Monedas (Challenge Alura/Oracle ONE)

Este proyecto implementa un conversor de monedas que consulta tasas de cambio en tiempo real a través de una API externa, utilizando **Java** con Maven, HTTP Client nativo y la librería Gson para el manejo de JSON.

## 🚀 1. Requisitos del Sistema

  * **Java Development Kit (JDK):** Versión 16 o superior (necesaria para usar `record`s).
  * **Maven:** Versión 3.x para la gestión de dependencias y compilación.
  * **API Key:** Necesitarás una clave de la API [ExchangeRate-API](https://www.exchangerate-api.com/).

## ⚙️ 2. Configuración e Instalación

### 2.1. Gestión de Dependencias

Este proyecto utiliza **Maven**. Asegúrate de que las siguientes dependencias estén incluidas en tu archivo `pom.xml`:

| Dependencia | Propósito |
| :--- | :--- |
| `gson` | Deserialización del JSON de la API en objetos Java (`record`s y clases). |

### 2.2. Configuración de Variables de Entorno

**Es fundamental** que tu clave de API no esté escrita directamente en el código. El programa espera que la clave se establezca como una variable de entorno.

1.  **En WSL/Linux/macOS:** Abrí tu terminal y exportá la variable (o añádela a tu `~/.bashrc` o `~/.zshrc`):
    ```bash
    export EXCHANGE_API_KEY="TU_CLAVE_AQUI"
    ```
2.  **En Windows (CMD/PowerShell):**
    ```powershell
    $env:EXCHANGE_API_KEY="TU_CLAVE_AQUI"
    ```

## 💻 3. Estructura del Proyecto

El proyecto sigue una estructura simple de MVC (Modelo-Vista-Controlador):

| Archivo/Clase | Función | Conceptos Implementados |
| :--- | :--- | :--- |
| `App.java` | Punto de entrada principal. | |
| `ConversorApp.java` | **Controlador/Lógica.** Contiene el menú principal y la lógica de conversión. | `switch`, `Scanner`, Manejo de errores de IO. |
| `ApiClient.java` | **Capa de Servicio.** Maneja la comunicación con la API externa. | `HttpClient`, `HttpRequest`, `HttpResponse`, Lectura de variables de entorno. |
| `ExchangeResponse.java` | **DTO (Data Transfer Object).** Representación exacta de la respuesta JSON de la API. | **`record`** (Inmutabilidad), Genéricos (`Map<String, Double>`). |
| `Exchange.java` | **Modelo de Dominio.** Clase interna que contiene solo la moneda base y las tasas para la lógica. | **Inmutabilidad** (`final`), Mapeo DTO -\> Modelo. |

## 💡 4. Conceptos Clave Implementados

  * **Variables de Entorno:** Uso de `System.getenv()` en `ApiClient.java` para proteger secretos (`EXCHANGE_API_KEY`).
  * **Records (DTOs):** Se usa el `record` `ExchangeResponse` para una deserialización concisa e inmutable de la respuesta JSON.
  * **Mapeo de Datos:** Se realiza la **transformación** del `record` (`ExchangeResponse`) a la clase de dominio `Exchange` para desacoplar el modelo de negocio del formato de la API externa.
  * **Inmutabilidad:** Las clases de datos clave utilizan el modificador **`final`** para garantizar que sus valores (como las tasas de cambio) no puedan ser modificados después de su inicialización.

## 🧯 5. Errores Comunes de Compilación y Solución

Si encuentras errores de compilación (`mvn compile`), verifica lo siguiente:

  * **`required: reference, found: double`**: Debes usar la clase **`Double`** en lugar del tipo primitivo `double` dentro de los *generics* de `Map` (ej. `Map<String, Double>`).
  * **`int cannot be dereferenced`**: El método `response.statusCode()` devuelve un `int`. No puedes llamar métodos como `.equalsIgnoreCase()` sobre él. La verificación debe ser: `if (response.statusCode() == 200)`.
  * **Acceso a `record`**: Debes usar los métodos *accessor* (ej. `data.base_code()`) en lugar de acceder directamente a los campos (ej. `data.base_code`).

-----

## 🏃 6. Uso del Conversor

1.  **Compilar el proyecto:**
    ```bash
    mvn clean install
    ```
2.  **Ejecutar el programa:**
    ```bash
    mvn exec:java
    ```
3.  Sigue las opciones del menú interactivo para realizar conversiones entre USD y las monedas ARS, BRL, COP, y CLP.
