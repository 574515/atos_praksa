#ifndef STUD_HEADER
#define STUD_HEADER
#include <iostream>

class Student {
    private:
        std::string studentName;

    public:
        std::string getName(){ return studentName;}
        Student(std::string name);
};

#endif