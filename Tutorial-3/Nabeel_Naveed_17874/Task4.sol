pragma solidity >=0.8.0 <0.8.12;

contract reportApp {

    struct Student {
        string name;
        uint erpID;
        uint[] threeSubjects;
    }
//mapped by erp
    mapping(uint=>Student) private studentData;
    function addStudent(string calldata _name, uint _erpID,uint[] calldata _scoreOfThreeSubjects) public {
        Student memory newUser = Student(_name,_erpID,_scoreOfThreeSubjects);
        newUser.name = _name;
        newUser.erpID = _erpID;
        newUser.threeSubjects = _scoreOfThreeSubjects;
        studentData[_erpID] = newUser;

    }
    function getUserGrade(uint _erpID) public view returns(uint[] memory) {
        return studentData[_erpID].threeSubjects;
    }
    function getAverageGrade(uint _erpID) public view  returns(uint) {
        Student memory user = studentData[_erpID];
        uint  sum =0;
        for(uint i =0;i< user.threeSubjects.length;i++){
            sum+=user.threeSubjects[i];
        }
        return sum/user.threeSubjects.length;
    }
   

}