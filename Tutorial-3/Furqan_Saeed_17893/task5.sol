// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.5.0 <0.9.0;

contract Global{
    uint public num = 86400;

    function fp() public returns (uint end){
        uint start = gasleft();
        num = num * 2;
        end = start - gasleft();
    }
}