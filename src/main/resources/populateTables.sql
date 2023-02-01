truncate table users cascade;
truncate table orders cascade;
truncate table user_payments cascade;
truncate table product cascade;
truncate table product_review cascade;
truncate table order_details cascade;

insert into users
values (1000000, 'tets@gmail.com', 'test', true, false, 'users', '!test123', 'token'),
	(1000001, 'mickeymouse@example.com', 'Mickey', true, false, 'Mouse', 'pass123', 'token'),
	(1000002, 'tonythetiger@example.com', 'Tony', false, false, 'Tiger', 'pass123', 'token'),
	(1000003, 'wirtualtm@example.com', 'Wirtual', false, true, 'TM', 'pass123', 'token'),
	(1000004, 'nameynamenson@example.com', 'Namey', true, true, 'Namenson', 'pass123', 'token'),
	(1000005, 'profile@test.com', 'Profile', true, false, 'Test', 'ProfileTest!', 'token'),
	(1000006, 'testymctesterson@gmail.com', 'Testy', true, false, 'McTesterson', 'Abcdef1!', 'token'),
	(1000007, 'testymctesterson221205@gmail.com', 'Testy', true, false, 'McTesterson', 'Abcdef1!', 'token'),
	(1000008, 'jesterjesterson@example.com', 'Jester', true, false, 'Jesterson', 'pass123', 'token'),
	(1000009, 'tobedeleted@example.com', 'Tobe', true, false, 'Deleted', 'pass123', 'token');

insert into user_payments
values ('CC1', '1111-2222-3333-4444', '123', '1-1-2030', 1000000),
	('CC2', '1111-2222-3333-4444', '123', '1-1-2030', 1000000),
	('CC3', '1111-2222-3333-4444', '123', '1-1-2030', 1000001),
	('CC4', '1111-2222-3333-4444', '123', '1-1-2030', 1000002),
	('CC5', '1111-2222-3333-4444', '123', '1-1-2030', 1000003),
	('CC6', '1111-2222-3333-4444', '123', '1-1-2030', 1000004);

insert into product
values (1000000, 'this product is  an apple', 'https://cdn.pixabay.com/photo/2014/02/01/17/28/apple-256261_960_720.jpg', true, 'apple', 2, 15),
	(1000001, 'this product is  an orange', 'https://cdn.pixabay.com/photo/2017/12/29/16/34/fruit-3048001_960_720.jpg', true, 'orange', 1, 20),
	(1000002, 'this product is  a pineapple', 'https://cdn.pixabay.com/photo/2015/02/14/18/10/pineapple-636562_960_720.jpg', true, 'pineapple', 4, 25),
	(1000003, 'See and speak at the same time!', 'https://cdn.pixabay.com/photo/2017/10/30/23/34/lamp-2903830_960_720.jpg', true, 'Microphone Lamp', 10.99, 10),
	(1000004, 'Spice up any bookshelp with this keepsake!', 'https://cdn.pixabay.com/photo/2017/04/16/16/52/lightning-mcqueen-2235210_960_720.jpg', false, 'Lightning McQueen Model', 3.99, 20),
	(1000005, 'Keep ahead of fashion by taking these wherever you go!', 'https://cdn.pixabay.com/photo/2015/04/14/17/08/alien-722415_960_720.jpg', true, 'Shrek Crocs', 5.99, 1);

insert into orders
values (1000000, '1-1-2023', 'Example Address', 'CC1', 1000000),
	(1000001, '1-1-2023', 'Example Address', 'CC2', 1000000),
	(1000002, '1-1-2023', 'Example Address', 'CC3', 1000001),
	(1000003, '1-1-2023', 'Example Address', 'CC4', 1000002),
	(1000004, '1-1-2023', 'Example Address', 'CC5', 1000003),
	(1000005, '1-1-2023', 'Example Address', 'CC6', 1000004);

insert into order_details
values (1000000, 2, 1000000, 1000003),
	(1000001, 3, 1000000, 1000005),
	(1000002, 20, 1000001, 1000002),
	(1000003, 1, 1000001, 1000005),
	(1000004, 1, 1000002, 1000000),
	(1000005, 1, 1000003, 1000000),
	(1000006, 1, 1000004, 1000000),
	(1000007, 1, 1000005, 1000000);

insert into product_review
values (1000000, 'the apples had brown spots and were over ripe.', 1, 1000000, 1000001),
	(1000001, 'the oranges had so much juice!', 4, 1000001, 1000000),
	(1000002, 'Pineapples are the best of both the apple and pine worlds!', 5, 1000002, 1000000),
	(1000003, 'Wow! I didn''t know that I needed this!', 5, 1000003, 1000002),
	(1000004, 'I love cars!', 5, 1000004, 1000003),
	(1000005, 'These crocs are rad!', 5, 1000005, 1000004);