//SPDX-License-Identifier: GPL-3.0
pragma solidity >= 0.6.0 <0.9.0;

contract GradeReport {
    struct Student {
        uint ERP;
        string Name;
        uint[] marks;
    }
    mapping(uint => Student) public students;

    function addStudent(uint _erp, string memory _name, uint[] memory _marks) public{
        students[_erp] =  Student({ERP:_erp, Name:_name, marks: _marks });
    }

    function getGrades(uint _erp) view public returns (uint[] memory){
        return students[_erp].marks;
    }

    function averageGrade(uint _erp) view public returns (uint256){
        Student memory student = students[_erp];
        uint average = 0;
        for(uint i = 0; i<student.marks.length; i++){
            average += student.marks[i];
        }
        return average/student.marks.length;
    }
} 
