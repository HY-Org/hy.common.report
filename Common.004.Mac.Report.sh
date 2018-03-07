#!/bin/sh

cd ./bin


rm -R ./org/hy/common/report/junit


jar cvfm hy.common.report.jar MANIFEST.MF META-INF org

cp hy.common.report.jar ..
rm hy.common.report.jar
cd ..





cd ./src
jar cvfm hy.common.report-sources.jar MANIFEST.MF META-INF org 
cp hy.common.report-sources.jar ..
rm hy.common.report-sources.jar
cd ..
