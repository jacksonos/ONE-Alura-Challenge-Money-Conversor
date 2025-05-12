
---

# 💱 Conversor de Moneda - ONE | Alura Challenge

Aplicación de consola en Java para convertir entre monedas usando datos en tiempo real de la API [ExchangeRate API](https://www.exchangerate-api.com/).  
Desarrollado como parte del programa **Oracle Next Education (ONE)** en colaboración con **Alura LATAM**.

---

## 🧩 Funcionalidades

- ✅ Conversión entre USD, PEN, BRL, ARS
- 📡 Consumo de API en tiempo real
- 📜 Historial detallado de conversiones con fecha y hora
- 🔐 Lectura segura de API key desde `.env`
- 📦 Manejo robusto de errores y respuestas HTTP
- 🧪 Código modular con paquetes organizados (`main`, `config`)

---

## 🖥️ Menú Principal (Ejemplo)

```
1. DÓLAR ==> NUEVO SOL
2. NUEVO SOL ==> USD
3. DÓLAR ==> REAL BRASILEÑO
4. REAL BRASILEÑO a DÓLAR
5. DÓLAR ==> PESO ARGENTINO
6. PESO ARGENTINO ==> DÓLAR
7. VER HISTORIAL DE CONVERSIONES
8. SALIR
```

---

## 📁 Estructura del Proyecto

```
├── src/
│   ├── main/
│   │   └── Main.java
│   └── config/
│       └── ApiActions.java
├── .env
├── .gitignore
└── README.md

````
---

## ⚙️ Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/jacksonos/ONE-Alura-Challenge-Money-Conversor.git
cd ONE-Alura-Challenge-Money-Conversor
````

### 2. Crear el archivo `.env`

Crea un archivo llamado `.env` en la raíz con el siguiente contenido:

```
EXCHANGE_RATE_API_KEY=TU_API_KEY_AQUI
```

> Puedes obtener una API key gratuita desde: [https://www.exchangerate-api.com/](https://www.exchangerate-api.com/)

### 3. Compilar y ejecutar

Desde la terminal:

```bash
javac -d out src/config/*.java src/main/*.java
java -cp out Main
```

O bien, abre el proyecto en tu IDE favorito como IntelliJ IDEA o VS Code y ejecuta desde ahí.

---

## 🛠️ Tecnologías Usadas

* Java 17+
* [Gson](https://github.com/google/gson)
* API REST (ExchangeRate)
* `java.net.http.HttpClient`
* `java.time` para fecha/hora
* `.env` + `Properties` para configuración

---

## 📌 Ejemplo de salida

```
Resultado:      100.00 USD =      372.50 PEN
Resultado:       50.00 BRL =        9.23 USD
```

Historial:

```
1:     100.00 USD =     372.50 PEN | F.H. Consulta: 2025-05-11 14:32:10
2:      50.00 BRL =       9.23 USD | F.H. Consulta: 2025-05-11 14:33:02
```
---

## 📄 Licencia

Este proyecto es de uso libre y educativo como parte del challenge del programa ONE de Oracle + Alura.

---
