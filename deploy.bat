@echo off
setlocal
echo ========================================================
echo   Deploying Employee Leave Management System to Tomcat
echo ========================================================

set "TOMCAT_WEBAPPS=C:\Program Files\Apache Software Foundation\Tomcat 10.1\webapps"
set "WAR_FILE=target\leavemanagement.war"

if not exist "%WAR_FILE%" (
    echo WAR file not found. Running build first...
    call build.bat
    if %ERRORLEVEL% NEQ 0 (
        echo [ERROR] Build failed. Deployment aborted.
        exit /b 1
    )
)

if not exist "%TOMCAT_WEBAPPS%" (
    echo [ERROR] Tomcat webapps directory not found at: %TOMCAT_WEBAPPS%
    exit /b 1
)

echo Copying %WAR_FILE% to %TOMCAT_WEBAPPS% ...
copy /Y "%WAR_FILE%" "%TOMCAT_WEBAPPS%\leavemanagement.war"

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================================
    echo   DEPLOY SUCCESS!
    echo   Application URL: http://localhost:8080/leavemanagement/
    echo ========================================================
) else (
    echo [ERROR] Failed to copy WAR file.
)

endlocal

