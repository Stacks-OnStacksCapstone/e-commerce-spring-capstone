-- read-only test data
INSERT INTO users (email, password, first_name, last_name, is_active, is_admin) VALUES
(
    -- 1
    'admin@example.com',
    'guest',
    'AdminFirst',
    'AdminLast',
    true,
    true
),
(
    -- 2
    'test@example.com',
    'guest',
    'TestFirst',
    'TestLast',
    true,
    false
);


INSERT INTO product (quantity, price, description, image, name, is_active) VALUES (
    10,
    20.00,
    'A nice pair of headphones',
    'https://i.insider.com/54eb437f6bb3f7697f85da71?width=1000&format=jpeg&auto=webp',
    'Headphones',
    true
),
(
    5,
    45.00,
    'A nice TeeShirt',
    'https://d3o2e4jr3mxnm3.cloudfront.net/Mens-Jake-Guitar-Vintage-Crusher-Tee_68382_1_lg.png',
    'TeeShirt',
    true
),
(
    20,
    2.50,
    'A reusable shopping bag',
    'https://images.ctfassets.net/5gvckmvm9289/3BlDoZxSSjqAvv1jBJP7TH/65f9a95484117730ace42abf64e89572/Noissue-x-Creatsy-Tote-Bag-Mockup-Bundle-_4_-2.png',
    'Shopping Bag',
    true
),
(
    20,
    10.00,
    'A fancy cap for a fancy person',
    'https://d3o2e4jr3mxnm3.cloudfront.net/Rocket-Vintage-Chill-Cap_66374_1_lg.png',
    'Baseball Cap',
    true
),
(
    3,
    80.00,
    'A nice coat',
    'https://www.pngarts.com/files/3/Women-Jacket-PNG-High-Quality-Image.png',
    'Coat',
    true
);

-- user-profile test data
INSERT INTO users (email, password, first_name, last_name, is_active, is_admin) VALUES
(
    -- 3
    'user-profile@example.com',
    'guest',
    'UserProfileFirst',
    'UserProfileLast',
    true,
    false
),
(
    -- 4
    'user-profile@tobedeactivated.com',
    'guest',
    'UserProfileFirst',
    'UserProfileLast',
    true,
    false
),
(
    -- 5
    'user-profile@badkeyphrase.com',
    'guest',
    'UserProfileFirst',
    'UserProfileLast',
    true,
    false
),
(
    -- 6
    'user-profile@onetime.com',
    'guest',
    'UserProfileFirst',
    'UserProfileLast',
    true,
    false
);

INSERT INTO user_payments (id, card_number, ccv, exp_date, user_id) VALUES (
    1,
    1234567890123456,
    123,
    '2077-12-01',
    3
),
(
    2,
    2234567890123456,
    123,
    '1993-12-01',
    3
),
(
    'b14d6ae6-b027-47f1-8676-36ebea57b453',
    5555444433332222,
    777,
    '2077-07-06',
    6
);

-- review-orders inserts
INSERT INTO users (email, password, first_name, last_name, is_active, is_admin) VALUES
(
    -- 7
    'revieworders@example.com',
    'Review10!',
    'Review',
    'Orders',
    true,
    false
);

INSERT INTO orders (id, order_date, shipment_address, payment_id, user_id) VALUES (
    1,
    '2022-12-06',
    '555 Review Rd, Reviewella, RI, 12000, US',
    'b14d6ae6-b027-47f1-8676-36ebea57b453',
    7
);

INSERT INTO order_details (id, quantity, order_id, product_id) VALUES (
    1,
    1,
    1,
    4
),
(
    2,
    1,
    1,
    2
),
(
    3,
    1,
    1,
    1
),
(
    4,
    1,
    1,
    3
);

INSERT INTO PRODUCT_REVIEW (id,comment,rating,product_id,user_id) VALUES (
    1,
    'Liked the bag at first. Unfortunately, I found that every time I put something into the bag - whether my phone, or candy, or my Pomeranian - the items ended up teleporting to a parallel world. Will not be recommending friends!',
    3,
    3,
    2
)