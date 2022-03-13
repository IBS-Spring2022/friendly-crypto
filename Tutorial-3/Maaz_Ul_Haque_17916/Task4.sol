//SPDX-License-Identifier: GPL-3.0

pragma solidity >= 0.6.0 < 0.9.0;

contract Task4{
    
    struct student{
        uint erp;
        string name;
        uint[3] grades;
    }

    student[] public Students;

    mapping(uint => student) public studentDetails;

    function getReport(uint _erp) public view returns(uint){
        uint[3] memory stdgrades;
        stdgrades = studentDetails[_erp].grades;

        uint average;
        uint sum = 0;
        for (uint i = 0; i < stdgrades.length; i++) {
            sum += stdgrades[i];
        }
        average = sum / stdgrades.length;
        return average;

    }

    function addStudent(uint _erp, string memory _name, uint _g1, uint _g2, uint _g3) public{
        uint[3] memory thisGrades = [_g1, _g2, _g3]; 
        studentDetails[_erp] = student({erp: _erp, name: _name, grades: thisGrades});
    }
}