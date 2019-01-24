# puma-wos-importer

A java program to interface the PUMA Corpus Creator application with the Web of science api in order to add some publication to the corpus. This program require a valid subscribtion to the web of science web API.

Web of science api reference : 
AMR API : http://ipscience-help.thomsonreuters.com/LAMRService/WebServicesOverviewGroup/overview.html
WOS API : http://ipscience-help.thomsonreuters.com/wosWebServicesExpanded/WebServicesExpandedOverviewGroup/Introduction.html

# Quick start

```bash
mvn clean
export PUMA_IMPORTER_WOS_PASSWORD='WOS PASSWORD'
export PUMA_IMPORTER_WOS_USER='WOS USER'
export PUMA_IMPORTER_WOS_AMR_PASSWORD='AMR PASSWORD'
export PUMA_IMPORTER_WOS_AMR_USER='AMR USER'
export PUMA_IMPORTER_WOS_BACK_UP_DIRECTORY='/tmp'

export PUMA_IMPORTER_WOS_VM_ARGS='-Dserver.address=127.0.0.1'
export PUMA_IMPORTER_WOS_PORT=8080

mvn package

./run.sh
```

# Throttle

Some part of the web of science have usage limit and can be subject to thottle of 5 minutes for the authentification
