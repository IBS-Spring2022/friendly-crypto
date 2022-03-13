//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract Task2{
    string public name = "Cryptos";
    uint supply;
    address public owner;

    constructor(){
        owner = msg.sender;
    }

    function getSupply() public view returns(uint) {
        return supply;
    }

    function setSupply(uint _supply) public {
        supply = _supply;
    }
}
