pragma solidity >=0.5.0 <0.9.0;
 
contract A{
    uint public x = 10;

    
    function f() internal view returns(uint){
        return x;
    }
    
}

contract derivedContract is A{
    uint a = f();
}