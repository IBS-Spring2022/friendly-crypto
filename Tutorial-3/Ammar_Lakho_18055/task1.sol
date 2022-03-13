//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken {
    string constant public name = "Cryptos";
    uint supply;

    function setSupply(uint _supply) public {
        supply = _supply;
    }

    function getSupply() public view returns (uint) {
        return supply;
    }
}