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
CMAKE_SOURCE_DIR = D:\Dev\HW5\HW5\problem1

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = D:\Dev\HW5\HW5\problem1\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/problem1.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/problem1.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/problem1.dir/flags.make

CMakeFiles/problem1.dir/main.obj: CMakeFiles/problem1.dir/flags.make
CMakeFiles/problem1.dir/main.obj: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=D:\Dev\HW5\HW5\problem1\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/problem1.dir/main.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\problem1.dir\main.obj -c D:\Dev\HW5\HW5\problem1\main.cpp

CMakeFiles/problem1.dir/main.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/problem1.dir/main.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E D:\Dev\HW5\HW5\problem1\main.cpp > CMakeFiles\problem1.dir\main.i

CMakeFiles/problem1.dir/main.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/problem1.dir/main.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S D:\Dev\HW5\HW5\problem1\main.cpp -o CMakeFiles\problem1.dir\main.s

CMakeFiles/problem1.dir/CSI.obj: CMakeFiles/problem1.dir/flags.make
CMakeFiles/problem1.dir/CSI.obj: ../CSI.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=D:\Dev\HW5\HW5\problem1\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/problem1.dir/CSI.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\problem1.dir\CSI.obj -c D:\Dev\HW5\HW5\problem1\CSI.cpp

CMakeFiles/problem1.dir/CSI.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/problem1.dir/CSI.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E D:\Dev\HW5\HW5\problem1\CSI.cpp > CMakeFiles\problem1.dir\CSI.i

CMakeFiles/problem1.dir/CSI.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/problem1.dir/CSI.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S D:\Dev\HW5\HW5\problem1\CSI.cpp -o CMakeFiles\problem1.dir\CSI.s

CMakeFiles/problem1.dir/TestHelper.obj: CMakeFiles/problem1.dir/flags.make
CMakeFiles/problem1.dir/TestHelper.obj: ../TestHelper.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=D:\Dev\HW5\HW5\problem1\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Building CXX object CMakeFiles/problem1.dir/TestHelper.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\problem1.dir\TestHelper.obj -c D:\Dev\HW5\HW5\problem1\TestHelper.cpp

CMakeFiles/problem1.dir/TestHelper.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/problem1.dir/TestHelper.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E D:\Dev\HW5\HW5\problem1\TestHelper.cpp > CMakeFiles\problem1.dir\TestHelper.i

CMakeFiles/problem1.dir/TestHelper.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/problem1.dir/TestHelper.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S D:\Dev\HW5\HW5\problem1\TestHelper.cpp -o CMakeFiles\problem1.dir\TestHelper.s

# Object files for target problem1
problem1_OBJECTS = \
"CMakeFiles/problem1.dir/main.obj" \
"CMakeFiles/problem1.dir/CSI.obj" \
"CMakeFiles/problem1.dir/TestHelper.obj"

# External object files for target problem1
problem1_EXTERNAL_OBJECTS =

problem1.exe: CMakeFiles/problem1.dir/main.obj
problem1.exe: CMakeFiles/problem1.dir/CSI.obj
problem1.exe: CMakeFiles/problem1.dir/TestHelper.obj
problem1.exe: CMakeFiles/problem1.dir/build.make
problem1.exe: CMakeFiles/problem1.dir/linklibs.rsp
problem1.exe: CMakeFiles/problem1.dir/objects1.rsp
problem1.exe: CMakeFiles/problem1.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=D:\Dev\HW5\HW5\problem1\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_4) "Linking CXX executable problem1.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\problem1.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/problem1.dir/build: problem1.exe

.PHONY : CMakeFiles/problem1.dir/build

CMakeFiles/problem1.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\problem1.dir\cmake_clean.cmake
.PHONY : CMakeFiles/problem1.dir/clean

CMakeFiles/problem1.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" D:\Dev\HW5\HW5\problem1 D:\Dev\HW5\HW5\problem1 D:\Dev\HW5\HW5\problem1\cmake-build-debug D:\Dev\HW5\HW5\problem1\cmake-build-debug D:\Dev\HW5\HW5\problem1\cmake-build-debug\CMakeFiles\problem1.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/problem1.dir/depend

