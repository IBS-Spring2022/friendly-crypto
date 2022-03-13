//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string constant public name = "Cryptos";
    uint supply;
    address public owner;

    constructor() {    
    supply = 0;    
    owner = msg.sender;
}

function store(uint _supply) public {
    supply = _supply;
}


function retrieve() public view returns(uint) {
    return supply;
}

}