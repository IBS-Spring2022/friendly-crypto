//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    int public x = 10;

    
    function f() internal returns(int){
        return x;
    }
    
}

contract B is A {
    int a = A.f();
    function inherit() public view returns(int) {
        return a;
    }  

    // A.f();
    // super.f();
}