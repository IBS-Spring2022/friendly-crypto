//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract StudentGradeReportApp{
    struct StudentInfo{
        string name;
        string erp;
        uint[3] subjectgrades;
    }

    mapping (string => StudentInfo) studentMap;

    function createStudent (string memory _name, string memory _erp, uint _subject1, uint _subject2, uint _subject3) public{
        StudentInfo memory newStudent = StudentInfo(_name, _erp, [_subject1, _subject2, _subject3]);
        studentMap[_erp] = newStudent;
    }

    function getGrades (string memory _erp, uint8 _subjectIndex) public view returns (uint){
        StudentInfo memory student = studentMap [_erp];
        return student.subjectgrades[_subjectIndex];
    }

    function Average(string memory _erp) public view returns (uint){
        StudentInfo memory student = studentMap [_erp];
        uint avg = (student.subjectgrades[0] + student.subjectgrades[1] + student.subjectgrades[2])/3;
        return avg;
    }

}

