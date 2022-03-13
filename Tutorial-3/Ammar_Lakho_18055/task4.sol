//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract GradeApp {

    mapping (uint => Student) students;

    struct Student {
        bool created;
        string name;
        uint[3] grades;
    }

    function enrollStudent(uint erp, string memory _name, uint[3] memory _grades) public {
        require(!students[erp].created, "Student with this erp already exists");
        students[erp] = Student(
            {
                created: true,
                name: _name,
                grades: _grades
            }
        );
    }

    function getAllGrades(uint erp) public view returns (uint[3] memory) {
        require(students[erp].created, "Student with this erp does not exist");
        return students[erp].grades;
    }
    
    function getAverageGrade(uint erp) public view returns (uint) {
        require(students[erp].created, "Student with this erp does not exist");
        uint avg = (students[erp].grades[0] + students[erp].grades[1] + students[erp].grades[2])/3;
        return avg;
    }

    function setStudentGradeForSubject(uint erp, uint gradeIndex, uint grade) public {
        require(students[erp].created, "Student with this erp does not exist");
        require (gradeIndex >=0 && gradeIndex <=2, "Invalid grade index");
        students[erp].grades[gradeIndex] = grade;
    }
        
}