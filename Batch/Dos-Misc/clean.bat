@Echo off


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void main()
@rem ---
@rem ---   Deletes all directories within this project which contain temporary data
@rem ---   (e.g. compiler output, log files, etc.).
@rem ---

:main

	call:defineMacros
	call:setUp

	echo.
	echo project dir .... %projectDir%
	echo.

	for /L %%i in (1, 1, %directory.length%) do (

		setlocal EnableDelayedExpansion

			call:deleteDirectory !directory[%%i]!

		endlocal
	)

	call:tearDown

	pause

%return%


@rem ================================================================================
@rem ===
@rem ===   Internal Subroutines
@rem ===

@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void defineMacros()
@rem ---
@rem ---   The subroutine defines required macros.
@rem ---

:defineMacros

	set "ifError=set foundErr=1&(if errorlevel 0 if not errorlevel 1 set foundErr=)&if defined foundErr"
	
	set "cprintln=echo"
	set "cprint=echo|set /p="
	
	set "return=exit /b"

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void setUp()
@rem ---
@rem ---   Declares and initializes variables and constants.
@rem ---

:setUp

	set projectDir=%~dp0..\..\Sources\

	set directory.length=7

	set directory[1]=%projectDir%.data\
	set directory[2]=%projectDir%Core\classes\
	set directory[3]=%projectDir%Core-Tests\classes\
	set directory[4]=%projectDir%LogViewer\classes\
	set directory[5]=%projectDir%LogViewer-Tests\classes\
	set directory[6]=%projectDir%Version\classes\

	set directory[7]=%projectDir%..\Batch\classes\

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void tearDown()
@rem ---
@rem ---   Declared variables and constants are cleaned up.
@rem ---

:tearDown

	for /L %%i in (1, 1, %directory.length%) do (

		set directory[%%i]=
	)

	set directory.length=

	set projectDir=

%return%


@rem --------------------------------------------------------------------------------
@rem ---
@rem ---   void deleteDirectory(String _path)
@rem ---
@rem ---   Deletes the specified directory and all its content.
@rem ---
@rem ---
@rem ---   @param _path
@rem ---          the specified path represents a directory
@rem ---

:deleteDirectory

	set "_path=%1"
	if '%_path%'=='' (

		echo No directory has been specified! >&2
		%return% 2
	)
	set "_path=%_path:"=%"


	if exist %_path% (

		echo deleting %_path%
		rmdir /S /Q %_path%

	) else (

		echo %_path% doesn't exist.
	)


	set _path=

%return%
