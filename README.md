# Talleres HJELP

Práctica para la asignatura ASI del MUEI.

## Dependencias

Se necesitan las siguientes dependencias:

* Node 14.15.0+.
* Java 11
* Maven 3.6+.
* MySQL 8+.

El backend hace uso de las siguientes tecnologías: Spring-boot, Maven, JPA, Hibernate.

El frontend hace uso de las siguientes tecnologías: NPM, React.

## Ejecutar la aplicación 
Una vez hayamos instalado las dependencias mencionadas para la instalación del proyecto se deben seguir los pasos siguientes:

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

URL del repositorio: https://github.com/ASI-MUEI/TalleresHJELP

## Información del grupo
Integrantes: 
	Hilda Romero Velo,
	Paula Fernández Blanco,
	José Antonio Figueiras Martinez,
	Laura Insua Regueiro
