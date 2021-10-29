FROM openjdk:8

WORKDIR /usrapp/bin

ENV PORT 6000
ENV SERVER_POOL http://web1:6000;http://web2:6000;http://web3:6000

COPY /target/classes /usrapp/bin/classes
COPY /target/dependency /usrapp/bin/dependency

CMD ["java","-cp","./classes:./dependency/*","co.edu.escuelaing.loadbalancer.SparkWebServer"]