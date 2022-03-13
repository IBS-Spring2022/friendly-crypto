//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    int public x = 10;

    
    function f() internal view returns(int){
        return x;
    }
    
}
contract B is A {
    function g() public view returns (int){
        int val = f(); 
        return val; // return the value received from f, so I can run it and check
    }
}