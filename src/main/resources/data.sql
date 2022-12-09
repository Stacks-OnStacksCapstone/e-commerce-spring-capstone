
INSERT INTO product (id, quantity, price, description, image, name, is_active)
VALUES (
      1,
      10,
      20.00,
      'A nice pair of headphones',
      'https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp',
      'Headphones',
      true
  ),
  (
      2,
      5,
      45.00,
      'A nice TeeShirt',
      'https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png',
      'TeeShirt',
      true
  ),
  (
      3,
      20,
      2.50,
      'A reusable shopping bag',
      'https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png',
      'Shopping Bag',
      true
  ),
  (
      4,
      20,
      10.00,
      'A fancy cap for a fancy person',
      'https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png',
      'Baseball Cap',
      true
  ),
  (
      5,
      3,
      80.00,
      'A nice coat',
      'https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png',
      'Coat',
      true
  );

INSERT INTO users (id, email, password, first_name, last_name, is_active, is_admin)
VALUES (1,'testuser@gmail.com','password','Test','User',true,true),
       (2,'jane@gmail.com','password','Jane','Doe',true,false),
       (3,'mark@gmail.com','password','Mark','Man',true,false),
       (4,'sean@gmail.com','password','Sean','Person',true,false);

INSERT INTO user_payments (id, card_number, ccv, exp_date, user_id)
VALUES ('1','123400001234','123','2027-07-07',2),
       ('2','999900009999','999','2031-01-01',4),
       ('3','555500005555','500','2023-03-03',3);


INSERT INTO product_review (id, comment, rating, product_id, user_id)
VALUES (1, 'Best quality sound quality ever', 5, 1, 3),
       (2, 'There are better options', 2, 1, 4),
       (3, 'Very comfortable and stylish', 5, 5, 2),
       (4, 'I love this cap!', 4, 4, 2),
       (5, 'The size runs a little small', 3, 2, 3);

INSERT INTO orders (id, order_date, shipment_address, payment_id, user_id)
VALUES (1,'2022-12-02','123 Home St, Atlanta, GA, 12345, USA','1',2),
       (2,'2022-12-15','500 Nowhere Rd, Dallas, TX, 50000, USA','3',3),
       (3,'2022-12-16','123 Home St, Atlanta, GA, 12345, USA','1',2),
       (4,'2022-12-22','999 Place Dr, Chicago, IL, 99999, USA','2',4);

INSERT INTO order_details (id, quantity, order_id, product_id)
VALUES (1, 1, 1, 5),
       (2, 1, 2, 1),
       (3, 1, 2, 2),
       (4, 1, 3, 4),
       (5, 1, 4, 1);

