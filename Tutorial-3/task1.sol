//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public name = "Cryptos";
    uint supply;


    function getSupply() public returns (uint) {
        return supply;
    }

    function setSupply(uint supp) public {
        supply = supp;
    }

}