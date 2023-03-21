# cd /tmp/test-zk/datos
cd /home/alumnos/ah13/test-zk/test-zk-new/gendata-java
COUNT=1
# Generar los archivos
for i in 1 6 11 21 24
do
    num_records=$((100*i-50))
    java -cp target/data-generator-1.0.0-dep.jar DataGenerator -n $num_records -p /home/alumnos/ah13/test-zk/test-zk-new/datos_restantes
    echo "this is the number file " $i "wuth number of records" $num_records
done