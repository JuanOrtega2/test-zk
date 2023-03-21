# Pruebas con Zookeeper

Para ejecutar las pruebas hay que realizar los siguientes pasos : 

- Modificar el fichero docker-compose.yml para que el directorio compartido apunte al directorio de datos, es aconsejable no cambiar la parte destino /tmp/test-zk

- Crear los containers con **docker-compose up -d**

- Entrar en el docker con el comando : **docker exec -it test-zk_zoo1_1 bash**  (el nombre del container puede variar)

- Comprobar que los ficheros compartidos estan en su sitio: **ls -lrt /tmp/test-zk/** (el directorio puede variar si se ha llamado con otro nombre)

- Crear los nodos base con el comando: **/apache-zookeeper-3.7.0-bin/bin/zkCli.sh < /tmp/test-zk/create-nodes.cmd**

- Lanzar la prueba: **/tmp/test-zk/execute-test.sh**


Finalmente para borrar los containers hay que ejecutar **docker-compose rm -f -s**

## Generacion de datos aleatorios
Para los frikis del java hay un proyecto que incluye una libreria para generar datos aleatorios

Para compilarlo (en el directorio gendata-java) : **mvn clean compile assembly:single**

(Esto genera un fichero .jar con el codigo)

Para ejecutarlo: **java -cp target/data-generator-1.0.0-dep.jar DataGenerator**

(genera un fichero en el directorio local admite un par de parametros, los podeis ver con la opcion -h)
