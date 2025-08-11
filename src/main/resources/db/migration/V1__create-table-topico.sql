CREATE TABLE topico (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        titulo VARCHAR(100) NOT NULL,
                        mensaje VARCHAR(250) NOT NULL UNIQUE,
                        fechaCreacion DATETIME,
                        status CHAR(1) NOT NULL,
                        autor VARCHAR(100) NOT NULL,
                        curso VARCHAR(100) NOT NULL,
                        PRIMARY KEY (id)
);
