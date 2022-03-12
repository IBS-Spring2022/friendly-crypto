//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public name = "Cryptos";
    uint supply;
    address public owner;

    constructor() public {
        owner = msg.sender;
        supply = 15;
    }



    function retrive() public view returns(uint){
        return supply;
    }

    function store(uint _supply) public{

        supply=_supply;
    }
}