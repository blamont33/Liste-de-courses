CREATE TABLE recettes(
id_recette INTEGER IDENTITY(1,1) CONSTRAINT PK_recettes PRIMARY KEY,
nom_recette VARCHAR (100) NOT NULL,
lien VARCHAR (300) NULL
);

CREATE TABLE ingredients(
id_ingredient INTEGER IDENTITY(1,1) CONSTRAINT PK_ingredients PRIMARY KEY,
nom_ingredient VARCHAR (100) NOT NULL

);

CREATE TABLE composition(
id_recette INTEGER NOT NULL CONSTRAINT FK_composition_recettes REFERENCES recettes (id_recette),
id_ingredient INTEGER NOT NULL CONSTRAINT FK_composition_ingredient REFERENCES ingredients (id_ingredient),
quantite INTEGER NOT NULL,
unite VARCHAR (10) NOT NULL,
CONSTRAINT PK_composition PRIMARY KEY (id_recette, id_ingredient)
);

CREATE TABLE listes(
id_liste INTEGER IDENTITY(1,1) NOT NULL CONSTRAINT PK_listes PRIMARY KEY,
nom_liste VARCHAR (100) NOT NULL
);

CREATE TABLE compositionlistes(
id_liste INTEGER NOT NULL CONSTRAINT FK_compositionlistes_listes REFERENCES listes (id_liste),
id_ingredient INTEGER NOT NULL CONSTRAINT FK_compositionlistes_ingredient REFERENCES ingredients (id_ingredient),
nom_ingredient VARCHAR (100) NOT NULL,
quantite INTEGER NOT NULL,
unite VARCHAR (10) NOT NULL
);

CREATE TABLE menus(
id_recette INTEGER NOT NULL CONSTRAINT FK_menus_recettes REFERENCES recettes(id_recette),
date_menu DATE NOT NULL
);
