//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
function gas() public returns(uint){

    uint gasStart = gasleft();

    uint a = 5;
    uint b = 7+a;

    return gasStart-gasleft;
}

}