//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public name;
    uint supply;
    address public owner;
    constructor(string memory _name,uint _supply) public {
      name = _name;
      supply = _supply;
      owner = msg.sender;
   }
    function Supset(uint s) public {
            supply=s;
    }
    function Supget() public view returns (uint){
        return supply;
    }
}