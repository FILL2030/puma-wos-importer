#!/bin/sh
#
# Copyright 2019 Institut Laue–Langevin
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

#####################################
#           default values          #
#####################################

default_wos_user=""
default_wos_password=""
default_wos_amr_user=""
default_wos_amr_password=""
default_wos_backup_directory="/tmp"

export PUMA_IMPORTER_WOS_VM_ARGS_DEFAULT=
export PUMA_IMPORTER_WOS_PORT_DEFAULT=8080

#####################################
#    environment variables names    #
######################################
# PUMA_IMPORTER_WOS_USER
# PUMA_IMPORTER_WOS_PASSWORD
# PUMA_IMPORTER_WOS_AMR_USER
# PUMA_IMPORTER_WOS_AMR_PASSWORD
# PUMA_IMPORTER_WOS_BACK_UP_DIRECTORY
#####################################



# PUMA_IMPORTER_WOS_SPRING_PROFILE
if [ -z ${PUMA_IMPORTER_WOS_SPRING_PROFILE} ]; then
        export PUMA_IMPORTER_WOS_SPRING_PROFILE=dev
fi
echo "PUMA_IMPORTER_WOS_SPRING_PROFILE : $PUMA_IMPORTER_WOS_SPRING_PROFILE";



# PUMA_IMPORTER_WOS_USER
if [ -z ${PUMA_IMPORTER_WOS_USER} ]; then
        export PUMA_IMPORTER_WOS_USER=$default_wos_user
fi
echo "PUMA_IMPORTER_WOS_USER : $PUMA_IMPORTER_WOS_USER";



# PUMA_IMPORTER_WOS_PASSWORD
if [ -z ${PUMA_IMPORTER_WOS_PASSWORD} ]; then
        export PUMA_IMPORTER_WOS_PASSWORD=$default_wos_password
fi
echo "PUMA_IMPORTER_WOS_PASSWORD : $PUMA_IMPORTER_WOS_PASSWORD";



# PUMA_IMPORTER_WOS_AMR_USER
if [ -z ${PUMA_IMPORTER_WOS_AMR_USER} ]; then
        export PUMA_IMPORTER_WOS_AMR_USER=$default_wos_amr_user
fi
echo "PUMA_IMPORTER_WOS_AMR_USER : $PUMA_IMPORTER_WOS_AMR_USER";



# PUMA_IMPORTER_WOS_AMR_PASSWORD
if [ -z ${PUMA_IMPORTER_WOS_AMR_PASSWORD} ]; then
        export PUMA_IMPORTER_WOS_AMR_PASSWORD=$default_wos_amr_password
fi
echo "PUMA_IMPORTER_WOS_AMR_PASSWORD : $PUMA_IMPORTER_WOS_AMR_PASSWORD";



# PUMA_IMPORTER_WOS_BACK_UP_DIRECTORY
if [ -z ${PUMA_IMPORTER_WOS_BACK_UP_DIRECTORY} ]; then
        export PUMA_IMPORTER_WOS_BACK_UP_DIRECTORY=$default_wos_backup_directory
fi
echo "PUMA_IMPORTER_WOS_BACK_UP_DIRECTORY : $PUMA_IMPORTER_WOS_BACK_UP_DIRECTORY";

if [ -z ${PUMA_IMPORTER_WOS_PORT} ]; then
        export PUMA_IMPORTER_WOS_PORT="$PUMA_IMPORTER_WOS_PORT_DEFAULT"
        echo "PUMA_IMPORTER_WOS_PORT set to $PUMA_IMPORTER_WOS_PORT"
else
        echo "PUMA_IMPORTER_WOS_PORT defined as $PUMA_IMPORTER_WOS_PORT"
fi

if [ -z "$PUMA_IMPORTER_WOS_VM_ARGS" ]; then
	export PUMA_IMPORTER_WOS_VM_ARGS="$PUMA_IMPORTER_WOS_VM_ARGS_DEFAULT"
	echo "PUMA_IMPORTER_WOS_VM_ARGS set to $PUMA_IMPORTER_WOS_VM_ARGS"
else
	echo "PUMA_IMPORTER_WOS_VM_ARGS defined as $PUMA_IMPORTER_WOS_VM_ARGS"
fi


java  $PUMA_IMPORTER_WOS_VM_ARGS -Dspring.profiles.active=$PUMA_IMPORTER_WOS_SPRING_PROFILE -jar target/puma-wos-importer.jar --server.port=$PUMA_IMPORTER_WOS_PORT

