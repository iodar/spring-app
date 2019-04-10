create table public."user" (
	id numeric not null,
	nachname varchar(200),
	vorname varchar(200),
	geburtsdatum date,
	primary key(id)
);

insert into public."user" (id, nachname, vorname, geburtsdatum) values (1, 'Granzow', 'Dario', to_date('31-05-1996', 'DD-MM-YYYY'));
insert into public."user" (id, nachname, vorname, geburtsdatum) values (2, 'Granzow', 'Heike', to_date('04-02-1967', 'DD-MM-YYYY'));
insert into public."user" (id, nachname, vorname, geburtsdatum) values (3, 'Granzow', 'Rocco', to_date('26-06-1966', 'DD-MM-YYYY'));
insert into public."user" (id, nachname, vorname, geburtsdatum) values (4, 'Granzow', 'Hartmut', to_date('09-09-1940', 'DD-MM-YYYY'));
insert into public."user" (id, nachname, vorname, geburtsdatum) values (5, 'Granzow', 'Christel', to_date('18-11-1940', 'DD-MM-YYYY'));

