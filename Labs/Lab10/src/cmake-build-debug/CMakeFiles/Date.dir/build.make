# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2020.2.4\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2020.2.4\bin\cmake\win\bin\cmake.exe" -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = D:\Dev\lab10

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = D:\Dev\lab10\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Date.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Date.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Date.dir/flags.make

CMakeFiles/Date.dir/Date.cpp.obj: CMakeFiles/Date.dir/flags.make
CMakeFiles/Date.dir/Date.cpp.obj: ../Date.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=D:\Dev\lab10\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Date.dir/Date.cpp.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Date.dir\Date.cpp.obj -c D:\Dev\lab10\Date.cpp

CMakeFiles/Date.dir/Date.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Date.dir/Date.cpp.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E D:\Dev\lab10\Date.cpp > CMakeFiles\Date.dir\Date.cpp.i

CMakeFiles/Date.dir/Date.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Date.dir/Date.cpp.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S D:\Dev\lab10\Date.cpp -o CMakeFiles\Date.dir\Date.cpp.s

# Object files for target Date
Date_OBJECTS = \
"CMakeFiles/Date.dir/Date.cpp.obj"

# External object files for target Date
Date_EXTERNAL_OBJECTS =

Date.exe: CMakeFiles/Date.dir/Date.cpp.obj
Date.exe: CMakeFiles/Date.dir/build.make
Date.exe: CMakeFiles/Date.dir/linklibs.rsp
Date.exe: CMakeFiles/Date.dir/objects1.rsp
Date.exe: CMakeFiles/Date.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=D:\Dev\lab10\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable Date.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\Date.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Date.dir/build: Date.exe

.PHONY : CMakeFiles/Date.dir/build

CMakeFiles/Date.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\Date.dir\cmake_clean.cmake
.PHONY : CMakeFiles/Date.dir/clean

CMakeFiles/Date.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" D:\Dev\lab10 D:\Dev\lab10 D:\Dev\lab10\cmake-build-debug D:\Dev\lab10\cmake-build-debug D:\Dev\lab10\cmake-build-debug\CMakeFiles\Date.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Date.dir/depend

