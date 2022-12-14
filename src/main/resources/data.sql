
--PRODUCTS

INSERT INTO product (id, quantity, price, description, image, name, is_active) VALUES (
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
    'Hat',
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
),
(
    6,
    10,
    120.00,
    'A beater set of headphones',
    'https://www.beatsbydre.com/content/dam/beats-support/global/images/product-hero/set-up-and-use-studio-wireless.jpg.large.2x.jpg',
    'Headphones',
    true
),
(
    7,
    5,
    99.99,
    'A nicer MeeShirt',
    'https://m.media-amazon.com/images/I/B1DBWbloIpS._CLa%7C2140%2C2000%7C51snuhwZFIL.png%7C0%2C0%2C2140%2C2000%2B0.0%2C0.0%2C2140.0%2C2000.0_AC_SL1500_.png',
    'TeeShirt',
    true
),
(
    8,
    20,
    12.50,
    'A reusable surgical mask',
    'https://media-cldnry.s-nbcnews.com/image/upload/newscms/2022_29/3512377/screen_shot_2021-10-13_at_2-00-29_pm.png',
    'Mask',
    true
),
(
    9,
    1,
    1000.00,
    'A fancier hat for a fancier person',
    'https://cdn.shopify.com/s/files/1/2624/7744/products/ReversibleRen-RedSilver-Leather-TopHat-AmericanHatMakers-Men-STUD_900x900.jpg?v=1668713281',
    'Hat',
    true
),
(
    10,
    3,
    1.99,
    'A niceish coat',
    'https://media.bergdorfgoodman.com/f_auto,q_auto:good,ar_5:7,c_fill,dpr_1.0,w_720/01/bg_4370145_100664_m',
    'Coat',
    true
);

--USERS

INSERT INTO users (id, email, password, first_name, last_name, is_active, is_admin) VALUES
(

    1,
    'testuser@gmail.com',
    'password',
    'Testerson',
    'Usertown',
    true,
    true

),
(   2,
    'notadmin@gmail.com',
    'password',
    'notingham',
    'admindude',
    true,
    false
),
(   3,
    'project03test@gmail.com',
    'Password1*',
    'Fakey',
    'McFake',
    true,
    false
);

--USER PAYMENTS
INSERT INTO user_payments(id, card_number, ccv, exp_date, user_id) VALUES
(
    '5bc1bb79-6ef8-48e1-be83-8dfee8f981a7',
    '9999888877776666',
    '123',
    '2029-12-22',
    1
),
(
    'f6eb6c0c-2a9d-4e8a-8541-6d9c7ba37ad2',
    '1111222233334444',
    '321',
    '2028-12-27',
    2
),(
    3,
    '9119228873372266',
    '123',
    '2029-12-22',
    1
),
(
    4,
    '1115522255334524',
    '321',
    '2028-12-27',
    2
);

--PRODUCT REVIEWS
INSERT INTO product_review (id, comment, rating, product_id, user_id) VALUES
(
    1,
    'This shirt is the best',
    4,
    2,
    1
),
(
    2,
    'These sound like tin cans',
    1,
    1,
    1
),
(
    3,
    'Holds the things',
    5,
    3,
    2
),
(
    4,
    'Cool beans',
    1,
    4,
    2
),
(
    5,
    'A very nice coat',
    5,
    5,
    2
),
(
    6,
    'These sound like tin cans',
    2,
    6,
    1
),
(
    7,
    'A vote for me is a vote for this shirt',
    4,
    7,
    1
),
(
    8,
    'Air is the enemy',
    5,
    8,
    1
),
(
    9,
    'Nice feather',
    3,
    9,
    2
),
(
    10,
    'So puffy its like a cloud',
    5,
    10,
    2
);

-- ORDERS
INSERT INTO orders (id, order_date, shipment_address, payment_id, user_id) VALUES
(
    1,
    '2020-12-27',
    '1234 This Rd. Sometown, FL',
    '5bc1bb79-6ef8-48e1-be83-8dfee8f981a7',
    1
),
(
    2,
    '2020-12-27',
    '1234 This Rd. Sometown, FL',
    '5bc1bb79-6ef8-48e1-be83-8dfee8f981a7',
    1
),
(
    3,
    '2020-12-27',
    '1234 This Rd. Sometown, FL',
    'f6eb6c0c-2a9d-4e8a-8541-6d9c7ba37ad2',
    2
),
(
    4,
    '2020-12-27',
    '1234 This Rd. Sometown, FL',
    'f6eb6c0c-2a9d-4e8a-8541-6d9c7ba37ad2',
    2
);

-- ORDER DETAILS
INSERT INTO order_details (id, quantity, order_id, product_id) VALUES (
    1,
    2,
    1,
    2
),
(
    2,
    1,
    2,
    1
),
(
    3,
    2,
    3,
    10
),
(
    4,
    2,
    4,
    9
);

