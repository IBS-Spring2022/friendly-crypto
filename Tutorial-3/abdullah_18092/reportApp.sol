pragma solidity  >=0.5.0 <0.9.0;

contract reportApp {
    address owner;
    struct studentDetails  {
        string  name;
        string  erp;
        uint[3] grades;
    }
    mapping (string => studentDetails) classList;


    constructor(){
        owner = msg.sender;
    }

    modifier mustBeTeacher{
        require(msg.sender == owner);
        _;
    }

    function createStudent (string memory _name, string memory _erp, uint _gradeA, uint _gradeB, uint _gradeC) public mustBeTeacher{
        studentDetails memory newStudent = studentDetails(_name, _erp, [_gradeA, _gradeB, _gradeC]);
        classList [_erp] = newStudent;
    }

    function getStudentGrades (string memory _erp, uint8 _gradeIndex) public view returns (uint){
        studentDetails memory student = classList [_erp];
        return student.grades[_gradeIndex];
    }

    function calculateAvg (string memory _erp) public view returns (uint){
        studentDetails memory student = classList [_erp];
        uint avg = (student.grades[0] + student.grades[1] + student.grades[2]) / 3;
        return avg;
    }

}