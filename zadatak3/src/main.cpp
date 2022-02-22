#include <iostream>
#include <cstring>
#include "Professor.h"
#include "Student.h"

int main(){
    std::string name;

    std::cout << "Enter student's name: ";
    std::cin >> name;
    Student student(name);
    std::cout << "Enter professor's name: ";
    std::cin >> name;
    Professor professor(name);

    std::cout << "\nStudent's name: " << student.getName() << std::endl;
    std::cout << "Professor's name: " << professor.getName() << std::endl;
    return 0;
}