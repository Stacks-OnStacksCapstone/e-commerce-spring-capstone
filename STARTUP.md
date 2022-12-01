
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
for your h2 database instance, and to connect it to the `8080` port on your computer.
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
    host: smtp@gmail.com
    port: 465
    properties:
      mail:
        smtp:
          auth: false
          starttls:
            enable: false
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
  
springdoc:
  swagger-ui:
    path: swagger-ui-custom.html
    operationsSorter: method
    
  ```

### Step 4:

Afterwards, what you want to do is create a `data.sql` file and put this in a `resources` directory on the same level as your project. 
This file will contain some temporary data you can use to spin up your `h2` database instance. If you don't have at least some data in your h2 database you might receive an error. 
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

INSERT INTO users (id, email, password, first_name, last_name, is_active, is_admin) VALUES (
    1,
    'testuser@gmail.com',
    'password',
    'Test',
    'User',
    true,
    true
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