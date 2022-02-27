# Areesha Kashif - 19678
# Wallet Address + RIPEMD-160

import hashlib
import base64
import base58
from bitcoin import*

def hash():
    x = hashlib.new('ripemd160')
    x.update(b"Areesha Kashif")                                 # passing name to hash using ripemd-160
    print("\n\nName Hash - Hex Format - RIPEMD-160 : ")
    print(x.hexdigest())                                        # hec format

def walletAddress():
    encodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwC3VhNVhrHVSs5K5kovt7J2kqnuD5azZ+EhdPR0QJ7vmREAlXLFWP/P9H8VVqGhbcsHzLClDGWysUrEDPc83jLLgz+QNzU8NMvImas8ivGnkYb930jWtjmn9vcawS+L2zlzGczVSxsQqTlYW2nq/jNdCO1cJGWY8Yg17tFml7HIKGyRgLV9UGQBk6ccUV3xepIUAoWaoyKi1Zo4l+ChgdnldRp6hPYuYPA+ia1naIZkarB77UeuYEdWH3eAFlzesR1k51K+3yqrFfghIo9Wv5ahhbDhdbI5Qu9BZH1ZrTT8yG2e/P6uKpsxTYWlks0fqieoNw/V6MkhbBJbRnZxTbwIDAQAB"
    decodedPublicKey = base64.b64decode(encodedPublicKey)

    hashResult1 = hashlib.new('sha256')
    hashResult1.update(decodedPublicKey)

    hashResult2 = hashlib.new('ripemd160')                                           # TypeError: object supporting the buffer API required
    hashResult2.update(hashResult1.hexdigest().encode('utf-8'))

    hashVersionByte = '00' + hashResult2.hexdigest()

    hashResult3 = hashlib.new('sha256')
    hashResult3.update(hashVersionByte.encode('utf-8'))
    hashResult4 = hashlib.new('sha256')
    hashResult4.update(hashResult3.hexdigest().encode('utf-8'))
    checksum = hashResult4.hexdigest()[0:8]

    address = base58.b58encode(hashVersionByte+checksum)
    print("\n\nBitcoin Wallet Address: ")
    print(address)

#def wa():
#    encodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwC3VhNVhrHVSs5K5kovt7J2kqnuD5azZ+EhdPR0QJ7vmREAlXLFWP/P9H8VVqGhbcsHzLClDGWysUrEDPc83jLLgz+QNzU8NMvImas8ivGnkYb930jWtjmn9vcawS+L2zlzGczVSxsQqTlYW2nq/jNdCO1cJGWY8Yg17tFml7HIKGyRgLV9UGQBk6ccUV3xepIUAoWaoyKi1Zo4l+ChgdnldRp6hPYuYPA+ia1naIZkarB77UeuYEdWH3eAFlzesR1k51K+3yqrFfghIo9Wv5ahhbDhdbI5Qu9BZH1ZrTT8yG2e/P6uKpsxTYWlks0fqieoNw/V6MkhbBJbRnZxTbwIDAQAB"
#    decodedPublicKey = base64.b64decode(encodedPublicKey)
#    addr = pubtoaddr(decodedPublicKey)
#    print(addr)


hash()
walletAddress()