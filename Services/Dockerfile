FROM amazon/aws-eb-glassfish:5.0-al-onbuild-2.11.1

ADD https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.16/mysql-connector-java-8.0.16.jar ${GLASSFISH_HOME}/glassfish/domains/domain1/lib/

CMD ["/bin/bash"]

EXPOSE 80

ENV TZ=America/Mexico_City

RUN sed -i 's/Xmx512m/Xmx2048m/g' ${GLASSFISH_HOME}/glassfish/domains/domain1/config/domain.xml
RUN sed -i 's/<http default-virtual-server="server">/<http timeout-seconds="80" max-connections="500" default-virtual-server="server">/g' ${GLASSFISH_HOME}/glassfish/domains/domain1/config/domain.xml
RUN sed -i 's/<http max-connections="250" default-virtual-server="server">/<http timeout-seconds="80" max-connections="500" default-virtual-server="server">/g' ${GLASSFISH_HOME}/glassfish/domains/domain1/config/domain.xml

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN asadmin start-domain && \
    asadmin create-jdbc-connection-pool --ping --datasourceclassname com.mysql.cj.jdbc.MysqlConnectionPoolDataSource \
    --restype javax.sql.ConnectionPoolDataSource --steadypoolsize 4 --maxpoolsize 10 \
    --property user=admin:password=n0l0dig4s:characterEncoding=UTF-8:useSSL=false:autoReconnect=true:url="jdbc\:mysql\:\/\/albo-comics-library.clo68xadu8ck.us-east-2.rds.amazonaws.com\:3306\/albo_comics_library_1.0.1" alboComicsLibraryPool && \
    asadmin create-jdbc-resource --connectionpoolid alboComicsLibraryPool jdbc/alboComicsLibrary \
