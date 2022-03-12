pragma solidity >=0.5.0 <0.9.0;

contract studentGradeReport {

    struct Student{
        string name;
        uint ERP;
        uint[] marks;
    }

    mapping(uint => Student) public gradeReport;

    function saveStudent(string memory _name, uint _erp, uint[] memory _marks) public {
         Student memory oneStudent = Student( _name, _erp, _marks);
        gradeReport[_erp] = oneStudent;
    }

    function getGrades(uint _erp) public view returns(uint[] memory) {
        return gradeReport[_erp].marks;
    }
 
    function getAverageMarks(uint _erp) public view returns(uint){
        Student memory std = gradeReport[_erp];
        uint sum = 0;
        for(uint i=0; i<std.marks.length; i++){
            sum += std.marks[i];
        }
        return (sum/std.marks.length);
    } 
}