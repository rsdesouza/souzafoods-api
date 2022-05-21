insert into cozinha (id, nome) values (1, 'Tailandesa');
insert into cozinha (id, nome) values (2, 'Indiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Gourmet', 10, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Thai Delivery', 9.50, 1);
insert into restaurante (nome, taxa_frete, cozinha_id) values ('Tuk Tuk Comida Indiana', 15, 2);

insert into forma_pagamento (id, descricao) values (1, 'Pix');
insert into forma_pagamento (id, descricao) values (2, 'Boleto');
insert into forma_pagamento (id, descricao) values (3, 'Crédito');
insert into forma_pagamento (id, descricao) values (4, 'Débito');

insert into permissao (id, nome, descricao) values (1, 'ADMIN', 'Permissão de Administrador com acesso total os sistema');
insert into permissao (id, nome, descricao) values (2, 'USER', 'Permissão de Usuário, alguns acessos ao sistema');
insert into permissao (id, nome, descricao) values (3, 'COVIDADO', 'Permissão de Covidado, acesso limitado');

insert into estado (id, nome) values (1, 'Pernambuco');
insert into estado (id, nome) values (2, 'Alagoas');
insert into estado (id, nome) values (3, 'Sergipe');
insert into estado (id, nome) values (4, 'Paraiba');

insert into cidade (id, nome, estado_id) values (1, 'Recife', 1);
insert into cidade (id, nome, estado_id) values (2, 'Cabo de Santo Agostinho', 1);
insert into cidade (id, nome, estado_id) values (3, 'Ipojuca', 1);
insert into cidade (id, nome, estado_id) values (4, 'Maceió', 2);
insert into cidade (id, nome, estado_id) values (5, 'Aracaju', 3);
insert into cidade (id, nome, estado_id) values (6, 'João Pessoa', 4);