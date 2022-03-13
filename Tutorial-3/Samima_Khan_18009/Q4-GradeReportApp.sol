//SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.6.0 < 0.9.0;

contract GradeReportApp {

struct Student {
    string name;
    uint erp;
    uint[] grades;
}


mapping(uint=> Student) public erpToStudent;

function addStudent(string memory _name, uint _erp, uint[] memory _grades) public {
  Student memory student = Student(_name, _erp, _grades);
  erpToStudent[_erp] = student;
}

function getGrade(uint _erp) public view returns(uint[] memory) {
 return erpToStudent[_erp].grades;
}



function getAverage(uint _erp) public view returns(uint) {
  uint totalMarks = 0;
 for (uint i = 0; i < erpToStudent[_erp].grades.length; i++){
   totalMarks += erpToStudent[_erp].grades[i];
 }
 return totalMarks/3;
}



}