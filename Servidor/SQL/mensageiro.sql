create table usuario(
	id int not null auto_increment primary key,
	registro varchar(250),
	email varchar(250),
	senha varchar(250),
	nick varchar(50)
);

create table amigo(
	id int not null,
	idAmigo int not null,
	nick varchar(250),
	foreign key(id) references usuario(id))
);

create table mensagem(
	id int not null auto_increment primary key,
	idFrom varchar(250),
	idTo varchar(250),
	mensagem text,
	visto int not null,
	id_loc int
);

CREATE TABLE `localizacao` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `coordenadas` point NOT NULL,
  UNIQUE KEY `id` (`id`),
  SPATIAL KEY `idx_coordenadas` (`coordenadas`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;




INSERT INTO `spatial_test`.`locations` (`id`, `coordinates`) VALUES (NULL, GeomFromText('POINT(-4.968833 -39.018888)'));

SELECT x(`spatial_test`.`locations`.`coordinates`) AS latitude, y(`spatial_test`.`locations`.`coordinates`) AS longitude FROM `spatial_test`.`locations`;


 $sql = "SELECT id, (6371 * acos(cos(radians(?)) * cos(radians(x(`spatial_test`.`locations`.`coordinates`)))
                    * cos(radians(y(`spatial_test`.`locations`.`coordinates`)) - radians(?)) + sin(radians(?))
                    * sin(radians(x(`spatial_test`.`locations`.`coordinates`))))) AS distance
                    FROM `spatial_test`.`locations`
                    HAVING distance < ?
                    ORDER BY id
            ;";


