DROP DATABASE IF EXISTS LaMascarada;
CREATE DATABASE LaMascarada;
USE LaMascarada;

DROP TABLE IF EXISTS Equipo_Partida_Personaje;
DROP TABLE IF EXISTS Equipo;
DROP TABLE IF EXISTS Opcion;
DROP TABLE IF EXISTS Personaje;
DROP TABLE IF EXISTS Clan;
DROP TABLE IF EXISTS Habilidad;
DROP TABLE IF EXISTS Partida;
DROP TABLE IF EXISTS Texto_Escena;
DROP TABLE IF EXISTS Escena;
DROP TABLE IF EXISTS Usuario;

CREATE TABLE IF NOT EXISTS Usuario (
	Usuario VARCHAR(20) PRIMARY KEY,
	Pass VARCHAR(20) NOT NULL,
	Ultima_Modificacion TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS Habilidad (
	Nombre VARCHAR(10) PRIMARY KEY,
	Descripcion VARCHAR(280) NOT NULL
);

CREATE TABLE IF NOT EXISTS Clan (
	Nombre VARCHAR(10) PRIMARY KEY,
	Descripcion VARCHAR(280) NOT NULL,
	Imagen VARCHAR(50) NOT NULL,
    Habilidad_1 VARCHAR(10),
    Habilidad_2 VARCHAR(10),
    Habilidad_3 VARCHAR(10),
    Habilidad_4 VARCHAR(10),
    FOREIGN KEY (Habilidad_1) REFERENCES Habilidad(Nombre),
    FOREIGN KEY (Habilidad_2) REFERENCES Habilidad(Nombre),
    FOREIGN KEY (Habilidad_3) REFERENCES Habilidad(Nombre),
    FOREIGN KEY (Habilidad_4) REFERENCES Habilidad(Nombre)
);

CREATE TABLE IF NOT EXISTS Personaje (
	Nombre VARCHAR(20) PRIMARY KEY,
	Ataque INT NOT NULL,
    Defensa INT NOT NULL,
    VidaMax INT NOT NULL,
    Vida INT NOT NULL,
    Dinero INT NOT NULL,
    EstadoAnimo INT NOT NULL,
    NombreClan VARCHAR(10),
    NombreHabilidad1 VARCHAR(10),
    NombreHabilidad2 VARCHAR(10),
    FOREIGN KEY (NombreClan) REFERENCES Clan(Nombre),
    FOREIGN KEY (NombreHabilidad1) REFERENCES Habilidad(Nombre),
    FOREIGN KEY (NombreHabilidad2) REFERENCES Habilidad(Nombre)
);

CREATE TABLE IF NOT EXISTS Escena (
	IdEscena INT PRIMARY KEY,
    Imagen VARCHAR(50) NOT NULL,
    NombrePersonaje VARCHAR(20) NOT NULL,
    FOREIGN KEY (nombrePersonaje) REFERENCES Personaje(Nombre)
);

CREATE TABLE IF NOT EXISTS Texto_Escena (
    IdEscena INT,
	Condicion INT,
	Texto VARCHAR(560) NOT NULL,
	PRIMARY KEY (IdEscena, Condicion),
    FOREIGN KEY (IdEscena) REFERENCES Escena(IdEscena)
);

CREATE TABLE IF NOT EXISTS Partida (
	IdPartida INT PRIMARY KEY,
	Fecha Timestamp NOT NULL,
    Tiempo INT NOT NULL,
    Progreso INT NOT NULL,
    SedSangre INT NOT NULL,
    Sospecha INT NOT NULL,
    UltimaPista VARCHAR(280),
    IdEscena INT,
    Usuario VARCHAR(20),
    FOREIGN KEY (IdEscena) REFERENCES Escena(IdEscena),
    FOREIGN KEY (Usuario) REFERENCES Usuario(Usuario)
);

CREATE TABLE IF NOT EXISTS Personaje_En_Partida (
	Nombre VARCHAR(20) PRIMARY KEY,
	Ataque INT NOT NULL,
    Defensa INT NOT NULL,
    VidaMax INT NOT NULL,
    Vida INT NOT NULL,
    Dinero INT NOT NULL,
    EstadoAnimo INT NOT NULL,
    NombreClan VARCHAR(10),
    NombreHabilidad1 VARCHAR(10),
    NombreHabilidad2 VARCHAR(10),
	IdPartida INT,
    FOREIGN KEY (NombreClan) REFERENCES Clan(Nombre),
    FOREIGN KEY (NombreHabilidad1) REFERENCES Habilidad(Nombre),
    FOREIGN KEY (NombreHabilidad2) REFERENCES Habilidad(Nombre),
	FOREIGN KEY (IdPartida) REFERENCES Partida(IdPartida)
);

CREATE TABLE IF NOT EXISTS Opcion (
	IdOpcion INT PRIMARY KEY,
	Texto VARCHAR(50) NOT NULL,
    Accion INT NOT NULL,
	Condicion INT NOT NULL,
    Tiempo INT NOT NULL,
    NombreClan VARCHAR(10),
    IdEscena INT,
    IdEscenaSiguiente INT,
    FOREIGN KEY (NombreClan) REFERENCES Clan(Nombre),
    FOREIGN KEY (IdEscena) REFERENCES Escena(IdEscena),
    FOREIGN KEY (IdEscenaSiguiente) REFERENCES Escena(IdEscena)
);

CREATE TABLE IF NOT EXISTS Equipo (
	Nombre VARCHAR(20) PRIMARY KEY,
	Descripcion VARCHAR(280),
	Ataque INT NOT NULL,
    Defensa INT NOT NULL,
    Vida INT NOT NULL,
    Precio INT NOT NULL
);

CREATE TABLE IF NOT EXISTS Equipo_Partida_Personaje_En_Partida (
	NombreEquipo VARCHAR(20),
    IdPartida INT,
    NombrePersonaje VARCHAR(10),
    EnUso BOOLEAN,
    PRIMARY KEY (NombreEquipo, IdPartida),
    FOREIGN KEY (NombreEquipo) REFERENCES Equipo(Nombre),
    FOREIGN KEY (IdPartida) REFERENCES Partida(IdPartida),
    FOREIGN KEY (NombrePersonaje) REFERENCES Personaje(Nombre)
);

CREATE TABLE IF NOT EXISTS Equipo_Partida_Personaje (
	NombreEquipo VARCHAR(20),
    NombrePersonaje VARCHAR(10),
    EnUso BOOLEAN,
    PRIMARY KEY (NombreEquipo, NombrePersonaje),
    FOREIGN KEY (NombreEquipo) REFERENCES Equipo(Nombre),
    FOREIGN KEY (NombrePersonaje) REFERENCES Personaje(Nombre)
);