# Simulador de un diccionario con varios servicios implementado en Java y Node

#### Para levantar las bases de datos y el broker de rabbitmq en docker:

``docker-compose -f docker/dictionary-stack.yaml up -d --build``

---

#### Ejecucion de scripts para levantar los servicios

- Levantar bases de datos y broker de rabbit
```
node exec_aux_services.js
```

- Compilar servicios
```
node build.js
```

- Levantar servicios
```
node exec.js
```