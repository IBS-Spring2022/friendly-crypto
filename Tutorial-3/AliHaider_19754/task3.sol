//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string[] public tokens = ['BTC', 'ETH'];
    function changeTokens() public{
        string[] memory i = tokens;
        i[0] = 'VET';
        tokens = i;
    }
}
