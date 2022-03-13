//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract StudentGradeReport{

    Student[] public student;
    uint256 sum;
    string grade;


    struct Student{
        uint256 erp;
        string name;
        uint256[3] marks;
    }

    mapping(uint => uint256[]) public erpToGrades;

    function addDetails(uint _erp, string memory _name, uint[3] memory _marks) public{

         Student memory d = Student(_erp, _name, _marks);
         student.push(d);
         erpToGrades[_erp] = _marks;
       // marks.push(_mark1);
    }

     function getMarks(uint _erp) public view returns (uint256[] memory)
    {
        return erpToGrades[_erp];
    }

   
    function avgMarks(uint erp) public returns (string memory){
       uint256[] memory marksList = erpToGrades[erp];

    
       for(uint i = 0; i<marksList.length; i++)
       {
           sum = sum + marksList[i];
       }

        if(sum > 80){
            return "A";
        }
        else if (sum > 60){
            return "B";
        }
        return "C";
    }
  
}