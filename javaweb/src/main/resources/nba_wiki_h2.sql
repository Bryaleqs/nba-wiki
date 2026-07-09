-- =========================================================
-- NBA Wiki - Version para H2 (base de datos embebida)
-- Se ejecuta automaticamente al arrancar la app (ver web.xml).
-- Mismo contenido que database/nba_wiki.sql, adaptado a H2.
-- =========================================================

DROP TABLE IF EXISTS campeonato_roster;
DROP TABLE IF EXISTS campeonatos;
DROP TABLE IF EXISTS jugadores;
DROP TABLE IF EXISTS equipos;

CREATE TABLE IF NOT EXISTS equipos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    ciudad VARCHAR(60) NOT NULL,
    conferencia VARCHAR(10) NOT NULL CHECK (conferencia IN ('Este','Oeste')),
    color_principal VARCHAR(7) NOT NULL DEFAULT '#000000',
    abreviatura VARCHAR(4) NOT NULL
);

CREATE TABLE IF NOT EXISTS jugadores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    posicion VARCHAR(20),
    nacionalidad VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS campeonatos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    anio INT NOT NULL,
    equipo_campeon_id INT NOT NULL,
    equipo_finalista_id INT NOT NULL,
    resultado_serie VARCHAR(10) NOT NULL,
    mvp_finales_id INT,
    FOREIGN KEY (equipo_campeon_id) REFERENCES equipos(id),
    FOREIGN KEY (equipo_finalista_id) REFERENCES equipos(id),
    FOREIGN KEY (mvp_finales_id) REFERENCES jugadores(id)
);

