cmake_minimum_required(VERSION 3.2)
project(zadatak6 VERSION 1.0.0)

set(CMAKE_RUNTIME_OUTPUT_DIRECTORY "${CMAKE_SOURCE_DIR}/build")
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY "${CMAKE_SOURCE_DIR}/lib")

add_executable(zadatak6 "${CMAKE_CURRENT_SOURCE_DIR}/src/main.cpp")

target_link_libraries(zadatak6 PRIVATE "${CMAKE_SOURCE_DIR}/lib/libProfessor.so")
target_link_libraries(zadatak6 PRIVATE "${CMAKE_SOURCE_DIR}/lib/libStudent.so")