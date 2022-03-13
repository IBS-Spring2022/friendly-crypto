pragma solidity >= 0.6.0 < 0.9.0;
contract SimpleStorage{
    
    Student [] public student;
    
    struct Student{
        string name;
        uint erp;
        uint grade1;
        uint grade2;
        uint grade3;
    }
   

    function addStudent(uint  erp, string memory name, uint grade1,uint grade2,uint grade3) public {
        student.push(Student(name,erp,grade1,grade2,grade3));
       

    }
    
    
    function retieve(uint erp) public view returns (string memory){
        for(uint i=0;i<student.length;i++){
            if(student[i].erp==erp){
                string memory x= string(abi.encodePacked(student[i].name," Grade 1: ",uint2str(student[i].grade1)," Grade 2: ",uint2str(student[i].grade2)," Grade 3: ",uint2str(student[i].grade3)," Grade Average: ",uint2str(GradeAvg(student[i].grade1,student[i].grade2,student[i].grade3))));
                return x;

            }else
            return "Record not found";
        }
    }
function GradeAvg( uint grade1,uint grade2,uint grade3) public view returns (uint) {
       uint x=(grade2+grade3+grade1)/3;
       return x;

    }
function uint2str(uint _i) internal pure returns (string memory _uintAsString) {
        if (_i == 0) {
            return "0";
        }
        uint j = _i;
        uint len;
        while (j != 0) {
            len++;
            j /= 10;
        }
        bytes memory bstr = new bytes(len);
        uint k = len;
        while (_i != 0) {
            k = k-1;
            uint8 temp = (48 + uint8(_i - _i / 10 * 10));
            bytes1 b1 = bytes1(temp);
            bstr[k] = b1;
            _i /= 10;
        }
        return string(bstr);
    }
}