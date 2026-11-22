@echo off
setlocal
echo ========================================================
echo   Building Employee Leave Management System
echo ========================================================

set "JAVA_HOME=C:\Program Files\Java\jdk-26.0.2"
set "MAVEN_CMD=C:\Users\Administrator\.m2\wrapper\dists\apache-maven-3.9.16-bin\5grr65jo27hi51sujmtcldfovl\apache-maven-3.9.16\bin\mvn.cmd"

if not exist "%JAVA_HOME%" (
    echo [ERROR] JDK not found at %JAVA_HOME%
    exit /b 1
)

if not exist "%MAVEN_CMD%" (
    echo [ERROR] Maven not found at %MAVEN_CMD%
    exit /b 1
)

echo Using JAVA_HOME: %JAVA_HOME%
echo Running: mvn clean package ...
call "%MAVEN_CMD%" clean package

if %ERRORLEVEL% EQU 0 (
    echo.
    echo ========================================================
    echo   BUILD SUCCESS!
    echo   WAR package generated at: target\leavemanagement.war
    echo ========================================================
) else (
    echo.
    echo [ERROR] Build failed with exit code %ERRORLEVEL%
)

endlocal

