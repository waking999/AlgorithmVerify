@echo off
set ANT_HOME=D:\dev\apache-ant-1.9.2
set PATH=%PATH%;%ANT_HOME%\bin
ant.bat -buildfile D:\dev\workspace\AlgorithmVerify\src\main\java\build.xml
@exit