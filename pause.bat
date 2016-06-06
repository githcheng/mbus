SET tmpError=%errorlevel%

rem author: wencheng.song 
@rem If %errorlevel% is not 0 then pause; If %errorlevel% is 0, which means there is no error, then do nothing.
@rem ps: In Winndows bat, %errorlevel% is the return code of the last command.

if %tmpError% NEQ 0 (
@Pause
exit %tmpError%
)