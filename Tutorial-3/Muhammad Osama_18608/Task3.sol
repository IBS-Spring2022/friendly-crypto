//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract MyTokens{
    string[] public tokens = ['BTC', 'ETH'];
    
    function changeTokens() public {
        //vestring[] memory t = tokens;
        tokens[0] = 'IOTA';
        tokens[1] =  'LUNA';

    }
    
}