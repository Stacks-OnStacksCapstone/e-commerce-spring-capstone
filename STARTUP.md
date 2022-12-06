
## Backend
There aren't many instructions in order to get this application set up and running. 
So our team hopes to help by following these tips. One aspect to keep in mind is that this application actually comes in two repositories. We recommend you get the back end up and running first then the front end. 

#### Recommended Prerequistes: 
* Docker Installed on your machine.
* IntelliJ installed and working.
* npm and node installed. 



### Step 1:
Open up the POM.xml file on intelliJ and add these lines of code into your "plugins" section.

                <plugins>
			        <plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<version>${project.parent.version}</version>
				<configuration>
					<excludes>
						<exclude>
							<groupId>org.projectlombok</groupId>
							<artifactId>lombok</artifactId>
						</exclude>
					</excludes>
				</configuration>
			</plugin>
		</plugins>
The purpose of these lines of code above is to basically include the version of the Spring framework. 

### Step 2:

Go to the SpringSecurityConfig.java file in your repository. It should be in the "securities" directory
and copypaste this line of code:

   `httpSecurity.headers().frameOptions().sameOrigin();`
####
The purpose of the line of code above is to set the configuration for which origins you would like back end to accept. 

### Step 3:

Create an `application.yml` on the same level as your main project directory. The purpose of this file is to add more configuration settings 
for your h2 database instance, and to connect it to the `8080` port on your computer. You will also need to set up an email in order for 
emails to be sent to users to reset their password or receive information about their orders. If you are using gmail, you will need
to enable 2-factor authentication by going to your security settings. After this, create an app password by selecting mail for app,
other for device, and then giving it a name. This will give you special password that will be placed in the yaml file under the username.
The URl in Line 59 in AuthService.java must also be changed to `http://localhost:3000/reset-password/` so that the reset password link 
will redirect the user to the home page where the application is currently running.
####
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

### Step 4:

Afterwards, what you want to do is create a `data.sql` file and put this in a `resources` directory on the same level as your project. 
This file will contain some temporary data you can use to spin up your `h2` database instance. If you don't have at least some data in your h2 database you might receive an error. 
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
 
## Frontend

To get the front end up and running it is actually much simpler than the back end. Follow the steps below. 
### Step 1:

Go to the directory "src/remote/e-commerce-api/eCommerceClient.ts" and comment out:
``` 
 // baseURL: 'http://ecommercespringcapstone-env.eba-gshjzfrb.us-east-1.elasticbeanstalk.com',
```
and replace it with:
``` 
 baseURL: "http://localhost:8080"
```
What this will do is actually change the location 
that the request goes to, to a port on your machine 
"port: 8080". This is the port that your backend 
server is listening on and will 
serve as the interface between the frontend and backend. 

### Step 2: 

Now you will need to build a docker image based off the docker file you have in your project.
In order to do this, on vscode go to the terminal in the bottom and type. 
The image that you build will then be used as a 
blueprint to containerize your application. 

```
docker build -t image_name .
docker container run -p 3000:80 image_name
```