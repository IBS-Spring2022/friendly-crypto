//SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.5.0 <0.9.0;


contract CryptosToken{
    uint supply;
    string public constant name = "Cryptos";
    address public owner;

    function getSupply() returns(uint){
        returns supply;
    }

    function setSupply(uint newSupply) {
        supply = newSupply;
    }

    constructor(uint _supply, string _name) public{
        supply = _supply;
        name = _name;
        owner = msg.sender;
    }
}

