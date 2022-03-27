# estimator

### In dev mode:
- checkout the code
- clean and package

```
mvn clean package
```

make sure that there is no application using the port 8888, if it is the case, then you can change the configuration at
the level of the file src/main/resources/application.properties

```
netstat -tulpn | grep 8888
```
Next, you can start the application, using the following code line:

```
mvn spring-boot:start
``` 
- In order to test "connect wallet" feature, you can use the script clientScript.sh 
note in dev mode set the variable PORT to 8888

### In production mode

you can import the docker image 
```
docker import estimator_image.tar estimator:latest
```

you can check if the image is correctly imported
```
docker image ls -a
```

then you can run the docker as follow:

```
sudo docker run -p8887:8888 -it estimator:latest sh
```

Finally, in the prompt, you can  start the java application:

```
java -jar estimator.jar
```

Then, before you run the clientScript.sh make sure that the variable PORT is set to 8887
