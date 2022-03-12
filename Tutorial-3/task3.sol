//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract MyTokens{
    string[] public tokens = ['BTC', 'ETH'];
    
    function changeTokens(string memory str) public{
        //string[] memory t = tokens;
        tokens.push(str);
    }
    
}