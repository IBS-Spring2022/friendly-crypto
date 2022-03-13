//Add a public state variable of type address called the owner.
//● Declare the constructor and initialize all the state variables in the constructor. The owner
//should be initialized with the address of the account that deploys the contract.

//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public name;
    uint supply;
    address public owner;

    constructor() public {

        owner = msg.sender;
    }

    function get() public view returns (uint) {
        return supply;
    }

     function set(uint new_score) public {
        supply = new_score;
    }

}