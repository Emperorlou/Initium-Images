for /f "tokens=2 delims==" %%I in ('wmic os get localdatetime /format:list') do set datetime=%%I
SET datetime=%datetime:~0,8%-%datetime:~8,6%

git -C c:\Universe\Initium-Resources fetch origin
FOR /F "tokens=* USEBACKQ" %%F IN (`git -C c:\Universe\Initium-Resources rev-list HEAD...origin/master --count`) DO (
	SET var=%%F
)
ECHO %var%

IF %var% GTR 0 (
	c:\Universe\Initium-Resources\deploy\testdeploydo.bat>c:\Universe\Initium-Resources\deploy\logs\testdeploy-%datetime%.log


)
