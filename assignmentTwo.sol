pragma solidity ^0.4.24;

contract assignmentTwo {

uint public studentNumber;
//1
uint public GasUsed;

address public student;

constructor() public {

student = msg.sender;

}

function setStudentNumber(uint _studentNumber) public {

    
    uint256 startGas = gasleft();
    studentNumber = _studentNumber;
    //3
    setGasUsed(startGas - gasleft());

}

//2
function setGasUsed(uint _GasUsed) private {
    GasUsed = _GasUsed;
 }

 function getGasUsed() public view returns(uint) {
           return GasUsed;
 }


}