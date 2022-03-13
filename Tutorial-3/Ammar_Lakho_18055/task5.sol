//SPDX-License-Identifier: GPL-3.0

pragma solidity >=0.5.0 <0.9.0;

contract FindGas {
    
    function findGas(uint n) public view returns (uint) {
        uint start = gasleft();
        for (uint i=0; i<n; i++) {
            
        }
        uint end = gasleft();
        return (start - end);
    }
}