pragma solidity >=0.5.0 <0.9.0;

contract task4{
    struct Student{
        uint erp;
        string name;
        uint[] grades;
    }
    Student[] public student;
    mapping(uint => uint[]) public mapErpandGrades;
    function createStudent(uint _erp, string memory _name, uint[] memory _grades) public{
        student.push(Student({erp: _erp, name: _name, grades: _grades})) ;
        mapErpandGrades[_erp]= _grades;

    }
    function getGrades(uint _erp) public view returns(uint[] memory){
        return mapErpandGrades[_erp];
    }
    function avgGrades(uint _erp) public view{
        uint[] memory grade  = getGrades(_erp);
        uint sum = 0;
        uint i;
        uint average;
        for(i = 0; i != 3; i++){
            sum = sum + grade[i];
        }
        average = sum/3;
    }



}