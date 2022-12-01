# README poop


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
    host: smtp@gmail.com
    port: 465
    properties:
      mail:
        smtp:
          auth: false
          starttls:
            enable: false
jwt:
  secret: This is a secret 
```
The `data.sql` file will house some temporary h2 data that testers can use for their E2E and unit tests. It should look something like this. 

``` 
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


INSERT INTO users (id, email, password, first_name, last_name, is_active, is_admin) VALUES
(

    1,
    'testuser@gmail.com',
    'password',
    'Test',
    'User',
    true,
    true

),
(   2,
    'notadmin@gmail.com',
    'password',
    'not',
    'admin',
    true,
    false
);



```
