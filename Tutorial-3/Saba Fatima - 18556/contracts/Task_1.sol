pragma solidity >= 0.6.0 < 0.9.0;

contract CryptosToken{

    string public name = "Cryptos";
    uint supply;

    function setSupply(uint _supply) public {
        supply = _supply;
    }
    function getSupply() public view returns (uint){
        return supply;
    }
}