FROM openjdk
ADD target/capstone-backend.jar capstone-backend
ENTRYPOINT ["java", "-jar","capstone-backend"]
EXPOSE 8080