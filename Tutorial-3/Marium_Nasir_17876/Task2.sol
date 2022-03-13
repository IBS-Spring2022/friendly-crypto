//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public constant name = "Cryptos";
    uint supply;
    address public owner;

    constructor(){
        supply = 9;
        owner = 0x8CA1419bD47041dD800E088E512aFb2CeFE6f77a;
    }

    function setSupply(uint _supply) public{
        supply = _supply;
    }

    function retrieve() public view returns(uint) {
        return supply;
    }
} 
