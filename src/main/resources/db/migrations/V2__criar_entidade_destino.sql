CREATE TABLE destino (
                         id SERIAL PRIMARY KEY,
                         nome VARCHAR(255) NOT NULL,
                         localizacao VARCHAR(255) NOT NULL,
                         descricao TEXT,
                         media_avaliacao DOUBLE PRECISION DEFAULT 0,
                         total_avaliacoes INT DEFAULT 0
);
