//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract CryptoTokens{
    int public a = 10;
    function func() internal view returns(int){
        return a;
    }   
}

contract Task6 is CryptoTokens{
    function driver() public view returns(int){
        return func();
    }
}