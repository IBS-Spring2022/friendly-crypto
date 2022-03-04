//SPDX-License-Identifier: GPL-3.0
 
pragma solidity >=0.5.0 <0.9.0;
 
contract GlobalVars{
    // the current time as a timestamp (seconds from 01 Jan 1970)
    uint this_moment = block.timestamp; // `now` is deprecated and is an alias to block.timestamp)
 
    // the current block number
    uint block_number = block.number;
    
    // the block difficulty
    uint difficulty = block.difficulty;
    
    // the block gas limit
    uint gaslimit = block.gaslimit;    
    
    address owner;
    uint sentValue;
 
    uint gasSpent;

    constructor(){
        // msg.sender is the address that interacts with the contract (deploys it in this case)
        owner = msg.sender;
    }
    
    function sendEther() public payable returns(uint _gas){ // must be payable to receive ETH with the transaction
        // msg.value is the value of wei sent in this transaction (when calling the function)
        _gas = gasleft();

        sentValue = msg.value;
        
        gasSpent = _gas - gasleft();
    }
    
    // returning the balance of the contract
    function getBalance() public returns(uint _balance, uint _gas){
        _gas = gasleft();

        _balance = address(this).balance;  

        gasSpent = _gas - gasleft();
    }

    function myFunction() public payable returns (uint _gas){
        _gas = gasleft();
        
        getBalance();
        sendEther();
        getBalance();

        gasSpent = _gas - gasleft();
    }

    function getGasSpent() public view returns(uint){
        return gasSpent;
    }
}