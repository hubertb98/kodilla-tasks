@call runcrud.bat
if "%ERRORLEVEL%" == "0" goto startchrome
echo.
echo GRADLEW BUILD has errors - breaking work
goto fail

:fail
echo.
echo Error

:startchrome
@timeout 5
@start chrome.exe http://localhost:8080/crud/v1/task/getTasks