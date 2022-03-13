//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract Task4{
     struct Student{
          string name;
          uint ERP;
          uint[] grades;
     }
     //grades = new uint256[](3);
       Student[] student;
      mapping(uint => uint[]) ERPtoGrades;


        function addStudent(uint _ERP ,string memory _name,uint[] memory _grades)public{
           //grades.push(_grades[0]);
           Student memory std = Student(_name, _ERP,_grades);
            student.push(std);
            ERPtoGrades[_ERP] = _grades;
        }


        function getGrades(uint _erp) public view returns(uint[] memory){
         return ERPtoGrades[_erp];
        }
        function average(uint _erp) public view returns(uint){
         uint [] memory _marks = getGrades(_erp);
         uint sum = 0;
         for (uint i=0;i<_marks.length;i++){
                sum  = sum + _marks[i];
            }
        sum = sum/3;
        return sum;
        }
}
       