pragma solidity >= 0.6.0 < 0.9.0;

contract simple_storage{

    People[] public people;

    mapping(string => uint) public nameToFavNumber;
    
    struct People{
        uint256 number;
        string name;
    }

    function addPerson(uint num, string memory _name) public{

        people.push(People({number: num, name: _name}));
    }
    
    uint256 favNumber = 0;
    int favNumberi = 0;
    bool favBool = false;
    string favString = "test";
    address favAdress = 0x7E8018754d7b325cca0f6000DEa55568bC4c9F43;
    bytes32 favbyte = "Cat";

    function store(uint _favNumber) public {
        favNumber = _favNumber;
    }
    
    function retrieve() public view returns (uint){
        return favNumber;
    }
    
    
}