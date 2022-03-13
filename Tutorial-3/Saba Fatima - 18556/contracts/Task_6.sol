pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    int public x = 10;

    
    function f() internal view returns(int){
        return x;
    }
    
}

contract Derived is A{
    int num = f();
}