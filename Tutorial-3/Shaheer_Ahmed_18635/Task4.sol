//SPDX-License-Identifier: GPL-3.0

pragma solidity >= 0.6.0 < 0.9.0;

contract Task4{

    struct Student{ 
        uint erp;
        string name;
        uint grade1;
        uint grade2;
        uint grade3;
    }

    mapping(uint => Student[]) students;

    function addStudent(uint _erp, string memory _name, uint _grade1, uint _grade2, uint _grade3) public {
        Student memory student = Student (_erp, _name, _grade1, _grade2, _grade3);
        
        students[_erp].push(student);
    }

    function getStudents(uint _erp) public view returns (
        string memory _name,
        uint _grade1,
        uint _grade2,
        uint _grade3,
        uint _avg
    ){
        uint length = students[_erp].length;

        for(uint i=0; i<length;){
            _name = students[_erp][i].name;
            _grade1 = students[_erp][i].grade1;
            _grade2 = students[_erp][i].grade2;
            _grade3 = students[_erp][i].grade3;
            _avg = avgGrade(_grade1, _grade2, _grade3);

            return (_name, _grade1, _grade2, _grade3, _avg); 
        }
    }

    function avgGrade(uint _grade1, uint _grade2, uint _grade3) private pure returns(uint _avg){
        _avg = ((_grade1 + _grade2 + _grade3)/3);

        return _avg;
    }
}