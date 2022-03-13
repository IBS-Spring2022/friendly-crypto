//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract GasLeft{

    function calculateGasLeft () public view returns (uint){

        uint gasBefore = gasleft();

        uint a = 0;
        uint b = a + 25;
         for(uint i=0;i<5;i++)
         {
         a+=i;
         }

        uint gasAfterUse = gasleft();
        uint gasUsed = (gasBefore-gasAfterUse);

        return gasUsed;  
    }
}