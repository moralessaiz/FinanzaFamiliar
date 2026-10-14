<div align="center">

# 💰 FinanzaFamiliar

### Sistema de Contabilidad Familiar — Backend Java + JDBC + MySQL

Módulos de **Usuarios** y **Movimientos** (ingresos y gastos del hogar) con las cuatro operaciones del CRUD sobre una base de datos MySQL, usando JDBC puro.

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?logo=apachemaven&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-4479A1?logo=mysql&logoColor=white)
![JDBC](https://img.shields.io/badge/JDBC-Connector%2FJ%2026.7.0-00758F)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJ%20IDEA-compatible-000000?logo=intellijidea&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans-compatible-1B6AC6?logo=apachenetbeanside&logoColor=white)

**SENA · Tecnólogo en Análisis y Desarrollo de Software (ADSO) · Ficha 3336193**
Evidencia `GA7-220501096-AA2-EV01` — Codificación de módulos del software

</div>

---

## 📑 Tabla de contenido

1. [¿Qué hace este proyecto?](#-qué-hace-este-proyecto)
2. [Requisitos previos](#-requisitos-previos)
3. [Paso 1 — Clonar el repositorio](#-paso-1--clonar-el-repositorio)
4. [Paso 2 — Crear la base de datos en MySQL Workbench](#-paso-2--crear-la-base-de-datos-en-mysql-workbench)
5. [Paso 3A — Ejecutar con IntelliJ IDEA](#-paso-3a--ejecutar-con-intellij-idea)
6. [Paso 3B — Ejecutar con NetBeans](#-paso-3b--ejecutar-con-netbeans)
7. [Paso 3C — Ejecutar desde la terminal (opcional)](#-paso-3c--ejecutar-desde-la-terminal-opcional)
8. [Resultado esperado](#-resultado-esperado)
9. [Estructura del proyecto](#-estructura-del-proyecto)
10. [Modelo de la base de datos](#-modelo-de-la-base-de-datos)
11. [Solución de problemas](#-solución-de-problemas)
12. [Estándares de codificación](#-estándares-de-codificación)
13. [Trabajo en equipo con Git](#-trabajo-en-equipo-con-git)
14. [Autores](#-autores)

---

## 🎯 ¿Qué hace este proyecto?

Es una aplicación de consola en Java que se conecta a MySQL por medio de **JDBC** y prueba el CRUD completo de dos módulos:

| Módulo | Tabla | Crear | Consultar | Actualizar | Eliminar |
|:--|:--|:--:|:--:|:--:|:--:|
| 👤 **Usuarios** | `usuario` | ✅ | ✅ | ✅ | ✅ |
| 💸 **Movimientos** | `movimiento` | ✅ | ✅ | ✅ | ✅ |

Todas las consultas usan `PreparedStatement` para evitar inyección SQL, y las conexiones se cierran solas con `try-with-resources`.

---

## 🧰 Requisitos previos

Instala lo siguiente **antes de empezar**:

| Herramienta | Versión | ¿Para qué? | Descarga |
|:--|:--|:--|:--|
| ☕ **JDK** | 17 o superior | Compilar y ejecutar Java | [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) |
| 🐬 **MySQL Server** | 8.0 o superior | Motor de base de datos | [MySQL Installer](https://dev.mysql.com/downloads/installer/) |
| 🛠️ **MySQL Workbench** | 8.0 o superior | Ejecutar los scripts SQL | Se instala junto con MySQL Installer |
| 💻 **IDE** | IntelliJ IDEA **o** NetBeans | Abrir y ejecutar el proyecto | [IntelliJ](https://www.jetbrains.com/idea/download/) · [NetBeans](https://netbeans.apache.org/front/main/download/) |
| 🌿 **Git** | Cualquiera | Clonar el repositorio | [git-scm.com](https://git-scm.com/downloads) |

> [!NOTE]
> **No necesitas descargar el driver de MySQL (`.jar`) a mano.** El proyecto usa **Maven**, que lo descarga automáticamente la primera vez que se abre el proyecto (se necesita internet). Tampoco hace falta instalar Maven: IntelliJ y NetBeans ya lo traen incluido.

> [!IMPORTANT]
> Ten a mano la **contraseña del usuario `root` de MySQL**, la que pusiste al instalar MySQL Server. La vas a necesitar en el Paso 2.

---

## 📥 Paso 1 — Clonar el repositorio

Abre una terminal (Git Bash, PowerShell o CMD) en la carpeta donde quieras guardar el proyecto y ejecuta:

```bash
git clone https://github.com/moralessaiz/FinanzaFamiliar.git
cd FinanzaFamiliar
```

> 💡 También puedes descargarlo como ZIP desde GitHub: botón verde **Code → Download ZIP**, y descomprimirlo.

---

## 🐬 Paso 2 — Crear la base de datos en MySQL Workbench

El proyecto trae **dos scripts SQL** en la carpeta [`sql/`](sql/), que se ejecutan **en este orden**:

| Orden | Script | ¿Qué hace? |
|:--:|:--|:--|
| 1️⃣ | [`finanzafamiliar_movimientos.sql`](sql/finanzafamiliar_movimientos.sql) | Crea la base de datos `finanzafamiliar`, las tablas `usuario`, `categoria` y `movimiento`, y los datos iniciales |
| 2️⃣ | [`crear_usuario_bd.sql`](sql/crear_usuario_bd.sql) | Crea el usuario de MySQL `finanza_user`, que es el que usa Java para conectarse |

### 2.1 Verificar que MySQL esté encendido

1. Presiona `Win + R`, escribe `services.msc` y presiona **Enter**.
2. Busca el servicio **MySQL80** (o `MySQL`).
3. Su estado debe ser **En ejecución**. Si no lo está: clic derecho → **Iniciar**.

### 2.2 Conectarse como `root`

1. Abre **MySQL Workbench**.
2. En la pantalla de inicio, en **MySQL Connections**, haz clic en **Local instance MySQL80**.
3. Escribe la contraseña de `root` y presiona **OK**.

<details>
<summary>🔧 <b>¿No aparece ninguna conexión? Haz clic aquí para crearla</b></summary>

<br>

1. Haz clic en el botón **⊕** al lado de *MySQL Connections*.
2. Llena el formulario así:

   | Campo | Valor |
   |:--|:--|
   | Connection Name | `Local` |
   | Hostname | `127.0.0.1` |
   | Port | `3306` |
   | Username | `root` |

3. Presiona **Test Connection**, escribe la contraseña de `root` y confirma que diga *Successfully made the MySQL connection*.
4. Presiona **OK** y luego haz clic en la nueva conexión para abrirla.

</details>

### 2.3 Ejecutar el script de la base de datos y las tablas

1. Ve al menú **File → Open SQL Script…** (`Ctrl + Shift + O`).
2. Busca la carpeta del proyecto y abre `sql/finanzafamiliar_movimientos.sql`.
3. Presiona el botón del **rayo ⚡** de la barra de herramientas (o `Ctrl + Shift + Enter`).
4. Revisa el panel **Output** en la parte inferior: todas las filas deben tener un ✔️ **verde**.

> [!WARNING]
> Hay **dos rayos** en la barra. Usa el **rayo solo ⚡** (ejecuta todo el script).
> El **rayo con cursor** (`Ctrl + Enter`) ejecuta **solo la línea** donde está el cursor.

> [!CAUTION]
> Ejecuta este script **una sola vez**. Si lo vuelves a ejecutar, los usuarios y categorías de ejemplo quedarán **duplicados**. Si necesitas empezar de cero, ejecuta primero `DROP DATABASE finanzafamiliar;` y luego el script otra vez.

### 2.4 Ejecutar el script del usuario de conexión

1. Abre de la misma forma el archivo `sql/crear_usuario_bd.sql`.
2. Ejecútalo con el **rayo ⚡**.
3. Al final aparece una pestaña de resultados con los permisos del usuario (`SHOW GRANTS`). Si la ves, todo salió bien ✅.

Este script crea el siguiente usuario, que coincide con los datos de [`ConexionBD.java`](src/main/java/com/finanzafamiliar/contabilidad/conexion/ConexionBD.java):

| Dato | Valor |
|:--|:--|
| 🖥️ Servidor | `localhost:3306` |
| 🗄️ Base de datos | `finanzafamiliar` |
| 👤 Usuario | `finanza_user` |
| 🔑 Contraseña | `Finanza2026*` |
| 🔐 Permisos | `SELECT`, `INSERT`, `UPDATE`, `DELETE` sobre `finanzafamiliar` |

> 💡 Si quieres usar otro usuario o contraseña, cámbialos **en los dos lugares**: en `sql/crear_usuario_bd.sql` y en las constantes `USUARIO` y `CONTRASENA` de `ConexionBD.java`.

### 2.5 Verificar que todo quedó creado

1. En el panel izquierdo **Navigator → Schemas**, presiona el botón 🔄 (*refresh*).
2. Debe aparecer la base de datos **`finanzafamiliar`** con sus tres tablas:

   ```
   📁 finanzafamiliar
    └── 📁 Tables
         ├── 📄 categoria
         ├── 📄 movimiento
         └── 📄 usuario
   ```

3. Para ver los datos: clic derecho sobre `usuario` → **Select Rows – Limit 1000**. Deben aparecer **2 usuarios**:

   | id_usuario | nombre | correo | rol |
   |:--:|:--|:--|:--|
   | 1 | Amalfi Aya | amalfi.aya@finanzafamiliar.com | Administradora |
   | 2 | Cristhian Morales | cristhian.morales@finanzafamiliar.com | Integrante |

   Y en `categoria`, **4 categorías**: Salario, Arriendo, Mercado y Servicios públicos.

🎉 **¡La base de datos está lista!** Ahora elige tu IDE: [IntelliJ IDEA](#-paso-3a--ejecutar-con-intellij-idea) o [NetBeans](#-paso-3b--ejecutar-con-netbeans).

---

## 🟣 Paso 3A — Ejecutar con IntelliJ IDEA

### Abrir el proyecto

1. Abre IntelliJ IDEA → **File → Open…**
2. Selecciona la carpeta **`FinanzaFamiliar`** (la que contiene el archivo `pom.xml`) y presiona **OK**.
3. Si pregunta, elige **Open as Project** y luego **Trust Project**.
4. Espera a que termine la barra de progreso inferior: IntelliJ está descargando el driver de MySQL con Maven.

### Configurar el JDK

1. Ve a **File → Project Structure…** (`Ctrl + Alt + Shift + S`).
2. En la sección **Project**:
   - **SDK:** selecciona tu JDK (17 o superior).
   - **Language level:** `17`.
3. Presiona **Apply → OK**.

> 💡 Si tu JDK no aparece en la lista: **Add SDK → JDK…** y busca la carpeta donde está instalado, por ejemplo `C:\Program Files\Java\jdk-25`.

### Cargar las dependencias de Maven

1. Abre el panel **Maven** (ícono **m** en la barra lateral derecha).
2. Presiona 🔄 **Reload All Maven Projects**.
3. En **Dependencies** debe aparecer `com.mysql:mysql-connector-j:26.7.0`.

### Ejecutar

1. En el panel **Project**, abre `src/main/java/com/finanzafamiliar/contabilidad/app/Main.java`.
2. Haz clic en el ▶️ **verde** que aparece al lado de `public static void main` → **Run 'Main.main()'**.
3. El resultado aparece en la consola inferior → mira el [resultado esperado](#-resultado-esperado).

---

## 🔵 Paso 3B — Ejecutar con NetBeans

> [!NOTE]
> Este proyecto es de tipo **Maven**, no **Ant** (el tipo que NetBeans usa por defecto en *Java with Ant*). NetBeans abre proyectos Maven directamente: **no hay que crear un proyecto nuevo, ni agregar el `.jar` en *Libraries***.

### Abrir el proyecto

1. Abre NetBeans → **File → Open Project…** (`Ctrl + Shift + O`).
2. Busca la carpeta **`FinanzaFamiliar`**. NetBeans la muestra con un ícono especial de Maven (**ma**) porque contiene el `pom.xml`.
3. Selecciónala y presiona **Open Project**.
4. La primera vez, NetBeans descarga las dependencias. Si el proyecto aparece con un ⚠️ o en rojo, clic derecho sobre el proyecto → **Resolve Project Problems…** → **Resolve**, o bien clic derecho → **Build with Dependencies**.

### Configurar el JDK

1. Si tu JDK no está registrado: **Tools → Java Platforms → Add Platform…** → selecciona la carpeta del JDK (17 o superior) → **Finish**.
2. Clic derecho sobre el proyecto → **Properties**.
3. En **Build → Compile**, selecciona tu JDK en **Java Platform**.
4. En **Sources**, verifica que **Source/Binary Format** sea `17`.
5. Presiona **OK**.

### Ejecutar

Tienes dos opciones:

| Opción | Cómo | Atajo |
|:--|:--|:--:|
| ▶️ **Run Project** | Botón verde ▶️ de la barra superior. La clase principal ya está definida en el `pom.xml` (`exec.mainClass`) | `F6` |
| 📄 **Run File** | En *Projects*, abre **Source Packages → `com.finanzafamiliar.contabilidad.app`**, clic derecho sobre `Main.java` → **Run File** | `Shift + F6` |

El resultado aparece en la ventana **Output** → mira el [resultado esperado](#-resultado-esperado).

> 💡 NetBeans puede crear los archivos `nb-configuration.xml` o `nbactions.xml` en la raíz del proyecto. Son configuraciones locales de NetBeans y no afectan a IntelliJ.

---

## ⌨️ Paso 3C — Ejecutar desde la terminal (opcional)

Solo si tienes [Apache Maven](https://maven.apache.org/download.cgi) instalado y en el `PATH`:

```bash
mvn compile exec:java
```

---

## ✅ Resultado esperado

Si todo está bien configurado, la consola mostrará algo parecido a esto (los `id` pueden variar según cuántas veces hayas ejecutado el programa):

```text
=== FinanzaFamiliar - Modulo de Usuarios (CRUD con JDBC) ===

[CREATE] Usuario insertado con id: 3

[READ] Listado de usuarios registrados:
  Usuario{idUsuario=1, nombre='Amalfi Aya', correo='amalfi.aya@finanzafamiliar.com', rol='Administradora'}
  Usuario{idUsuario=2, nombre='Cristhian Morales', correo='cristhian.morales@finanzafamiliar.com', rol='Integrante'}
  Usuario{idUsuario=3, nombre='Laura Morales', correo='laura.morales@finanzafamiliar.com', rol='Integrante'}

[READ] Usuario consultado por id (3): Usuario{idUsuario=3, nombre='Laura Morales', ...}

[UPDATE] Usuario actualizado: true
[UPDATE] Nuevo estado: Usuario{idUsuario=3, ..., correo='laura.m@finanzafamiliar.com', rol='Administradora'}

[DELETE] Usuario con id 3 eliminado: true
...

=== FinanzaFamiliar - Modulo de Movimientos (CRUD con JDBC) ===

[CREATE] Movimiento insertado con id: 1
[CREATE] Movimiento insertado con id: 2
...
[UPDATE] Movimiento actualizado: true
[DELETE] Movimiento con id 1 eliminado: true
...

=== Prueba de CRUD finalizada correctamente ===
```

> [!NOTE]
> El usuario de prueba (*Laura Morales*) se **elimina al final** de su prueba, así que la tabla `usuario` no se llena de registros repetidos. En cambio, la prueba de movimientos deja guardado **un gasto** cada vez que se ejecuta.

---

## 🗂️ Estructura del proyecto

```text
FinanzaFamiliar/
├── 📄 pom.xml                         ← Configuración de Maven (Java 17 + driver MySQL)
├── 📄 README.md                       ← Este archivo
├── 📄 .gitignore
├── 📁 sql/
│   ├── finanzafamiliar_movimientos.sql   ← 1️⃣ Base de datos, tablas y datos iniciales
│   └── crear_usuario_bd.sql              ← 2️⃣ Usuario de MySQL para la aplicación
└── 📁 src/main/java/com/finanzafamiliar/contabilidad/
    ├── 📁 app/
    │   └── Main.java                  ← Punto de entrada: prueba el CRUD de ambos módulos
    ├── 📁 conexion/
    │   └── ConexionBD.java            ← Abre y cierra la conexión JDBC
    ├── 📁 modelo/
    │   ├── Usuario.java               ← POJO de la tabla usuario
    │   └── Movimiento.java            ← POJO de la tabla movimiento
    └── 📁 dao/
        ├── UsuarioDAO.java            ← CRUD de usuarios
        └── MovimientoDAO.java         ← CRUD de movimientos
```

### ¿Cómo se comunican las capas?

```mermaid
flowchart LR
    A["🚀 Main<br/><small>app</small>"] --> B["📦 UsuarioDAO<br/>MovimientoDAO<br/><small>dao</small>"]
    B --> C["🔌 ConexionBD<br/><small>conexion</small>"]
    C -->|JDBC| D[("🐬 MySQL<br/>finanzafamiliar")]
    B -. "crea y devuelve" .-> E["🧩 Usuario<br/>Movimiento<br/><small>modelo</small>"]
```

| Paquete | Responsabilidad |
|:--|:--|
| `app` | Ejecuta el programa y muestra los resultados en consola |
| `dao` | *Data Access Object*: contiene el SQL de cada tabla (insertar, consultar, actualizar, eliminar) |
| `conexion` | Centraliza los datos de conexión y entrega objetos `Connection` |
| `modelo` | Clases simples (POJO) que representan una fila de cada tabla |

---

## 🗄️ Modelo de la base de datos

```mermaid
erDiagram
    USUARIO ||--o{ MOVIMIENTO : "registra"
    CATEGORIA ||--o{ MOVIMIENTO : "clasifica"

    USUARIO {
        int id_usuario PK
        varchar nombre
        varchar correo
        varchar rol
    }
    CATEGORIA {
        int id_categoria PK
        varchar nombre
        varchar tipo "ingreso o gasto"
    }
    MOVIMIENTO {
        int id_movimiento PK
        varchar tipo "ingreso o gasto"
        decimal monto
        varchar concepto
        date fecha
        int id_categoria FK
        int id_usuario FK
    }
```

- Un **usuario** puede registrar muchos **movimientos**.
- Una **categoría** puede clasificar muchos **movimientos**.
- No se puede eliminar un usuario que tenga movimientos registrados: MySQL lo impide por la llave foránea `fk_movimiento_usuario`.

---

## 🩺 Solución de problemas

| ❌ Mensaje de error | 🔍 Causa | ✅ Solución |
|:--|:--|:--|
| `Access denied for user 'finanza_user'@'localhost'` | No se ha creado el usuario de MySQL | Ejecuta `sql/crear_usuario_bd.sql` en Workbench ([Paso 2.4](#24-ejecutar-el-script-del-usuario-de-conexión)) |
| `Unknown database 'finanzafamiliar'` | No se ha creado la base de datos | Ejecuta `sql/finanzafamiliar_movimientos.sql` ([Paso 2.3](#23-ejecutar-el-script-de-la-base-de-datos-y-las-tablas)) |
| `Communications link failure` | El servidor MySQL está apagado | Inicia el servicio **MySQL80** ([Paso 2.1](#21-verificar-que-mysql-esté-encendido)) |
| `Cannot add or update a child row: a foreign key constraint fails` | El movimiento usa un `id_usuario` o `id_categoria` que no existe | Verifica que la tabla `usuario` tenga el id `1` y `categoria` los ids `1` y `3` |
| `Cannot delete or update a parent row` | Intentas eliminar un usuario que tiene movimientos | Es el comportamiento esperado. Elimina primero sus movimientos |
| `java: JDK isn't specified for module` *(IntelliJ)* | El proyecto no tiene JDK asignado | **File → Project Structure → Project → SDK** ([Paso 3A](#configurar-el-jdk)) |
| `No se encontro el driver JDBC de MySQL` | Maven no descargó las dependencias | IntelliJ: panel Maven → 🔄 Reload. NetBeans: clic derecho → **Build with Dependencies**. Verifica tu conexión a internet |
| `package com.mysql... does not exist` / clases en rojo | Las dependencias no se han cargado | Igual que el caso anterior |
| `invalid target release: 17` | El JDK instalado es anterior a la versión 17 | Instala JDK 17 o superior y selecciónalo en el IDE |

---

## 📐 Estándares de codificación

El código sigue el documento de estándares del proyecto (evidencia `GA7-220501096-AA1-EV02`):

| Elemento | Convención | Ejemplo |
|:--|:--|:--|
| 📦 Paquetes | minúsculas, dominio inverso | `com.finanzafamiliar.contabilidad.dao` |
| 🏷️ Clases | `UpperCamelCase`, sustantivos | `UsuarioDAO`, `ConexionBD` |
| 🔤 Variables y atributos | `lowerCamelCase` | `idUsuario`, `idMovimiento` |
| ⚙️ Métodos | `lowerCamelCase`, verbos | `insertar()`, `consultarPorId()` |
| 🔒 Constantes | `UPPER_SNAKE_CASE` | `DRIVER`, `CONTRASENA` |
| 📝 Comentarios | Javadoc `/** ... */` en clases y métodos públicos | — |
| ↔️ Identación | 4 espacios, máximo 120 caracteres por línea | — |

---

## 🌿 Trabajo en equipo con Git

Antes de empezar a trabajar, trae siempre los últimos cambios de tu compañera o compañero:

```bash
git pull origin main
```

Después de terminar un avance:

```bash
git add .
git commit -m "Descripción corta y clara del cambio"
git push origin main
```

> 💡 Haz **commits pequeños**: uno por funcionalidad, por ejemplo *"Agregar UsuarioDAO"* o *"Corregir consulta de movimientos"*. Así es más fácil revisar el historial y deshacer un cambio si algo falla.

---

## 👥 Autores

<table>
  <tr>
    <td align="center"><b>Amalfi Aya de Gómez</b><br/><sub>Aprendiz ADSO</sub></td>
    <td align="center"><b>Cristhian Andrés Morales Saiz</b><br/><sub>Aprendiz ADSO</sub></td>
  </tr>
</table>

<div align="center">

**Servicio Nacional de Aprendizaje — SENA**
Tecnólogo en Análisis y Desarrollo de Software · Ficha 3336193 · 2026

</div>
