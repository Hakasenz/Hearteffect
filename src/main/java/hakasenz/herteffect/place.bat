@echo off
setlocal enabledelayedexpansion

for %%I in (*.java) do (
    set "fileName=%%~nI"
    set "contentFound="
    set "javaFiles="
    for /F "usebackq delims=" %%A in ("%%I") do (
        set "line=%%A"
        set "line=!line: =!"
        if "!line:~0,5!"=="%fileName%Speed" (
            set "contentFound=true"
        )
        if defined contentFound (
            set "javaFiles=!javaFiles!%%A"
        )
        if "!line!"=="HitPlayerSpeed=1; }" (
            set "contentFound="
            if not exist resource\%fileName%.txt (
                break > resource\%fileName%.txt
            )
            (echo !javaFiles!) >> resource\%fileName%.txt
            set "javaFiles="
        )
    )
)
