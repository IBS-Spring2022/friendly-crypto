//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public name = "Cryptos";
    uint sup;
    address public owner;

    constructor(){
        owner = msg.sender;
    
    }

    function Supp() public view returns(uint) {
        return sup;
    
    }

    function sSupp(uint _sup) public {
        sup = _sup;
    
    }
}
