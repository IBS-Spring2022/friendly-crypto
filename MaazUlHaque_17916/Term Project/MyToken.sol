//SPDX-License-Identifier: GPL-3.0
pragma solidity >=0.5.0 <0.9.0;
import "https://github.com/OpenZeppelin/openzeppelin-contracts/blob/master/contracts/token/ERC20/IERC20.sol";

contract MyToken is IERC20{

    mapping(address => uint256) private balances;
    mapping(address => mapping(address => uint256)) private allowances;
    
    uint256 private tokenSupply;
    string private tokenName;
    string private tokenSymbol;

    constructor (string memory name, string memory symbol, uint256 supply){
        tokenName = name;
        tokenSymbol = symbol;
        tokenSupply = supply;
        balances[msg.sender] = tokenSupply;
    }

    function balanceOf(address account) external override view returns (uint256) {
		return balances[account];
	}

	function allowance(address owner, address spender) external override view returns (uint256) {
		return allowances[owner][spender];
	}

    function totalSupply() external override view returns (uint256) {
		return tokenSupply;
	}

    function transfer(address recipient, uint256 amount) external override returns (bool) {
        // check sender is valid
        require(msg.sender != address(0), "Invalid sender address");
        // check recipient is valid
        require(recipient != address(0), "Invalid sender address");
        // sender balance is greater than or equal to amount being sent
        require(balances[msg.sender] >= amount, "Error: Sender does not have enough balance.");

        emit Transfer(msg.sender, recipient, amount);
        balances[msg.sender] = balances[msg.sender] - amount;
        balances[recipient] = balances[recipient] + amount;
		return true;
	}

    function approve(address spender, uint256 amount) external override returns (bool){
        allowances[msg.sender][spender] = amount;
		return true;
    }

    function transferFrom( address from, address to, uint256 amount) external override returns (bool){
        // check sender is valid
        require(from != address(0), "Error: Invalid sender address");
        // check recipient is valid
        require(to != address(0), "Error: Invalid recipient address");
        // check for allowed value smaller than or equal to amount
        require(allowances[from][msg.sender] >= amount, "Error: Approved value is smaller than amount");

        emit Transfer(from, to, amount);
        balances[from] = balances[from] - amount;
        balances[to] = balances[to] + amount;

        return true;
    }

}