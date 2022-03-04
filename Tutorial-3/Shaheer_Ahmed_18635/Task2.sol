//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public name;
    uint supply;
    address public owner;

    constructor () {
        name = "Shaheer";
        supply = 45;
        owner = msg.sender;

    }

    function setSupply(uint _supply) public{ 
        supply = _supply;
    }

    function getSupply() public view returns (uint _supply){
        return supply;
    }
}