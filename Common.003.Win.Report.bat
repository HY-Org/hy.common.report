

del /Q hy.common.report.jar
del /Q hy.common.report-sources.jar


call mvn clean package
cd .\target\classes

rd /s/q .\org\hy\common\report\junit


jar cvfm hy.common.report.jar META-INF/MANIFEST.MF META-INF org

copy hy.common.report.jar ..\..
del /q hy.common.report.jar
cd ..\..

pause



cd .\src\main\java
xcopy /S ..\resources\* .
jar cvfm hy.common.report-sources.jar META-INF\MANIFEST.MF META-INF org 
copy hy.common.report-sources.jar ..\..\..
del /Q hy.common.report-sources.jar
rd /s/q META-INF
cd ..\..\..
