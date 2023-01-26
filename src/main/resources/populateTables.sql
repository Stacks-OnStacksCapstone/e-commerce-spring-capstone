truncate table users cascade;
truncate table orders cascade;
truncate table user_payments cascade;
truncate table product cascade;
truncate table product_review cascade;
truncate table order_details cascade;

insert into users
values (1, 'tets@gmail.com', 'test', true, false, 'users', '!test123', 'token'),
	(2, 'mickeymouse@example.com', 'Mickey', true, false, 'Mouse', 'pass123', 'token'),
	(3, 'tonythetiger@example.com', 'Tony', false, false, 'Tiger', 'pass123', 'token'),
	(4, 'wirtualtm@example.com', 'Wirtual', false, true, 'TM', 'pass123', 'token'),
	(5, 'nameynamenson@example.com', 'Namey', true, true, 'Namenson', 'pass123', 'token');

insert into user_payments
values ('CC1', '1111-2222-3333-4444', '123', '1-1-2030', 1),
	('CC2', '1111-2222-3333-4444', '123', '1-1-2030', 1),
	('CC3', '1111-2222-3333-4444', '123', '1-1-2030', 2),
	('CC4', '1111-2222-3333-4444', '123', '1-1-2030', 3);

insert into product
values (1, 'this product is  an apple', 'none', true, 'apple', 2, 15),
	(2, 'this product is  an orange', 'none', true, 'orange', 1, 20),
	(3, 'this product is  a pineapple', 'none', true, 'pineapple', 4, 25),
	(4, 'See and speak at the same time!', '', true, 'Microphone Lamp', 10.99, 10),
	(5, 'Spice up any bookshelp with this keepsake!', '', false, 'Lightning McQueen Model', 3.99, 0),
	(6, 'Keep ahead of fashion by taking these wherever you go!', '', true, 'Shrek Crocs', 5.99, 1);

insert into orders
values (1, '1-1-2023', 'Example Address', 'CC1', 1),
	(2, '1-1-2023', 'Example Address', 'CC2', 1),
	(3, '1-1-2023', 'Example Address', 'CC3', 2),
	(4, '1-1-2023', 'Example Address', 'CC4', 3);

insert into order_details 
values (1, 2, 1, 1),
	(2, 3, 2, 2),
	(3, 20, 3, 3),
	(4, 4, 4, 1);
	

insert into product_review (comment, rating, product_id, user_id) values ('the apples had brown spots and were over ripe.', 1, 1, 2);
insert into product_review (comment, rating, product_id, user_id) values ('the oranges had so much juice!', 4, 2, 1);


	

