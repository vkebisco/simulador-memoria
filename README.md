
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
mvn package
java -jar memory-simulator.jar
```

## uma documentação do swagger pode ser acessada em

``
http://localhost:8080/swagger-ui/index.html
``

### observação:
ao setar as partições, use o valor do campo 'tipoAlocacao' como:
1 - best fit
2 - worst fit
3 - first fit

ex para best fit:

 ```
 {
  "particoes": [
    100
  ],
  "tipoAlocacao": 1
}
 ```
