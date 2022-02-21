#ifndef PROF_HEADER
#define PROF_HEADER
#include <iostream>

class Professor {
    private:
        std::string professorName;

    public:
        std::string getName(){ return professorName;}
        Professor(std::string name);
};

#endif