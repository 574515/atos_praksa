#include <iostream>
#include <cstring>
using namespace std;

class Student {
    private:
        string studentName;
    public:
        string getName(){
            return studentName;
        }
        Student(){}
        ~Student(){}
        Student(string name){
            studentName = name;
        }
        friend ostream& operator<<(ostream& os, Student& student){
            os << "Student's name: " << student.getName() << "\n";
            return os;
        }
};

class Professor {
    private:
        string professorName;
    public:
        string getName(){
            return professorName;
        }
        Professor(){}
        ~Professor(){}
        Professor(string name){
            professorName = name;
        }
        friend ostream& operator<<(ostream& os, Professor& professor){
            os << "Professor's name: " << professor.getName() << "\n";
            return os;
        }
};

int main(){
    string name;
    cout << "Enter student's name: ";
    cin >> name;
    Student student(name);
    cout << "Enter professor's name: ";
    cin >> name;
    Professor professor(name);
    cout << "\n";
    cout << student;
    cout << professor;
    return 0;
}