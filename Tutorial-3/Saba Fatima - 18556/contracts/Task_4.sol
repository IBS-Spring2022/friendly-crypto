pragma solidity >= 0.5.0 < 0.9.0;

contract Task_4{

    struct Student_report{
        string name;
        string ERP;
        int[3] grades;
    }
    //Student_report[] public students;
     mapping(string => Student_report) public students;

    function addStudent(string memory _name, string memory _ERP, int[3] memory _grade) public {
        students[_ERP] = Student_report(_name, _ERP, _grade);
        //students.push(Student_report({name: _name, ERP: _ERP, grades: _grade}));
    }
    function getGrade(string memory _ERP) public view returns (int[3] memory){
        return students[_ERP].grades;
    }
    function avg_Grade(string memory _ERP) public view returns (int){
        return ((students[_ERP].grades[0]+ students[_ERP].grades[1] + students[_ERP].grades[2])/3);
    }

    
}