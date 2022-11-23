
# Simulador de memória backend


## Para rodar com docker

```
docker run -d -p 8080:8080 vkebisco/memory-simulator

```

## buildando uma versão customizada local com docker 

```
./mvnw spring-boot:build-image
```

## ou simplesmente

```
java -jar memory-simulator.jar
```