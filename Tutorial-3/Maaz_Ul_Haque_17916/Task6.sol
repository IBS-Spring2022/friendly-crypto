//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    int public x = 10;

    
    function f() internal view returns(int){
        return x;
    }
    
}

contract Task6 is A{
    function driver() public view returns(int){
        return f();
    }
}