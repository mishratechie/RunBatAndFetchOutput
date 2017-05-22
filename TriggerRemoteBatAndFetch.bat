set ipaddress=%1
set uname=%2
set pwd=%3
set exeOrBat=%4

PsExec64.exe \\%ipaddress% -u %uname% -p %pwd% %exeOrBat%