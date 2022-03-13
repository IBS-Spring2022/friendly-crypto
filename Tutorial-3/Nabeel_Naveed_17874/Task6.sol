//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    int public x = 10;

    
    function f3() internal view returns(int){
        return x;
    }
    
}

contract B is A {


    function callingF3fromDerived() public view returns(int) {
        //we can also do super.f3() incase derving contract also has a fn named f3 or A.f3(); if multiple inheritance
 
    //     super.f3();
    //     A.f3();
   return f3();

     }
}
