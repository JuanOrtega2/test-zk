
#cd /tmp/test-zk/datos/gendata-java
COUNT=1
# Generar los archivos
# for i in $(seq 1 100)
# do
#     num_records=$((50*i+(i-1)*50))
#     java -cp target/data-generator-1.0.0-dep.jar  DataGenerator -n $num_records -p /home/alumnos/ah13/test-zk/test-zk-new/datos

# done
cd /tmp/test-zk
echo "Start time : " $(TZ=CET date) "-----------------------" >> prueba1.txt

for FILENAME in /tmp/test-zk/datos_restantes/data_*
do
    TSTART=$(date +%s%N | cut -b1-13)
    TZ=CET date "+test_$COUNT starts %Y-%m-%d %H:%M:%S " >> prueba1.txt 
    /apache-zookeeper-3.7.0-bin/bin/zkCli.sh < "$FILENAME" > /dev/null
    TEND=$(date +%s%N | cut -b1-13)
    echo test_$COUNT tiempo_ms $((TEND - TSTART)) >> prueba1.txt
    COUNT=$((COUNT+1))
done

echo "end time : " $(TZ=CET date) "-----------------------" >> prueba1.txt

docker-compose rm -f -s