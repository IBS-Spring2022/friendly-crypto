//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0; 


contract GasUsage {
uint a = 2;
uint b = 3; 
uint c = 0;

function sumOperation() public {
     c = a + b;
}

function gasBurned() public view returns (uint256)
{
    uint256 gasUsed;
    uint256 startGas = gasleft();
    gasUsed = startGas - gasleft();

    return gasUsed;
}  
  
  }