CREATE TABLE IF NOT EXISTS campeonato_roster (
    campeonato_id INT NOT NULL,
    jugador_id INT NOT NULL,
    PRIMARY KEY (campeonato_id, jugador_id),
    FOREIGN KEY (campeonato_id) REFERENCES campeonatos(id),
    FOREIGN KEY (jugador_id) REFERENCES jugadores(id)
);

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_usuario VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    salt VARCHAR(64) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS favoritos (
    usuario_id INT NOT NULL,
    campeonato_id INT NOT NULL,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (usuario_id, campeonato_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (campeonato_id) REFERENCES campeonatos(id)
);

INSERT INTO equipos (id, nombre, ciudad, conferencia, color_principal, abreviatura) VALUES
(1,  'Warriors',  'Golden State',   'Oeste', '#1D428A', 'GSW'),
(2,  'Cavaliers', 'Cleveland',      'Este',  '#860038', 'CLE'),
(3,  'Raptors',   'Toronto',        'Este',  '#CE1141', 'TOR'),
(4,  'Lakers',    'Los Angeles',    'Oeste', '#552583', 'LAL'),
(5,  'Heat',      'Miami',          'Este',  '#98002E', 'MIA'),
(6,  'Bucks',     'Milwaukee',      'Este',  '#00471B', 'MIL'),
(7,  'Suns',      'Phoenix',        'Oeste', '#E56020', 'PHX'),
(8,  'Nuggets',   'Denver',         'Oeste', '#0E2240', 'DEN'),
(9,  'Celtics',   'Boston',         'Este',  '#007A33', 'BOS'),
(10, 'Mavericks', 'Dallas',         'Oeste', '#00538C', 'DAL'),
(11, 'Thunder',   'Oklahoma City',  'Oeste', '#007AC1', 'OKC'),
(12, 'Pacers',    'Indiana',        'Este',  '#FDBB30', 'IND'),
(13, 'Knicks',    'New York',       'Este',  '#F58426', 'NYK'),
(14, 'Spurs',     'San Antonio',    'Oeste', '#C4CED4', 'SAS');

INSERT INTO jugadores (id, nombre, posicion, nacionalidad) VALUES
(1,  'Stephen Curry',            'Base',   'Estados Unidos'),
(2,  'Klay Thompson',            'Escolta','Estados Unidos'),
(3,  'Draymond Green',           'Alero',  'Estados Unidos'),
(4,  'Andre Iguodala',           'Alero',  'Estados Unidos'),
(5,  'LeBron James',             'Alero',  'Estados Unidos'),
(6,  'Kyrie Irving',             'Base',   'Estados Unidos'),
(7,  'Kevin Love',               'Ala-Pivot','Estados Unidos'),
(8,  'Kevin Durant',             'Alero',  'Estados Unidos'),
(9,  'Kawhi Leonard',            'Alero',  'Estados Unidos'),
(10, 'Kyle Lowry',               'Base',   'Estados Unidos'),
(11, 'Pascal Siakam',            'Ala-Pivot','Camerun'),
(12, 'Fred VanVleet',            'Base',   'Estados Unidos'),
(13, 'Anthony Davis',            'Pivot',  'Estados Unidos'),
(14, 'Rajon Rondo',              'Base',   'Estados Unidos'),
(15, 'Giannis Antetokounmpo',    'Ala-Pivot','Grecia'),
(16, 'Khris Middleton',          'Alero',  'Estados Unidos'),
(17, 'Jrue Holiday',             'Base',   'Estados Unidos'),
(18, 'Andrew Wiggins',           'Alero',  'Canada'),
(19, 'Nikola Jokic',             'Pivot',  'Serbia'),
(20, 'Jamal Murray',             'Base',   'Canada'),
(21, 'Aaron Gordon',             'Ala-Pivot','Estados Unidos'),
(22, 'Jaylen Brown',             'Escolta','Estados Unidos'),
(23, 'Jayson Tatum',             'Alero',  'Estados Unidos'),
(24, 'Kristaps Porzingis',       'Pivot',  'Letonia'),
(25, 'Shai Gilgeous-Alexander',  'Escolta','Canada'),
(26, 'Jalen Williams',           'Alero',  'Estados Unidos'),
(27, 'Chet Holmgren',            'Pivot',  'Estados Unidos'),
(28, 'Jalen Brunson',            'Base',   'Estados Unidos'),
(29, 'OG Anunoby',               'Alero',  'Reino Unido'),
(30, 'Karl-Anthony Towns',       'Pivot',  'Estados Unidos'),
(31, 'Josh Hart',                'Escolta','Estados Unidos');

INSERT INTO campeonatos (id, anio, equipo_campeon_id, equipo_finalista_id, resultado_serie, mvp_finales_id) VALUES
(1,  2015, 1,  2,  '4-2', 4),
(2,  2016, 2,  1,  '4-3', 5),
(3,  2017, 1,  2,  '4-1', 8),
(4,  2018, 1,  2,  '4-0', 8),
(5,  2019, 3,  1,  '4-2', 9),
(6,  2020, 4,  5,  '4-2', 5),
(7,  2021, 6,  7,  '4-2', 15),
(8,  2022, 1,  9,  '4-2', 1),
(9,  2023, 8,  5,  '4-1', 19),
(10, 2024, 9,  10, '4-1', 22),
(11, 2025, 11, 12, '4-3', 25),
(12, 2026, 13, 14, '4-1', 28);

INSERT INTO campeonato_roster (campeonato_id, jugador_id) VALUES
(1, 1), (1, 2), (1, 3), (1, 4),
(2, 5), (2, 6), (2, 7),
(3, 1), (3, 8), (3, 2), (3, 3),
(4, 1), (4, 8), (4, 2), (4, 3),
(5, 9), (5, 10), (5, 11), (5, 12),
(6, 5), (6, 13), (6, 14),
(7, 15), (7, 16), (7, 17),
(8, 1), (8, 2), (8, 3), (8, 18),
(9, 19), (9, 20), (9, 21),
(10, 22), (10, 23), (10, 24),
(11, 25), (11, 26), (11, 27),
(12, 28), (12, 29), (12, 30), (12, 31);
