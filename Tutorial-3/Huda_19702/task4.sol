pragma solidity >= 0.6.0 < 0.9.0;

contract Task4 {

    struct Student {
        uint ERP;
        string name;
        int[3] grade;
    }

    mapping(uint => Student) public students;
    
    function addStudent(uint _ERP, string memory _name, int[3] memory _grade) public {
        students[_ERP] = Student(_ERP, _name, _grade);
    }

    function getGrades (uint _ERP) public view returns (int[3] memory _grade) {
        return students[_ERP].grade;
    }

    function getAverageGrades(uint _ERP) public view returns (int _grade) {
        return ((students[_ERP].grade[0]+students[_ERP].grade[1]+students[_ERP].grade[2])/3);
    }

}