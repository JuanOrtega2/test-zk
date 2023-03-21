
echo "Start time : " $(TZ=CET date) "-----------------------" >> prueba2.txt

cd /tmp/test-zk
for FILENAME in /tmp/test-zk/datos_prueba2/data_*
do
    echo "Start time : " $(TZ=CET date) "file: " "$FILENAME" "-----------------------" >> prueba2.txt
    for COUNT in $(seq 1 100)
    do
        TSTART=$(date +%s%N | cut -b1-13)
        TZ=CET date "+test_$COUNT starts %Y-%m-%d %H:%M:%S" >> prueba2.txt 
        /apache-zookeeper-3.7.0-bin/bin/zkCli.sh < "$FILENAME" > /dev/null
        TEND=$(date +%s%N | cut -b1-13)
        echo test_$COUNT tiempo_ms $((TEND - TSTART)) >> prueba2.txt
    done
    echo "end time : " $(TZ=CET date) "file: " "$FILENAME" "-----------------------" >> prueba2.txt
done
echo "end time : " $(TZ=CET date) "-----------------------" >> prueba2.txt