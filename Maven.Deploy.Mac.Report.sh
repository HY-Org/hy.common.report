#!/bin/sh

mvn deploy:deploy-file -Dfile=hy.common.report.jar                              -DpomFile=./src/META-INF/maven/org/hy/common/report/pom.xml -DrepositoryId=thirdparty -Durl=http://HY-ZhengWei:1481/repository/thirdparty
mvn deploy:deploy-file -Dfile=hy.common.report-sources.jar -Dclassifier=sources -DpomFile=./src/META-INF/maven/org/hy/common/report/pom.xml -DrepositoryId=thirdparty -Durl=http://HY-ZhengWei:1481/repository/thirdparty
