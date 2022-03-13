pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string name;
    uint public supply;
    address public owner;
    constructor(){
	name = "Cryptos";
	supply = 1234;
        owner = msg.sender;
    }

    function getSupply() public view returns(uint){
        return supply;
    }
    function setSupply(uint _supply) public {
        supply = _supply; 
    }
}