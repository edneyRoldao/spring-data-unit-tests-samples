
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS entidade_taxa;

CREATE TABLE entidade_taxa (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    id_tipo_taxa             INT,
    nome                     VARCHAR(200),
    active                   INT
);

CREATE TABLE products (
    id          INT             AUTO_INCREMENT PRIMARY KEY  ,
    sku         VARCHAR(200)                                ,
    name        VARCHAR(200)                                ,
    amount      INT                                         ,
    category    VARCHAR(200)                                ,
    description VARCHAR(200)                                ,
    created     DATETIME

);

INSERT INTO products (sku, name, amount, category, description, created) VALUES
    ('PRD-124578', 'notebook', 500000, 'electronic', 'pc gamer', '2021-08-20 10:00:00'),
    ('PRD-785412', 'nintendo switch', 250000, 'electronic', 'nintendo console', '2021-08-25 01:22:00'),
    ('PRD-639685', 't-shirt', 3500, 'clothes', 'a girl t-shirt', '2021-08-30 10:00:00'),
    ('PRD-369645', 'socks', 1000, 'clothes', 'a sock to warm your foot', '2021-09-05 15:20:00'),
    ('PRD-123245', 'foot ball', 7500, 'toy', 'a soccer ball', '2021-09-10 00:00:00'),
    ('PRD-030310', 'dice', 500, 'toy', 'a dice from king dice', '2021-09-15 10:00:00'),
    ('PRD-108574', 'sofa', 1500000, 'furniture', 'a sofa with 7 places', '2021-09-20 22:00:00'),
    ('PRD-114466', 'table', 250000, 'furniture', 'office table', '2021-10-05 23:59:00'),
    ('PRD-331177', 'backpack', 11000, 'bag', 'high school backpack', '2021-10-18 23:59:00');
