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
include CMakeFiles/exercise1.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/exercise1.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/exercise1.dir/flags.make

CMakeFiles/exercise1.dir/exercise1.cpp.obj: CMakeFiles/exercise1.dir/flags.make
CMakeFiles/exercise1.dir/exercise1.cpp.obj: ../exercise1.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=D:\Dev\lab10\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/exercise1.dir/exercise1.cpp.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\exercise1.dir\exercise1.cpp.obj -c D:\Dev\lab10\exercise1.cpp

CMakeFiles/exercise1.dir/exercise1.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/exercise1.dir/exercise1.cpp.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E D:\Dev\lab10\exercise1.cpp > CMakeFiles\exercise1.dir\exercise1.cpp.i

CMakeFiles/exercise1.dir/exercise1.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/exercise1.dir/exercise1.cpp.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S D:\Dev\lab10\exercise1.cpp -o CMakeFiles\exercise1.dir\exercise1.cpp.s

# Object files for target exercise1
exercise1_OBJECTS = \
"CMakeFiles/exercise1.dir/exercise1.cpp.obj"

# External object files for target exercise1
exercise1_EXTERNAL_OBJECTS =

exercise1.exe: CMakeFiles/exercise1.dir/exercise1.cpp.obj
exercise1.exe: CMakeFiles/exercise1.dir/build.make
exercise1.exe: CMakeFiles/exercise1.dir/linklibs.rsp
exercise1.exe: CMakeFiles/exercise1.dir/objects1.rsp
exercise1.exe: CMakeFiles/exercise1.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=D:\Dev\lab10\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable exercise1.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\exercise1.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/exercise1.dir/build: exercise1.exe

.PHONY : CMakeFiles/exercise1.dir/build

CMakeFiles/exercise1.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\exercise1.dir\cmake_clean.cmake
.PHONY : CMakeFiles/exercise1.dir/clean

CMakeFiles/exercise1.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" D:\Dev\lab10 D:\Dev\lab10 D:\Dev\lab10\cmake-build-debug D:\Dev\lab10\cmake-build-debug D:\Dev\lab10\cmake-build-debug\CMakeFiles\exercise1.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/exercise1.dir/depend
