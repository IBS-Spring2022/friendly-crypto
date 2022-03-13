//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    int public num = 5;

    function f3() internal view returns(int){
        return num;
    }
    
}

contract B is A {
    int derivedVal = f3();
}