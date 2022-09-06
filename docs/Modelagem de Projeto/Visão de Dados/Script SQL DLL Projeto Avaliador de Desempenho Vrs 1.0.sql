begin;

create table organizacao (
    id serial primary key,
    nome character varying(100) not null,
    cnpj character varying(18) not null unique,
    emaildecontato character varying(100) unique not null
);

create table organizacao_endereco (
    id serial primary key,
	organizacao_id integer not null references organizacao(id) on update cascade,
	numero character varying(20) not null,
	bairro character varying(20) not null,
    apartamento character varying(100),
	cep character varying(9) not null
);

create table colaborador (
    id serial primary key,
    organizacao_id integer not null references organizacao(id) on update cascade,
    tipo character varying(11) not null check (tipo in ('Avaliador', 'Funcionario')),
    nome character varying(100) not null,
    sexo character varying(100) not null,
    datanasc date,
    cpf character varying(14) not null,
    telefone character (19) not null,
    email character varying(100) not null,
    cargo character varying(100),
    setor character varying (100),
    senha character varying(100) not null
);

create table avaliacao (
    id serial primary key,
    datahora timestamp without time zone not null,
    datalimite timestamp without time zone,
    titulo character varying(100) not null unique,
	colaborador_id integer not null references colaborador(id) on update cascade
);

create table pergunta(
    id serial primary key,
    avaliacao_id integer not null references avaliacao(id) on update cascade,
    descricao text not null,
    pergunta_fechada boolean not null
);

create table pergunta_img(
    id serial primary key,
    pergunta_id integer not null references pergunta(id) on update cascade,
    foto character varying(200) not null,
    unique(pergunta_id, foto)
);

create table alternativa(
	id serial primary key,
	descricao text not null,
	correta boolean not null,
	pergunta_id integer not null references pergunta(id) on update cascade 
);

create table resposta(
    id serial primary key,
    texto_resposta text,
    colaborador_id integer not null references colaborador(id) on update cascade,
    pergunta_id integer not null references pergunta(id) on update cascade,
    alternativa_resposta_id integer references alternativa(id) on update cascade
);

commit;