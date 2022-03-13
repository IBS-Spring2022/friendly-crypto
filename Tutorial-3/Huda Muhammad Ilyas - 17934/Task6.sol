//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    int public x = 10;

    
    function f3() internal view returns(int){
        return x;
    }
    
}

contract der is A{
   
   function callFromA() public view returns(int){
       return f3();
   }
}