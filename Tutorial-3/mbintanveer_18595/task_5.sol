pragma solidity >=0.5.0 <0.9.0;
 
contract GlobalVars{
uint public this_moment = block.timestamp; // `now` is deprecated and is an alias to block.timestamp)
 
uint public block_number = block.number;
 
uint public bdifficulty = block.bdifficulty;
 
uint public gaslimit = block.gaslimit;

 
address public owner;
uint public sentValue;
 
constructor(){
owner = msg.sender;
}
 
 
function ownerReplace() public{
owner = msg.sender;//Sender is inputted
}
 
function sendEther() public payable{ // must be payable to receive ETH with the transaction
sentValue = msg.value;
}
 
function getBalance() public view returns(uint){
return address(this).balance;
}
function Add() public view returns(uint){
    uint x=gasleft();
    uint add=1+1;
    uint y= x-gasleft();
    return y;
}