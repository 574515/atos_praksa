drop table if exists korisnik;
create table korisnik (
	oib char(11) not null primary key,
	firstName varchar(20) not null,
	lastName varchar(20) not null,
	workplace varchar(30) not null,
	pwd varchar(32) not null,
	usr_role varchar(10) default('user') not null
);

drop table if exists zadatak;
create table zadatak (
	id int primary key,
	name varchar(30) not null,
  	description varchar(100),
  	task_type ENUM('bug', 'task') not null,
  	status ENUM('open', 'closed', 'inprogress') not null,
  	complexity tinyint not null,
	time_needed int not null,
	start_date varchar(12) not null,
	end_date varchar(12) not null
);

drop table if exists zadaciKorisnika;
create table zadaciKorisnika (
	korisnik char(11),
	zadatak int,
	foreign key (korisnik) references korisnik(oib),
	foreign key (zadatak) references zadatak(id),
	primary key(korisnik, zadatak)
);