-- Categorías
INSERT INTO Categoria (id, nombre) VALUES (1, 'Embutidos');
INSERT INTO Categoria (id, nombre) VALUES (2, 'Lácteos');

-- Productos de Embutidos
INSERT INTO Producto (id, nombre, precio, categoria_id) VALUES (1, 'Jamón', 15.90, 1);
INSERT INTO Producto (id, nombre, precio, categoria_id) VALUES (2, 'Chorizo', 12.50, 1);
INSERT INTO Producto (id, nombre, precio, categoria_id) VALUES (3, 'Salchicha', 8.90, 1);

-- Productos de Lácteos
INSERT INTO Producto (id, nombre, precio, categoria_id) VALUES (4, 'Queso Fresco', 9.90, 2);
INSERT INTO Producto (id, nombre, precio, categoria_id) VALUES (5, 'Yogurt', 5.50, 2);
INSERT INTO Producto (id, nombre, precio, categoria_id) VALUES (6, 'Leche', 4.90, 2);
