CREATE TABLE "directores" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL,
	"url_foto"	TEXT NOT NULL,
	"url_web"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "generos" (
	"id"	INTEGER,
	"descripcion"	TEXT NOT NULL,
	PRIMARY KEY("id")
);

CREATE TABLE "artistas" (
	"id"	INTEGER,
	"nombre"	TEXT NOT NULL,
	"url_foto"	TEXT NOT NULL,
	"url_web"	TEXT NOT NULL,
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "peliculas" (
	"id"	INTEGER,
	"titulo"	TEXT NOT NULL,
	"id_director"	INTEGER,
	"anyo"	INTEGER NOT NULL,
	"url_caratula"	TEXT NOT NULL,
	"id_genero"	INTEGER,
	"es_animacion"	INTEGER,
	FOREIGN KEY("id_genero") REFERENCES "generos"("id"),
	FOREIGN KEY("id_director") REFERENCES "directores"("id"),
	PRIMARY KEY("id" AUTOINCREMENT)
);

CREATE TABLE "repartos" (
	"id_pelicula"	INTEGER,
	"id_artista"	INTEGER,
	PRIMARY KEY("id_pelicula","id_artista")
);

INSERT INTO generos (id, descripcion) VALUES (1, "ACCION");
INSERT INTO generos (id, descripcion) VALUES (2, "AVENTURA");
INSERT INTO generos (id, descripcion) VALUES (3, "COMEDIA");
INSERT INTO generos (id, descripcion) VALUES (4, "DRAMA");
INSERT INTO generos (id, descripcion) VALUES (5, "FANTASIA");
INSERT INTO generos (id, descripcion) VALUES (6, "TERROR");
INSERT INTO generos (id, descripcion) VALUES (7, "FICCION");
INSERT INTO generos (id, descripcion) VALUES (8, "MUSICAL");
INSERT INTO generos (id, descripcion) VALUES (9, "SUSPENSE");
INSERT INTO generos (id, descripcion) VALUES (10, "WESTERN");
INSERT INTO generos (id, descripcion) VALUES (11, "DOCUMENTAL");
INSERT INTO generos (id, descripcion) VALUES (12, "BIOGRAFICO");
INSERT INTO generos (id, descripcion) VALUES (13, "ROMANCE");

COMMIT;