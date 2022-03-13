pragma solidity >=0.5.0 <0.9.0;

contract task5{

    function f() public view returns (uint) {
    uint Gas = gasleft();
    uint a = 1;
    a = a+a+4;
    return Gas - gasleft();
}
}