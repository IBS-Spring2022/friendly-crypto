//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    uint public x = 10;

    
    function f3() internal view returns(uint){
        return x;
    }
    
}

contract B is A{
    function f2() public view returns(uint){
        return f3();
    }
}