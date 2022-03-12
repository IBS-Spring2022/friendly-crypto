pragma solidity >=0.5.0 <0.9.0;

/**   Task:1
     * Change the state variable name to be declared as a public constant
     * Create setSupply fuction
     * Create getSupply function
     */
contract CryptosToken{
    string constant public name = "Cryptos";
    uint supply;

    function setSupply(uint256 _supply) public {
        supply = _supply;
    }

    function getSupply() public view returns(uint256) {
        return supply;
    }
}