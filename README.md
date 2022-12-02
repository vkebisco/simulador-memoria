
# Simulador de memória backend


## Para rodar com docker

```
docker run -d -p 8080:8080 vkebisco/memory-simulator-v2

```

## buildando uma versão customizada local com docker 

```
./mvnw spring-boot:build-image
```

## ou simplesmente

```
java -jar memory-simulator.jar
```

## uma documentação do swagger pode ser acessada em

``
http://localhost:8080/swagger-ui/index.html
``
