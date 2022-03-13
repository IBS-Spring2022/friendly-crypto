pragma solidity  >= 0.6.0 < 0.9.0;

 contract SimpleStorage {

//TASK4:

     uint256 erp = 0;
     uint256 course1 = 0;
     uint256 avg = 0;
     uint256 course2 = 0;
     uint256 course3 = 0;
     string STDname = "";

    function store(uint _erp, string memory _name, uint _course1, uint _course2, uint _course3) public {
        erp = _erp;
        course1 = _course1;
        course2 = _course2;
        course3 = _course3;
        STDname = _name;
    }

    function retrieve(uint _erp) public view returns (uint _course1, uint _course2, uint _course3, uint _avg) {
        _avg = (course1 + course2 + course3) / 3;
        return (course1, course2, course3, _avg);
    }
    
}