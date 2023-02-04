

cd /tmp/test-zk

echo "Start time : " $(TZ=CET date) "-----------------------"


for COUNT in $(seq 1 100)
do
   TSTART=$(date +%s%N | cut -b1-13)
   TZ=CET date "+%Y-%m-%d %H:%M:%S"
   /apache-zookeeper-3.7.0-bin/bin/zkCli.sh < data.cmd > /dev/null
   TEND=$(date +%s%N | cut -b1-13)
   echo test $COUNT tiempo_ms $((TEND - TSTART))
done

echo "end time : " $(TZ=CET date) "-----------------------"
