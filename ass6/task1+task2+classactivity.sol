//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public name = "Cryptos";
    uint256 supply;
    address public owner;

    constructor () {
        owner = msg.sender;
    }

    function getter () public view returns(uint){
        return supply;
    }

    function setter (uint _supply) public{
        supply  =   _supply;
    } 
    
    People[] public people;
    
    struct People {
        uint256 number;
        string name;
    }

    mapping(string => uint) public nameToFavNumber;

    function addPerson(uint num, string memory _name ) public{

        people.push(People({number: num, name: _name}));
        nameToFavNumber[_name] = num;
    }
}
