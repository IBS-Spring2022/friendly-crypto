pragma solidity >= 0.6.0 < 0.9.0;

contract CryptosToken{

    string public name = "Cryptos";
    uint supply;
    address public owner = 0x7E8018754d7b325cca0f6000DEa55568bC4c9F43;

    function setSupply(uint _supply) public {
        supply = _supply;
    }
    function getSupply() public view returns (uint){
        return supply;
    }

    struct Owner{
        string name;
        uint supply;
        address owner;
    }
}