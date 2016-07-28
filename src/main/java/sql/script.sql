/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Michel
 * Created: 11/07/2016
 */

/*produto */
CREATE SEQUENCE public.produto_id_produto;
CREATE TABLE produto
  (
    id_produto BIGINT NOT NULL DEFAULT nextval('public.produto_id_produto'),
    product VARCHAR(255) NOT NULL,
    price numeric(10,2) not null,
    CONSTRAINT PK_produto PRIMARY KEY (id_produto)/* cria a chave primaria */
  );

/*pedido */
CREATE SEQUENCE public.pedido_id_pedido;
CREATE TABLE pedido
  (
    id_pedido BIGINT NOT NULL DEFAULT nextval('public.pedido_id_pedido'),
    member_id VARCHAR(255) NOT NULL,
    order_number bigint not null,
    gift_message varchar(255) not null,
    CONSTRAINT PK_pedido PRIMARY KEY (id_pedido)/* cria a chave primaria */
  );


/*produto pedido */
CREATE SEQUENCE public.pedido_produto_id;
CREATE TABLE pedido_produto
  (
    id BIGINT NOT NULL DEFAULT nextval('public.pedido_produto_id'),
    id_produto bigint not null,
    id_pedido bigint not null,
    CONSTRAINT PK_produto_pedido PRIMARY KEY (id),/* cria a chave primaria */
	CONSTRAINT produto_pedido_produto_fk
	FOREIGN KEY (id_produto)
	REFERENCES produto (id_produto)
	ON UPDATE CASCADE,
	CONSTRAINT pedido_pedido_produto_fk
	FOREIGN KEY (id_pedido)
	REFERENCES pedido (id_pedido)
	ON UPDATE CASCADE
  );

/* usuario */
CREATE SEQUENCE public.usuario_id_usuario;
CREATE TABLE usuario
  (
    id_usuario        BIGINT NOT NULL DEFAULT nextval('public.usuario_id_usuario'),
    email             VARCHAR(100) UNIQUE not null,
    senha             VARCHAR(255) not null,
    caminho_foto      VARCHAR(255),
    caminho_foto_capa VARCHAR(255),
	status  	      CHAR,
    CONSTRAINT PK_usuario PRIMARY KEY (id_usuario)/* cria a chave primaria */
  );  
  
  /*Papel do Usuario */
CREATE SEQUENCE public.papel_usuario_id_papel_usuario;
CREATE TABLE papel_usuario
  (
    id_papel_usuario  BIGINT NOT NULL DEFAULT nextval('public.papel_usuario_id_papel_usuario'),
    nome              VARCHAR(120) unique NOT NULL,
    CONSTRAINT PK_papel_usuario PRIMARY KEY (id_papel_usuario)/* cria a chave primaria */
  );

/* usuario_papel_usuario */
CREATE SEQUENCE public.usuario_papel_usuario_id_upu;
CREATE TABLE usuario_papel_usuario
  (
    id_upu        	  BIGINT NOT NULL DEFAULT nextval('public.usuario_papel_usuario_id_upu'),
	id_usuario        BIGINT not null,
    id_papel_usuario  BIGINT not null,
    CONSTRAINT PK_usuario_papel_usuario PRIMARY KEY (id_upu),
	CONSTRAINT usuario_usuario_papel_usuario_fk
	FOREIGN KEY (id_usuario)
	REFERENCES usuario (id_usuario)
	ON UPDATE CASCADE,
    CONSTRAINT papel_usuario_usuario_papel_usuario_fk
	FOREIGN KEY (id_papel_usuario)
	REFERENCES papel_usuario (id_papel_usuario)
	ON UPDATE CASCADE	
  );  

/* acesso */
CREATE SEQUENCE public.acesso_id;
CREATE TABLE acesso
  (
    id      BIGINT NOT NULL DEFAULT nextval('public.acesso_id'),
    chave   VARCHAR(10) NOT NULL,
    papel   VARCHAR(30) NOT NULL,
    CONSTRAINT PK_acesso PRIMARY KEY (id)
  );  


Insert into produto values(1,'Mouse', 10.5);
Insert into produto values(2,'Speaker', 30.5);
Insert into produto values(3,'Computer', 1000);

Insert into papel_usuario values(1,'ADMIN');
Insert into papel_usuario values(2,'USER');

/*
JSON sem JSON Injection

{
  "member_id": "John Dole",
  "order_number": 101,
  "produtos": [
    {
      "id_produto": 1,
      "product": "Mouse",
      "price": 10.5
    },
    {
      "id_produto": 2,
      "product": "speaker",
      "price": 30.5
    }
  ],
  "gift_message": "Happy"
}


JSON com JSON Injection

{
  "member_id": "John Dole",
  "order_number": 102,
  "produtos": [
    {
      "id_produto": 1,
      "product": "Mouse",
      "price": 10.5
    },
    {
      "id_produto": 2,
      "product": "speaker",
      "price": 30.5
    }
  ],
  "gift_message": "Happy","produtos": [
    {
      "id_produto": 3,
      "product": "Computer",
      "price": 1000
    }],
    "member_id": "John Dole"
}

*/