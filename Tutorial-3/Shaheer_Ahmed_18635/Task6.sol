//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;

// Parent Contract
contract A{
    int public x = 20;

    function f() external view returns(int){
        return x;
    }
    
    function g() internal view returns(int){
        return x*2;
    }
}

// Non inheriting contract
contract B{
    int public local;

    function readData() public{
        A a = new A();
        local = a.f();
    }
}

// Inherited contract
contract C is A{
    int public local;

    function readData() public{
        local = g();
    }
}

