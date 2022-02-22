cmake_minimum_required(VERSION 3.2)

set(PROJECT_NAME Zadatak7)
set(SOURCE_DIR src)

set(SOURCE ${SOURCE_DIR}/main.cpp)

set(LIBRARIES
	Student
	Professor
)

project(${PROJECT_NAME})

add_executable(zadatak7 ${SOURCE})

add_subdirectory("Student")
add_subdirectory("Professor")

target_link_libraries(zadatak7 ${LIBRARIES})
