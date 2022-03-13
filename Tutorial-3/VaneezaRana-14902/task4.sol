//SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.5.0 <0.9.0;

contract Grades {

    struct Student{
    string name;
    uint ERP;
    uint[3] grades;
    }
    
    mapping (uint => Student) public studentMaps;

    function newStudent(string memory _name, uint _ERP, uint _grade1, uint _grade2 , uint _grade3 ) public {
        Student memory newstudent = Student(_name,_ERP,[_grade1,_grade2,_grade3]);
        newstudent.name = _name;
        newstudent.ERP = _ERP;
        newstudent.grades = [_grade1,_grade2,_grade3];
        studentMaps[_ERP]= newstudent;
    }
    function getStudentGrade(uint _ERP, uint _arrayIndex)public view returns(uint){
           Student memory newstudent = studentMaps[_ERP];
           return newstudent.grades[_arrayIndex];
    }
    function getAvgGrade(uint _ERP)public view returns(uint){
        Student memory newstudent = studentMaps[_ERP];
        uint average = (newstudent.grades[0]+newstudent.grades[1]+newstudent.grades[2])/3;
        return average;
    }
}