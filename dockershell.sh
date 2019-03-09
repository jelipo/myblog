#!/bin/bash

config_dir=${CONF_DIR}
app_name=${APP_NAME}
java_opts = ${JAVA_OPTS}

#mkdir -p ${config_dir}
#export CONF_FOLDER=${config_dir}
#
#if [ -f ${config_dir}"/"${app_name}".conf" ];then
#    echo "File '"${config_dir}"/"${app_name}".conf' already existed."
#else
#    echo "Creating '"${config_dir}"/"${app_name}.conf"' and writing."
#    echo "JAVA_OPTS=-Dspring.config.location="${config_dir}"/application.properties\n" > ${config_dir}/${app_name}.conf
#fi
#
#if [ -f ${config_dir}"/application.properties" ];then
#    echo "File '"${config_dir}"/application.properties' already existed."
#else
#    echo "Creating '"${config_dir}"/application.properties' ."
#    echo "" > ${config_dir}/application.properties
#fi

chmod 777 /opt/app/myblog.jar
java -jar  /opt/app/myblog.jar ${java_opts}