pragma solidity >=0.5.0 <0.9.0;

contract Task5 {
    uint public gaslimit = block.gaslimit;

    int a = 1 + 2;

    uint public remaininggas = gaslimit - gasleft();
}