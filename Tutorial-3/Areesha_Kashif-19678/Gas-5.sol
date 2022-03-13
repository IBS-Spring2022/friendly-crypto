//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract Gas{

  uint sum = 0;

  function update(uint no) public returns (uint)
  {
      uint initialGas = gasleft();
      sum = sum + no;
      uint gasUsed = initialGas - gasleft();
      return gasUsed;
  }  
}