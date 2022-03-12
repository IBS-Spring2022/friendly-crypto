pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string public name = "Cryptos";
    uint supply;
    address public owner;

    constructor(string memory _name, uint _supply){
        name = _name;
        supply = _supply;
        owner = msg.sender;
    }

    function getSupply() public returns (uint) {
        return supply;
    }

    function setSupply(uint supp) public {
        supply = supp;
    }

}