//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract Task5{
    function test(uint a) public returns (uint256){
    uint256 startGas = gasleft();
    a = 4+5*a;
    uint256 gasUsed = startGas - gasleft();
    return gasUsed;
    }
}