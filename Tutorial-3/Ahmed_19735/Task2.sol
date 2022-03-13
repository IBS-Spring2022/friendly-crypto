//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken
{
    string public name  = "Cryptos";
    uint supply;
    address public owner;

    function getSupply() public view returns(uint){
        return supply;
    }
    function setSupply(uint newsupply) public{
        supply = newsupply;
    } 

    constructor(string memory _name, uint _supply)public{
	name = _name;
	supply = _supply;
	owner = msg.sender;
    }
    
}