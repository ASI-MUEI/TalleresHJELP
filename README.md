# Talleres HJELP

Práctica para la asignatura ASI del MUEI.

## Ejecutar aplicación

Se necesitan las siguientes dependencias:

* Node 14.15.0+.
* Java 11
* Maven 3.6+.
* MySQL 8+.

Luego de su instalación:

[Backend]

```
cd backend
mvn install
mvn sql:execute (only first time to create tables)
mvn spring-boot:run
```
[Frontend]

```
cd frontend
npm install (only first time to download libraries)
npm start
```
