//SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.5.0 <0.9.0;

contract studentReport{

    struct Student{
        uint erp;
        string name;
        string[] grade;
    }
    Student[] public student;
    mapping(uint => string[]) public erpToGrades;
    mapping(uint => uint) public erpToavgGrades;
    function addStudent(uint _erp, string memory _name, string[] memory _grade) public{
        student.push(Student({erp: _erp, name: _name, grade: _grade})) ;
        erpToGrades[_erp]= _grade;

    }
    function retrieveGrades(uint _erp) public view returns(string[] memory){
        return erpToGrades[_erp];
    }
    function averageGrades(uint _erp) public returns(uint){
        string[] memory grades  = erpToGrades[_erp];
        uint average;
        uint i;
        for(i = 0; i != 3; i++){
            if(keccak256(abi.encodePacked(grades[i]))==keccak256(abi.encodePacked("A"))){
                average = average+90;
            }
            else if(keccak256(abi.encodePacked(grades[i]))==keccak256(abi.encodePacked("B"))){
                average = average+85;
            }
            else if(keccak256(abi.encodePacked(grades[i]))==keccak256(abi.encodePacked("C"))){
                average = average+80;
            }
            else 
                average = average+0;
        }
        return average/3;
    }
    function storeAverage(uint _erp) public{
        erpToavgGrades[_erp] = averageGrades(_erp);
    }



}