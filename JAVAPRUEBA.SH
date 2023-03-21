# cd /tmp/test-zk/datos
cd /home/alumnos/ah13/test-zk/test-zk-new/gendata-java
COUNT=1
# Generar los archivos
for i in 1 4 14 21
do
    java -cp target/data-generator-1.0.0-dep.jar DataGenerator -f $i -p /home/alumnos/ah13/test-zk/test-zk-new/datos_prueba2
done