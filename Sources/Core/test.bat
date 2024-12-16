@Echo Off

@rem ================================================================================
@rem ===
@rem ===   int main(String... args)
@rem ===
@rem ===   The main body of this starter script.
@rem ===
@rem ===
@rem ===   @param args
@rem ===          all specified command line arguments
@rem ===
@rem ===   @return An exit code of this batch script. 0 zero represents everything
@rem ===           is OK. An error code greater than 0 represents a processing error.
@rem ===
@rem ===


call:resetErrorLevel
call:defineMacros


set JCP=.\classes
set JCP=%JCP%;..\..\Libraries\Jmul\jmul-2.1.5.jar
set JCP=%JCP%;..\..\Libraries\Jmul\properties

set STARTING_CLASS=assistant.TestRunner

call:resetErrorLevel

@rem start /B java -cp %JCP% %STARTING_CLASS% %*
@rem java -cp %JCP% %STARTING_CLASS% %*
start /B /WAIT java -cp %JCP% %STARTING_CLASS% %*
if %ERRORLEVEL%==0 (

	rem OK
	echo ^(%ERRORLEVEL%^)

) else (

	if %ERRORLEVEL%==130 (

		echo ^(%ERRORLEVEL%^) Shutdown by Ctrl+C.
		pause
		%return% 0

	) else (

		echo ERROR %ERRORLEVEL%: An error occurred while invoking the java application! >&2
		pause
		%return% %ERRORLEVEL%
	)
)



@rem %ifError% (
@rem 
@rem 	echo An error happened ^(%ERROROLEVEL%^)!
@rem 
@rem ) else (
@rem 
@rem 	echo Good Bye.
@rem )

%return%


@rem ================================================================================
@rem ===
@rem ===   Internal Subroutines
@rem ===

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   int resetErrorLevel()
@rem ---
@rem ---   Resets the error level (i.e. the currently set exit code) to 0.
@rem ---
@rem ---
@rem ---   @return Returns 0 and overrides the previously set exit code.
@rem ---
@rem ---

:resetErrorLevel

exit /b 0


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   int defineMacros()
@rem ---
@rem ---   Defines macros that are used frequently (i.e. packs certain expressions
@rem ---   into an environment variable). The interpreter expands the macros at
@rem ---   runtime.
@rem ---
@rem ---
@rem ---   @return An exit code of this batch script. 0 zero represents everything
@rem ---           is OK. An error code greater than 0 represents a processing error.
@rem ---
@rem ---

:defineMacros

	set "ifError=set foundErr=1&(if errorlevel 0 if not errorlevel 1 set foundErr=)&if defined foundErr"
	
	set "cprintln=echo"
	set "cprint=echo|set /p="
	
	set "return=exit /b"

%return%
