
:: Clean

set CL_DIR= classes
set EXP_DIR= api_export_files
set JC_PATH=%CL_DIR%;lib\api.jar;lib\102241_Annex_D.jar;lib\31130_Annex_D_V721.jar;lib\usim-api-for-java-card-REL-8.jar;
set JCFLAGS=-g -d %CL_DIR% -classpath "%JC_PATH%" 
set JAVAC_CP=.\lib\api.jar;
::rmdir /s /q ..\classes
::mkdir ..\classes
:: Compile samples
echo %JAVA_HOME%
echo %CL_DIR%
echo %EXP_DIR%

%JAVA_HOME%\bin\javac  -source 1.3 -target 1.3 -bootclasspath %JAVA_HOME%\jre\lib\rt.jar %JCFLAGS% classes\testpackage\*.java


:: Convert samples
::cd ..
cd classes

echo Sarabjeet Singh
call %JC_HOME%\bin\converter -config ..\src\testpackage\HelloWorld.opt
pause
cd ..

echo Cap Varifier
 :: Cap Varifier
call %JC_HOME%\bin\verifycap %EXP_DIR%\javacard\framework\javacard\framework.exp %EXP_DIR%\java\lang\javacard\lang.exp  %EXP_DIR%\uicc\access\javacard\access.exp %EXP_DIR%\uicc\access\fileadministration\javacard\fileadministration.exp %EXP_DIR%\uicc\toolkit\javacard\toolkit.exp %EXP_DIR%\uicc\usim\toolkit\javacard\toolkit.exp  %EXP_DIR%\uicc\access\fileadministration\javacard\fileadministration.exp %EXP_DIR%\javacard\security\javacard\security.exp %EXP_DIR%\javacardx\crypto\javacard\crypto.exp %EXP_DIR%\uicc\usim\geolocation\javacard\geolocation.exp %CL_DIR%\testpackage\javacard\testpackage.cap
 :: EXP Varifier
call %JC_HOME%\bin\verifyexp %CL_DIR%\testpackage\javacard\testpackage.exp
 :: Script generator
echo Script generator
call %JC_HOME%\bin\scriptgen %CL_DIR%\testpackage\javacard\testpackage.cap
 
pause