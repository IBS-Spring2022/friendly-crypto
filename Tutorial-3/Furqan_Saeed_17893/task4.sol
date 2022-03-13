//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract task4{

    mapping(uint => Student) erpToStudents;

    struct Student{
    string name;
    uint[3] classToGrade;
    }

    function newStudent(uint erp, string memory _name, uint _grade1, uint _grade2, uint _grade3) public {
        Student storage student = erpToStudents[erp];
        student.name = _name;
        student.classToGrade = [_grade1, _grade2, _grade3];
    }

    function getGradeFromERP(uint erp) public view returns(uint[3] memory){
        Student memory student = erpToStudents[erp];
        return student.classToGrade;
    }

    function getAverageGradeFromERP(uint erp) public view returns(uint){
        Student memory student = erpToStudents[erp];
        uint[3] memory grades = student.classToGrade;
        uint avg = 0;
        uint i = 0;

        for(i = 0; i < 3; i++){
            avg = avg + grades[i];
        }

        return avg / 3;
    }
}