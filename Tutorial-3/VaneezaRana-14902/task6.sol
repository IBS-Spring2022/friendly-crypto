//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    int public a = 20;

    function f3() internal view returns(int){
        return a+a;
    }
    
}

contract B is A{
    uint b = f3();
}