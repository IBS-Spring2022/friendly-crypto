//SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.5.0 <0.9.0;

contract Auction{
    address payable manager;
    uint startTime;
    uint endTime;
    bool cancelled;
    uint floorPrice;
    address payable highestBidder = payable(address(0));
    address payable secondHighestBidder = payable(address(0));
    address payable winner = payable(address(0));
    address payable[] public participants;
    uint increment;
    mapping(address => uint) private bids;

    constructor(uint _floorPrice, uint _increment){
        manager = payable(msg.sender);
        floorPrice = _floorPrice;
        increment = _increment;
        cancelled = false;
    }

    modifier onlyManager{
        require(msg.sender == manager, "Access is prohibited!");
        _;
    }

    modifier isActive{
        require(startTime < block.timestamp && endTime > block.timestamp, "Auction is not active");
        _;
    }

    modifier isEnded{
        require(startTime < block.timestamp && endTime < block.timestamp, "Auction has not ended yet");
        _;
    }

    modifier isPending{
        require(startTime == 0 && endTime == 0, "Auction has not started yet.");
        _;
    }

    modifier notCancelled{
        require(cancelled == false, "Auction has been cancelled.");
        _;
    }

    modifier validBid{
        require(msg.sender != address(0), "Invalid address.");
        require(msg.value > floorPrice, "Error: Your bid is lower than the floor price.");
        require(msg.value > bids[highestBidder], "Error: Your bid is lower than the highest bid.");
        _;
    }

    function startAuction(uint _duration) onlyManager isPending notCancelled public{
        startTime = block.timestamp;
        endTime = block.timestamp + _duration;
    }

    function endAuction() onlyManager isActive notCancelled public{
        endTime = block.timestamp;
    }

    function cancelAuction() onlyManager notCancelled payable public{
        cancelled = true;
        for(uint i = 0; i < participants.length; i++){
            if(bids[participants[i]] != 0){
                payable(participants[i]).transfer(bids[participants[i]]);
            }            
        }
    }

    function finaliseAuction() onlyManager notCancelled isEnded payable public{
        // if highest bid is greater than increment value plus second highest bid, return extra amount over increment value of second highest bid
        if(bids[highestBidder] > bids[secondHighestBidder] + increment){
            uint diff = bids[highestBidder] - bids[secondHighestBidder] - increment;
            uint temp = bids[secondHighestBidder] + increment;
            bids[highestBidder] = 0;
            // return extra amount over secondHighestBid plus increment
            payable(highestBidder).transfer(diff);
            // transfer winning bid to manager
            payable(manager).transfer(temp);
            winner = highestBidder;          
        } else { // if highest bid is smaller than increment value plus second highest bid, simply transfer highestbid to manager and change bids mapping appropriately
            uint temp = bids[highestBidder];
            bids[highestBidder] = 0;
            payable(manager).transfer(temp);
            winner = highestBidder;
        }
    }

    function placeBid() payable isActive notCancelled validBid external{
        participants.push(payable(msg.sender));

        // return amount of previous bid if it was placed
        if(bids[msg.sender] != 0){
            payable(msg.sender).transfer(bids[msg.sender]);
            bids[msg.sender] = msg.value;
        } else { // simply place msg.value in the bids mapping for the bidder
            bids[msg.sender] = msg.value;
        }

        // swap highestBidder and secondHighestBidder if needed
        if(msg.value > bids[highestBidder]){
            secondHighestBidder = highestBidder;
            highestBidder = payable(msg.sender);
        }
    }

    function withdrawBid() payable isEnded notCancelled external{
        // return amount of bid if it was placed
        if(bids[msg.sender] != 0){
            payable(msg.sender).transfer(bids[msg.sender]);
            bids[msg.sender] = 0;
        }
    }

    function getWinner() public view returns(address){
        return winner;
    }
    
}