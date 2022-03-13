pragma solidity  >= 0.6.0 < 0.9.0;

 contract TaskOneAndTwo {

//TASK1:

    string public constant name1 = "Cryptos";
    uint supply;

    function store(uint _supply) public {
        supply = _supply;
    }

    function retrieve() public view returns(uint){
        return supply;
    }

//Task2:

    address public owner;
    string public name2;
    //uint public supply2;


    constructor() public {
        supply = 10;
        owner = msg.sender;
        name2 = "Ammar";

    } 

 }