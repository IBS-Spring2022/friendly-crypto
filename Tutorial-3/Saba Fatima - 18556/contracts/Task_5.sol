pragma solidity >=0.5.0 <0.9.0;
 
contract GlobalVars{
    int a = 1;
    int b = 2;
    int c;
    //uint gasLeft;
    
    function addVals() public returns(uint){
        c = a+b;
        return gasleft();
        }
    }