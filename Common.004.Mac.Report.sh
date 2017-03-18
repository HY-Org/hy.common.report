#!/bin/sh

cd ./bin

rm -R ./org/hy/common/report/junit

jar cvfm hy.common.report.jar MANIFEST.MF LICENSE org

cp hy.common.report.jar ..
rm hy.common.report.jar
cd ..

