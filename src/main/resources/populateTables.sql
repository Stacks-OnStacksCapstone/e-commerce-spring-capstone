insert into users (email, first_name, is_active, is_admin, last_name, password) values ('tets@gmail.com', 'test', true, false, 'users', '!test123');
insert into product (description, image, is_active, name, price, quantity) values ('this product is  an apple', 'none', true, 'apple', 2, 15);
insert into product (description, image, is_active, name, price, quantity) values ('this product is  an orange', 'none', true, 'orange', 1, 20);
insert into product (description, image, is_active, name, price, quantity) values ('this product is  a pineapple', 'none', true, 'pineapple', 4, 25);
insert into product_review (comment, rating, product_id, user_id) values ('the apples had brown spots and were over ripe.', 1, 1, 2);
insert into product_review (comment, rating, product_id, user_id) values ('the oranges had so much juice!', 4, 2, 1);