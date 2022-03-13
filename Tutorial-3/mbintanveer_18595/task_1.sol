//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public constant name = "Cryptos";
    uint supply;

    function get() public view returns (uint) {
        return supply;
    }

     function set(uint new_score) {
        supply = new_score;
    }

}