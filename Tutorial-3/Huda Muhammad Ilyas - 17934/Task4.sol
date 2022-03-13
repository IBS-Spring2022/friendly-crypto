pragma solidity >=0.6.0 <0.9.0;

contract Task4 {
    
    
    

    struct Student{
        string name;
        uint erp;
        uint[3] grade;

    }
    Student[] public student;

    mapping(uint => Student) public erptoGrade;

    

     function store(string memory _name, uint _erp, uint _grade1, uint _grade2, uint _grade3 ) public {
        uint[3] memory _grade = [_grade1,_grade2,_grade3];
        student.push(Student({name: _name, erp: _erp,grade: _grade}));
        erptoGrade[_erp] = Student({name: _name, erp: _erp,grade: _grade}) ;

     }
     function getGrades(uint _erp) view public returns (uint[3] memory){
        return erptoGrade[_erp].grade;
    }

     function getAvg(uint _erp) view public returns (uint){
        return (erptoGrade[_erp].grade[0] + erptoGrade[_erp].grade[1] + erptoGrade[_erp].grade[2]) / 3;
    }



  

     


}