//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

//TASK1 and TASK2
contract CryptosToken {
    string public constant name = "Cryptos";
    uint supply;
    address public owner;

    constructor() public {
        owner = msg.sender;
    }

    function getter() public view returns (uint) {
        return supply;
    }

    function setter(uint _setValue) public {
        supply = _setValue;
    }
}

//TASK3
contract MyTokens {
    string[] public tokens = ['BTC', 'ETH'];
    
    function changeTokens() public {
        string[] storage t = tokens;
        t[0] = 'VET';
    }
}

//TASK4
contract StudentGradeApp {
    struct Student {
        string name;
        uint[3] grades;
    }

    mapping (string => Student) classList;

    function newStudent(string memory _name, string memory _erp, uint _A, uint _B, uint _C) public {
        Student memory newUser = Student(_name, [_A, _B, _C]);
        classList[_erp] = newUser;
    }

    function getGrades(string memory _erp) public view returns (uint[3] memory ) {
        Student memory gradeMan =  classList[_erp];
        return gradeMan.grades;
    }

    function average(string memory _erp) public view returns (uint) {
        Student memory gradeMan =  classList[_erp];
        uint avg = gradeMan.grades[0] + gradeMan.grades[1] + gradeMan.grades[2];
        avg = (avg / 3);
        return avg;
    }
}

//TASK5
contract gasObservation {

    function gasUsed() public view returns (uint) {
        uint gasBefore = gasleft();
        uint a = 0;
        a = a + 2;
        uint gasAfter = gasleft();
        return (gasBefore - gasAfter);
    }
}

//TASK6
contract A {
    int public x = 10;

    
    function f3() internal view returns(int){
        return x;
    }
}

contract B is A {
    function inheritFunction() private view returns (int) {
        return f3();
    }
}