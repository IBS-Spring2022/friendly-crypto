pragma solidity >=0.5.0 <0.9.0;

contract Task4{

    Student [] public student;

    struct Student {
        string name;
        uint erp;
        uint[3] grades;
        
    }

    mapping(uint => Student) public nameToGrade;

    function addStudent(uint _erp, string memory _name, uint _grade1, uint _grade2, uint _grade3) public{
        uint[3] memory studentgrades = [_grade1, _grade2, _grade3]; 
        nameToGrade[_erp] = Student({erp: _erp, name: _name, grades: studentgrades});
    }

    function getGrades(uint _erp) view public returns (uint[3] memory){
        return nameToGrade[_erp].grades;
    }

    function getAverage(uint _erp) view public returns (uint){
       // for (uint i = 0; i < nameToGrade.length; i++) {
        return (nameToGrade[_erp].grades[0] + nameToGrade[_erp].grades[1] + nameToGrade[_erp].grades[2]) / 3;
  //  }
}
}