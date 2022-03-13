pragma solidity >=0.6.0 < 0.9.0;
/*
Task #4: Student Grade Report Application

@Author MShaheerUddin
Some assumptions:
Grade should be given in letter format
Possible allowed grades: A,B,C,D,F where F is Fail
Particular grade has been allotted a fixed numeric grade for average computation.

For further queries reach me at:
Email: m.uddin_19757@khi.iba.edu.pk

*/
contract StudentGradeReport {
         
Student[] public student;

struct Student {
      string name;
      uint256 ERP_ID;
      string gradeOfSubject1;
      string gradeOfSubject2;
      string gradeOfSubject3;
      
} 

mapping(uint256 => string) public erpToGrade;
mapping(string  => uint256) public nameToAverageGrade;
mapping(uint256  => uint256) public erpToAverageGrade;




function addStudent(string memory _name,
                    uint256 erp, 
                    string memory _gradeOfSubject1, 
                    string memory  _gradeOfSubject2, string memory _gradeOfSubject3) public {
    student.push(Student({name: _name, ERP_ID: erp,gradeOfSubject1:_gradeOfSubject1,gradeOfSubject2:_gradeOfSubject2
    ,gradeOfSubject3: _gradeOfSubject3}));



    erpToGrade[erp] = string(abi.encodePacked("Grade of Course 1: ",_gradeOfSubject1, ",","Grade of Course 2: ", _gradeOfSubject2,",","Grade of Course 3: ", _gradeOfSubject3));
    nameToAverageGrade[_name] = computeAverage(_gradeOfSubject1,_gradeOfSubject2,_gradeOfSubject3);
    erpToAverageGrade[erp] = computeAverage(_gradeOfSubject1,_gradeOfSubject2,_gradeOfSubject3);
}

function compareStrings(string memory a, string memory b) private view returns (bool) {
    return (keccak256(abi.encodePacked((a))) == keccak256(abi.encodePacked((b))));
}

function getNumericGrade(string memory grade) private view returns(uint256) {
    if (compareStrings(grade,"A")) return 90;
    else if (compareStrings(grade,"B")) return 80;
    else if (compareStrings(grade,"C")) return 70;
    else if (compareStrings(grade,"D")) return 60;
    else if (compareStrings(grade,"F")) return 50;
    else return 1;
}

function computeAverage(string memory grade1,string memory grade2,string memory grade3) private view returns(uint256) {
             uint256 averageOfGrade = 0;
             uint256 sum = 0;
             sum = getNumericGrade(grade1) + getNumericGrade(grade2) + getNumericGrade(grade3);
             averageOfGrade = sum / 3;
             return averageOfGrade;
}







}