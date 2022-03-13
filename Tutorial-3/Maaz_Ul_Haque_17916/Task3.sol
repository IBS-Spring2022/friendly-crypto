//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract Task3{
    string[] public tokens = ['BTC', 'ETH'];
    
    function changeTokens() public{
        // string[] storage t = tokens;
        tokens.push('VET');
    }

    function getTokens() public view returns(string[] memory){
        return tokens;
    }
    
}