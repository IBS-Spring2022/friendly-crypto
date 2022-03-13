// SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.5.0 <0.9.0;

contract visibility{
    uint num = 86400;

    function f() internal returns (uint){
        num = num * 2;
        return num;
    }
}

contract A is visibility{
    function callInternal() public returns(uint){
        return f();
    }
}