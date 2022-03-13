pragma solidity >=0.6.0 < 0.9.0; //Use features of versions from 0.6.0 to 0.9.0 (flexible)

contract SimpleStorage {   //just like class
   //by default all var are private
   //reading from blockchain - no money - ORANGE
   uint256 favNumber = 0;   //money is always positive
   int256 favNumberi = -1;
   bool favBool = false;
   string favString = "test";
   address favAddress = 0x612489902EAc635670544491C2A5FB5a9593A9d5;
   bytes32 favbyte = "Cat";

//its object but not OOP object (just like C++)
People[] public people;

struct People {
     uint256 number;
     string name;
}
//converting struct to Hash Map
//mapping structure (Hash Map), you tell it what you want when you provide it with a key

mapping(string => uint256) public nameToFavNumber;


//use term memory for reference variables like store, list , addresss etc
//If you dont use it, then it will charge money for storing it in main storage 

function addPerson(uint256 num,string memory _name) public {
    people.push(People({number: num, name: _name}));
    nameToFavNumber[_name] = num;
}