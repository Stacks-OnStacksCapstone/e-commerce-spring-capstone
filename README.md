# README 


## General Application Information

Congo is an application that is really similiar to amazon.
There are 2 different types of users on our product, a customer and an administrator. 
Each user has a different level of access depending on 
whether they are a customer or not. 
For example customers can search for a product, 
create an account, log in, edit their profile, edit their orders, reset their password, 
change the website from dark to light mode. 
Admins on the site can create new products, set the price, image and title. 

## Prerequistes/Additional Software Needed
Setting up the software does take a bit of prework please see the steps on the `STARTUP.MD` 
file on our git repository for a more detailed walk through. 
### Software Requirements and Dependencies
* Mac OS/Windows/Linux Machine.
* Docker installed.
* IntelliJ as well as JDK 8.
* Maven, Spring, JWT, h2.
* JPA
* PostgreSQL
* nginx server
* VScode

## Environment Variables

You will need to set environment 
and configuration variables 
on both the frontend and backend repositories. 
A more detailed walkthrough 
can be found on our `STARTUP.MD` file. 

An important aspect to note is that you will need to create a `data.sql` file and `application.yml` file. 
The `application.yml` file will have the JWT credentials as well as h2 configuration to run on your machine. 
```
server:
  port: 8080
  forward-headers-strategy: framework
spring:
  jpa:
    hibernate:
      ddl-auto: create-drop
    database-platform: org.hibernate.dialect.H2Dialect
    defer-datasource-initialization: true
  datasource:
    url: jdbc:h2:mem:memdb
    driver-class-name: org.h2.Driver
    username: sa
    password: password
  h2:
    console.enabled: true
  mail:
    host: smtp.gmail.com
    username: @gmail.com
    password: 
    port: 465
    properties:
      "mail.smtp.ssl.enable": true
      "mail.smtp.auth": true
      "mail.smtp.starttls.enable": true
springdoc:
  swagger-ui:
    path: swagger-ui-custom.html
    operationsSorter: method


management:
  endpoints:
    web:
      exposure:
        include: mappings
jwt:
  secret: This is a secret
```
The `data.sql` file will house some temporary h2 data that testers can use for their E2E and unit tests. It should look something like this. 

``` 
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
);

--USER PAYMENTS
INSERT INTO user_payments(id, card_number, ccv, exp_date, user_id) VALUES
(
    'safecard111',
    '9999888877776666',
    '123',
    '2029-12-22',
    1
),
(
    'notincard233',
    '1111222233334444',
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
    'safecard111',
    1
),
(
    2,
    '2020-12-27',
    '1234 This Rd. Sometown, FL',
    'safecard111',
    1
),
(
    3,
    '2020-12-27',
    '1234 This Rd. Sometown, FL',
    'notincard233',
    2
),
(
    4,
    '2020-12-27',
    '1234 This Rd. Sometown, FL',
    'notincard233',
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

```
