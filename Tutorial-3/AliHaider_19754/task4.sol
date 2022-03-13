//SPDX-License-Identifier: GPL-3.0

pragma solidity >= 0.6.0 < 0.9.0;

contract Task4{

    struct Student{ 
        uint ERPID;
        string name;
        uint g1;
        uint g2;
        uint g3;
    }

    mapping(uint => Student[]) students;

    function addStudent(uint _ERPID, string memory _name, uint _g1, uint _g2, uint _g3) public {
        Student memory student = Student (_ERPID, _name, _g1, _g2, _g3);
        
        students[_ERPID].push(student);
    }

    function getStudents(uint _ERPID) public view returns (string memory _name,uint _g1,uint _g2,uint _g3,uint _average){
        uint length = students[_ERPID].length;
        for(uint i=0; i<length;){
            _name = students[_ERPID][i].name;
            _g1 = students[_ERPID][i].g1;
            _g2 = students[_ERPID][i].g2;
            _g3 = students[_ERPID][i].g3;
            _average = averageGrade(_g1, _g2, _g3);
            return (_name, _g1, _g2, _g3, _average); 
        }
    }

    function averageGrade(uint _g1, uint _g2, uint _g3) private pure returns(uint _average){
        _average = ((_g1 + _g2 + _g3)/3);

        return _average;
    }
}