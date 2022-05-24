//SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.5.0 <0.9.0;
import "https://github.com/OpenZeppelin/openzeppelin-contracts/blob/master/contracts/token/ERC20/IERC20.sol";

contract ICO{

    address public owner;
    address public tokenContract;
    uint public sTime;
    uint public eTime;
    uint public hardCap;
    uint public price;
    uint public raised;
    uint public maxInvestment;
    uint public minInvestment;

    constructor(address tokenCntrct, uint cap, uint tokenPrice, uint min, uint max){
        owner = msg.sender;
        tokenContract = tokenCntrct;
        sTime = block.timestamp;
        eTime = block.timestamp + 604800;
        hardCap = cap;
        price = tokenPrice;
        minInvestment = min;
        maxInvestment = max;
    }

    modifier liveSale{
        require(block.timestamp>sTime && block.timestamp<eTime, "ICO is not live.");
        require(hardCap > raised, "ICO hardcap reached.");
        _;
    }

    modifier onlyOwner{
        require(owner == msg.sender, "Error: Only owner has access to this resource.");
        _;
    }

    modifier validtransactionAmount{
        require(msg.value >= minInvestment, "Error: Value is smaller than minimum limit of tokens.");
        require(msg.value <= maxInvestment, "Error: Value is larger than maximum limit of tokens.");
        require(raised + msg.value <= hardCap, "Error: Hardcap is exceeded.");
        _;
    }

    function buyTokens() validtransactionAmount liveSale external payable{
        uint amt = msg.value/price;
        IERC20(tokenContract).transfer(msg.sender, amt);
        raised += amt;
    }
}