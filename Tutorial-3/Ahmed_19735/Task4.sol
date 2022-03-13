/SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract GradeReport{

     struct Student{
          string Name;
          uint ERP;
          uint[] Marks;
     }
    
       Student[] student;
        mapping(uint => uint[]) Report;


        function saveStudent(uint _ERP ,string memory _Name,uint[] memory _Marks)public{
        
           Student memory s = Student(_Name, _ERP,_Marks);
            student.push(s);
            Report[_ERP] = _Marks;
        }
	
	function getMarks(uint _erp) public view returns(uint[] memory){
         return Report[_erp];
        }

        function getAverageMarks(uint _erp) public view returns(uint){
         uint [] memory _points = getMarks(_erp);
         uint sum = 0;
         for (uint i=0;i<_points.length;i++){
                sum  = sum + _points[i];
            }
        sum = sum/_points.length;
        return sum;
        }

}
       