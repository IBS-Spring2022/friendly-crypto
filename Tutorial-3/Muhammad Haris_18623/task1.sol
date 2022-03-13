pragma solidity >=0.5.0 <0.9.0;

contract CryptosToken{
    string name = "Cryptos";
    uint public supply;

    function _getSupply() public view returns(uint){
        return supply;
    }
    function _setSupply(uint _supply) public {
        supply = _supply; 
    }
}